#include <stdio.h>
#include <stdlib.h>

typedef struct informacion {
		
	char name[40];
	// Aqui se pone la informacion que se quiera almacenar
	
} info;


typedef struct nodo {
	
    info Inf;
    struct nodo * sig;
	struct nodo * ant;
  	
} NODO;

typedef struct Cab {
	NODO * I;
	NODO * F;
	int NE;
} cabecera;


// Alias

typedef  cabecera * lista;
typedef  NODO * link;
typedef  enum men { NO_MEMORY , OK , INDEXOUTOFBOUND, EMPTY } mensaje;
typedef  enum b { FALSE , TRUE } booleano;


// Prototipos 

info get ( int pos , lista l );  

link ubicar ( int pos , lista l );                                        

mensaje add ( int pos, info E , lista l );         

mensaje borrar (  int Pos , lista l );                   

booleano empty ( lista l  );
 
mensaje set ( int Pos ,  info E , lista l);      

mensaje  crearlista( lista * l ); 

void  liberarlista( lista * l );  
	   
void vaciarlista( lista l ); 

/*
main(){
	
	int i;
	lista L;
	info Dato;
	crearlista(&L);
	
	Dato.N=0;
	add(0,Dato,L);
	Dato.N=1;
	add(1,Dato,L);
	Dato.N=2;
	add(2,Dato,L);
	Dato.N=3;
	add(3,Dato,L);
	
	for(i=0;i<L->NE;i++)
	  printf("lista[%d]=%d\n",i,get(i,L).N);
	
	Dato.N=10;
	set(0,Dato,L);
	
	printf("Se sustituyo el elemento de la posicion 0 \n");
	for(i=0;i<L->NE;i++)
	  printf("lista[%d]=%d\n",i,get(i,L).N);
	

}
*/

info get ( int pos , lista l ) {
	
	info copia;
	
	link Ite= ubicar (pos , l);
	
	if (Ite == NULL)
	    return copia;
	
	copia=Ite->Inf;
	
	return copia;   
}                                        

link ubicar ( int p , lista l ){
	int mitad,i;
	link Ite;
	if (l == NULL || empty (l))
	    return NULL;    
	if ( p == 0 )    
	    return l->I;
	if ( p == l->NE-1 )    
	    return l->F;    
	if (p>0 && p<l->NE-1)    
	    {
	       mitad=l->NE/2;
	       
	       if ( p >= mitad )
	        {
	        	Ite=l->I;
	        	
	        	for(i=0;i<p;i++)
	        	    Ite=Ite->sig;
						  
			} else {
				
				Ite=l->F;
	        	
	        	for(i=0;i<(l->NE-1)-p;i++)
	        	    Ite=Ite->ant;	    
			}
	       
	       return Ite;
		}
	return NULL;	
} 


mensaje add ( int pos, info E , lista l ){
	
	link act,ant;
	
	link temp = (link) malloc (sizeof(NODO));
	
	if (temp == NULL)
	    return NO_MEMORY; 
	
	temp->Inf=E;
		
	if ( empty (l) )
	   {
	   	  temp-> ant = temp -> sig = NULL;
		  l->I=l->F=temp;	    
	   } else {
	   	
	   	  if (pos <= 0){ // Al Inicio
	   	  	    temp->sig=l->I;
	   	  	    temp->ant=NULL;
	   	  	    l->I->ant=temp;
	   	  	    l->I=temp;
			 }else if( pos >= l->NE){ // Al final
			 	temp->sig=NULL;
	   	  	    temp->ant=l->F;
	   	  	    l->F->sig=temp;
			    l->F=temp;
			 }else{ // En medio
		
		        act= ubicar (pos , l);
				ant= act->ant;
				     	 	
			 	temp->sig=act;
			 	temp->ant=ant;
			 	act->ant=temp;
			 	ant->sig=temp;
			 	
			 }
	   }
	   
	l->NE++;
	return OK;
}        

mensaje borrar (  int p , lista l ){

    link temp,s,a,act;
    
	if (l == NULL || empty (l))
	    return EMPTY;
	    
	if ( !(p>=0 && p<l->NE) )    
		return INDEXOUTOFBOUND;

    if( p == 0 && l->NE == 1 ) // lista con un elemento
    {
    	 free(l->I);
    	 l->I=l->F=NULL;
    	 
	}else if (p == 0 && l->NE > 1){  // Al Inicio
		
		 temp=l->I->sig;
		 free(l->I);
		 temp->ant=NULL;
		 l->I=temp;
		 
	} else if ( p == l->NE-1 ){ // Al final
		
		  temp=l->F->ant;
		  free(l->F);
		  temp->sig=NULL;
		  l->F=temp;
		
	}else {  //En medio
		
		act= ubicar (p , l);
		a=act->ant;
		s=act->sig;
		free(act);
		a->sig=s;
		s->ant=a;
	}
      
    l->NE--;
	return OK;   

}                

booleano empty ( lista l  ){
	
    if ( l != NULL && l->I == NULL && l->F == NULL && l->NE == 0 )	
	    return TRUE;
	return FALSE;
}

mensaje set ( int p , info E , lista l){
	link Ite;
	if (l == NULL || empty (l))
	    return EMPTY;
	    
	if ( !(p>=0 && p<l->NE) )    
		return INDEXOUTOFBOUND;
		
	Ite=ubicar (p , l);	
		  
	Ite->Inf=E;
	
	return OK;
}      

mensaje crearlista( lista * l ){
	
	lista temp= (lista) malloc (sizeof(cabecera));
	
	if ( temp == NULL)
	    return NO_MEMORY;
	
	temp->I=temp->F=NULL;
	temp->NE=0;
	
	*l=temp;
	
	return OK;
}

void  liberarlista( lista * l ){
	if ( *l != NULL ){
	
	  vaciarlista ( *l  );
	  
	  free(*l);
	  
	  *l=NULL;
    }
}  

void  vaciarlista ( lista l ){
	
	   while (!empty (l)){
	   	
	   	     borrar(0,l);
	   	
	   }
	
}   


