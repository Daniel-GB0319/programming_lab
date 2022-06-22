import ply.lex as lex
import os
import re
import sys

#Funcion para limpiar la pantalla de la terminal
def borrarPantalla():  
	if os.name == "posix":
		os.system ("clear")
	elif os.name == "ce" or os.name == "nt" or os.name == "dos":
		os.system ("cls")

##### ESTRUCTURA DEL ANALIZADOR LEXICO #####
		
# Declaracion de tokens
tokens = ['ID','NUMBER','PLUS','MINUS','TIMES','DIVIDE','ASSIGN','NE','LT','LTE','GT','GTE','LPARENT','RPARENT','COMMA','SEMMICOLOM','DOT','UPDATE','COMMENT']
		
# Declaracion de palabras reservadas
reservadas = ['BEGIN','END','IF','THEN','WHILE','DO','CALL','CONST','INT','PROCEDURE','OUT','IN','ELSE','ODD','VAR']
	
tokens = tokens + reservadas
	
# Estructura de los Tokens por medio de expresiones regulares
t_PLUS = r'\+'
t_MINUS = r'\-'
t_TIMES = r'\*'
t_DIVIDE = r'/'
t_ASSIGN = r'='
t_NE = r'<>'
t_LT = r'<'
t_LTE = r'<='
t_GT = r'>'
t_GTE = r'>='
t_LPARENT = r'\('
t_RPARENT = r'\)'
t_COMMA = r'\,'
t_SEMMICOLOM = r'\;'
t_DOT = r'\.'
t_UPDATE = r':='

# Caracteres ignorados 
t_ignore = r'[\t]*[ ]*[	]*'

#Lectura de ID
def t_ID(t):
	r'[a-zA-Z_][a-zA-Z0-9_]*'
	if t.value.upper() in reservadas:
		t.value = t.value.upper()
		t.type = t.value
	return t

def t_NUMBER(t):
	r'\d+'
	t.value = int(t.value)
	return t

# Lectura de comentarios
def t_COMMENT (t):
	r'\#.*'
	pass
	
# Lectura de espacios
def t_newline(t):
    r'\n+'
    t.lexer.lineno += len(t.value)
    
# Lectura de caracteres que no son validos
def t_error(t):
    print("Caracter invalido '%s'" % t.value[0])
    t.lexer.skip(1)
    
# Se genera el Analizador Lexico
lexer = lex.lex()

##### PROGRAMA PRINCIPAL #####
borrarPantalla()
print("***** Proyecto - Compilador de un lenguaje de programacion (PL/0) *****\n")
print("INSTRUCCIONES: Este programa realiza el analisis lexico, sintactico y semantico de un codigo escrito para PL/0.\n")

file = open("prueba.pl0","r") # Realiza lectura del archivo con el codigo

buff = file.read() # Se lee todo el contenido del archivo seleccionado
lexer.input(buff) # Se ingresa la cadena completa al analizador lexico

file.close()

# Ciclo para Tokenizar cada elemento de la cadena y guardarlo en un archivo
arch = open("tokens.txt","w")

while True:
	tok = lexer.token()
	if (not tok):
		break      # No more input
	arch.write(str(tok)+"\n")

arch.close()

print("** Tokens generados por el Analizador Lexico guardados en archivo 'tokens.txt'")