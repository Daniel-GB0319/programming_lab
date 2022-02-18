#include <stdlib.h>
#include "pila.h"

mensaje push( pila * P , Info I ){
   
   pila temp=NULL; 

   temp=(pila) malloc (sizeof(Nodo)); // paso 1
		 
	  if(temp == NULL)
	       return NOMEMORY;
		   
    temp->E=I; // paso 1
	   
   if (empty(*P)){
   	   	  
	  temp->sig=NULL; // paso 2
	  
   }else{
	 		 	
	  temp->sig=*P;  // paso 2	
	 	 	
	 }

     *P = temp; // paso 3
     
     return OK;
}

Info pop ( pila * p ){
	
	// paso 1
	pila aux;
	Info copia;
	copia=(*p)->E; // paso 1
	aux=(*p)->sig; // paso 2 
	free(*p); // paso 3 
	*p=aux; // paso 4 
	
	return copia; 
}

Info top ( pila p ){

	return p->E;

}

booleano empty (pila p){
	if( p == NULL )
	    return TRUE;
	return FALSE;      
}

void vaciarpila( pila * p ){
	   while ( !empty( *p ) ) {
	   	        pop( p );
	   }	
}

void crearpila ( pila * p  ){
	   *p = NULL;
}



