import os # Libreria para llamadas a sistema
import random # Libreria para el manejo de numeros aleatorios


# Esta funcion procesa la cadena con la ayuda del automata.
def automata(cadena,alfabeto,eInicial,eFinal):
	q = eInicial # Asigna a q el valor del estado inicial
	state = 0 
	 
	# Bucle para analizar cada uno de los caracteres de la cadena 
	for i in range(len(cadena)):
		if cadena[i] not in alfabeto:
			print("\n!!! La cadena contiene caracteres que no pertenecen al alfabeto !!!")
			state = 1
			break
	
		# Se realizan las transiciones
		if (cadena[i] == 0) and (q == 0):
			q = 0
		elif (cadena[i] == 1) and (q == 0):
			q = 1
		elif (cadena[i] == 0) and (q == 1):
			q = 0
		else:
			q = 1
		
	if q == eFinal and state == 0: # Si q al final del analisis es igual al estado de aceptacion, la cadena es aceptada
		print("\n!!! La cadena ha sido aceptada, es un numero par en binario !!!")
	else:
		print("\n!!! La cadena no ha sido aceptada, ya que no es un numero par en binario !!!")
		
	return 0
	


# ---- Programa Principal ----
file = open("AFD.txt","r")
buff = file.readline() # Lee los caracteres del alfabeto (0 , 1)
alfabeto = buff.split()

buff = file.readline()
estados = buff.split() # Lee los estados existentes 

eInicial = int(file.readline()) # Lee cual es el estado inicial
eFinal = int(file.readline()) # Lee cual es el estado final
file.close()

# Se convierten las listas de alfabeto y estados de tipo str a int para practicidad
for i in range(len(alfabeto)):
	alfabeto[i] = int(alfabeto[i])

for i in range(len(estados)):
	estados[i] = int(estados[i])

# Bucle para el funcionamiento del programa hasta que usuario decida parar
while(True):

	os.system("clear")
	print("***** Practica 1 - Programacion de un AFD *****")
	print("INSTRUCCIONES: Este programa indicara si una cadena es la representacion binaria de un numero par por medio de un AFD.")
	
	print("\n\nInformacion Obtenida del AFD")
	print(f"Alfabeto = {alfabeto}")
	print(f"Estados q = {estados}")
	print(f"Estado Inicial q = {eInicial}")
	print(f"Estado Final o de Aceptacion q = {eFinal}")
	
	while(True):
		print("\nDesea ingresar una cadena o generarla automaticamente?")
		print("A = Generar cadena automaticamente")
		print("M = Ingresar cadena manualmente")
		opcion = input("Ingrese su respuesta: ")
		if (opcion == 'A') or (opcion == 'M'):
			break
	
	if (opcion == "A"): # Se genera cadena aleatoriamente
		cadena = bin(random.randint(512,1024)) # Genera un numero binario aleatorio de 10 digitos
		cadena = cadena[2:len(cadena)]
		print(f"\nLa cadena generada es {cadena}")

	else:# Se ingresa cadena manualmente
		cadena = input("\nIngrese el numero binario que desea probar: " )
	
	cadena = list(cadena)
	
	for i in range(len(cadena)):
		cadena[i] = int(cadena[i])
		
	automata(cadena,alfabeto,eInicial,eFinal)
	
	menu = input("\nDesea analizar otra cadena? (S / N): ")
	if (menu == "N") or (menu == "n"):
		print("\n!!! Gracias por utilizar el programa !!!\n")
		break



