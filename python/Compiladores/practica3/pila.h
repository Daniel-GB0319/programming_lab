
typedef struct ele_inf { // Estructura del elemento que se almacenara en la pila
  char elem[3]; 	
} Info;

typedef struct nodo { // Estrutura del nodo que conectara los elementos de la pila
	struct ele_inf E;
	struct nodo * sig;
} Nodo;

// Seccion de Alias
typedef struct nodo * pila;
typedef enum b { FALSE, TRUE } booleano;
typedef enum m { OK, NOMEMORY} mensaje; 

// Prototipos de las operaciones
Info  pop ( pila *  P ); // Saca un elemento de la pila
mensaje push ( pila * P , Info I ); // Ingresa un elemento en la pila
booleano  empty ( pila P ); // Verifica si la pila esta vacia
Info  top ( pila P );	// Obtiene la informacion del elemento del tope
void crearpila ( pila * P ); // Inicializa una pila
void vaciarpila ( pila * P ); // Elimina una pila  

  

























