import os
from time import sleep
from xml.etree.ElementTree import tostringlist
from tabulate import tabulate

#Funcion para limpiar la pantalla de la terminal
def borrarPantalla(): 
	sleep(.5)

	if os.name == "posix":
		os.system ("clear")
	elif os.name == "ce" or os.name == "nt" or os.name == "dos":
		os.system ("cls")


##### ESTRUCTURA DEL ANALIZADOR SINTACTICO #####
def analizador(entrada):
	salida = ""
	pila = ["$", "E"] # Sirve para almacenar las terminales y no terminales
	coincidencia = ["" , ""] # Sirve para comparar los topes de las pilas
	#E = ["Ec", "T"]
	#Ec = ["Ec", "T", "+"]
	#T = ["Tc", "F"]
	#Tc = ["Tc", "F", "*"]
	#F = [")" , "E" , "("]

	# Se inicializa la tabla de amortizacion
	tabla = [["Coincidencia","Pila","Cadena Entrada","Salida Accion"],["-",pila,entrada,"-"]]

	while(True):
		coincidencia[0] = pila[-1]
		coincidencia[1] = entrada[0]

		if( (coincidencia[0] == "$") and (coincidencia[1] == "$") ):
			print("!!! La cadena ha sido aceptada !!!\n\n")	
			break
		else:
			if(coincidencia[0] == coincidencia[1]):
				salida = "Reconoce " + coincidencia[1]
				entrada.pop(0)
				pila.pop()

			if(coincidencia[0] == "E" and ( (coincidencia[1] == "id") or (coincidencia[1] == "(") ) ):
				pila.pop()
				pila.extend(["Ec", "T"])
				salida = "E -> TE'"

			if(coincidencia[0] == "Ec"):
				if(coincidencia[1] == "+"):
					pila.pop()
					pila.extend(["Ec", "T", "+"]) 
					salida = "E' -> +TE'"
				else:
					pila.pop()
					pila.append("epsilon")
					salida = "E' -> epsilon"

			if(coincidencia[0] == "T" and ( (coincidencia[1] == "id") or (coincidencia[1] == "(") )):
				pila.pop()
				pila.extend(["Tc", "F"])
				salida = "T -> FT'"

			if(coincidencia[0] == "Tc"):
				if(coincidencia[1] == "*"):
					pila.pop()
					pila.extend(["Tc", "F", "*"]) 
					salida = "T' -> *FT'"
				else:
					pila.pop()
					pila.append("epsilon")
					salida = "T' -> epsilon"

			if(coincidencia[0] == "F"):
				if(coincidencia[1] == "id"):
					pila.pop()
					pila.append("id") 
					salida = "F -> id"
				else:
					pila.pop()
					pila.extend([")" , "E" , "("])
					salida = "F -> (E)"

		
			# Se ingresa la fila con los datos calculados en cada iteracion
			tabla.append([coincidencia,pila,entrada ,salida])  
			# Se imprime la tabla
			print( tabulate(tabla, headers='firstrow', tablefmt='psql') )
			borrarPantalla()


##### PROGRAMA PRINCIPAL #####
while(True):
	entrada = list()
	usuario = ""

	borrarPantalla()
	print("***** Practica 3 - Analizador Sintactico LL(1) *****\n")
	print("INSTRUCCIONES: Este programa evaluara una cadena ingresada por el usuario por medio de un analizador sintactico LL(1).\n")

	usuario = input("Escriba la cadena para analizar: ")
	usuario = usuario + "$"
	usuario.split
	
	entrada.extend(usuario)
	

	analizador(entrada)

	opcion = input("\nDesea ingresar otra cadena al analizador sintactico? (S / N): ")
	if (opcion == "N") or (opcion == "n"):
		print("\n!!! Gracias por utilizar el programa !!!\n")
	break
