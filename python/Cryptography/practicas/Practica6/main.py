#  Copyright (c) 2022.
#  Autor: Gonzalez Barrientos Geovanni Daniel
#  Boleta: 2020630148
#  Materia: Cryptography
#  Grupo: 3CM14
#  ***** Lab6 - Finite Fields *****
#  Descripción: Este programa realiza la multiplicación de GF(2^5)
#  con el polinomio irreducible X^5 + X^2 + 1.
#  *********************************************
#


# Realiza la multiplicación GF * polinomio irreducible
def galois_multiplication():
    # Se indica el polinomio a utilizar
    poly = format(5, "b")
    print(f"\nPolinomio irreducible: {poly} ")
    print("\nRealizando multiplicaciones, por favor espere...\n")

    # Se realiza la multiplicación
    for i in range(32):
        if i >= 16:
            num = format(i, "b")
            shift_num = format((i << 1) & 31, "b")
            result = format((int(shift_num) ^ int(poly)) & 31, "b")
        else:
            num = format(i, "b")
            shift_num = format((i << 1) & 31, "b")
            result = shift_num
        print(f'#{i} ({num}) - Resultado de \'{shift_num}\' * \'{poly}\' = {result}')

    print("\n\nImprimiendo lista de elementos GF(2^5) y sus inversos, por favor espere... ")
    inverse_list()


# Muestra la lista de elementos GF junto con inversos multiplicativos
def inverse_list():
    for i in range(1, 32):
        numero = format(i, "b")
        gdc, x, y = gcd_ext_euclides(i, 5)
        if x < 0:
            x = 5 - ((-1 * x) % 5)
        inverso = format(x, "b")
        print(f"Elemento #{i} = {numero} | Inverso = {inverso}")


# Algoritmo extendido de Euclides recursivo
def gcd_ext_euclides(a, b):
    # Caso base
    if a == 0:
        return b, 0, 1

    # Se actualiza X y Y en cada llamada recursiva
    gcd, x1, y1 = gcd_ext_euclides(b % a, a)
    x = y1 - (b // a) * x1
    y = x1
    return gcd, x, y


# Función principal
if __name__ == '__main__':
    print("**** Lab6 - Finite Fields - Cryptography - 05/Dec/2022 ****")
    print("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n")
    galois_multiplication()
