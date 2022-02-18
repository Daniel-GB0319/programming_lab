#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<windows.h>

// ARREGLAR PROBLEMAS CON LA DEFENSA YA QUE SUMA PUNTOS DE VIDA SI ATAQUE HA SIDO FALLIDO

int atkPablito(int buche, int ataquePablito);
int atkGina(int buche, int ataqueGina);

int main (){
    srand(time(NULL)); 
    int vidaPablito, ataquePablito, defensaPablito, ataqueGina, defensaGina, vidaGina, buche, menu, round=1;
// Se le asigna ataque y defensa a cada buche
    ataquePablito=80;
    defensaPablito=100;

    ataqueGina=100;
    defensaGina=80;

// Inicia el Programa
    do{
        // Se le asigna puntos de vida a cada buche
        vidaPablito=2000;
        vidaGina=2000;
        
        system("cls");
        // Se pregunta a jugador que pollo escoje
        printf ("!!! Escoje a tu Personaje !!!");
        printf ("\n1) Pablito");
        printf ("\n2) Gina");
        printf ("\n Ingresa la opcion deseada y pulsa Enter: ");
        scanf("%i",&buche);

        do{ // Combate
            system("cls"); 
            if(buche==1){ // Se escogio pablito, empieza el primero
                printf ("\t\t****** ROUND %i ******",round);
                printf ("\nVida del Oponente (Gina) = %i pts.",vidaGina);
                printf ("\nVida del Jugador (Pablito) = %i pts.",vidaPablito);
                vidaGina = vidaGina-(atkPablito(buche,ataquePablito)/*-defensaGina*/);
                if(vidaGina<0){ // Oponente (Gina) ha sido vencida
                    printf ("\n\n!!! Gina ha sido Vencida !!! Ahora ese el Big Buche");
                    vidaGina=0;
                }else { // Oponente (Gina) aun sigue con vida
                    vidaPablito= vidaPablito-(atkGina(buche,ataqueGina)/*-defensaPablito*/);
                    if(vidaPablito<0){ // Jugador (Pablito) ha sido derrotado
                        printf ("\n\n!!! Pablito ha sido Vencido !!! Perdiste por Gillipollas");
                        vidaPablito=0;
                    }
                round++;
                }
            }else { // Se escogio Gina, empieza ella primero
                printf ("!!! Round %i !!!",round);
                printf ("\nVida del Oponente (Pablito) = %i pts.",vidaPablito);
                printf ("\nVida del Jugador (Gina) = %i pts.",vidaGina);
                vidaPablito= vidaPablito-(atkGina(buche,ataqueGina)/*-defensaPablito*/);
                if(vidaPablito<0){ // Oponente (Pablito) ha sido vencido
                    printf ("\n\n!!! Pablito ha sido Vencido !!! Ahora eres la Big Buche");
                    vidaPablito=0;
                }else { // Oponente (Pablito) aun sigue con vida
                    vidaGina = vidaGina-(atkPablito(buche,ataquePablito)/*-defensaGina*/);
                    if(vidaGina<0){ // Jugador (Gina) ha sido vencida
                        printf ("\n\n!!! Gina ha sido Vencida !!! Perdiste por Gillipollas");
                        vidaGina=0;
                    }
                round++;
                }
            }
        }while(vidaGina!=0 && vidaPablito!=0); // Termina Combate
    round=1;
    printf ("\nDeseas empezar un nuevo combate? (Si=1 / No=0)");
    printf ("\n Ingresa la opcion deseada y pulsa Enter: ");
    scanf("%i",&menu);
    if (menu==0){
        printf ("\n !!! Hasta la Proxima Hijo del Buche 3:^c !!!");
    }
    }while(menu!=0);
    return 0;
}

// ####### ATAQUE DEL PABLITO ##########
int atkPablito(int buche, int ataquePablito){
    int atk1=10, atk2=20, atk3=25, atk4=5, hit, crit, opcAtk;

    hit=rand()%101;
    crit=rand()%101;
    printf("\nhit = %i",hit);
    printf("\ncrit = %i",crit);
// Se escoje ataque a utilizar
    if(buche==1){ // Si es el pollo seleccionado, escoje jugador
        printf ("\n\n--- Ataques del Pablito ---");
        printf ("\n1) Patada");
        printf ("\n2) Plancha");
        printf ("\n3) Espolonazo");
        printf ("\n4) Picotazo");
        printf ("\n Ingresa la opcion deseada y pulsa Enter: ");
        scanf("%i",&opcAtk);
    } else { // Si es CPU el pollo, ataque se escoje aleatorio
        opcAtk=rand()%101;
        if(opcAtk>=0 && opcAtk<=24){
            opcAtk=1;
        }else {
            if(opcAtk>=25 && opcAtk<=49){
                opcAtk=2;
            }else {
                if(opcAtk>=50 && opcAtk<=74){
                opcAtk=3;
                }else {
                    if(opcAtk>=75 && opcAtk<=100){
                        opcAtk=4;
                    }
                }
            }
        } 
    }
        
    // Se procede a realizar los ataques
        switch (opcAtk){
            case 1: // Ataque 1 
                printf ("\n\nPablito a usado Patada");
                hit=hit-atk1;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=70){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque ha sido un Golpe Critico porque la Patada ha derribado al oponente.");
                        Sleep(3000);
                        return ataquePablito+(atk1*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! Ha dado en el blanco");
                        Sleep(3000);
                        return ataquePablito+atk1;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Ha Fallado el Ataque porque Pablito se tropezo mientras corria.");
                    Sleep(3500);
                    return 0;
                }
            break;
            case 2: // Ataque 2
                printf ("\n\nPablito a usado Plancha");
                hit=hit-atk2;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=70){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque a sido un Golpe Critico porque ha caido completamente sobre el oponente.");
                        Sleep(3000);
                        return ataquePablito+(atk2*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! Se ha logrado tirar al oponente con la Plancha");
                        Sleep(3000);
                        return ataquePablito+atk2;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Has Fallado el Ataque porque Pablito cayo de cabeza.");
                    Sleep(3500);
                    return 0;
                }
            break;
            case 3: // Ataque 3
                printf ("\n\nPablito a usado Espolonazo");
                hit=hit-atk3;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=70){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque a sido un Golpe Critico porque se han clavado los 2 espolones.");
                        Sleep(3000);
                        return ataquePablito+(atk3*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! El espolon ha picado en un costado.");
                        return ataquePablito+atk3;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Ha Fallado el Ataque porque Pablito se estrello al brincar.");
                    Sleep(3500);
                    return 0;
                }
            break;
            default: // Ataque 4
                printf ("\n\nPablito a usado Picotazo");
                hit=hit-atk4;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=70){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque a sido un Golpe Critico porque ha ponchado el Buche.");
                        Sleep(3000);
                        return ataquePablito+(atk4*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! El picotazo ha dolido mucho.");
                        Sleep(3000);
                        return ataquePablito+atk4;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Ha Fallado el Ataque porque Pablito no estiro bien el pezcuezo.");
                    Sleep(3500);
                    return 0;
                }
            break;
        }
}

// ########### ATAQUE GINA #################
int atkGina(int buche, int ataqueGina){
    int atk1=20, atk2=29, atk3=15, atk4=10, hit, crit, opcAtk;

    hit=rand()%101;
    crit=rand()%101;
    printf("\nhit = %i",hit);
    printf("\ncrit = %i",crit);
// Se escoje ataque a utilizar
    if(buche==2){ // Si es el pollo seleccionado, escoje jugador
        printf ("\n\n--- Ataques de la Gina ---");
        printf ("\n1) Guardias");
        printf ("\n2) Corte Bonito");
        printf ("\n3) Pela-Buches");
        printf ("\n4) Lanza-Huevos");
        printf ("\n Ingresa la opcion deseada y pulsa Enter: ");
        scanf("%i",&opcAtk);
    } else { // Si es CPU el pollo, ataque se escoje aleatorio
        opcAtk=rand()%101;
        if(opcAtk>=0 && opcAtk<=24){
            opcAtk=1;
        }else {
            if(opcAtk>=25 && opcAtk<=49){
                opcAtk=2;
            }else {
                if(opcAtk>=50 && opcAtk<=74){
                opcAtk=3;
                }else {
                    if(opcAtk>=75 && opcAtk<=100){
                        opcAtk=4;
                    }
                }
            }
        } 
    }
        
    // Se procede a realizar los ataques
        switch (opcAtk){
            case 1: // Ataque 1 
                printf ("\n\nGina ha usado a los Guardias");
                hit=hit-atk1;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=80){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque ha sido un Golpe Critico porque los Guardias han dejado pelon al oponente.");
                        Sleep(3000);
                        return ataqueGina+(atk1*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! Han golpeado al oponente");
                        Sleep(3000);
                        return ataqueGina+atk1;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Ha Fallado el Ataque porque los Guardias ya fueron horneados.");
                    Sleep(3500);
                    return 0;
                }
            break;
            case 2: // Ataque 2
                printf ("\n\nGina ha usado Corte Bonito");
                hit=hit-atk2;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=80){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque a sido un Golpe Critico porque le ha quitado la cresta al oponente.");
                        Sleep(3000);
                        return ataqueGina+(atk2*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! Se ha dejado con corte militar al oponente.");
                        Sleep(3000);
                        return ataqueGina+atk2;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Has Fallado el Ataque porque Gina olvido las clases de Peluqueria.");
                    Sleep(3500);
                    return 0;
                }
            break;
            case 3: // Ataque 3
                printf ("\n\nGina ha usado Pela-Buches");
                hit=hit-atk3;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=80){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque a sido un Golpe Critico porque le ha quitado todas las plumas del buche al oponente.");
                        Sleep(3000);
                        return ataqueGina+(atk3*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! Se ha despuntado parcialmente el buche del oponente.");
                        Sleep(3000);
                        return ataqueGina+atk3;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Ha Fallado el Ataque porque a la Gina la intimidaron antes de acercarse.");
                    Sleep(3500);
                    return 0;
                }
            break;
            default: // Ataque 4
                printf ("\n\nGina ha usado Lanza Huevos");
                hit=hit-atk4;
                Sleep(3000);
                if(hit>=40){ // Si el ataque ha sido realizado con exito
                    if(crit>=80){ // Si ataque ha sido critico
                        printf ("\n!!! OH SHIT !!! El ataque a sido un Golpe Critico porque del huevo nacio un pollo y tambien golpeo al oponente.");
                        Sleep(3000);
                        return ataqueGina+(atk4*5);
                    }else {
                        printf ("\n!!! Golpe Exitoso !!! El huevo a dado en la cabeza del oponente.");
                        Sleep(3000);
                        return ataqueGina+atk4;
                    }
                }else { // Golpe Fallido
                    printf ("\n!!! Valio Buche !!! Ha Fallado el Ataque porque la Gina se comio el huevo.");
                    Sleep(3500);
                    return 0;
                }
            break;
        }
}