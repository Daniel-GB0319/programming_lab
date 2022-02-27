# Esto 
# es 
# un 
# comentario 
# multiple

print("\tPROGRAMA DE PRUEBAS EN PYTHON\n")
print("hello 'world' con comilla doble ")
print('Ejemplo con "comilla simple" \n')
print("""Ejemplo con triple "comilla doble" y usando mensaje con 'comilla simple' """)

numero=10
# Utilizando format (f) en printf permite acomodar el valor de las variables en el texto directamente
print(f"Ejemplo de valor = '{numero}', Tipo = '{type(numero)}' y el ID = '{id(numero)}' de la variable 'numero'") 

#Escribir variable despues de comilla agrega un espacio automatico
print("Ejemplo de tipo de variable 'numero' igual a",type(numero))

# La variable puede recibir cualquier valor de cualquier tipo de dato directamente
numero="Ahora soy una cadena"
print(f"Ejemplo 2 de valor = '{numero}', Tipo = '{type(numero)}' y el ID = '{id(numero)}' de la variable 'numero'\n")

# Aqui se utiliza variables lista y tupla. Lista se pueden cambiar valores y tupla no se puede
miTupla = [1,3,5,6,"hola",36,"Tupla"] # Camel Case
mi_lista = ("adios","lista",1,34,567) # Snake Case
MiCadena = "CiRcUlO rOsAdO"

print(f"Ejemplo de valor = '{miTupla}', Tipo = '{type(miTupla)}' y el ID = '{id(miTupla)}' de la variable 'miTupla'")

print(f"Ejemplo de valor = '{mi_lista}', Tipo = '{type(mi_lista)}' y el ID = '{id(mi_lista)}' de la variable 'mi_lista'")

print(f"Ejemplo de imprimir valor = '{MiCadena}', Tipo = '{type(MiCadena)}' y el ID = '{id(MiCadena)}' de la variable 'MiCadena'\n")


# Se utiliza entrada del usuario " input() " para leer datos 
print("A continuacion se deberan ingresar 3 numeros para ver cual es el mayor de los 3 con if")

#input() puede ir vacio o puede mostrar algun mensaje en pantalla y despues esperar la entrada
a,b,c=input("Escribe el primer numero: "),input("Escribe el segundo numero: "),input("Escribe el tercer numero: ") 

# input() devuelve un valor tipo str. Si se quiere convertir a int o float, utilizar funcion int(), float()

if a>b and a>c: # Condicional if (requiere identacion para reconocer procedimiento)
	print("El numero mas grande es",a)
	print ("La concatenacion de los tres numeros es",a+b+c)
	#print ("La suma de los tres numeros convirtiendo str a int es",int(a)+int(b)+int(c))
	print ("La suma total en float es",float(a)+float(b)+float(c))

else:
	if b>a and b>c:
		print("El numero mas grande es",b)
		print ("La concatenacion de los tres numeros es",a+b+c)
		#print ("La suma de los tres numeros convirtiendo str a int es",int(a)+int(b)+int(c))
		print ("La suma total en float es",float(a)+float(b)+float(c))

	else:
		print("El numero mas grande es",c)
		print ("La concatenacion de los tres numeros es",a+b+c)
		#print ("La suma de los tres numeros convirtiendo str a int es",int(a)+int(b)+int(c))
		print ("La suma total en float es",float(a)+float(b)+float(c))

# While repite procesos mientras se cumpla la condicion
print("\nPrueba con Bucle While")
i = 0
while i<6:
	print("Numero While = " + str(i)) # No se puede concatenar numero y cadena, por lo que se convierte int a str
	i=i+1


print("\n### Fin del programa ###\n")
