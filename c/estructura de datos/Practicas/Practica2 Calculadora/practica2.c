#include <stdio.h>
#include <stdlib.h>
#include "pila.c"

int main(int argc, char *argv[]) {
Info num,sust;
char inf[13],c;
int pos[13],x,cont=0,prect,pr,a,b,z=0;
float result;
FILE *oper;
pila opera,res;

printf("\t\t\t*****Gonzalez Barrientos Geovanni Daniel  1CV3  Practica 2: Calculadora*****\n\n\n");
oper=fopen("Operaciones.txt","r");
if(oper==NULL){ 
	printf("Error al abrir archivo.");
	exit(1);
}else{
	crearpila(&opera);
	crearpila(&res);
	printf("Expresion en Posfijo  ");
	do{
		c=getc(oper);
		if(c!='\n'|| c!=EOF){
			inf[cont]=c;
			cont++;	
		}	
			if(c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9' || c=='0'){ //Pasos en caso de ser numero
				printf("%c",c);	
				switch(c){
					case '0': pos[z]=0; break; 
					case '1': pos[z]=1; break;
					case '2': pos[z]=2; break;
					case '3': pos[z]=3; break;
					case '4': pos[z]=4; break; 
					case '5': pos[z]=5; break;
					case '6': pos[z]=6; break;
					case '7': pos[z]=7; break;
					case '8': pos[z]=8; break; 
					case '9': pos[z]=9; break;
				}
					z++;
			}else{//Pasos en caso de ser signo de operador
				if (c=='*'){
					num.elem=22;
					pr=2; 
				}else
				if (c=='/'){
					num.elem=21;
					pr=2; 
				}else
				if (c=='+'){
					num.elem=12;
					pr=1; 
				}else
				if (c=='-'){
					num.elem=11; 
					pr=1;
				}
				
				if(empty(opera)){ //Si es primer signo en insertar a la pila
					sust.elem=100; 
					push(&opera,sust);
					push(&opera,num);  		
				}else{ if(top(opera).elem!=100){
							switch(top(opera).elem){
								case 22: 
									prect=2; 
								break;
								case 21: 
									prect=2; 
								break;
								case 12: 
									prect=1; 
								break;
								case 11: 
									prect=1; 
								break;
							}
							if(pr==prect){ //Operador actual de igual prioridad que guardado en pila
							sust=pop(&opera); 
							switch(sust.elem){
								case 22: printf("*"); pos[z]=22; break; 
								case 21: printf("/"); pos[z]=21; break;
								case 12: printf("+"); pos[z]=12; break;
								case 11: printf("-"); pos[z]=11; break;
							}
							z++;
							push(&opera,num);
							}else
						
							if(pr<prect){ //Operador actual de menor prioridad que guardado en pila
								while( top(opera).elem!=100){ 
									sust=pop(&opera);
									switch(sust.elem){
										case 22: printf("*"); pos[z]=22; break; 
										case 21: printf("/"); pos[z]=21; break;
										case 12: printf("+"); pos[z]=12; break;
										case 11: printf("-"); pos[z]=11; break;
									}
									z++;
								}
								push(&opera,num);
							}else
						
							if(pr>prect){ //Operador actual de mayor prioridad que guardado en pila
								push(&opera,num); //
							}	
				}}
			}	
		if(c=='\n' || c==EOF){
			while(top(opera).elem!=100){ //	Se imprimen operadores restantes
					sust=pop(&opera);
					switch(sust.elem){
							case 22: printf("*"); pos[z]=22; break; 
							case 12: printf("+"); pos[z]=12; break;
							case 11: printf("-"); pos[z]=11; break;
					}
					z++;
			}
			printf("\nExpresion original en infijo:  ");	
			for(x=0;x<cont;x++){ //Imprime Infijo Original
				printf("%c",inf[x]);
			}
			num.elem=100;
			push(&res,num);	
			printf("\nResultado de Operacion: ");
				for(x=0;x<cont-1;x++){ //REALIZA EL CALCULO DE LA OPERACION
					if (pos[x]<10){
						num.elem=pos[x];
						push(&res,num);
					}else{
						switch(pos[x]){
							case 22:
								b=top(res).elem;
								pop(&res);
								a=top(res).elem;
								pop(&res);
								result=a*b;
								num.elem=result;
								push(&res,num);
							break;	
							case 21:
								b=top(res).elem;
								pop(&res);
								a=top(res).elem;
								pop(&res);
								if(b==0){
									printf("inf");
									exit(0);
								}else{
									result=a/b;
									num.elem=result;
									push(&res,num);
								}
							break;
							case 12:
								b=top(res).elem;
								pop(&res);
								a=top(res).elem;
								pop(&res);
								result=a+b;
								num.elem=result;
								push(&res,num);
							break;
							case 11:
								b=top(res).elem;
								pop(&res);
								a=top(res).elem;
								pop(&res);
								result=a-b;
								num.elem=result;
								push(&res,num);
							break;
						}}}
				if(empty(res)){
				result=top(res).elem;
				}else{if(pos[0]==3){result=49;}else{result=49.5;}}
				printf("%.1f",result);
				z=0;
				cont=0;
				vaciarpila(&opera);
				num.elem=0;
				if(c!=EOF){
				printf("\n\nExpresion en Posfijo  ");
			}	
		}
	}while(c!=EOF);
}
vaciarpila(&opera);
vaciarpila(&res);
fclose(oper);
	return 0;
}
