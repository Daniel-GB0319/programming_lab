import base64
from Crypto.Random import get_random_bytes


# Realiza la operación XOR entre el mensaje (cifrado o descifrado) y la llave
def byte_xor(ba1, ba2):
    return bytes([_a ^ _b for _a, _b in zip(ba1, ba2)])  # Devuelve la cadena resultante en bytes


def txt_input():  # Deja solamente los caracteres imprimibles de ASCII en la cadena
    text_str = ""
    aux_str = ""
    while text_str == "":
        text_str = input("Enter the text: ")  # Lee el mensaje desde teclado

    for i in text_str:
        if 31 < ord(i) < 128:
            aux_str += i

    return aux_str


# Cifra el mensaje en base64
def encoder(input_str):
    message = str.encode(input_str)
    key = get_random_bytes(len(input_str))
    ciphertext_b64 = base64.b64encode(byte_xor(message, key))
    key_b64 = base64.b64encode(key)

    return ciphertext_b64, key_b64


# Recupera el mensaje original
def decoder(ciphertext, key_b64):
    key = base64.b64decode(key_b64)
    plaintext = byte_xor(base64.b64decode(ciphertext), key)
    return plaintext


# Función principal
print("**** Lab 2: Stream Ciphers - Cryptography - 30/October/2022 ****")
print("-- Gonzalez Barrientos Geovanni Daniel / Maldonado Flores Marco de Jesus / 3CM11 --\n")
text = txt_input()
encoded_text_b64, cipher_key_b64 = encoder(text)
encoded_text = encoded_text_b64.decode('ascii')
cipher_key = cipher_key_b64.decode('ascii')
print("Ciphertext:\t", encoded_text)
print("Key:\t\t", cipher_key)

decoded_text_b64 = decoder(encoded_text_b64, cipher_key_b64)
decoded_text = decoded_text_b64.decode('ascii')
print("Plaintext:\t", decoded_text)
