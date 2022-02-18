#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pila.c"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	FILE *dir;
	pila next,back;
	Info aux;
	char urlActual[30];
	int i,menu,numdir=0;
	
	dir=fopen("historial.txt","r");
	if(dir==NULL){ // Se comprueba la apertura del fichero "historial.txt"
		printf("Error al abrir archivo.");
		exit(1);
	}else{
	//Se crean las pilas para cada boton
	crearpila(&next); //Boton Siguiente
	crearpila(&back); //Boton Anterior
	
	//se obtienen las url del archivo historial.txt
	do{
		fgets (urlActual,30,dir);
		strcpy(aux.url,urlActual);
		push(&back,aux); //se guardan las url en la pila back
		numdir++;
	}while(numdir!=10);
	aux=pop(&back); //se saca valor de pila back
	strcpy(urlActual,aux.url);
	
	do{ //Comienza programa con el menu principal y se repetira hasta que usuario seleccione "Salir"
		printf ("Estructuras de Datos   1CV3  ***Examen Equipo #1***\n");
		printf ("Integrantes:\n*Espinosa Perdomo Jose Andre\n*Gonzalez Barrientos Geovanni Daniel\n*Torres Martinez Brayam Jeovanny\n\n");
		printf ("*************************MENU DEL NAVEGADOR****************************\n\n");
		printf("URL ACTUAL: %s\n",urlActual);
		printf("1) URL Anterior\n"); //Utilizacion de pila Back   
		printf("2) URL Siguiente\n"); //Utilizacion de pila Next
		printf("3) Ingresar URL\n"); //Lectura de lineas del fichero "historial.txt"
		printf("0) Salir del programa\n\n"); // Finalizar Programa	
		printf("Ingrese el NUMERO de la opcion deseada y presione ENTER: ");
		scanf("%i",&menu);
		
		switch(menu){
			case 1: //URL Anterior
				if (!empty(back) ){ //verifica si pila back no esta vacia
					strcpy(aux.url,urlActual); 
					push(&next,aux);//se guarda url actual en pila next
					aux=pop(&back); //se saca valor de pila back y se guarda en aux
					strcpy(urlActual,aux.url); //se asigna a urlActual el valor sacado para mostrar en pantalla 
					system ("cls"); 
				} else {//Procedimiento en caso de estar vacia la pila back
					printf("No hay direcciones url almacenadas en el historial\n");
					system ("pause");
					system ("cls");
				}
			break;
			case 2: // URL Siguiente
				if (!empty(next) ){ //verifica si pila back no esta vacia
					strcpy(aux.url,urlActual);
					push(&back,aux); //Se guarda url actual en back
					aux=pop(&next); //se saca valor de pila next
					strcpy(urlActual,aux.url); //se asigna a urlActual el valor sacado de next para mostrar en pantalla
					system ("cls"); 
				} else {
					printf("No hay mas direcciones url disponibles para opcion Siguiente\n");
					system ("pause");
					system ("cls");
				}
			break;
			case 3: //Se ingresa una nueva URL
					strcpy(aux.url,urlActual);
					push(&back,aux); //se mete url actual a pila back
					printf("Ingrese la direccion URL (MAX. 30 Caracteres) y presione ENTER: ");  //Se ingresa nueva URL
					scanf("%s",&urlActual);
					vaciarpila(&next);
					system ("cls");
			break;
			case 0:
				printf("\n!!Hasta la Proximaaaa!!\n");
				exit(0);
			break;
			default:
				printf("\n!!Opcion invalida!! Intente de nuevo\n");
				system ("pause");
				system ("cls");
			}
	}while(menu!=0);//Termina programa
	
	}
	
	return 0;
}
