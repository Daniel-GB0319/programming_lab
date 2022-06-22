### ESTRUCTURA DEL ANALIZADOR SEMANTICO ###
cont = 0
bufferArchivo = " " # Buffer para la generacion del codigo en “Sketchviz”

def addCont(): # Funcion que genera el numero que identificara a los nodos
	global cont
	cont +=1
	return "%d" %cont

class Nodo(): # Se inicializa la clase nodo
	pass

class Null(Nodo): # Se inicializa el caso de sintaxis donde sale el valor null
	def __init__(self): # Constructor de la clase
		self.type = 'void'

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident + "nodo nulo")

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id+"[label= "+"nodo_nulo"+"]"+"\n\t"
		return id

class program(Nodo): # Se inicializa el caso de sintaxis program
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)

		print(ident + "Nodo: "+self.name)

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		bufferArchivo += id +"[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id +"->"+son1+"\n\t"
		return "digraph G {\n\t"+bufferArchivo+"}"

class block(Nodo): # Se inicializa el caso de sintaxis block
	def __init__(self,son1,son2,son3,son4,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3
		self.son4 = son4

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		if type(self.son1) == type(tuple()):
			self.son1[0].postOrden(" "+ident)
		else:
			self.son1.postOrden(" "+ident)
		if type(self.son2) == type(tuple()):
			self.son2[0].postOrden(" "+ident)
		else:
			self.son2.postOrden(" "+ident)
		if type(self.son3) == type(tuple()):
			self.son3[0].postOrden(" "+ident)
		else:
			self.son3.postOrden(" "+ident)
		if type(self.son4) == type(tuple()):
			self.son4[0].postOrden(" "+ident)
		else:
			self.son4.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		if type(self.son1) == type(tuple()):
			son1 = self.son1[0].generarCodigo()
		else:
			son1 = self.son1.generarCodigo()
		if type(self.son2) == type(tuple()):
			son2 = self.son2[0].generarCodigo()
		else:
			son2 = self.son2.generarCodigo()
		if type(self.son3) == type(tuple()):
			son3 = self.son3[0].generarCodigo()
		else:
			son3 = self.son3.generarCodigo()
		if type(self.son3) == type(tuple()):
			son4 = self.son4[0].generarCodigo()
		else:
			son4 = self.son4.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		bufferArchivo += id + " -> " + son4 + "\n\t"
		return id

class constDecl(Nodo): # Se inicializa el caso de sintaxis constDecl
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		if type(self.son1) == type(tuple()):
			son1 = self.son1[0].generarCodigo()
		else:
			son1 = self.son1.generarCodigo()

		bufferArchivo += id +"[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id +"->"+son1+"\n\t"
		return id

class constAssignmentList1(Nodo): # Se inicializa el caso de sintaxis constAssignmentList
	def __init__(self,son1,son2,son3,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		self.son3.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()
		son3 = self.son3.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		return id

class constAssignmentList2(Nodo): # Se inicializa el caso de sintaxis constAssignmentList
	def __init__(self,son1,son2,son3,son4,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3
		self.son4 = son4

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		self.son3.postOrden(" "+ident)
		self.son4.postOrden(" "+ident)

		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()
		son3 = self.son3.generarCodigo()
		son4 = self.son4.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		bufferArchivo += id + " -> " + son4 + "\n\t"
		return id

class varDecl1(Nodo): # Se inicializa el caso de sintaxis varDecl1
	def __init__(self,son1, name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)

		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class identList1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class identList2(Nodo):
	def __init__(self,son1,son2,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		return id

class procDecl1(Nodo):
	def __init__(self,son1,son2,son3,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		if type(self.son1) == type(tuple()):
			self.son1[0].postOrden(" "+ident)
		else:
			self.son1.postOrden(" "+ident)

		self.son2.postOrden(" "+ident)
		self.son3.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()
		son3 = self.son3.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		return id

class statement1(Nodo):
	def __init__(self,son1,son2,son3,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		self.son3.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()
		son3 = self.son3.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		return id

class statement2(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class statement3(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class statement4(Nodo):
	def __init__(self,son1,son2,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		if type(self.son2) == type(tuple()):
			self.son2[0].postOrden(" "+ident)
		else:
			self.son2.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		return id

class statement5(Nodo):
	def __init__(self,son1,son2,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		if type(self.son2) == type(tuple()):
			self.son2[0].postOrden(" "+ident)
		else:
			self.son2.postOrden(" "+ident)

		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		return id

class statementList1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class statementList2(Nodo):
	def __init__(self,son1,son2,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		return id

class condition1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class condition2(Nodo):
	def __init__(self,son1,son2,son3,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		self.son3.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()
		son3 = self.son3.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		return id

class relation1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class relation2(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class relation3(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class relation4(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class relation5(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class relation6(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class expression1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class expression2(Nodo):
	def __init__(self,son1,son2,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		return id

class expression3(Nodo):
	def __init__(self,son1,son2,son3,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		self.son3.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()
		son3 = self.son3.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		return id

class addingOperator1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class addingOperator2(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class term1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class term2(Nodo):
	def __init__(self,son1,son2,son3,name): # Constructor de la clase
		self.name = name
		self.son1 = son1
		self.son2 = son2
		self.son3 = son3

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		self.son2.postOrden(" "+ident)
		self.son3.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)

	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()
		son2 = self.son2.generarCodigo()
		son3 = self.son3.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		bufferArchivo += id + " -> " + son2 + "\n\t"
		bufferArchivo += id + " -> " + son3 + "\n\t"
		return id

class multiplyingOperator1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class multiplyingOperator2(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class factor1(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class factor2(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class factor3(Nodo):
	def __init__(self,son1,name): # Constructor de la clase
		self.name = name
		self.son1 = son1

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		self.son1.postOrden(" "+ident)
		print(ident + "Nodo: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		son1 = self.son1.generarCodigo()

		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		bufferArchivo += id + " -> " + son1 + "\n\t"
		return id

class Id(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"ID: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= "+self.name+"]"+"\n\t"
		return id

class Assign(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"Assign: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class NE(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"NE: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class LT(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"LT: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class GT(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"GT: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class LTE(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"LTE: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class GTE(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"GTE: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class Plus(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"Plus: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class Minus(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"Minus: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class Times(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"Times: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class Divide(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"Divide: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class Update(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"Update: "+self.name)
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= \""+self.name+"\"]"+"\n\t"
		return id

class Number(Nodo):
	def __init__(self,name): # Constructor de la clase
		self.name = name

	def postOrden(self,ident): # Ordena el codigo de “Sketchviz” para arbol en postorden
		print(ident+"Number: "+str(self.name))
			
	def generarCodigo(self): # Genera el codigo para realizar el arbol en “Sketchviz”
		global bufferArchivo
		id = addCont()
		bufferArchivo += id + "[label= "+str(self.name)+"]"+"\n\t"
		return id