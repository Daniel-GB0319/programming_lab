import os # Libreria para hacer llamadas al sistema

# **************************************************************************
# *************************** Funciones ************************************
# **************************************************************************

# Esta funcion imprime el laberinto y el recorrido
def imprimirLaberinto(matriz):
    i,j = 0,1
    
    os.system("clear") # Limpia pantalla    
    print("Estado Actual \n\n")
    
    for i in range(matriz):
		print(f" { matriz[i] }")
		
		if i is ((10*j)-1):
			print("\n")
			j =+ 1 
		
    print("\nSIMBOLOGIA:\nx = Pared\n* = Recorrido del Programa\nI = Inicio\nF = Salida \n\n")
    
    return 0


# Esta funcion se encarga de realizar los movimientos
def movimientos(matriz,pos):
    # Caso Base
    if matriz[pos] == "F": 
		return 0 # Laberinto Resuelto 
	
	# Caso Recursivo para Movimientos 
	else:
		if(matriz[pos] is not "x") and (matriz[pos] is not "*"):
			matriz[pos] = "*" # Se marca casilla como Recorrida por el Programa
			
			if(movimientos(matriz,pos-1)) or (movimientos(matriz,pos+1)) # Movimientos hacia derecha o izquierda 
			or (movimientos(matriz,pos+10)) or (movimientos(matriz,pos-10)): # Movimientos hacia arriba o abajo
				return 0
				
			else:
				matriz[pos] = "" # Se descarta el camino previamente recorrido
    
    return 1 # Laberinto Sin Solucion


# ***********************************************************************************
# ********************** Desarrollo Principal del programa **************************
# ***********************************************************************************
solucion = 0
pos = 10 # Posicion Inicial
recorrido = [] # Lista de Pasos Realizados (Up , Down , Right , Left)
matriz = ["x","x","x","x","x","x","x","x","x","x",
		  "I",   ,   ,   ,   ,"x",   ,"x","x","x",
		  "x","x",   ,"x",   ,"x",   ,"x",   ,"x",
		  "x",   ,   ,"x",   ,"x",   ,"x",   ,"x",
		  "x","x","x","x",   ,"x",   ,   ,   ,"x",
		  "x",   ,   ,   ,   ,"x",   ,"x","x","x",
		  "x",   ,"x","x","x","x",   ,   ,   ,"x",
		  "x",   ,   ,   ,"x",   ,   ,"x",   ,"x",
		  "x",   ,"x",   ,   ,   ,"x","x",   ,"F",
		  "x","x","x","x","x","x","x","x","x","x",] # Declaracion de matriz 10x10

# Aqui inicia el programa
os.system("clear") # Limpia pantalla 
print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n")
print("------- Practica 2 - Laberinto -------\n\n")
print("INSTRUCCIONES: Este programa resolvera un laberinto predeterminado de forma automatica\n\n")
input("Presione ENTER para comenzar a resolver el laberinto... ")
    
solucion = movimientos(matriz,pos):
    
if (solucion==0):    
	print("!!! El Laberinto Ha Sido Resuelto !!!\n\n")
	
else:
	print("!!! El Laberinto No Tiene Solucion !!!\n\n")
print("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n")
