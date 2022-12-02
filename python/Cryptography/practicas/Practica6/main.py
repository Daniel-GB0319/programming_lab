#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab6 - Finite Fields *****
#  Descripción: Este programa realiza la multiplicación de GF(2^5)
#  CON EL POLINOMIO IRREDUCIBLE X^5 + X^2 + 1.
#  *********************************************
#

import numpy as np  # Permite realizar operaciones con arrays
import galois  # Permite trabajar con GF(P^M)


# Realiza la multiplicación GF * polinomio irreducible
def galois_multiplication():
    # Se crea el arreglo GF
    galois_array = galois.GF(2 ** 5)
    print(galois_array.properties)

    # Se indica el polinomio a utilizar
    poly = galois.Poly([1, 0, 0, 1, 0, 1], field=galois_array)
    print(poly)

    # Se realiza la multiplicación
    result_mult = galois_array * poly
    print(f"El resultado de la multiplicación es: {result_mult}")

    print("Imprimiendo lista de elementos GF(2^5), por favor espere... ")
    inverse_list(galois_array)


# Muestra la lista de elementos GF junto con inversos multiplicativos
def inverse_list(galois_array):
    # Convirtiendo a binario GF
    for i in galois_array:
        print(f"#{i} = {galois_array[i]}")
        print(f"Inverse = {np.reciprocal(galois_array[i])}\n")


# !! Aún falta realizar pruebas de funcionamiento y representación en binario

# Función principal
if __name__ == '__main__':
    print("**** Lab6 - Finite Fields - Cryptography - 01/Dec/2022 ****")
    print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")

    print("Realizando multiplicación, por favor espere... ")
    galois_multiplication()
