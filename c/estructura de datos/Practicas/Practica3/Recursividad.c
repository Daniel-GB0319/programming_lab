#include <stdio.h>
#include <stdlib.h>
#include <math.h>
/* run this program using the console pauser or add your own getch, system("pause") or input loop */
long factorial ( int num );
long fib( unsigned long num);
long fibo( unsigned long num,long *A);
long fibm( unsigned long num);
void hanoi( unsigned int num, char O , char A , char D );
void decimalBin(int num);
unsigned int binarioDec(char *s,int c,int p);

int main(int argc, char *argv[]) {
int menu,num,longitud;
char s[9];
do{
	printf("\t\t***************Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 3: Recursividad***************\n\n");
		printf ("*************************MENU DE OPERACIONES****************************\n\n");    //Inicia menu para seleccionar operacion
		printf("1) Factorial\n");   
		printf("2) Fibonacci\n");
		printf("3) Fibonacci con Arreglo\n");
		printf("4) Torres de Hanoi\n");
		printf("5) Convertir Decimal a Binario\n");
		printf("6) Convertir Binario a Decimal\n");
		printf("0) Salir del programa\n\n");	
		printf("Ingrese el NUMERO de la opcion deseada y presione ENTER: ");
		scanf("%i",&menu);
		
		switch(menu){	//Se selecciona la operacion a realizar
			case 1: 
				system("cls");
				printf("\t\t\t**********Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 3: Recursividad**********\n\n");
				printf ("*************************FACTORIAL****************************\n\n");
				printf("\nIngrese el numero al que desea calcular el Factorial: ");
				scanf("%d",&num);
				printf("\nEl Factorial de %d es igual a: %d ",num,factorial (num));
				printf("\nPresione Cualquier Tecla para continuar con el programa ");
				getch();
				system("cls");
			break;
			case 2:
				system("cls");
				printf("\t\t\t**********Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 3: Recursividad**********\n\n");
				printf ("*************************FIBONACCI****************************\n\n");
				printf("\nIngrese la posicion del valor que desea ver de la Serie de Fibonacci: ");
				scanf("%d",&num);
				printf("\nLa posicion #%d de la Serie de Fibonacci corresponde a: %d ",num,fib(num));
				printf("\nPresione Cualquier Tecla para continuar con el programa ");
				getch();
				system("cls");
			break;
			case 3:
				system("cls");
				printf("\t\t\t**********Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 3: Recursividad**********\n\n");
				printf ("*************************FIBONACCI CON ARREGLO****************************\n\n");
				printf("\nIngrese la posicion del valor que desea ver de la Serie de Fibonacci: ");
				scanf("%d",&num);
				printf("\nLa posicion #%d de la Serie de Fibonacci corresponde a: %d ",num,fibm(num));
				printf("\nPresione Cualquier Tecla para continuar con el programa ");
				getch();
				system("cls");
			break;
			case 4:
				system("cls");
				printf("\t\t\t**********Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 3: Recursividad**********\n\n");
				printf ("*************************TORRES DE HANOI****************************\n\n");
				printf("\nI = Poste Izquierdo ");
				printf("\nC = Poste Central ");
				printf("\nD = Poste Derecho ");
				printf("\nIngrese el numero de discos de la Torre de Hanoi: ");
				scanf("%d",&num);
				hanoi(num,'I','C','D');
				printf("\nPresione Cualquier Tecla para continuar con el programa ");
				getch();
				system("cls");
			break;
			case 5:
				system("cls");
				printf("\t\t\t**********Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 3: Recursividad**********\n\n");
				printf ("*************************DECIMAL A BINARIO****************************\n\n");
				printf("\nIngrese el numero Decimal que desea convertir a Binario: ");
				scanf("%d",&num);
				printf("\nEl numero binario resultante es: ");
				decimalBin(num);
				printf("\nPresione Cualquier Tecla para continuar con el programa ");
				getch();
				system("cls");
			break;
			case 6:
				system("cls");
				printf("\t\t\t**********Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 3: Recursividad**********\n\n");
				printf ("*************************BINARIO A DECIMAL****************************\n\n");
				printf("\nIngrese el numero binario a convertir (MAX. 9 Digitos): ");
				scanf("%s",s);
				longitud=0;
				num=0;
				while(s[num]!='\0'){
					longitud++;
					num++;
				}
				printf("\nEn decimal es igual a: %d ",binarioDec(s,0,longitud-1));
				printf("\nPresione Cualquier Tecla para continuar con el programa ");
				getch();
				system("cls");
			break;
			case 0:
				printf("\n!!!Hasta la proxima!!!");
			break;
			default:
				printf("\n!!!Ha insertado una opcion invalida!!! Presione Cualquier Tecla para intentar de nuevo ");
				getch();
				system("cls");	
	}
}while(menu!=0);
	return 0;
}

long factorial ( int num ){
	// Caso Base
	if ( num == 0 || num == 1   )
	      return 1;
	// Parte recursiva	  
	return num*factorial(num-1);	          
}

long fib( unsigned long num){//Fibonacci Simple
	 //Casos Base
	  if (num == 0 || num == 1)
	         return num;
	  // Parte recursiva
	  return fib(num-2)+fib(num-1);  
}

void hanoi ( unsigned int num, char O , char A , char D ){
	   //caso base
	   if ( num == 1  )
	       printf("Mover el Disco #1 del Poste %c al Poste %c \n", O , D );
	   else {
	   	      hanoi(num-1,O,D,A);
	   	      printf("Mover el Disco #%d del Poste %c al Poste %c \n", num , O , D );
	   	      hanoi(num-1,A,O,D);   
	   }
}

void decimalBin (int num){
	if (num<2)
	printf("%i",num);
	else{
		decimalBin(num/2);
		printf("%i",num%2);
	}
}

long fibo( unsigned long num,long *A){//Fibonacci con arreglo
	 //Casos Base
	  if (A[num]>-1)
	     return A[num];
	  // Parte recursiva
	  A[num]=fibo(num-1,A)+fibo(num-2,A);  
	  return A[num];
}

long fibm( unsigned long num){//Fibonacci con arreglo
	 int i;
	 long Z [num+1];
	 Z[0]=0;
	 Z[1]=1;
	 for(i=2;i<=num;i++){
	 	Z[i]=-1;
	 }
	return fibo(num,Z);
}

unsigned int binarioDec(char *s,int c,int p){
	int a;
	if (p==0){
		if(s[c]=='1')
			return 1;
		else{
			return 0;
		}
	}
	if(s[c]=='1')
		a=1;
	else{
		a=0;
	}
	a=a*pow(2,p);
	c++;
	p--;
	return a+binarioDec(s,c,p);
}
