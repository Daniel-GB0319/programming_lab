import random

n = 0
file = open("pruebaL1_c.txt", "w")

while True:
    numero = random.randint(2048, 4096)  # Genera números enteros aleatorios entre 512 y 1024
    file.write(f"{bin(numero)[2:]} | ")  # Escribe los números generados en binario dentro del fichero
    if n == 50000:
        break

    n = n + 1

file.close()
