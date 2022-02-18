#include <stdio.h>
#include <stdlib.h>
#include "pila.c"
#include <windows.h>

int main(int argc, char *argv[]) {
	int opcion, row=0, line=1;
	FILE *arch;
	char c,code[3];
	pila hand;
	Info llaves;

	do{ //El programa se ejecutara hasta que el usuario decida cerrarlo
		
		do{
		printf("\t\t\t*****Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 1: Mini Compilador*****\n\n\n");
		printf ("*************************Menu****************************\n\n");    //Inicia menu para seleccionar archivo *.c para trabajar
		printf("1) Abrir archivo \"1.c\"\n");   
		printf("2) Abrir archivo \"2.c\"\n");
		printf("3) Abrir archivo \"3.c\"\n");
		printf("4) Abrir archivo \"4.c\"\n");
		printf("0) Salir del programa\n\n");	
		printf("Ingrese el NUMERO de la opcion deseada y presione ENTER: ");
		scanf("%i",&opcion);
		
		switch(opcion){	//Se selecciona el archivo que se abrira
			case 1:
				strcpy(code,"1.c");
			break;
			case 2:
				strcpy(code,"2.c");
			break;
			case 3:
				strcpy(code,"3.c");
			break;
			case 4:
				strcpy(code,"4.c");
			break;
			case 0:
				printf("!!!Hasta la proxima!!!");
				exit(0);
			break;
			default:
				printf("Ha insertado una opcion invalida. Presione ENTER para intentar de nuevo ");
				getch();
				system("cls");
		}
		}while(opcion>4);
		
		arch=fopen(code,"r"); //Se comienza a trabajar con el archivo
		if (arch==NULL){//Se comprueba que no existan errores de lectura
			printf("NO SE HA PODIDO ABRIR EL ARCHIVO. Asegurese de que el archivo elegido se encuentra en la misma direccion que \"practica1.c\" e intente de nuevo");
			exit(4);
		}else{  //Se empieza a leer los caracteres del archivo
			crearpila(&hand);
			do{ //Aqui el bucle sigue hasta que se llegue al final de archivo
				c=fgetc(arch);
				row++;	
				if (c=='{'){
					llaves.line=line;
					llaves.row=row;
					llaves.llave=123;
					push (&hand , llaves );
				} 
	
				if (c=='}'){
					if (top(hand).llave==123){
							pop (&hand);	
							}else{
								if (top(hand).llave==40){
								printf ("Error #2 : Falta { antes de linea %i y columna %i",line,row);
								exit(2);
								}else{
									llaves=pop (&hand);
									printf ("Error #3 : } No es del mismo tipo que %c ubicada en fila %i y columna %i\n. Cierre antes %c",llaves.llave,llaves.line,llaves.row,llaves.llave);
									exit(3);					
								}			
							}
				}	
	
				if (c=='['){
					llaves.line=line;
					llaves.row=row;
					llaves.llave=91;
					push (&hand , llaves );
				}
				
				if (c==']'){
					if (top(hand).llave==91){
						pop (&hand);
					}
					else{
						llaves=pop (&hand);
						printf ("Error #3 : ] No es del mismo tipo que %c ubicada en fila %i y columna %i\n. Cierre antes %c",llaves.llave,llaves.line,llaves.row,llaves.llave);
						exit(3);
					}
				}
				
				if (c=='('){
				llaves.line=line;
				llaves.row=row;
				llaves.llave=40;
				push (&hand , llaves );
				}
				if (c==')'){
				if (top(hand).llave==40){
				}
				else{
				llaves=pop (&hand);
				printf ("Error #3 : ) No es del mismo tipo que %c ubicada en fila %i y columna %i\n. Cierre antes %c",llaves.llave,llaves.line,llaves.row,llaves.llave);
				exit(3);
				}
				}
				if (c=='\n'){ line++; row=0; }
			}while(c!=EOF);		
			fclose(arch);			
				if (top(hand).llave==123){
					llaves=pop (&hand);
					printf ("Error #1 : Falta } en linea %i y columna %i",line,row);
					exit(1);	
				}else{
					printf ("!!!Llaves estan balanceadas!!! \n");
				}
			printf ("\n\n¿Desea continuar con el programa?\n");
			printf("1) SI\n");
			printf("0) NO\n\n");
			printf("Ingrese el NUMERO de la opcion deseada y presione ENTER ");
			scanf("%i",&opcion);
			row=0;
			line=1;
			vaciarpila(&hand);
			free (hand);
			system ("cls");
		}
	}while(opcion!=0);
	printf("\n\n!!!Hasta la proxima!!!");
	return 0;
}

