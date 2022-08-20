from ast import Str
import os

#Funcion para limpiar la pantalla de la terminal
def borrarPantalla():  
	if os.name == "posix":
		os.system ("clear")
	elif os.name == "ce" or os.name == "nt" or os.name == "dos":
		os.system ("cls")

# Funcion para obtener el mensaje descifrado con "C = (mi - K) mod n"
def descifrar(entrada):
    j = 0 # Auxiliar para recorrer la cadena ingresada
    llave = 0 # Llave para realizar el descifrado (K)
    resultado_mod = 0 # Indica la posicion de la letra que se colocara en la salida
    alfabeto = ["A","B","C","D","E","F","G","H","I","J","K", # Elementos del alfabeto
    "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"] 
    salida = "" # Sirve para imprimir el texto descifrado (C)
    mod = 26 # Indica la longitud del alfabeto utilizado (n)

    for llave in range(mod): # Bucle para realizar las pruebas con todos los posibles valores de la llave

        for j in range( len(entrada) ): # Bucle para recorrer cada letra de la cadena cifrada
            resultado_mod = alfabeto.index(entrada[j]) - llave # Se calcula "a = Numero de la letra (mi) - Llave (K)"

            if(resultado_mod < 0): # Se verifica si es necesario calcular el inverso aditivo
                resultado_mod = mod - ( (resultado_mod*(-1)) % mod) # Numero es negativo. Se calcula "n - (a mod n)"
            else:
                resultado_mod %= mod # Numero es positivo. Se calcula "a mod n"

            salida += alfabeto[resultado_mod] # Se asigna la letra que corresponde al resultado calculado
        
        print(f"Llave #{llave} = {salida}") # Se imprime el mensaje original descifrado
        salida = "" # Se limpia variable para almacenar mensaje recuperado en otra iteracion


# FUNCION MAIN
while(True): # Bucle para ejecutar el programa cuantas veces lo requiera el usuario
    menu = ""

    borrarPantalla() # Limpia la pantalla de la terminal
    print("**** ACTIVIDAD 1: SHIFT DECIPHER ****")
    print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 / Criptography --\n")
    entrada = str(input("Ingrese la cadena a descifrar (A-Z): ")).upper() # Se lee la cadena a descifrar (M)
    descifrar(entrada) # Se llama a la funcion que realizara el descifrado
    
    menu = input("Desea descifrar otro mensaje? (S/N) : ") # Se pregunta a usuario si desea descifrar otra cadena
    if (menu.upper() == "N"): # Procedimiento cuando usuario ya no desee continuar con la ejecucion
        print("Gracias por utilizar el programa. !!! Hasta la proxima !!!")
        break