import array
import numpy
import os # Libreria para hacer llamadas al sistema
import time # Libreria para hacer pausas en el sistema

# **************************************************************************
# *************************** Funciones ************************************
# **************************************************************************

# Esta funcion imprime el laberinto y el recorrido
def imprimirImagen(matriz):
	i = 0
	for i in range(7):
		print(f"{matriz[i]}")
	
	time.sleep(.3)
	return 0

# Esta funcion se encarga de realizar los movimientos
def movJ(matriz,x,y,J):
	# Caso Base
	if (J == 1):  
		return 0 

	# Caso Recursivo para Movimientos 
	else:
		if(y == 0) and (x<7):
			matriz[y,x+1] = int(matriz[y,x])
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			movJ(matriz,x+1,y,J)
		elif (x == 7) and (y<7):
			matriz[y+1 , x] = int(matriz[y , x])
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			movJ(matriz,x,y+1,J)
		elif (y == 7) and (x>0):
			matriz[y , x-1] = int(matriz[y , x])
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			movJ(matriz,x-1,y,J)
		elif (x==0) and (y>0):
			matriz[y-1,x] = int(matriz[y,x])
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			if (y == 1):
				J = 1
			movJ(matriz,x-1,y,J)
	return 0


# ***********************************************************************************
# ********************** Desarrollo Principal del programa **************************
# ***********************************************************************************
J,K,L,M,x,y = 0,0,0,0,0,0
matriz = [[0,0,0,0,0,0,1,1], # Y0
		  [0,0,0,0,0,1,1,0], # Y1
		  [1,0,0,0,1,1,0,0], # Y2		# Declaracion de matriz 8x8 donde se maneja matriz[y, x]
		  [1,0,0,1,1,0,0,0], # Y3
		  [1,0,1,1,0,0,0,0], # Y4
		  [1,1,1,0,0,0,0,0],
		  [1,1,0,0,0,0,0,0],
		  [1,1,1,1,1,1,0,0]] 
	   # X0 X1 X2 X3 X4

# Aqui inicia el programa
os.system("cls") # Limpia pantalla 
print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n")
print("------- Practica 3 - Rotacion de Matriz -------\n\n")
print("INSTRUCCIONES: Este programa rotara una matriz de forma automatica\n\n")
imprimirImagen(matriz)
input("\nPresione ENTER para comenzar a rotar la imagen... ")
os.system("cls") # Limpia pantalla

movJ(matriz,x,y,J)
x,y = 1,1

  
print("!!! Matriz ha sido rotada !!!\n")
print("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n")