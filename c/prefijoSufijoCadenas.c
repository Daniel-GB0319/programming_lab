#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#define TAM 100

char * invertir (char cadena[]);
void prefSuf(char cadena[]);

int main(int argc, char *argv[]){
	char *cad;
	int menu,i;

    do{ // Comienza programa iterativo hasta que usuario decida salir
        system("cls");
        printf("\t*************** Equipo 4  2CM11  Practica 2: Prefijo y Sufijo de una Cadena ***************\n\n");
		printf ("Integrantes:\n");
		printf ("Gonzalez Barrientos Geovanni Daniel:\n");
		printf ("Lopez Ramirez Oscar Ivan\n");
        printf ("\n\n********************** MENU PRINCIPAL *************************\n\n"); 
        printf("1) Calcular Prefijo y sufijo\n");
        printf("0) Salir del Programa\n\n");	
        printf("Ingrese el NUMERO de la opcion deseada y presione ENTER: ");
        scanf("%d",&menu);
        
        switch(menu){ 
            case 1: // Comienza operacion de sufijo y prefijo
                printf ("\n*** Prefijo y Sufijo de una Cadena ***\n");  
                i=0;  
                printf("\nIngrese el numero de caracteres que tendra su cadena:\n");
                scanf("%d",&i);
                i=i+1; //se agregan espacio extra para "\0" 
                cad=(char*) malloc(i*sizeof(char));
                printf("\nIngrese la cadena a utilizar (MAX. %d caracteres y SIN ESPACIOS):\n",i-1);
	            scanf("%s",cad);
                prefSuf(cad);
                free(cad);
                system("pause");
            break;
            
            case 0: // Se escoje opcion salida
                printf("\n!!!Hasta la proxima!!!\n");
                system("pause"); 
                system("cls");
            break;
            
            default: // se ingresa una opcion invalida
                printf("\n!!!Ha ingresado una opcion invalida!!! Intente de nuevo\n"); 
                system("pause"); 
        }
    } while (menu!=0);
    free(cad);
	return 0;
}

// invierte la cadena para los sufijos
char * invertir (char cadena[]){
	char *start = cadena;
	char *end = cadena;
	char aux;

	for(;*end!='\0';end++);
		end--;
		for(;start<=end;start++){
			aux=*start;
			*start=*end;
			*end=aux;
			end--;
		}
	return cadena;
}

// calcula el sufijo y prefijo
void prefSuf(char cadena[]){
	char *p1 = cadena;
	char *p2 = cadena;
	
	printf("\nPREFIJOS: \n\n");
	printf("E\n");
	for(;*p1!='\0';p1++){
		for(;p2<=p1;p2++){
			printf("%c",*p2);
		}
		p2 = cadena;
		printf("\n");
	}
	
	invertir(cadena);
	p1 = cadena;
	printf("\nSUFIJOS: \n\n");
	printf("E \n");
	for(;*p1!='\0';p1++){
		for(p2 = p1;*p2!='\0';p2--){
			printf("%c",*p2);
		}
		printf("\n");
	}
    free(p1);
    free(p2);
}
