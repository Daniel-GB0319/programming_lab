import ply.yacc as yacc
import os
import re
from sys import stdin
from ALexico import tokens
from ASemantico import *


##### ESTRUCTURA DEL ANALIZADOR SINTACTICO #####
fichero = open("parsing.txt","w")

# Definicion de la precedencia
precedence = ( ('right','ID','CALL','BEGIN','IF','WHILE'), ('right','PROCEDURE'), ('right','VAR'), ('right','ASSIGN'), ('right','UPDATE'), ('left','NE'), ('left','LT','LTE','GT','GTE')
, ('left','PLUS','MINUS'), ('left','TIMES','DIVIDE'), ('right','ODD'), ('left','LPARENT','RPARENT')
)

# Definicion de las gramaticas
def p_program(p):
	'''program : block'''
	fichero.write('program\n')
	p[0] = program(p[1],"program")

def p_block(p):
	'''block : constDecl varDecl procDecl statement'''
	p[0] = block(p[1],p[2],p[3],p[4],"block")
	fichero.write("block\n")

def p_constDecl(p):
	'''constDecl : CONST constAssignmentList SEMMICOLOM'''
	p[0] = constDecl(p[2],'constDecl')
	fichero.write("constDecl\n")

def p_constDeclEmpty(p):
	'''constDecl : empty'''
	p[0] = Null()
	fichero.write("nulo\n")

def p_constAssignmentList1(p):
	'''constAssignmentList : ID ASSIGN NUMBER'''
	p[0] = constAssignmentList1(Id(p[1]),Assign(p[2]),Number(p[3]),"constAssignmentList1")
	fichero.write("constAssignmentList 1\n")

def p_constAssignmentList2(p):
	'''constAssignmentList : constAssignmentList COMMA ID ASSIGN NUMBER'''
	p[0] = constAssignmentList2(p[1],Id(p[3]),Assign(p[4]),Number(p[5]),"constAssignmentList2")
	fichero.write("constAssignmentList 2\n")

def p_varDecl1(p):
	'''varDecl : VAR identList SEMMICOLOM'''
	p[0] = varDecl1(p[2],"VarDecl1")
	fichero.write("varDecl1\n")

def p_varDeclEmpty(p):
	'''varDecl : empty'''
	p[0] = Null()
	fichero.write("nulo\n")

def p_identList1(p):
	'''identList : ID'''
	p[0] = identList1(Id(p[1]),"identList1")
	fichero.write("identList 1\n")

def p_identList2(p):
	'''identList : identList COMMA ID'''
	p[0] = identList2(p[1],Id(p[3]),"identList2")
	fichero.write("identList 2\n")

def p_procDecl1(p):
	'''procDecl : procDecl PROCEDURE ID SEMMICOLOM block SEMMICOLOM'''
	p[0] = procDecl1(p[1],Id(p[3]),p[5],"procDecl1")
	fichero.write("procDecl 1\n")

def p_procDeclEmpty(p):
	'''procDecl : empty'''
	p[0] = Null()
	fichero.write("nulo\n")

def p_statement1(p):
	'''statement : ID UPDATE expression'''
	p[0] = statement1(Id(p[1]),Update(p[2]),p[3],"statement1")
	fichero.write("statement 1\n")

def p_statement2(p):
	'''statement : CALL ID'''
	p[0] = statement2(Id(p[2]),"statement2")
	fichero.write("statement 2\n")

def p_statement3(p):
	'''statement : BEGIN statementList END'''
	p[0] = statement3(p[2],"statement3")
	fichero.write("statement 3\n")

def p_statement4(p):
	'''statement : IF condition THEN statement'''
	p[0] = statement4(p[2],p[4],"statement4")
	fichero.write("statement 4\n")

def p_statement5(p):
	'''statement : WHILE condition DO statement'''
	p[0] = statement5(p[2],p[4],"statement5")
	fichero.write("statement 5\n")

def p_statementEmpty(p):
	'''statement : empty'''
	p[0] = Null()
	fichero.write("nulo\n")

def p_statementList1(p):
	'''statementList : statement'''
	p[0] = statementList1(p[1],"statementList1")
	fichero.write("statementList 1\n")

def p_statementList2(p):
	'''statementList : statementList SEMMICOLOM statement'''
	p[0] = statementList2(p[1],p[3],"statementList2")
	fichero.write("statementList 2\n")

def p_condition1(p):
	'''condition : ODD expression'''
	p[0] = condition1(p[2],"condition1")
	fichero.write("condition 1\n")

def p_condition2(p):
	'''condition : expression relation expression'''
	p[0] = condition2(p[1],p[2],p[3],"condition2")
	fichero.write("condition 2\n")

def p_relation1(p):
	'''relation : ASSIGN'''
	p[0] = relation1(Assign(p[1]),"relation1")
	fichero.write("relation 1\n")

def p_relation2(p):
	'''relation : NE'''
	p[0] = relation2(NE(p[1]),"relation2")
	fichero.write("relation 2\n")

def p_relation3(p):
	'''relation : LT'''
	p[0] = relation3(LT(p[1]),"relation3")
	fichero.write("relation 3\n")

def p_relation4(p):
	'''relation : GT'''
	p[0] = relation4(GT(p[1]),"relation4")
	fichero.write("relation 4\n")

def p_relation5(p):
	'''relation : LTE'''
	p[0] = relation5(LTE(p[1]),"relation5")
	fichero.write("relation 5\n")

def p_relation6(p):
	'''relation : GTE'''
	p[0] = relation6(GTE(p[1]),"relation6")
	fichero.write("relation 6\n")

def p_expression1(p):
	'''expression : term'''
	p[0] = expression1(p[1],"expression1")
	fichero.write("expression 1\n")

def p_expression2(p):
	'''expression : addingOperator term'''
	p[0] = expression2(p[1],p[2],"expression2")
	fichero.write("expression 2\n")

def p_expression3(p):
	'''expression : expression addingOperator term'''
	p[0] = expression3(p[1],p[2],p[3],"expression3")
	fichero.write("expression 3\n")

def p_term1(p):
	'''term : factor'''
	p[0] = term1(p[1],"term1")
	fichero.write("term 1\n")

def p_term2(p):
	'''term : term multiplyingOperator factor'''
	p[0] = term2(p[1],p[2],p[3],"term2")
	fichero.write("term 2\n")

def p_multiplyingOperator1(p):
	'''multiplyingOperator : TIMES'''
	p[0] = multiplyingOperator1(Times(p[1]),"multiplyingOperator")
	fichero.write("multiplyingOperator 1\n")

def p_multiplyingOperator2(p):
	'''multiplyingOperator : DIVIDE'''
	p[0] = multiplyingOperator2(Divide(p[1]),"divisiongOperator")
	fichero.write("multiplyingOperator 2\n")

def p_addingOperator1(p):
	'''addingOperator : PLUS'''
	p[0] = addingOperator1(Plus(p[1]),"addingOperator")
	fichero.write("addingOperator 1\n")

def p_addingOperator2(p):
	'''addingOperator : MINUS'''
	p[0] = addingOperator2(Minus(p[1]),"subtractionOperator")
	fichero.write("addingOperator 2\n")

def p_factor1(p):
	'''factor : ID'''
	p[0] = factor1(Id(p[1]),"factor1")
	fichero.write("factor 1\n")

def p_factor2(p):
	'''factor : NUMBER'''
	p[0] = factor2(Number(p[1]),"factor2")
	fichero.write("factor 2\n")

def p_factor3(p):
	'''factor : LPARENT expression RPARENT'''
	p[0] = factor3(p[2],"factor3")
	fichero.write("factor 3\n")

def p_empty(p):
	'''empty : '''
	pass

def p_error(p):
	fichero.write("\nError de sintaxis " + str(p) + "\n")
	fichero.write("Error de linea " + str(p.lineno) + "\n\n")

# Se inicializa el analizador sintactico
arch = open("prueba.pl0","r")
cadena = arch.read()
parser = yacc.yacc()
result = parser.parse(cadena)
arch.close()

def generarCodigo(result):
	graphFile = open('grafo.vz','w')
	graphFile.write(result.generarCodigo())
	graphFile.close()


##### PROGRAMA PRINCIPAL #####
fichero.write(str(result)) 
fichero.close()

generarCodigo(result)

print("** Resultados del Analisis Sintactico guardados en archivo 'parsing.txt'")
print("** Grafo generado del Analisis semantico guardado en archivo 'grafo.vz'\n")
print("!!! Gracias por utilizar el programa !!!")
