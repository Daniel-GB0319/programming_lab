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
    alfabeto = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"] # Elementos del alfabeto
    salida = "" # Sirve para imprimir el texto descifrado (C)
    mod = 26 # Indica la longitud del alfabeto utilizado y permite obtener valores que corresponden con los elementos del mismo (n)

    for llave in range(mod): # Bucle para realizar las pruebas con todos los posibles valores de la llave
        print(f"Llave #{llave} = ", end="")
        
        for j in range( len(entrada) ): # Bucle para recorrer cada letra de la cadena ingresada y generar el mensaje de salida
            resultado_mod = alfabeto.index(entrada[j]) - llave # Se calcula "a = Valor numerico de la letra (mi) - Valor de la llave (K)"

            if(resultado_mod < 0): # Se verifica si es necesario calcular el inverso aditivo en caso de tener numero negativo
                resultado_mod = mod - ( (resultado_mod*(-1)) % mod) # Numero es negativo. Se calcula "n - (a mod n)" para obtener el resultado final
            else:
                resultado_mod %= mod # Numero es positivo. Se calcula "a mod n" para obtener el resultado final

            salida = alfabeto[resultado_mod] # Se busca la letra correspondiente al resultado final para mostrar el mensaje descifrado
            print(salida, end= " ")

        print("\n")


# FUNCION MAIN
while(True): # Bucle para ejecutar el programa cuantas veces lo requiera el usuario
    menu = ""

    borrarPantalla() # Limpia la pantalla de la terminal
    print("**** ACTIVIDAD 1: SHIFT DECIPHER ****")
    print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 / Criptography --\n")
    entrada = str(input("Ingrese la cadena a descifrar (A-Z): ")).upper() # Se lee la cadena a descifrar (M)
    descifrar(entrada) # Se llama a la funcion que realizara el descifrado
    
    menu = input("Desea descifrar otro mensaje? (S/N) : ") # Se pregunta a usuario si desea continuar con la ejecucion del programa
    if (menu.upper() == "N"): # Procedimiento cuando usuario ya no desee continuar con la ejecucion
        print("Gracias por utilizar el programa. !!! Hasta la proxima !!!")
        break