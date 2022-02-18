#include <stdio.h>   
#include <stdlib.h>
#include <time.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int main(int argc, char *argv[]) {
	srand(time(NULL)); 	 /* Se toma la fecha actual de la computadora para generar una raiz aleatoria*/
	int n=15,i;
	int *arreglo;
	
	printf("\t***Gonzalez Barrientos Geovanni Daniel \t1CV3 \tEstructuras de Datos***\n\n");
	 /* Se crea el arreglo dinamico*/
	arreglo= (int*) malloc(n*sizeof(int));
	
	/*Se verifica que exista espacio disponible en memoria para el arreglo*/
	if (arreglo==NULL) {
		printf("***No se ha podido reservar memoria suficiente***");
	}
	else{
		for (i=0;i<n;i++){
			/* Se le asignan valores aleatorios al arreglo dinamico*/
			*(arreglo + i)=rand()%101;
			printf("Valor %i es igual a %i\n",i+1,*(arreglo + i));
		}
	}
	 /*Se imprimen los valores contenidos en los lugares 5,10 y 11 del arreglo*/ 
	printf("*********************************************************************\n");
	printf("Valor 5 es igual a %i\n",*(arreglo + 4));
	printf("Valor 5 se localiza en %p\n\n",*(arreglo + 4));
			
	printf("Valor 10 es igual a %i\n",*(arreglo + 9));
	printf("Valor 10 se localiza en %p\n\n",*(arreglo + 9));
			
	printf("Valor 11 es igual a %i\n",*(arreglo + 10));
	printf("Valor 11 se localiza en %p\n\n",*(arreglo + 10));
	
	free(arreglo);
	return 0;
};
