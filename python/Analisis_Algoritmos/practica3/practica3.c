#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>


void imprimirMatriz(int matriz);
int movJ(int matriz,int x,int y,int J);
	

int main(int argc, char **argv){
	int matriz[7][7];
	int J = 0, K = 0, L = 0, M = 0, x = 0, y = 0;
	
	// Se inicializa matriz
	for (y = 0; y < 8 ; y++){
		for(x = 0; x < 8 ; x++){
			matriz[x][y] = 0;
		}
	}

	// Aqui inicia el programa
	system("clear"); // Limpia pantalla 
	printf("\n******* Gonzalez Barrientos Geovanni Daniel 3CV14  Analisis de Algoritmos *******\n");
	printf("------- Practica 3 - Rotacion de Matriz -------\n\n");
	printf("INSTRUCCIONES: Este programa rotara una matriz de forma automatica\n\n");
	
	imprimirMatriz(matriz);
	
	printf("\nPresione ENTER para comenzar a rotar la imagen... ");
	getchar();
	getchar();
	system("clear"); // Limpia pantalla

	matriz = movJ(matriz,x,y,J);
	x = 1;
	y = 1;

	  
	printf("!!! Matriz ha sido rotada !!!\n");
	printf("\n!!! Gracias por utilizar el programa !!! Hasta luego ;)\n");
	
	return 0;
}


void imprimirMatriz(int matriz){
	int i = 0, j = 0;
	
	for (j = 0; j < 8 ; j++){
		for(i = 0; i < 8 ; i++){
			printf("%d",matriz[i][j]);
		}
	}
		
	sleep(3);	
}

int movJ(int matriz,int x,int y,int J);
	if (J == 1){ // Caso Base
		return matriz;  
	  
	}else{ // Caso Recursivo para Movimientos
		if( (y == 0) && (x < 7)){
			matriz[x+1][y] = matriz[x][y];
			imprimirMatriz(matriz);
			system("clear"); // Limpia pantalla
			matriz = movJ(matriz,x+1,y,J);
		
		}else if ( (x == 7) && (y<7) ){
			matriz[x][y+1] = matriz[x][y];
			imprimirMatriz(matriz);
			system("clear"); // Limpia pantalla
			matriz = movJ(matriz,x,y+1,J);
		
		}else if ( (y == 7) && (x>0) ){
			matriz[x-1][y] = matriz[x][y];
			imprimirMatriz(matriz);
			system("clear"); // Limpia pantalla
			matriz = movJ(matriz,x-1,y,J);
			
		}else if ( (x==0) && (y>0) ){
			matriz[x][y-1] = matriz[x][y];
			imprimirMatriz(matriz);
			system("clear"); // Limpia pantalla
			
			if (y == 1){
				J = 1;
			}
			matriz = movJ(matriz,x-1,y,J);
		}
	}
	return matriz;
}
