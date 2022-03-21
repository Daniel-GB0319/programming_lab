from tabulate import tabulate
import os

# **************************************************************************
# *************************** Funciones ************************************
# **************************************************************************

# Esta funcion se encarga de llenar la tabla de amortizacion
def tabAmortizacion (renta,saldo,tasaPeriodo,i):
	interes=0
	amortizacion=0
	
	# Caso Base
	if saldo <= 0:
		return 0
	
	else:
		i = i + 1
		interes = round(saldo*tasaPeriodo, 2)
		amortizacion = round(renta - interes, 2)
		saldo = round(saldo-amortizacion, 2)
		
		tabla.append([i,renta,interes,amortizacion,saldo])  
		
		tabAmortizacion(renta,saldo,tasaPeriodo,i)
		
	return 0



# ***********************************************************************************
# ********************** Desarrollo Principal del programa **************************
# ***********************************************************************************

# ---- Variables ----
saldo = 0 # Guarda el Capital Prestado
numPagos = 0 # Guarda el numero de años que dura el pago
tasaPeriodo = 0 # Guarda la tasa de interes por periodo de capitalizacion
i = 0 # Guarda el numero de pago
renta = 0 

# --------- Instrucciones
os.system("clear") # Limpia pantalla 
print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n")
print("------- Examen 1 - Tabla de Amortizacion -------\n\n")
print("INSTRUCCIONES: Este programa calculara los valores de amortizacion segun el capital, plazo y tasa ingresados\n\n")

saldo = (input("\nIngrese el Saldo Inicial: "))
numPagos = int(input("\nIngrese el Plazo en años: "))
tasaPeriodo = int(input("\nIngrese la Tasa por Periodo de Capitalizacion (%): "))
tasaPeriodo = tasaPeriodo / 100
print(tasaPeriodo)

renta=round( ( saldo*tasaPeriodo)/(1-((1+tasaPeriodo)**(-numPagos))), 2 )
print(renta)

tabla = [["Periodo","Renta","Interes","Amortizacion","Saldo"],[i,"-","-","-",saldo]]

tabAmortizacion (renta,saldo,tasaPeriodo,i)
print(tabulate(tabla, headers='firstrow', tablefmt='psql'))

print("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n")
