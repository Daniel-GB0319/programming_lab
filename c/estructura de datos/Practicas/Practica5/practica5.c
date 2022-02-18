#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "prueba.c"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	lista lib,list1,list2;
	info song;
	int menu,submenu,listMenu,pos,i;
	
	//Se crean las listas a utilizar
	crearlista(&lib); // Biblioteca de Musica
	crearlista(&list1); // Lista de Reproduccion 1
	crearlista(&list2); // Lista de Reproduccion 2
	
	//Se precargan datos en la lista "Biblioteca de Musica"
	strcpy(song.name,"Always On My Mind - Elvis Presley");
	add(0,song,lib); 
	strcpy(song.name,"I'm Only Sleeping - The Beatles");
	add(1,song,lib);
	strcpy(song.name,"Paranoid - Black Sabbath");
	add(2,song,lib);
	strcpy(song.name,"Havana - Camila Cabello");
	add(3,song,lib);
	strcpy(song.name,"Bangarang - Skrillex");
	add(4,song,lib);
	//Se precargan datos en la Lista de Reproduccion 1
	strcpy(song.name,"Always On My Mind - Elvis Presley");
	add(0,song,list1);
	strcpy(song.name,"Paranoid - Black Sabbath");
	add(1,song,list1);
	//Se precargan datos en la Lista de Reproduccion 2
	strcpy(song.name,"I'm Only Sleeping - The Beatles");
	add(0,song,list2);
	strcpy(song.name,"Bangarang - Skrillex");
	add(1,song,list2);
	
	do{// Comienza Menu Principal del Programa
		printf("\t***************Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 5: Lista de Reproduccion***************\n\n");
		printf ("**********************MENU PRINCIPAL*************************\n\n"); 
		printf("!!!Bienvenido a Escomtunes!!!\n\n");
		printf("1) Mostrar Biblioteca de Musica\n2) Listas de Reproduccion\n0) Salir del Programa\n\n");	
		printf("Ingrese el NUMERO de la opcion deseada y presione ENTER: ");
		scanf("%i",&menu);
	
		switch (menu){ // Operaciones para el MENU PRINCIPAL
			case 1: // 1) Mostrar Biblioteca
				printf ("\n***Biblioteca de Musica Disponible***\n");    
				for(i=0;i<lib->NE;i++)
	  				printf("Cancion #%d: %s\n",i,get(i,lib).name); // Se imprimen los elementos de la lista "Biblioteca"
				system("pause"); system("cls");
			break;
			case 2: // 2) Listas de Reproduccion
				system("cls");
				do{ // Inicia Submenu Listas de Reproduccion
					printf("\t***************Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 5: Lista de Reproduccion***************\n\n");
					printf ("**********************SUBMENU LISTAS DE REPRODUCCION*************************\n\n"); 
					printf("1) Elegir Lista de Reproduccion\n0) Regresar al Menu Principal\n\n");	
					printf("Ingrese el NUMERO de la opcion deseada y presione ENTER: ");
					scanf("%i",&submenu);
					
					switch(submenu){
						case 1: // 2.1) Elegir Lista de Reproduccion
								printf("\n***LISTAS DE REPRODUCCION DISPONIBLES:***\n\n");
								printf("Lista #1 \"Para Dormir\"\n");
								printf("Lista #2 \"Para los quehaceres\"\n\n");
								printf("Ingrese el NUMERO de la lISTA deseada y presione ENTER: \n");
								scanf("%i",&i);
								system ("cls");
								
								switch(i){ // INICIO SWITCH OPERACIONES DE LISTA
									case 1: // Se escoje Lista 1
										do{ // Se repetira hasta seleccionar opcion REGRESAR
											printf("\n***Lista #1 \"Para Dormir\"***\n");
											for(i=0;i<list1->NE;i++)
		  										printf("Cancion #%d: %s\n",i,get(i,list1).name); // Se imprimen los elementos de la lista 1
											printf("\nOPERACIONES A REALIZAR EN LISTA:\n1) Agregar una Cancion\n2) Borrar una Cancion\n0) Regresar\n\n"); // Opciones 	
											printf("Ingrese el NUMERO de la operacion deseada y presione ENTER: ");
											scanf("%i",&listMenu);
									
											switch(listMenu){
											case 1: // 2.1.1) Agregar Cancion
												printf ("\n***Biblioteca de Musica Disponible***\n");    
												for(i=0;i<lib->NE;i++)
		  											printf("Cancion #%d: %s\n",i,get(i,lib).name); // Se imprimen los elementos de la lista "Biblioteca"
		  										printf("Ingrese el NUMERO de la cancion que desea agregar y presione ENTER: ");
												scanf("%i",&i);
												if (i>=0 && i<=4){
													if(i==0)
														strcpy(song.name,"Always On My Mind - Elvis Presley");
													if(i==1) 
														strcpy(song.name,"I'm Only Sleeping - The Beatles");
													if(i==2)
														strcpy(song.name,"Paranoid - Black Sabbath");
													if(i==3)
														strcpy(song.name,"Havana - Camila Cabello");
													if(i==4)
														strcpy(song.name,"Bangarang - Skrillex");
													printf("\nIngrese el NUMERO de la POSICION donde se desea agregar la cancion y presione ENTER: ");
													scanf("%i",&pos); //Se ingresa la posicion
													
													if (pos>=0){
														add (pos,song,list1); // Se agrega cancion a lista 1
														system("pause"); system("cls");
													}else{
														printf("\n!!!Ha insertado una rango de Posicion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");  
													}	
												} else{
													printf("\n!!!Ha insertado una opcion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");
												}
											break;
											case 2: // 2.1.2) Borrar una Cancion
												if (!empty(list1)){ // Se comprueba si no esta vacia
														printf("\nSeleccione la POSICION de la cancion que desea Borrar\n");
														scanf("%i",&pos);//Se ingresa la posicion
														if (pos>=0 && pos<list1->NE){
															borrar (pos,list1); // Se borra cancion de lista 1
														} else {
															printf("\nPOSICION fuera de Rango\n");
														}												
														system("pause"); system("cls");
												}else{
													printf("\n!!!La lista esta vacia!!!\n"); system("pause"); system("cls");
												}
											break;
											case 0: system("cls"); break; // 2.1.3) Regresar
											default: printf("\n!!!Ha insertado una opcion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");
											}
										}while(listMenu!=0);
									break;
									case 2: // Se escoge Lista 2
											do{ // Se repetira hasta seleccionar opcion REGRESAR
											printf("\n***Lista #2 \"Para los quehaceres\"***\n");
											for(i=0;i<list2->NE;i++)
		  										printf("Cancion #%d: %s\n",i,get(i,list2).name); // Se imprimen los elementos de la lista 2
											printf("\nOPERACIONES A REALIZAR EN LISTA:\n1) Agregar una Cancion\n2) Borrar una Cancion\n0) Regresar\n\n"); // Opciones 	
											printf("Ingrese el NUMERO de la operacion deseada y presione ENTER: ");
											scanf("%i",&listMenu);
									
											switch(listMenu){
											case 1: // 2.1.1) Agregar Cancion
												printf ("\n***Biblioteca de Musica Disponible***\n");    
												for(i=0;i<lib->NE;i++)
		  											printf("Cancion #%d: %s\n",i,get(i,lib).name); // Se imprimen los elementos de la lista "Biblioteca"
		  										printf("Ingrese el NUMERO de la cancion que desea agregar y presione ENTER: ");
												scanf("%i",&i);
												if (i>=0 && i<=4){
													if(i==0)
														strcpy(song.name,"Always On My Mind - Elvis Presley");
													if(i==1) 
														strcpy(song.name,"I'm Only Sleeping - The Beatles");
													if(i==2)
														strcpy(song.name,"Paranoid - Black Sabbath");
													if(i==3)
														strcpy(song.name,"Havana - Camila Cabello");
													if(i==4)
														strcpy(song.name,"Bangarang - Skrillex");
													printf("\nIngrese el NUMERO de la POSICION donde se desea agregar la cancion y presione ENTER: ");
													scanf("%i",&pos); //Se ingresa la posicion
													
													if (pos>=0){
														add (pos,song,list2); // Se agrega cancion a lista 2
														system("pause"); system("cls");
													}else{
														printf("\n!!!Ha insertado una rango de Posicion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");  
													}	
												} else{
													printf("\n!!!Ha insertado una opcion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");
												}
											break;
											case 2: // 2.1.2) Borrar una Cancion
												if (!empty(list2)){ // Se comprueba si no esta vacia
														printf("\nSeleccione la POSICION de la cancion que desea Borrar\n");
														scanf("%i",&pos);//Se ingresa la posicion
														if (pos>=0 && pos<list1->NE){
															borrar (pos,list2); // Se borra cancion de lista 2
														} else {
															printf("\nPOSICION fuera de Rango\n");
														}												
														system("pause"); system("cls");
												}else{
													printf("\n!!!La lista esta vacia!!!\n"); system("pause"); system("cls");
												}
											break;
											case 0: system("cls"); break; // 2.1.3) Regresar
											default: printf("\n!!!Ha insertado una opcion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");
											}
										}while(listMenu!=0);
									break;
									default: printf("\n!!!Ha insertado una opcion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");  	
								}// FIN SWITCH OPERACIONES DE LISTA		
						break;
						case 0: system("cls"); break; //2.2) Regresar al Menu Principal
						default: printf("\n!!!Ha insertado una opcion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");
					}	
				}while(submenu!=0); system("cls");
			break;
			case 0: // Mensaje al salir del programa
				printf("\n!!!Hasta la proxima!!!");
			break;
			default: printf("\n!!!Ha insertado una opcion invalida!!! Intente de nuevo\n"); system("pause"); system("cls");	
		}
	}while(menu!=0); 
	vaciarlista(list1);
	vaciarlista(list2);
	vaciarlista(lib);
	return 0;
}
