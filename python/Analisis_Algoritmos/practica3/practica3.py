import array
import numpy
import os # Libreria para hacer llamadas al sistema
import time # Libreria para hacer pausas en el sistema

# *************************** Funciones ************************************

# Esta funcion imprime la matriz
def imprimirImagen(matriz):
    i,j = 0, 0
	
    for i in range(len(matriz)):
        print(f"{matriz[i]} ",end = "  ")
        if (j == 7):
            print("\n")
            j = -1
        j = j + 1
    #time.sleep(.1)
    return 0


# Esta funcion se encarga de realizar los movimientos
def movJ(matriz,x,carry,ciclos,casoBase):
	if (ciclos == casoBase):  # Caso Base
		return 0 

	else:	# Caso Recursivo para Movimientos 
		if(carry[2] == 0): # Desplazamiento derecha
			carry[0] = carry [1] 
			carry[1] = matriz[x+1] 
			matriz[x+1] = carry[0]
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			if ((x == 6) or (x == 13) or (x == 20) or (x == 27)):
				carry[2] = carry[2]+1
			movJ(matriz,x+1,carry,ciclos,casoBase)

		if (carry[2] == 1): # Desplazamiento Abajo
			carry[0] = carry [1]
			carry[1] = matriz[x+8]
			matriz[x+8] = carry[0]
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			if ((x == 55) or (x == 46) or (x == 37) or (x == 28)):
				carry[2] = carry[2]+1 
			movJ(matriz,x+8,carry,ciclos,casoBase)

		if (carry[2] == 2): # Desplazamiento Izquierda
			carry[0] = carry [1]
			carry[1] = matriz[x-1]
			matriz[x-1] = carry[0] 
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			if ((x == 57) or (x == 50) or (x == 43) or (x == 36)):
				carry[2] = carry[2]+1
			movJ(matriz,x-1,carry,ciclos,casoBase)

		if (carry[2] == 3): # Desplazamiento Derecha
			carry[0] = carry [1]
			carry[1] = matriz[x-8]
			matriz[x-8] = carry[0]
			imprimirImagen(matriz)
			os.system("cls") # Limpia pantalla
			if ((x == 8) or (x == 17) or (x == 26) or (x == 35)):
				carry[2] = carry[2]+1
			movJ(matriz,x-8,carry,ciclos,casoBase)

		if (carry[2] == 4):   # Despues de haber rotado los valores del cuadrado entero,  
			ciclos = ciclos+1 # se procede a repetir el procedimiento hasta que la rotacion sea  
			carry[2] = 0      # de 90°
			carry[1] = matriz[0]
			x = carry[3]
			movJ(matriz,x,carry,ciclos,casoBase)
	return 0

# ********************** Desarrollo Principal del programa **************************

carry = [0,0, #(0,1) Auxiliares
		 0,   #(2) Indica la direccion del desplazamiento
		 0]	  #(3) Indica posicion inicial en cada ciclo L1(0), L2(9), L3(18) Y L4(27)
ciclos = 0 	 # Indica el numero de ciclos realizados para completar la rotacion 
casoBase = 7 # Define el caso base. L1(7), L2(5), L3(3) Y L4(1)
x = 0
matriz =  ["-","-","-","-","-","-","X","X", # Declaracion de matriz 8x8
		   "-","-","-","-","-","X","X","-", 
		   "X","-","-","-","X","X","-","-", 		
		   "X","-","-","X","X","-","-","-", 
		   "X","-","X","X","-","-","-","-", 
		   "X","X","X","-","-","-","-","-",
		   "X","X","-","-","-","-","-","-",
		   "X","X","X","X","X","X","-","-"] 

# Aqui inicia el programa
os.system("cls") # Limpia pantalla 
print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n")
print("------- Practica 3 - Rotacion de Matriz -------\n\n")
print("INSTRUCCIONES: Este programa rotara una matriz de forma automatica\n\n")
imprimirImagen(matriz)
input("\nPresione ENTER para comenzar a rotar la imagen... ")
os.system("cls") # Limpia pantalla

carry[1] = matriz[0]
movJ(matriz,x,carry,ciclos,casoBase) #Rotacion para L1 (Cuadrado EXTERNO)


carry[3] = carry[3]+9
x=carry[3]
carry[0] = 0
carry[1] = matriz[9]
ciclos=0
casoBase = casoBase-2
movJ(matriz,x,carry,ciclos,casoBase) #Rotacion para L2 (Cuadrado INTERNO)


carry[3] = carry[3]+9
x=carry[3]
carry[0] = 0
carry[1] = matriz[18]
ciclos=0
casoBase = casoBase-2
movJ(matriz,x,carry,ciclos,casoBase) #Rotacion para L3 (Cuadrado INTERNO 2)


carry[3] = carry[3]+9
x=carry[3]
carry[0] = 0
carry[1] = matriz[27]
ciclos=0
casoBase = casoBase-2
movJ(matriz,x,carry,ciclos,casoBase) #Rotacion para L4 (Cuadrado Central)

imprimirImagen(matriz)  
print("!!! Matriz ha sido rotada !!!\n")
print("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n")