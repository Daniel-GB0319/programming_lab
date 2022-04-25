import ply.lex as lex
import os

# Declaracion de tokens
tokens = (
	'ID', 'NUMERO', 
	'SUMA', 'RESTA')
	
# Estructura de los Tokens por medio de expresiones regulares
t_ID = r'[a-zA-Z]+'
t_NUMERO = r'[0-9]+'
t_SUMA = r'\+'
t_RESTA = r'-'

# Caracteres ignorados 
t_ignore = " \t"

# Lectura de espacios
def t_newline(t):
    r'\n+'
    t.lexer.lineno += t.value.count("\n")
    
# Lectura de caracteres que no son validos
def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)
    
# Se genera el Analizador Lexico
lexer = lex.lex()

##### PROGRAMA PRINCIPAL #####
while True:
	os.system("clear")
	print("***** Practica 2 - Analizador Lexico *****\n")
	print("INSTRUCCIONES: Este programa evaluara una cadena con un analizador lexico y mostrara los tokens generados para cada elemento.\n")
	
	data = input("Escriba la cadena para analizar con el evaluador de formulas: ")

	# Se ingresa la cadena al analizador lexico
	lexer.input(data)
	 
	# Ciclo para Tokenizar cada elemento de la cadena
	while True:
		tok = lexer.token()
		if (not tok):
			break      # No more input
		print(tok)
	
	opcion = input("\nDesea ingresar otra cadena al analizador lexico? (S / N): ")

	if (opcion is "N") or (opcion is "n"):
		print("\n!!! Gracias por utilizar el programa !!!\n")
		break
