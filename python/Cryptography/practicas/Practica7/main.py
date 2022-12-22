#  Copyright (c) 2022.
#  Autor:
#   Maldonado Flores Marco de Jesus
#   Gonzalez Barrientos Geovanni Daniel
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab7 - Hash Function and key generation in RSA *****
#  Descripción: Este programa se encarga de generar cadenas hash para
#  distintos tipos de archivos. De igual forma realiza la generacion
#  de un par de llaves para RSA.
#  *********************************************
#

from Crypto.Hash import SHA512
import json
from base64 import b64encode
from Crypto.PublicKey import RSA

# Realiza la lectura del contenido de un fichero
def readFile(file_name: str) -> bytes:
    input_file = open(file_name, 'rb')
    file_bytes = input_file.read()
    input_file.close()
    return file_bytes

# Realiza la creacion de un fichero con el contenido ingresado
def createFile(input_bytes: bytes, file_name: str) -> None:
    output_file = open(file_name, 'wb')
    output_file.write(input_bytes)
    output_file.close()

# Realiza el cálculo del hash para cada mensaje ingresado
def toSHA(file_bytes: bytes) -> bytes:
    file_hash = SHA512.new()
    file_hash.update(file_bytes)
    return file_hash.digest()

# Realiza la creacion del par de llaves para RSA y los almacena en un fichero
def createRSAKeys():
    key = RSA.generate(2048)
    private_key_b64 = b64encode(key.export_key('DER'))
    public_key_b64 = b64encode(key.public_key().export_key('DER'))

    createFile(private_key_b64, 'private.txt')
    createFile(public_key_b64, "public.txt")
    return private_key_b64, public_key_b64

# Función principal
print("**** Lab7 - Hash Function and key generation in RSA - Cryptography - 22/Diciembre/2022 - 3CM11 ****")
print("-- Gonzalez Barrientos Geovanni Daniel\n-- Maldonado flores Marco de Jesus\n")
print("\nA continuacion se realizara el calculo del hash de diversos ficheros. Por favor espere... \n")

# Cadena de texto plano
plain_password = "Password"

# Calcula el hash de diversos ficheros
sha_password = b64encode(toSHA(plain_password.encode('utf-8'))) # Cadena de 8 caracteres
sha_text = b64encode(toSHA(readFile("original_text.txt"))) # Archivo de texto plano
sha_pdf = b64encode(toSHA(readFile("understanding-cryptography.pdf"))) # Archivo pdf
sha_audioFile = b64encode(toSHA(readFile("song.mp3"))) # Archivo de audio
sha_img = b64encode(toSHA(readFile("img.jpg"))) # Imagen

# Genera un fichero con los resultados de calcular el hash a cada archivo
output = json.dumps({'sha_password': sha_password.decode('ascii'),
                     'sha_text': sha_text.decode('ascii'),
                     'sha_pdf': sha_pdf.decode('ascii'),
                     'sha_audioFile': sha_audioFile.decode('ascii'),
                     'sha_img': sha_img.decode('ascii')})

createFile(output.encode('ascii'), "output.txt")
print("!!! Fichero 'output.txt' con los hash calculados se ha generado con exito !!!\n")
print("\nA continuacion se realizara la creacion del par de llaves para RSA. Por favor espere... \n")

# Genera el par de llaves para RSA
private, public = createRSAKeys()
print("!!! Archivos 'public.txt' y 'private.txt' generados con exito !!!\n")
print("\n--- Programa Finalizado ---\n")