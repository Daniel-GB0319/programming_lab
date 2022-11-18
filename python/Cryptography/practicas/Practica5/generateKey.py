#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab5 - Advance Encryption Standard *****
#  Descripción: Este programa genera una llave válida para AES en base 64
#  y lo almacena en un fichero. Las llaves pueden ser de 128, 192 o 256 bits.
#  *********************************************
#

import base64

from Crypto.Random import get_random_bytes


# Escribe contenido en un fichero
def write_file(name_archivo, content):
    file = open(name_archivo, "w")
    file.write(str(content))
    file.close()
    print(f"!!! Llave generada en {name_archivo} con éxito !!!\n")


# Codifica una cadena en base64
def encode_base64(decodificado):
    codificado = base64.b64encode(decodificado)
    return codificado


# Genera una llave válida para AES
def generate_key(option_key):
    while True:  # Se genera la llave de 112 bits
        try:
            key_aes = get_random_bytes(option_key)
            break
        except ValueError:
            pass
    return key_aes


# Función principal
print("**** Lab5 - Advance Encryption Standard - Cryptography - 17/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")

llaves = [16, 24, 32]  # Tamaño de llaves
i = 0
while True:
    option = 0
    while option not in llaves:
        option = int(input("Ingrese el tamaño de la llave en Bytes (16 bytes = 128 bits / 24 bytes = 192 bits / 32 bytes = 256 bits): "))

    print(f"\n\nGenerando llave de {option} bytes, por favor espere... \n")
    # Se genera llave
    key = generate_key(option)

    # Se convierte llave generada a base64
    key = encode_base64(key)
    key = key.decode("ascii")
    # Se guarda la llave en un fichero
    name = "key" + str(i) +"-"+ str(option) + "bytes.txt"
    write_file(name, key)
    i = i + 1

    # Se pregunta a usuario si desea generar mas llaves
    menu = input(f"¿Desea generar otra llave? (S/N) ( {i} llave(s) generada(s) ): ")
    if menu == "n" or menu == "N":
        break
    print("-----------------------------------------------------\n\n")
