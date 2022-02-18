import java.util.Scanner;

public class ArregloTriangular {
// ############################ Main ###################################    
    public static void main (String[] args){
        System.out.println("\n***Gonzalez Barrientos Geovanni Daniel***");
        System.out.println("***Actividad 11: Arreglo Triangular para el Tringulo de Pascal***");

    // Se declara la variable para leer numero de filas por parte del usuario
        Scanner s = new Scanner (System.in);
        System.out.print("\n\nIngrese el numero de Filas del Triangulo de Pascal: "); // Se pide el numero de filas
        int n = s.nextInt(); // Se guarda el valor ingresado en una variable
        pascal(n); // Se llama a la funcion que imprime el Triangulo de Pascal
    }

// ######################### Funcion Triangulo de Pascal #############################
    public static void pascal(int n){ 
   if (n == 0) // Si se ingresa "0" en numero de filas, no se realiza nada 
   { 
      return; 
   } 
    
   int rows = n; // Numero de Filas  
   int cols = 2*n + 1; // Numero de columnas con 2 extras 
 
// Se declara arreglo a utilizar para el Triangulo de Pascal
   int[][] array = new int[rows][cols];    
   array[0][n] = 1; // Se asigna 1 a la primer Fila
    
// Bucle para generar valores del Triangulo de Pascal      
   for (int i = 1; i < rows; i++){                                         
       for (int j = 1; j < cols-1; j++){                      
          array[i][j] =  
          array[i-1][j-1] + array[i-1][j+1]; 
       } 
   } 
 
// Bucle para imprimir los Valores del Triangulo de Pascal      
    for (int i = 0; i < rows; i++){ 
      for (int j = 1; j < cols-1; j++){ 
         if (array[i][j] != 0)  
            System.out.print(array[i][j]);                     
         else 
            System.out.print(" "); 
      } 
      System.out.println(); 
    } 
        return; 
    } 

}