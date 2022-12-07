#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab3 - Permutations and 3DES *****
#  Descripción: Este programa genera un archivo cifrado, a partir de una llave y
#  un archivo fuente pasados como parámetros.
#  *********************************************
#

import base64
import sys

from Crypto.Cipher import DES3


# Lee contenido de un fichero
def read_file(name):
    file = open(name, "rb")
    file_msg = file.read()
    file.close()
    return file_msg


# Escribe contenido en un fichero
def write_file(name, content):
    name = "enc-%s" % name
    file = open(name, "w")
    file.write(content)
    file.close()
    print(f"!!! Archivo {name} generado con éxito !!!\n")


# Decodifica una cadena de base64
def decode_base64(codificado):
    decodificado = base64.b64decode(codificado)
    return decodificado


# Codifica una cadena en base64
def encode_base64(decodificado):
    codificado = base64.b64encode(decodificado)
    return codificado


# Se encarga de cifrar contenido con DES3
def encrypt_file(plaintext, des_key):
    cipher = DES3.new(des_key, DES3.MODE_CFB)
    ciphertext = cipher.encrypt(plaintext)
    iv = encode_base64(cipher.iv)
    iv = iv.decode("ascii")
    write_file("iv.txt", iv)
    return ciphertext


# Función principal
print("**** Lab 3: Triple DES - Cryptography - 4/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")
print("Generando archivo cifrado, por favor espere... \n")

# Lee archivo a cifrar y llave
mensaje = read_file(sys.argv[1])
llaveB64 = read_file(sys.argv[2])

# Se decodifica la llave generada en base64 a bytes
llave = decode_base64(llaveB64)

# Se realiza el cifrado del texto original
cipherText = encrypt_file(mensaje, llave)

# Se codifica cipherText a base64
cipherText = encode_base64(cipherText)
cipherText = cipherText.decode("ascii")

# Se almacena cipherText en fichero
write_file(sys.argv[1], cipherText)

print("-- Programa finalizado --\n")
