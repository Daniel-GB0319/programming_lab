import random

n = 0
file = open("pruebaL1_a.txt","w")

while(True):
    numero = random.randint(512,1024) # Genera numeros enteros aleatorios entre 512 y 1024
    file.write(f"{bin(numero)[2:]} | ") # Escribe los numeros generados en binario dentro del fichero
    if n == 10000:
        break

    n = n + 1

file.close()