#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main (){
	char cadena[20],*alfabeto,**resultado;
	int potencia, numAlpha, total, i, j;

// Se ingresa cuantas cadenas tiene el alfabeto
	printf("Ingrese el numero de Cadenas que tendra el alfabeto: ");
	scanf("%d",&numAlpha);

// Se crea el arreglo dinamico del alfabeto con el numero de cadenas ingresada
	alfabeto=(char*) malloc (numAlpha*sizeof(char));
	if (alfabeto==NULL){ // No hay espacio Suficiente
		printf("Espacio Insuficiente en Memoria");
		exit(1);
	}

// Se ingresan los valores de cada cadena del alfabeto
	for (i=0;i<numAlpha;i++){
		printf("Ingrese la cadena #%d del Alfabeto: ",i);
		scanf ("%s",cadena);
		strcpy((alfabeto+i),cadena);
	}

// Se ingresa la potencia 
	printf("Ahora Ingrese la Potencia para el Alfabeto: ");
	scanf("%d",&potencia);

// Se evalua el procedimiento a realizar segun la potencia ingresada
	if(potencia==0){ // Solo se imprime el conjunto vacio
		printf("{ E }");
	}else {
		if (potencia>0){ // Se verifica que potencia se un numero positivo
			if (potencia==1){ // Solo se imprimen las cadenas del alfabeto 
				printf("{ ");
				for (i=0;i<numAlpha;i++){
					printf("%s ",*(alfabeto + i));
				}
				printf("}");
			}else { // Se imprimen las combinaciones del alfabeto para la potencia ingresada
				total=pow(numAlpha,potencia);
				resultado=(char**) malloc (total*sizeof(cadena));
			}
		}		
	}
	
free(alfabeto);
free(resultado);
return 0;
}

