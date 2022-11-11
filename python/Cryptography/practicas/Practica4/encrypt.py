#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab4 - Modes of Operation *****
#  Descripción: Este programa genera un archivo cifrado, a partir de una llave y
#  un fichero pasados como parámetros.
#  *********************************************
#

import base64

from Crypto.Cipher import DES3
from Crypto.Util.Padding import pad
import sys


# Lee contenido de un fichero
def read_file(name):
    file = open(name, "r")
    mensaje_fichero = file.read()
    file.close()
    return mensaje_fichero


# Escribe contenido en un fichero
def write_file(name, content):
    name = "encrypted-%s" % name
    file = open(name, "w")
    file.write(f"{content}")
    file.close()
    print(f"!!! Archivo '{name}' generado con éxito !!!\n")


# Decodifica una cadena de base64
def decode_base64(codificado):
    decodificado = base64.b64decode(codificado)
    return decodificado


# Codifica una cadena en base64
def encode_base64(decodificado):
    codificado = base64.b64encode(decodificado)
    return codificado


# Se encarga de cifrar contenido con DES3
def encrypt_file(mensaje_plano, llave_des3):
    cipher = DES3.new(llave_des3, DES3.MODE_CBC)
    ciphertext = cipher.encrypt(pad(mensaje_plano, DES3.block_size))

    # Genera el IV correspondiente y lo almacena en un fichero
    iv = encode_base64(cipher.iv)
    iv = iv.decode('ascii')
    write_file('iv.txt', iv)

    return ciphertext


# Función principal
print("**** Lab 4: Modes of operation - Cryptography - 10/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")
print("Generando archivo cifrado, por favor espere... \n")

# Lee archivo a cifrar y llave
mensaje = read_file(sys.argv[1])
mensaje = mensaje.encode("ascii")
llaveB64 = read_file(sys.argv[2])
llaveB64 = llaveB64.encode("ascii")

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
