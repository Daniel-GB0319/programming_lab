	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>
	
	/* run this program using the console pauser or add your own getch, system("pause") or input loop */
	typedef struct datos{
	    char nombre[40];
	    char pais[25];
	}datos;
	
	typedef struct atleta{
	    char deporte[40];
	    datos personal;
	    int ndemed;
	}atleta;
	       
	
	int main(int argc, char *argv[]) {
		atleta ats[5];
		int x=0,num_mayor;
		
		printf("\t***Gonzalez Barrientos Geovanni Daniel \t1CV3 \tEstructuras de Datos***\n\n");
		
		/*Datos de atleta #1*/
		strcpy(ats[0].deporte,"Natacion");
		strcpy(ats[0].personal.nombre,"Juan Perez");
		strcpy(ats[0].personal.pais,"Mexico");
		ats[0].ndemed=13;
		printf("Atleta #1: %s\n",ats[0].personal.nombre);
		printf("MEDALLAS: %i\n\n",ats[0].ndemed);
		
		/*Datos de atleta #2*/
		strcpy(ats[1].deporte,"Gimnasia Ritmica");
		strcpy(ats[1].personal.nombre,"Karla Ortiz");
		strcpy(ats[1].personal.pais,"Argentina");
		ats[1].ndemed=15;
		printf("Atleta #2: %s\n",ats[1].personal.nombre);
		printf("MEDALLAS: %i\n\n",ats[1].ndemed);
		
		/*Datos de atleta #3*/
		strcpy(ats[2].deporte,"Esgrima");
		strcpy(ats[2].personal.nombre,"Walter White");
		strcpy(ats[2].personal.pais,"Estados Unidos");
		ats[2].ndemed=11;
		printf("Atleta #3: %s\n",ats[2].personal.nombre);
		printf("MEDALLAS: %i\n\n",ats[2].ndemed);
		
		/*Datos de atleta #4*/
		strcpy(ats[3].deporte,"Futbol");
		strcpy(ats[3].personal.nombre,"Ronaldinho Gaucho");
		strcpy(ats[3].personal.pais,"Brasil");
		ats[3].ndemed=7;
		printf("Atleta #4: %s\n",ats[3].personal.nombre);
		printf("MEDALLAS: %i\n\n",ats[3].ndemed);
		
		/*Datos de atleta #5*/
		strcpy(ats[4].deporte,"Triatlon");
		strcpy(ats[4].personal.nombre,"Virginia Berasategi");
		strcpy(ats[4].personal.pais,"Espana");
		ats[4].ndemed=12;
		printf("Atleta #5: %s\n",ats[4].personal.nombre);
		printf("MEDALLAS: %i\n\n",ats[4].ndemed);
		
		/*Datos de atleta #6*/
		strcpy(ats[5].deporte,"Halterofilia");
		strcpy(ats[5].personal.nombre,"Anastasiya Romanova");
		strcpy(ats[5].personal.pais,"Rusia");
		ats[5].ndemed=10;
		printf("Atleta #6: %s\n",ats[5].personal.nombre);
		printf("MEDALLAS: %i\n\n",ats[5].ndemed);
		
		/*Procedimiento para escojer al atleta con mas medallas*/
		num_mayor=0;
		for (x=1 ; x<=5; x++) {
				
			if(ats[num_mayor].ndemed<ats[x].ndemed) {
				num_mayor=x;	/*Si # de medallas del atleta actual es mayor al anterior considerado con mas medallas, guardar el numero del atleta actual en "num_mayor" */	
			}			
		} 
		
		/*Se muestras los datos del atle con mas medallas*/	
		printf("El atleta con el mayor numero de medallas es: \n\n");
		printf("NOMBRE: %s\n",ats[num_mayor].personal.nombre);
		printf("PAIS: %s\n",ats[num_mayor].personal.pais);
		printf("DEPORTE: %s\n",ats[num_mayor].deporte);
		printf("MEDALLAS: %i\n",ats[num_mayor].ndemed);
	
		return 0;
	}
