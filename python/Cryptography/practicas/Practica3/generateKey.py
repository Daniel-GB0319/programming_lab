#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab3 - Permutations and 3DES *****
#  Descripción: Este programa genera una llave para TripleDES en base 64
#  y lo almacena en un fichero.
#  *********************************************
#

import base64

from Crypto.Cipher import DES3
from Crypto.Random import get_random_bytes


# Escribe contenido en un fichero
def write_file(name, content):
    file = open(name, "w")
    file.write(str(content))
    file.close()
    print(f"!!! Llave generada en {name} con éxito !!!\n")


# Codifica una cadena en base64
def encode_base64(decodificado):
    codificado = base64.b64encode(decodificado)
    return codificado


# Genera una llave válida para DES3
def generate_key():
    while True:  # Se genera la llave de 112 bits
        try:
            key = DES3.adjust_key_parity(get_random_bytes(24))
            break
        except ValueError:
            pass
    return key


# Función principal
print("**** Lab 3: Triple DES - Cryptography - 4/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")

i = 0
while True:
    print("\n\nGenerando llave, por favor espere... \n")
    # Se genera llave
    key = generate_key()

    # Se convierte llave generada a base64
    key = encode_base64(key)
    key = key.decode("ascii")
    # Se guarda la llave en un fichero
    name = "key" + str(i) + ".txt"
    write_file(name, key)
    i = i + 1

    # Se pregunta a usuario si desea generar mas llaves
    menu = input(f"¿Desea generar otra llave? (S/N) ({i} llave(s) generada(s)): ")
    if menu == "n" or menu == "N":
        break
