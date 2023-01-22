import os # Libreria para hacer llamadas al sistema
import time # Libreria para hacer pausas en el sistema

# **************************************************************************
# *************************** Funciones ************************************
# **************************************************************************

# Esta funcion imprime el laberinto y el recorrido
def imprimirLaberinto(matriz):
	i = 0
	print(f"{matriz[0]} {matriz[1]} {matriz[2]} {matriz[3]} {matriz[4]} {matriz[5]} {matriz[6]} {matriz[7]} {matriz[8]} {matriz[9]}")
	print(f"{matriz[10]} {matriz[11]} {matriz[12]} {matriz[13]} {matriz[14]} {matriz[15]} {matriz[16]} {matriz[17]} {matriz[18]} {matriz[19]}")
	print(f"{matriz[20]} {matriz[21]} {matriz[22]} {matriz[23]} {matriz[24]} {matriz[25]} {matriz[26]} {matriz[27]} {matriz[28]} {matriz[29]}")
	print(f"{matriz[30]} {matriz[31]} {matriz[32]} {matriz[33]} {matriz[34]} {matriz[35]} {matriz[36]} {matriz[37]} {matriz[38]} {matriz[39]}")
	print(f"{matriz[40]} {matriz[41]} {matriz[42]} {matriz[43]} {matriz[44]} {matriz[45]} {matriz[46]} {matriz[47]} {matriz[48]} {matriz[49]}")
	print(f"{matriz[50]} {matriz[51]} {matriz[52]} {matriz[53]} {matriz[54]} {matriz[55]} {matriz[56]} {matriz[57]} {matriz[58]} {matriz[59]}")
	print(f"{matriz[60]} {matriz[61]} {matriz[62]} {matriz[63]} {matriz[64]} {matriz[65]} {matriz[66]} {matriz[67]} {matriz[68]} {matriz[69]}")
	print(f"{matriz[70]} {matriz[71]} {matriz[72]} {matriz[73]} {matriz[74]} {matriz[75]} {matriz[76]} {matriz[77]} {matriz[78]} {matriz[79]}")
	print(f"{matriz[80]} {matriz[81]} {matriz[82]} {matriz[83]} {matriz[84]} {matriz[85]} {matriz[86]} {matriz[87]} {matriz[88]} {matriz[89]}")
	print(f"{matriz[90]} {matriz[91]} {matriz[92]} {matriz[93]} {matriz[94]} {matriz[95]} {matriz[96]} {matriz[97]} {matriz[98]} {matriz[99]}")


	print("\nSIMBOLOGIA:\nx = Pared\n@ = Recorrido del Programa\nS = Inicio\nQ = Salida")
	time.sleep(.3)
	return 0


# Esta funcion se encarga de realizar los movimientos
def movimientos(matriz,pos):
	# Caso Base
	if matriz[pos] == "Q": 
		return 0 # Laberinto Resuelto 

	# Caso Recursivo para Movimientos 
	else:
		if(matriz[pos] is not "x") and (matriz[pos] is not "@"):
			matriz[pos] = "@" # Se marca casilla como Recorrida por el Programa
			imprimirLaberinto(matriz)
			os.system("cls") # Limpia pantalla

		# Movimientos  Izquierda				       Derecha							Abajo						   Arriba
			if(movimientos(matriz,pos-1)==0) or (movimientos(matriz,pos+1)==0) or (movimientos(matriz,pos+10)==0) or (movimientos(matriz,pos-10)==0):
				return 0

			else:
				matriz[pos] = " " # Se descarta el camino previamente recorrido
				imprimirLaberinto(matriz)
				os.system("cls") # Limpia pantalla

	return 1 # Laberinto Sin Solucion


# ***********************************************************************************
# ********************** Desarrollo Principal del programa **************************
# ***********************************************************************************
solucion = 0
pos = 10 # Posicion Inicial
matriz = ["x","x","x","x","x","x","Q","x","x","x",
		  "S"," "," "," "," ","x"," ","x","x","x",
		  "x","x"," ","x"," ","x"," ","x"," ","x",
		  "x"," "," ","x"," ","x"," ","x"," ","x",
		  "x","x","x","x"," ","x"," "," "," ","x",
		  "x"," "," "," "," ","x"," ","x","x","x",
		  "x"," ","x","x","x","x"," "," "," ","x",
		  "x"," "," "," ","x"," "," ","x"," ","x",
		  "x"," ","x"," "," "," ","x","x"," ","x",
		  "x","x","x","x","x","x","x","x","x","x"] # Declaracion de matriz 10x10

# Aqui inicia el programa
os.system("cls") # Limpia pantalla 
print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n")
print("------- Practica 2 - Laberinto -------\n\n")
print("INSTRUCCIONES: Este programa resolvera un laberinto predeterminado de forma automatica\n\n")
imprimirLaberinto(matriz)
input("\nPresione ENTER para comenzar a resolver el laberinto... ")
os.system("cls") # Limpia pantalla

solucion = movimientos(matriz,pos)

if (solucion==0):    
	print("!!! El Laberinto Ha Sido Resuelto !!!\n")
	imprimirLaberinto(matriz)
else:
	print("!!! El Laberinto No Tiene Solucion !!!\n")

print("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n")