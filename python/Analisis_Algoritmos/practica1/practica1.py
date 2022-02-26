from ast import While
import os # Libreria para hacer llamadas al sistema
import random # Libreria para numeros aleatorios

# **************************************************************************
# *************************** Funciones ************************************
# **************************************************************************

# Esta funcion imprime la cuadricula
def imprimirSudoku(matriz):
    print("     1   2   3")
    print("  ---------------")
    print("  ---------------")
    print(f"  || {matriz[0]} | {matriz[1]} | {matriz[2]} ||")
    print("  ||-----------||")
    print(f"4 || {matriz[3]} | {matriz[4]} | {matriz[5]} || 6")
    print("  ||-----------||")
    print(f"  || {matriz[6]} | {matriz[7]} | {matriz[8]} ||")
    print("  ---------------")
    print("  ---------------")
    print("     7   8   9\n\n")
    print("NOTA: Un 0 indica una casilla vacia\n")

    return 0


# Esta funcion se encarga de solucionar el sudoku
def solucionadorSudoku(matriz):
    sumas = [0,0,0,0,0,0] # Almacena la suma de cada fila y columna para verificar que todas son igual a 15
    aux = 0 
    i = 0 # Sirve para almacenar los numeros aleatorios
    casillasBase=[0,1,2,3,4,5,6,7,8] # Almacena las casillas que no fueron llenadas por el usuario
    numerosBase=[1,2,3,4,5,6,7,8,9] # Almacena los numeros que no fueron escogidos por el usuario
    numerosFaltantes=[] # Sirve para indicar al programa los numeros que faltan por colocar durante el bucle de asignacion


    # Bucle para verificar cuales casillas y numeros no fueron utilizados por el usuario por el usuario
    for aux in range(len(matriz)) :
        if int(matriz[aux]) != 0:
            casillasBase.remove(int(aux)) # Quita una casilla rellenada por el usuario de la lista de casillas libres
            numerosBase.remove(int(matriz[aux])) # Quita un numero ingresado por el usuario de la lista de numeros faltantes


    # Comienza asignacion de numeros faltantes
    while(True):
        numerosFaltantes = numerosBase.copy() # Copia los elementos de la lista de numeros faltantes original a lista auxiliar para el bucle  
        for aux in range(0,9):
            i = random.choice(numerosFaltantes) # Se selecciona un numero al azar de la lista de numeros faltantes
            if (int(aux) in casillasBase ) :
                matriz[int(aux)] = i # Asigna el numero escogido al azar a una casilla no seleccionada por usuario
                numerosFaltantes.remove(int(matriz[aux]))

        # Se realizan las sumas de cada fila y columna para su posterior verificacion 
        sumas[0] = int(matriz[0]) + int(matriz[1]) + int(matriz[2])
        sumas[1] = int(matriz[3]) + int(matriz[4]) + int(matriz[5])
        sumas[2] = int(matriz[6]) + int(matriz[7]) + int(matriz[8])
        sumas[3] = int(matriz[0]) + int(matriz[3]) + int(matriz[6])
        sumas[4] = int(matriz[1]) + int(matriz[4]) + int(matriz[7])
        sumas[5] = int(matriz[2]) + int(matriz[5]) + int(matriz[8])

        # Se verifica que todas las sumas tengan como resultado 15
        if((sumas[0]==15) and (sumas[1]==15) and (sumas[2]==15) and (sumas[3]==15) and (sumas[4]==15) and (sumas[5]==15)):
            break
        
        numerosFaltantes.clear() # Limpia la lista auxiliar de numeros faltantes

    return matriz


# ***********************************************************************************
# ********************** Desarrollo Principal del programa **************************
# ***********************************************************************************

matriz = [0,0,0,0,0,0,0,0,0] # Declaracion de la Cuadricula

# Aqui el usuario ingresa los valores de cada casilla
while(True):
    os.system("cls") # Limpia pantalla 
    print("\n******* Gonzalez Barrientos Geovanni Daniel 3CV1  Analisis de Algoritmos *******\n")
    print("------- Practica 1 - Sudoku -------\n\n")
    print("INSTRUCCIONES: Este programa resolvera el sudoku de 3x3 casillas a partir de los valores ingresados por el usuario\n\n")
    imprimirSudoku(matriz)
    
    # Se pide al usuario que ingrese los numeros iniciales en las casillas deseadas
    while(True):
        i=input("Ingrese el Numero de la Casilla que desea llenar (1 - 9): ")
        if 0 < int(i) < 10 : # Verifica que se ingrese un numero valido de casilla
            break
        else:
            print("!!! Ingreso un Valor de Casilla incorrecto !!! Intente de nuevo...\n")

    while(True):
        numero=input(f"Escriba el Numero que desea colocar en la casilla #{i} (1 - 9): ")
        if ((0 < int(numero) < 10) and (numero not in matriz)) : # Verifica que se ingrese un numero valido
            break
        else:
            print("!!! Ingreso un Numero fuera de rango o Repetido !!! Intente de nuevo...\n")

    matriz[int(i)-1] = numero # En cada iteracion se almacena el numero elegido en la respectiva casilla seleccionada por el usuario

    opcion=input("¿Desea rellenar otra casilla? (S / n): ")

    if (opcion == "n") or (opcion == "N") :
        break

# El programa procede a resolver el sudoku
print("Ahora espere mientras el programa determina la solucion del Sudoku ...\n\n")
matriz=solucionadorSudoku(matriz)

print("!!! El Sudoku ha sido resuelto !!!\n\n")
imprimirSudoku(matriz)

print("\n!!! Gracias por utilizar el programa !!! Hasta luego :)\n")