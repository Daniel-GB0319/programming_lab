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
    file = open(name, "r")
    mensaje = file.read()
    file.close()
    return mensaje


# Escribe contenido en un fichero
def write_file(name, content):
    name = "decrypted-%s" % name
    file = open(name, "w")
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
def decrypt_file(mensaje, llave):
    cipher = DES3.new(llave, DES3.MODE_CFB)
    plaintext = cipher.decrypt(mensaje)
    return plaintext


# Función principal
print("**** Lab 3: Triple DES - Cryptography - 4/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")
print("Recuperando contenido del archivo cifrado, por favor espere... \n")

# Lee ciphertext y llave
cipherText = read_file(sys.argv[1])
cipherText = cipherText.encode("ascii")

llave = read_file(sys.argv[2])
llave = llave.encode("ascii")

# Se decodifica la llave y el ciphertext generados en base64
llave = decode_base64(llave)
cipherText = decode_base64(cipherText)

# Se realiza el descifrado del texto original
plaintext = decrypt_file(cipherText, llave)

# Se almacena plaintext en fichero
write_file(sys.argv[1], str(plaintext))

print("-- Programa finalizado --\n")
