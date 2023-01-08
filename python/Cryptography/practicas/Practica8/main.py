#  Copyright (c) 2023.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab 08. Hard computational problems in cryptography *****
#  Descripción: Este programa realiza una serie de operaciones criptográficas complejas.
#  *********************************************
#

from sympy.ntheory import discrete_log, primefactors
from Crypto.Util.number import getPrime


# Función que resuelve problemas del logaritmo discreto
def discrete_log_solver(discrete_logs_list):
    for itr in discrete_logs_list:
        x = discrete_log(itr["mod"], itr["a"], itr["g"])
        print(f"{itr['g']}^x mod {itr['mod']} = {itr['a']} | x = {x}")


# Función para encontrar los factores primos de números compuestos
def prime_num_finder(composite_numbers_list):
    for x in composite_numbers_list:
        result = primefactors(x)
        print(f"Composite number: {x} | Prime factors = {result}")


# Función para generar números primos con longitud de 512 bits
def very_large_prime_numbers():
    primes = list()
    for itr in range(10):
        num = getPrime(512)
        primes.append(num)
        print(f"#{itr} {num}")

    # Manda la lista de numeros primos a otra función para encontrar los generadores
    # generators_finder(primes)


# # Función para encontrar los generadores de los números primos ingresados
# def generators_finder(primes):
#     for itr in primes:
#         print(" !!! Aquí estaría el procedimiento para obtener los generadores, si tan solo el programador
#         hubiera encontrado una manera eficiente de realizar dichas operaciones sin frustrarse ¯\_(ツ)_/¯ ")


# Funcionamiento principal del programa
if __name__ == "__main__":
    print("****  Lab 08. Hard computational problems in cryptography - Cryptography - 09/Enero/2023 ****")
    print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")

    # Lista de problemas de logaritmo discreto para resolver más adelante
    discrete_logs = [{"g": 11, "mod": 1009, "a": 400},
                     {"g": 5, "mod": 10007, "a": 5235},
                     {"g": 2, "mod": 100000000003, "a": 1922556950},
                     {"g": 3, "mod": 500000009, "a": 406870124},
                     {"g": 3, "mod": 500000009, "a": 187776257}]

    # Lista de números compuestos para obtener sus factores primos más adelante
    composite_numbers = [100160063, 10006200817, 250035001189, 250000009000000081]

    print("\nA continuación se resolverán una serie de problemas de logaritmo discreto. Por favor espere... \n")
    discrete_log_solver(discrete_logs)

    print("\nAhora se procederá a encontrar los factores primos de una serie de números compuestos... \n")
    prime_num_finder(composite_numbers)

    print("\nComo siguiente paso se generaran 10 números primos con longitud de 512 bits cada uno... \n")
    very_large_prime_numbers()

    print("\n--- Programa Finalizado ---\n")
