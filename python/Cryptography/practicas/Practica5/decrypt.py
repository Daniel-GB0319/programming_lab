#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab5 - Advance Encryption Standard *****
#  Descripción: Este programa genera un plaintext, a partir de un ciphertext, una llave
#  y un iv/nonce pasados como parámetro según el modo de operación.
#  *********************************************
#

import base64
from Crypto.Cipher import AES
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


# Se encarga de descifrar contenido en AES
def decrypt_file(mensaje, llave_aes, iv_nonce_aes, op_mode):
    try:
        if op_mode == 1:
            cipher = AES.new(llave_aes, AES.MODE_CBC, iv_nonce_aes)
        else:
            cipher = AES.new(llave_aes, AES.MODE_CTR, nonce=iv_nonce_aes)

        plaintext_aes = unpad(cipher.decrypt(mensaje), AES.block_size)
        return plaintext_aes
    except(ValueError, KeyError):
        print('!! Error al descifrar !! Verifique que la llave y el iv/nonce ingresados sean los correctos \n')


# Función principal
print("**** Lab5 - Advance Encryption Standard - Cryptography - 17/Noviembre/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")
print("Recuperando contenido del archivo cifrado, por favor espere... \n")

# Lee ciphertext, llave y el iv/nonce sucesivamente
cipherText = read_file(sys.argv[1])
llave = read_file(sys.argv[2])
iv_nonce = read_file(sys.argv[3])

# Se decodifica la llave, iv/nonce y el ciphertext generados en base64
llave = decode_base64(llave)
cipherText = decode_base64(cipherText)
iv_nonce = decode_base64(iv_nonce)

# Se detecta el modo de operación utilizado al cifrar el archivo
mode = 0
if "iv.txt" in sys.argv[3]:
    mode = 1  # Modo CBC
else:  # Modo CTR
    mode = 2

# Se realiza el descifrado del texto original
plaintext = decrypt_file(cipherText, llave, iv_nonce, mode)

# Se almacena plaintext en fichero
write_file(sys.argv[1], plaintext)
print("-- Programa finalizado --\n")
