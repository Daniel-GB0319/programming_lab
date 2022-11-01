import base64
from Crypto.Random import get_random_bytes

print("**** Lab 2: Stream Ciphers (Generador de cadenas en base64) - Criptography - 30/October/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / Maldonado Flores Marco de Jesus / 3CM11 --\n")

N = int(input('Ingrese el numero de cadenas pseudoaleatorias que desea crear: '))
longitud = int(input('Ingrese la longitud de las cadenas de bytes: '))

print('\nCadenas generadas:\n')
for i in range(N):
    key = get_random_bytes(longitud) # Obtiene las cadenas de bytes aleatoriamente segun la longitud dada
    key = base64.b64encode(key) # Convierte la cadena previamente generada en base64
    print(f'#{i} : {key}')

     