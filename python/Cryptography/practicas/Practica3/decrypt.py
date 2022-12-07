#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab3 - Permutations and 3DES *****
#  Descripción: Este programa genera un plaintext, a partir de una llave y
#  un archivo cifrado pasados como parámetros.
#  *********************************************
#

import base64
from Crypto.Cipher import DES3
import sys


# Lee contenido de un fichero
def read_file(name):
    file = open(name, "rb")
    mensaje = file.read()
    file.close()
    return mensaje


# Escribe contenido en un fichero
def write_file(name, content):
    name = "dec-%s" % name
    file = open(name, "wb")
    file.write(content)
    file.close()
    print(f"!!! Archivo recuperado con éxito !!!\n")


# Decodifica una cadena de base64
def decode_base64(codificado):
    decodificado = base64.b64decode(codificado)
    return decodificado


# Codifica una cadena en base64
def encode_base64(decodificado):
    codificado = base64.b64encode(decodificado)
    return codificado


# Se encarga de cifrar contenido con DES3
def decrypt_file(mensaje, des_llave, des_iv):
    cipher = DES3.new(des_llave, DES3.MODE_CFB, des_iv)
    original_plaintext = cipher.decrypt(mensaje)
    return original_plaintext


# Función principal
print("**** Lab 3: Triple DES - Cryptography - 4/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")
print("Recuperando contenido del archivo cifrado, por favor espere... \n")

# Lee ciphertext, llave e iv
cipherText = read_file(sys.argv[1])
llave = read_file(sys.argv[2])
iv = read_file(sys.argv[3])

# Se decodifica la llave y el ciphertext generados en base64
llave = decode_base64(llave)
cipherText = decode_base64(cipherText)
iv = decode_base64(iv)

# Se realiza el descifrado del texto original
plaintext = decrypt_file(cipherText, llave, iv)

# Se almacena plaintext en fichero
write_file(sys.argv[1], plaintext)

print("-- Programa finalizado --\n")
