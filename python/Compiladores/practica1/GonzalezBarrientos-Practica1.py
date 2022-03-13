import os
import random

numero = 0
file = open("cadenas.txt","r")
cadenas = file.read().splitlines()
file.close()

# ---- Programa Principal ----
os.system("cls")
print(f"Aqui esta el contenido de cadenas.txt ")
print(cadenas)
print(type(cadenas))

# Genera numeros binarios para despues guardarlos en fichero
file = open("cadenas.txt","w")
for i in range(10):
    numero = random.randint(512,1024) # Genera numeros enteros aleatorios entre 512 y 1024
    file.write(f"{bin(numero)[2:]}\n") # Escribe los numeros generados en binario dentro del fichero
file.close()