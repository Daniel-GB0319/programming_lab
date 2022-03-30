import array
import numpy
import os # Libreria para hacer llamadas al sistema
import time # Libreria para hacer pausas en el sistema

# **************************************************************************
# *************************** Funciones ************************************
# **************************************************************************

# Esta funcion imprime el laberinto y el recorrido
def imprimirImagen(matriz):
    i,j = 0, 0
    for i in range(len(matriz)):
        print(f"{matriz[i]} ",end = "")
        if (j == 7):
            print("\n")
            j = -1

        j = j + 1

    time.sleep(.15)
    return 0

# Esta funcion se encarga de realizar los movimientos
def movJ(matriz,x,carry):
	if (carry[8] == carry[9]):  # Caso Base
		return 0 

	else:	# Caso Recursivo para Movimientos 
		if(carry[2] == 0): # Desplazamiento derecha
			carry[0] = carry [1]
			carry[1] = matriz[x+1]
			matriz[x+1] = carry[0]
			imprimirImagen(matriz)
			os.system("clear") # Limpia pantalla
			if ((x == 6) or (x == 13) or (x == 20) or (x == 27)):
				carry[2] = carry[2]+1
			movJ(matriz,x+1,carry)

		if (carry[2] == 1): # Desplazamiento Abajo
			carry[0] = carry [1]
			carry[1] = matriz[x+8]
			matriz[x+8] = carry[0]
			imprimirImagen(matriz)
			os.system("clear") # Limpia pantalla
			if ((x == 55) or (x == 46) or (x == 37) or (x == 28)):
				carry[2] = carry[2]+1 
			movJ(matriz,x+8,carry)

		if (carry[2] == 2): # Desplazamiento Izquierda
			carry[0] = carry [1]
			carry[1] = matriz[x-1]
			matriz[x-1] = carry[0] 
			imprimirImagen(matriz)
			os.system("clear") # Limpia pantalla
			if ((x == 57) or (x == 50) or (x == 43) or (x == 36)):
				carry[2] = carry[2]+1
			movJ(matriz,x-1,carry)

		if (carry[2] == 3): # Desplazamiento Derecha
			carry[0] = carry [1]
			carry[1] = matriz[x-8]
			matriz[x-8] = carry[0]
			imprimirImagen(matriz)
			os.system("clear") # Limpia pantalla
			if ((x == 8) or (x == 17) or (x == 26) or (x == 35)):
				carry[2] = carry[2]+1
			movJ(matriz,x-8,carry)

		if (carry[2] == 4):
			carry[8] = carry[8]+1
			carry[2] = 0
			carry[1] = matriz[0]
			x = carry[3]
			movJ(matriz,x,carry)
	return 0


# ***********************************************************************************
# ********************** Desarrollo Principal del programa **************************
# ***********************************************************************************
carry = [0,0, #(0,1) Auxiliares
		 0,   #(2) Indica la direccion del desplazamiento
		 0]	  #(3) Indica posicion inicial en cada ciclo L1(0), L2(9), L3(18) Y L4(27)
ciclos = 0 #(8) Indica el numero de ciclos realizados para completar la rotacion 
base = 7 #(9) Define el caso base L1(7), L2(5), L3(3) Y L4(1)
x = 0
#matriz = [0,0,0,0,0,0,1,1, 
#		  0,0,0,0,0,1,1,0, 
#		  1,0,0,0,1,1,0,0, 		# Declaracion de matriz 8x8
#		  1,0,0,1,1,0,0,0, 
#		  1,0,1,1,0,0,0,0, 
#		  1,1,1,0,0,0,0,0,
#		  1,1,0,0,0,0,0,0,
#		  1,1,1,1,1,1,0,0] 

matriz = [0,1,2,3,4,5,6,7, 
		  27,0,0,0,0,1,0,8, 
		  26,0,0,0,1,1,0,9, 		# Declaracion de matriz 8x8
		  25,0,0,1,1,0,0,10, 
		  24,0,1,1,0,0,0,11,		#carry	 
		  23,1,1,0,0,0,0,12,
		  22,1,0,0,0,0,0,13,
		  21,20,19,18,17,16,15,14] 	  


# Aqui inicia el programa
os.system("clear") # Limpia pantalla 
print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n")
print("------- Practica 3 - Rotacion de Matriz -------\n\n")
print("INSTRUCCIONES: Este programa rotara una matriz de forma automatica\n\n")
imprimirImagen(matriz)
input("\nPresione ENTER para comenzar a rotar la imagen... ")
os.system("clear") # Limpia pantalla

carry[1] = matriz[0]
movJ(matriz,x,carry)

carry[3] = carry[3]+9
x=carry[3]
carry[1] = matriz[9]
carry[8]=0
carry[9] = carry[9]-2
movJ(matriz,x,carry)

carry[3] = carry[3]+9
x=carry[3]
carry[1] = matriz[18]
carry[8]=0
carry[9] = carry[9]-2
movJ(matriz,x,carry)

carry[3] = carry[3]+9
x=carry[3]
carry[1] = matriz[27]
carry[8]=0
carry[9] = carry[9]-2
movJ(matriz,x,carry)


imprimirImagen(matriz)  
print("!!! Matriz ha sido rotada !!!\n")
print("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n")
