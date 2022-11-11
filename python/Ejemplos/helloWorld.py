import os
import random

numero = 0
file = open("cadenas.txt", "r")
cadenas = file.read().splitlines()
file.close()

# ---- Programa Principal ----
os.system("cls")
print(f"Aquí esta el contenido de cadenas.txt ")
print(cadenas)
print(type(cadenas))

# Genera números binarios para después guardarlos en fichero
file = open("cadenas.txt", "w")
for i in range(10):
    numero = random.randint(512, 1024)  # Genera números enteros aleatorios entre 512 y 1024
    file.write(f"{bin(numero)[2:]}\n")  # Escribe los números generados en binario dentro del fichero
file.close()

# Esto 
# es 
# un 
# comentario 
# multiple

print("\tPROGRAMA DE PRUEBAS EN PYTHON\n")
print("hello 'world' con comillas doble ")
print('Ejemplo con "comillas simple" \n')
print("""Ejemplo con triple "comillas doble" y usando mensaje con 'comillas simple' """)

numero = 10
# Utilizando format (f) en printf permite acomodar el valor de las variables en el texto directamente
print(f"Ejemplo de valor = '{numero}', Tipo = '{type(numero)}' y el ID = '{id(numero)}' de la variable 'numero'")

# Escribir variable después de comillas agrega un espacio automático
print("Ejemplo de tipo de variable 'numero' igual a", type(numero))

# La variable puede recibir cualquier valor de cualquier tipo de dato directamente
numero = "Ahora soy una cadena"
print(f"Ejemplo 2 de valor = '{numero}', Tipo = '{type(numero)}' y el ID = '{id(numero)}' de la variable 'numero'\n")

# Aquí se utiliza variables lista y tuple. Lista se pueden cambiar valores y tuple no se puede
mi_tupla = [1, 3, 5, 6, "hola", 36, "Tupla"]  # Camel Case
mi_lista = ("adios", "lista", 1, 34, 567)  # Snake Case
MiCadena = "CiRcUlO rOsAdO"

print(f"Ejemplo de valor = '{mi_tupla}', Tipo = '{type(mi_tupla)}' y el ID = '{id(mi_tupla)}' de la variable 'miTupla'")

print(
    f"Ejemplo de valor = '{mi_lista}', Tipo = '{type(mi_lista)}' y el ID = '{id(mi_lista)}' de la variable 'mi_lista'")

print(
    f"Ejemplo de imprimir valor = '{MiCadena}', Tipo = '{type(MiCadena)}' y el ID = '{id(MiCadena)}' de la variable "
    f"'MiCadena'\n")

# Se utiliza entrada del usuario " input() " para leer datos
print("A continuación se deberán ingresar 3 números para ver cual es el mayor de los 3 con if")

# input() puede ir vacío o puede mostrar algún mensaje en pantalla y después esperar la entrada
a, b, c = input("Escribe el primer numero: "), input("Escribe el segundo numero: "), input("Escribe el tercer numero: ")

# input() devuelve un valor tipo str. Si se quiere convertir a int o float, utilizar función int(), float()

if a > b and a > c:  # Condicional if (requiere indentación para reconocer procedimiento)
    print("El numero mas grande es", a)
    print("La concatenación de los tres números es", a + b + c)
    # print ("La suma de los tres números convirtiendo str a int es",int(a)+int(b)+int(c))
    print("La suma total en float es", float(a) + float(b) + float(c))

else:
    if b > a and b > c:
        print("El numero mas grande es", b)
        print("La concatenación de los tres números es", a + b + c)
        # print ("La suma de los tres números convirtiendo str a int es",int(a)+int(b)+int(c))
        print("La suma total en float es", float(a) + float(b) + float(c))

    else:
        print("El numero mas grande es", c)
        print("La concatenación de los tres números es", a + b + c)
        # print ("La suma de los tres números convirtiendo str a int es",int(a)+int(b)+int(c))
        print("La suma total en float es", float(a) + float(b) + float(c))

# While repite procesos mientras se cumpla la condición
print("\nPrueba con Bucle While")
i = 0
while i < 6:
    print("Numero While = " + str(i))  # No se puede concatenar número y cadena, por lo que se convierte int a str
    i = i + 1

print("\n### Fin del programa ###\n")
