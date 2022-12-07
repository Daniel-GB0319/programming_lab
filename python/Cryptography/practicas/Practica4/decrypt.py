#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab4 - Modes of Operation *****
#  Descripción: Este programa genera un plaintext, a partir de un ciphertext, una llave y un iv pasados como parámetro.
#  *********************************************
#

import base64
from Crypto.Cipher import DES3
from Crypto.Util.Padding import unpad
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
def decrypt_file(mensaje, llave_des3, iv_des3):
    try:
        cipher = DES3.new(llave_des3, DES3.MODE_CBC, iv_des3)
        plaintext_des3 = unpad(cipher.decrypt(mensaje), DES3.block_size)
        return plaintext_des3
    except(ValueError, KeyError):
        print('!! Error al descifrar el archivo !! Verifique que la llave y el iv ingresados sean los correctos \n')


# Función principal
print("**** Lab 4: Modes of Operation - Cryptography - 10/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")
print("Recuperando contenido del archivo cifrado, por favor espere... \n")

# Lee ciphertext, llave y el iv sucesivamente
cipherText = read_file(sys.argv[1])
llave = read_file(sys.argv[2])
iv = read_file(sys.argv[3])

# Se decodifica la llave, iv y el ciphertext generados en base64
llave = decode_base64(llave)
cipherText = decode_base64(cipherText)
iv = decode_base64(iv)

# Se realiza el descifrado del texto original
plaintext = decrypt_file(cipherText, llave, iv)
plaintext = plaintext.decode('ascii')

# Se almacena plaintext en fichero
write_file(sys.argv[1], plaintext)

print("-- Programa finalizado --\n")
