#--------------USO DE COMANDO "PRINT" Y SUS DISTINTAS FORMAS-----------------
print("***Resultados de usar \"print\" con (\"), (\'), en distintas formaas ***")
print("hola dude")
print("""hola dude""")
print('hola dude')
print('''hola dude''')
print("soy una cadena " + "agrupada por el simbolo + en \"print\" ")  
# con el signo + se agrupan strings

#-------------USO DE COMANDO "TYPE"-----------------
print("\n\n***A continuacion se muestran los resultados de usar comando \"type\"***")

type("hola")  #directamente no muestra nada, solo en el interprete se puede asi 

#para mostrarlo al ejecutar, utilizar "print"
print("hola")
print(type("hola"))
print(100)
print(type(100))
print("c")
print(type("c"))
print(3.26)
print(type(3.26))
print([10, "hola", True])
print(type([10, "hola", True]))


#-----------------------TIPOS DE DATOS-----------------------------
# 1) string : "hola" 
# 2) Integer : 12
# 3) Float : 3.2
# 4) Boolean : True, False
# 5) List : [10, 20, 30] , ["hola", "ciao", "hello", "alo"] , [True, 3.22]
# 6) Tuples : (10, "hola", True)

#Con list podemos cambiar los datos internos
#Con tuples, los datos son inmutables, es decir no se pueden cambiar o son para siempre asi

# 7) Dictorionies: 
#{
#	"name" : "Rick",
#	"age" : 19,
#	"country" : "EUA"
#} 

#Dictorionies o Diccionarios sirven para agrupar datos con nombres claves de la sig. forma:
# { "key" : "value",
# "key" : "value"}

# 8) None : sirve para indicar algo vacio

#--------------------------Variables---------------------------- 
print("\n\n***Aqui se muestran los resultados de usar variables***")

sms = "hola"
num = 100 + 20

#Case Sensitive: las variables son diferentes por tener mayusculas o minusculas en el nombre 
alo, Alo = "c", 3.26  #tambien se pueden declarar variables separados por comas para ahorrar lineas

#Por Convencion, los nombres de variables deben ser intuitivas, especificas y con los sig. estilos:
num_chico = [10, "hola", True] #Snake case 

numGrande = {"name" : "Rick", "age" : 19, "country" : "EUA"}  #Camel case

NumDiferente = (10.1, "xd", False) #Pascal case  

PI = 3.1416 # Por convencion se escribe todo en mayusculas cuando el valor de la variable nunca cambiara  

print(sms, num)
print(alo)
print(Alo)
print(num_chico)
print(numGrande)
print(NumDiferente)
print(PI)

#--------------------STRINGS----------------------------
#dir(valor) muestra todas las opciones disponiobles para trabajar con ese valor, para mostrar usar print
myStr = "buona SERA cIaO"

print("\n\n***Aqui se muestran varias herramientas de strings con \""+ myStr + "\" de ejemplo ***")
print(".title () = "+ myStr.title()) # Pone la primer letra de cada palabra en mayuscula
print(".upper () = "+ myStr.upper()) # Convierte todo en mayusculas
print(".lower () = "+ myStr.lower()) # Convierte todo en minusculas
print(".capitalize () = "+ myStr.capitalize()) # Pone solo en mayuscula la primer letra de la cadena
print(".swapcase () = "+ myStr.swapcase()) # Intercambia mayusculas por minusculas y viceversa

print(".count (\"a\") = ") 
print(myStr.count("a")) # Cuenta el numero de veces que aparece el valor en la cadena

print(".startswith (\"buo\") = ")
print(myStr.startswith("buo")) # Verifica si la cadena empieza con un valor especifico

print(".endswith (\"4\") = ")
print(myStr.endswith("4")) # Similar a .startswith pero verifica el final de la cadena 
# .startswith () y .endswith () arrojan valores booleanos segun el resultado obtenido.

print(".replace (\"SERA\", \"pomeriggio\") = "+ myStr.replace("SERA","pomeriggio")) 
#Cambia un valor por otro

print(".split (\"a\") = ")
print(myStr.split("a")) # Separa la cadena en varios valores segun el parametro especifico
# .split () arroja los resultados en una list

print(".find (\"n\") = ")
print(myStr.find("n")) #Busca el valor especificado y muestra su posicion en la cadena
 
print(".len (variable) = ")
print(len(myStr)) # Muestra la longitud de la cadena 

print(".len () = ")
print(len(myStr)) # Muestra la longitud de la cadena 

