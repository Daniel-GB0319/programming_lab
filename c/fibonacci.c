#include <stdio.h>
#include <stdlib.h>
#include <time.h>

long fibonacci_iterativo (unsigned long n);
long fibonacci_recursivo( unsigned long p);

int main(){
    int n;
    scanf("%d",&n);

    double time_spent = 0.0;
 
    clock_t begin = clock();
    fibonacci_iterativo(n);
    clock_t end = clock();
    time_spent += (double)(end - begin) / CLOCKS_PER_SEC;
    
    printf("Tiempo iterativo: %f segundos\n", time_spent);

    double time_spent2 = 0.0;
    clock_t begin2 = clock();
    fibonacci_recursivo(n);
    clock_t end2 = clock();
 
    time_spent2 += (double)(end2 - begin2) / CLOCKS_PER_SEC;
 
    printf("Tiempo recursivo: %f segundos\n", time_spent2);  
}

long fibonacci_recursivo( unsigned long p){
	 //Casos Base
	  if (p < 2)
	         return p;
	  // Parte recursiva
	  return fibonacci_recursivo(p-1)+fibonacci_recursivo(p-2);  	
}

long fibonacci_iterativo (unsigned long n){
    int i = 0;
    int j = 1;

    for (int k = 1; k < n; k++){	
        int t;
        t = i + j;
        i = j;
        j = t;
    }
	return j;	
};

