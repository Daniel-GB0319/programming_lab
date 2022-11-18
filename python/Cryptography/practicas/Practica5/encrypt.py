#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab5 - Advance Encryption Standard *****
#  Descripción: Este programa genera un archivo cifrado, a partir de una llave y
#  un fichero pasados como parámetros.
#  *********************************************
#

import base64

from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
import sys


# Lee contenido de un fichero
def read_file(name):
    file = open(name, "rb")
    mensaje_fichero = file.read()
    file.close()
    return mensaje_fichero


# Escribe contenido en un fichero
def write_file(name, content):
    name = "enc-%s" % name
    file = open(name, "w")
    file.write(content)
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


# Se encarga de cifrar contenido con AES
def encrypt_file(mensaje_plano, llave_aes, operation_mode):
    if operation_mode == 1:
        cipher = AES.new(llave_aes, AES.MODE_CBC)
        ciphertext = cipher.encrypt(pad(mensaje_plano, AES.block_size))

        # Genera el IV correspondiente y lo almacena en un fichero
        iv = encode_base64(cipher.iv)
        iv = iv.decode('ascii')
        write_file('iv.txt', iv)
    else:
        cipher = AES.new(llave_aes, AES.MODE_CTR)
        ciphertext = cipher.encrypt(pad(mensaje_plano, AES.block_size))

        # Genera el nonce correspondiente y lo almacena en un fichero
        nonce = encode_base64(cipher.nonce)
        nonce = nonce.decode('ascii')
        write_file('nonce.txt', nonce)

    return ciphertext


# Función principal
print("**** Lab5 - Advance Encryption Standard - Cryptography - 17/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")

# Lee archivo a cifrar y llave
mensaje = read_file(sys.argv[1])
llaveB64 = read_file(sys.argv[2])

# Se decodifica la llave generada en base64 a bytes
llave = decode_base64(llaveB64)

# Se pregunta al usuario en que modo desea cifrar
op_modes = [1,2]
mode = 0
while mode not in op_modes:
    mode = int(input("Ingrese el numero correspondiente al modo con el que desea operar (1 = CBC / 2 = CTR): "))

print("Generando archivo cifrado, por favor espere... \n")
# Se realiza el cifrado del texto original
cipherText = encrypt_file(mensaje, llave, mode)

# Se codifica cipherText a base64
cipherText = encode_base64(cipherText)
cipherText = cipherText.decode("ascii")

# Se almacena cipherText en fichero
write_file(sys.argv[1], cipherText)
print("-- Programa finalizado --\n")
