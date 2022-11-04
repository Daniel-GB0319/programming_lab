#include <stdio.h>
#include <stdlib.h>
// UTILIZAR PUNTEROS PARA EL ARREGLO PERMUTACION

void generarPermutacion(int *permutacion){
    printf("Cadena para permutacion generada: ");
    // generar bits del 1 al 8 y almacenarlos en el unsigned char
        for(int i = 0; i < 8; i++){
            *permutacion[i] = rand()%9 +1;
        // FALTA ASEGURAR QUE NUMEROS DEL 1 AL 8 NO SE REPITAN EN EL ARREGLO

        printf("%i", *permutacion[i]);
        } // for
} // generar permutacion 

unsigned char permutar(unsigned char bloque, int *permutacion[8]){
    unsigned char aux;



    return bloque;
} // permutacion

int main(){
    srand(time(NULL));
    unsigned char bloque, resultado;
    int *permutacion[8];
 
    printf("Ingresa un caracter: ");
    scanf("%c",*bloque);

    printf("Representacion de %c en bits: 0x%X", bloque, bloque);

    generarPermutacion();

    resultado = permutar(bloque, *permutacion);
    printf("Resultado de la permutacion en bits: 0x%X", resultado);
    
    return 0;
} // main