from tabulate import tabulate
import os

# **************************************************************************
# *************************** Funciones ************************************
# **************************************************************************

# Esta funcion se encarga de llenar la tabla de amortizacion recursivamente
def tabAmortizacion (renta,saldo,tasaPeriodo,i,total):
	interes=0
	amortizacion=0
	
	# Caso Base
	if saldo <= 0: # Se verifica si el saldo ha llegado a cero
		return 0
	
	# Caso recursivo
	else:
		i = i + 1 # Numero de Pago se incrementa
		interes = round(saldo*tasaPeriodo, 2) # Se calcula el interes
		amortizacion = round(renta - interes, 2) # Se calcula la amortizacion
		saldo = round(saldo-amortizacion, 1) # Se calcula el nuevo saldo 
		
		# Se suman los valores obtenidos al total
		total[1] = total[1] + renta
		total[2] = total[2] + interes
		total[3] = total[3] + amortizacion

		tabla.append([i,renta,interes,amortizacion,saldo]) # Se ingresa la fila con los datos calculados a la tabla de amortizacion 
		
		tabAmortizacion(renta,saldo,tasaPeriodo,i,total) # Se vuelve a llamar a la funcion para trabajar con nuevo saldo

	return total


# ***********************************************************************************
# ********************** Desarrollo Principal del programa **************************
# ***********************************************************************************

# ---- Variables ----
saldo = 0 # Guarda el Capital Prestado
numPagos = 0 # Guarda el numero de años que dura el pago
tasaPeriodo = 0 # Guarda la tasa de interes por periodo de capitalizacion
i = 0 # Guarda el numero de pago
renta = 0 # Guarda el valor calculado de la renta fija
total = ["TOTAL",0,0,0,"-"] # Guarda el total de la renta, interes y amortizacion calculados

# --------- Instrucciones -----------
os.system("cls") # Limpia pantalla 
print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n")
print("------- Examen 1 - Tabla de Amortizacion -------\n\n")
print("INSTRUCCIONES: Este programa calculara los valores de amortizacion segun el capital, plazo y tasa ingresados\n\n")

# Se ingresan los valores necesarios para el calculo
saldo = int(input("\nIngrese el Saldo Inicial: "))
numPagos = int(input("\nIngrese el Plazo en años: "))
tasaPeriodo = int(input("\nIngrese la Tasa por Periodo de Capitalizacion (%): "))
tasaPeriodo = tasaPeriodo / 100

# Se calcula la renta fija
renta = round( ( saldo*tasaPeriodo)/(1-((1+tasaPeriodo)**(-numPagos))), 2 )

# Se inicializa la tabla de amortizacion
tabla = [["Periodo","Renta","Interes","Amortizacion","Saldo"],[i,"-","-","-",saldo]]

# Se hace el llamado a la funcion que se ejecutara recursivamente
total = tabAmortizacion (renta,saldo,tasaPeriodo,i,total)

# Se ingresan los montos totales a la tabla
tabla.append(total)

# Se imprime la tabla
print(tabulate(tabla, headers='firstrow', tablefmt='psql'))

print("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n")
