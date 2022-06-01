#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include "pila.c"

void analizador(pila entrada,Info aux){
	pila pilaLL; // Sirve para trabajar con los no terminales
	//char salida[10]; // Sirve para indicar la salida que se obtiene en cada iteracion
	char coincidencia[1][5]; // Sirve para mostrar la coincidencia
	
	crearpila(&pilaLL);
	
	strcpy(aux.elem, "$");
	push(&entrada, aux);
	
	strcpy(aux.elem, "E");
	push(&entrada, aux);
	
	
	/*
	#E = ["Ec", "T"]
	#Ec = ["Ec", "T", "+"]
	#T = ["Tc", "F"]
	#Tc = ["Tc", "F", "*"]
	#F = [")" , "E" , "("]
	*/


	while(1){
		strcpy( coincidencia[0], top(pilaLL).elem );
		strcpy( coincidencia[1], top(entrada).elem );
		
		// FALTA ARREGLAR EL FUNCIONAMIENTO DEL ANALIZADOR SEGUN LA TABLA DE LA PRACTICA 3 COMPILADORES
		
		if( ( strcmp(coincidencia[0],"$") == 0 ) && ( strcmp(coincidencia[1],"$") == 0 ) ){
			printf("!!! La cadena ha sido aceptada !!!\n\n");	
			break;
		}else{
			
			
			if( strcmp(coincidencia[0], coincidencia[1]) == 0 ){
				pop(&pilaLL);
				pop(&entrada);
			}else{
			
				
			}
				

			if(coincidencia[0] == "E" and ( (coincidencia[1] == "id") or (coincidencia[1] == "(") ) ):
				pila.pop()
				pila.extend(["Ec", "T"])
				salida = "E -> TE'"

			if(coincidencia[0] == "Ec"):
				if(coincidencia[1] == "+"):
					pila.pop()
					pila.extend(["Ec", "T", "+"]) 
					salida = "E' -> +TE'"
				else:
					pila.pop()
					pila.append("epsilon")
					salida = "E' -> epsilon"

			if(coincidencia[0] == "T" and ( (coincidencia[1] == "id") or (coincidencia[1] == "(") )):
				pila.pop()
				pila.extend(["Tc", "F"])
				salida = "T -> FT'"

			if(coincidencia[0] == "Tc"):
				if(coincidencia[1] == "*"):
					pila.pop()
					pila.extend(["Tc", "F", "*"]) 
					salida = "T' -> *FT'"
				else:
					pila.pop()
					pila.append("epsilon")
					salida = "T' -> epsilon"

			if(coincidencia[0] == "F"):
				if(coincidencia[1] == "id"):
					pila.pop()
					pila.append("id") 
					salida = "F -> id"
				else:
					pila.pop()
					pila.extend([")" , "E" , "("])
					salida = "F -> (E)"

			
			
			
		} // else
			
	} // while
		
		
			
	
	
	
	
	vaciarpila(&pilaLL);
}

// ##### PROGRAMA PRINCIPAL #####
int main(int argc, char **argv){
	pila entrada;
	Info aux;
	char menu;
	char cadena[20];
	int i = 0;
	
	crearpila(&entrada);
	
	strcpy(aux.elem, "$");
	push(&entrada, aux);
	
	while(1){
		system("clear");
		
		printf("***** Practica 3 - Analizador Sintactico LL(1) *****\n");
		printf("INSTRUCCIONES: Este programa evaluara una cadena ingresada por el usuario por medio de un analizador sintactico LL(1).\n");
		printf("Escriba la cadena para analizar: ");
		scanf("%s",cadena);
		
		for(i=20; i>0; i--){
			if(cadena[i] != "\0"){ // Verifica que no sea un espacio en blanco
				
				if(cadena[i] == "d" && cadena[i-1] == "i"){ // verifica si se ingresa elemento id
					strcpy(aux.elem, "id");
					push(&entrada, aux);
				}else{ // se ingresa otro elemento
					strcpy(aux.elem, cadena[i]);
					push(&entrada, aux);
				} // else
				
			} // if	
		} // for
		
		analizador(entrada, aux);

		printf("\nDesea ingresar otra cadena al analizador sintactico? (S / N): ");
		scanf("%c",&menu);
		
		if ( (menu == "N") || (menu == "n") ){
			print("\n!!! Gracias por utilizar el programa !!!\n")
			break;
		} // if
	} // while
		
	vaciarpila(&entrada);
	return 0;
} // main
