#include <stdio.h>
#include <windows.h>

int main()
{
    int menu, sub_menu, save_file
    
    /*Inicio del programa, este se repetira hasta que se escoja la opcion 0 en la variable "menu" */
    do{
        printf("\t\t\t*****PABLITO\'S GAME*****");
        printf("\n\nMENU PRINCIPAL");
        printf("\n\n1)Nueva Partida \n2)Cargar Partida \n0)Salir del juego");
        printf("\n\nSelecciona una opcion valida y presione ENTER : ");
        scanf ("%i",&menu);  /* Se ingresa la respuesta de menu principal en "menu" */
    
        switch (menu)
            {
                /* Menu para nueva partida */
            case 1:
                printf("\nSeleccione el Slot en donde desea guardar su nueva partida y presione ENTER : ");
                scanf ("%i",&sub_menu);
            break;
        
                /*Menu para cargar partida*/
            case 2:

            break;

            default:
            break;
            }
    
    
    FILE *savegame;
    savegame=fopen("save1.txt",r);
    if *savegame==NULL
        {
            printf("!!!No ha sido posible cargar los datos guardados");
            exit(1);
        }

    } while (menu!=0);
}