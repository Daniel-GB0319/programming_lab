
typedef struct ele_inf {
  int elem/*,prec*/; 	
} Info;

typedef struct nodo {
	
	struct ele_inf E;
	
	struct nodo * sig;
	
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
  
  

























