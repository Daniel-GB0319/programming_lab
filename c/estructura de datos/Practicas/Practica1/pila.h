
typedef struct ele_inf{
   	int line;
   	int row;
   	int llave;
   	  /*Aqui va la informacion que el programa almacenara en la pila*/
}Info;

typedef struct nodo {
	struct nodo * sig;
	Info E;
} Nodo;

// Seccion de Alias

typedef struct nodo * pila;
typedef enum b { FALSE, TRUE } booleano;
typedef enum m { OK, NOMEMORY} mensaje; 

// Prototipos de las operaciones

  Info  pop ( pila *  P );
  mensaje push ( pila * P , Info I );
  booleano  empty ( pila P );
  Info  top ( pila P );
  void crearpila ( pila * P );
  void vaciarpila ( pila * P );   
  
  

























