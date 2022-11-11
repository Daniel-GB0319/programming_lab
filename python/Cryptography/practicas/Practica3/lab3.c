#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <locale.h>


// Genera una cadena de permutacion 
void generarCadena(int *buffer){
    int numAux = 0, verificacion = 0, i=0;

    do{ // Ingresa numeros del 1-8 aleatoriamente
        numAux = rand()% 9 + 1;

        for(int j=0; j<8; j++){
            if(numAux == buffer[j]){
                verificacion = 1; // Numero ya existe
            }
        } // for verificar numero existente

        if(verificacion != 1){
            buffer[i] = numAux;
            i++;
        }// FOR
         verificacion = 0;
    }while(i == 8); // for generar la cadena
} // generarCadena


// Permuta los bits del caractér ingresado
unsigned char permutar(unsigned char arregloBits, int cadenaPermutacion[]){
    unsigned char auxiliar = 0, salida = 0;

    for(int i = 0; i<8; i++){
        auxiliar = arregloBits & (1 << (8 - cadenaPermutacion[i]));
        salida |= cadenaPermutacion[i] > i ? auxiliar << (cadenaPermutacion[i] - i - 1) : auxiliar >> (i-cadenaPermutacion[i]+1);
    } // for
    return salida;
} // permutar

int main(){
    setlocale(LC_ALL,"");
    srand(time(NULL)); // Semilla para números pseudoaleatorios
    unsigned char arregloBits;
    int resultado;
    int cadenaPermutacion[8];

    printf("**** Lab 3: Permutations and Triple DES - Cryptography - 4/Noviembre/2022 ****");
    printf("-- Gonzalez Barrientos Geovanni Daniel / 3CM11 --\n\n");

    printf("Ingrese un caractér: ");
    arregloBits = getchar();

    printf("Valor ingresado: %c =",arregloBits);
    printf(" %i\n",arregloBits);
    generarCadena(cadenaPermutacion);

    resultado = permutar(arregloBits, cadenaPermutacion);
    printf("El resultado de la permutacion es %i\n\n", resultado);
    return 0;
}
