import java.util.Arrays;
public class Arreglos {

public static void main(String args[]) {
    System.out.println("\n***Gonzalez Barrientos Geovanni Daniel***");
    System.out.println("***Actividad 9: Arreglos***");

// ---------------------DECLARACION DE ARRAYS----------------------------
    System.out.println("\n---------DECLARACION DE ARRAYS---------");
// Se declaran las variables
    int a[]= new int[100];

    for (int x=0;x<a.length;x++){
        a[x]=x; // Se llena el arreglo con numeros del 0 al 99
    }
    System.out.println("El Array \"a\" esta compuesto de los siguientes valores: ");
    for (int x=0;x<a.length;x++){
        System.out.print(a[x] + " ");    
    }

// ---------------------METODOS DE ARRAYS----------------------------
    System.out.println("\n\n---------METODOS DE ARREGLOS---------");
// Se declaran las variables
    int num[]={8, 10, 15, 20, 21, 25, 30, 32, 40, 41};

    System.out.println("El Array \"num\" esta compuesto de los siguientes valores: " + Arrays.toString(num));

// Devuelve un 4
    System.out.println("\nMetodo binarySearch: ");
    System.out.println("El valor 21 en el array \"num\" esta en la posicion #" + Arrays.binarySearch(num,21));

// Copia el Array num hasta la quinta posicion (este ultimo no incluido), devuelve un array
    int num2[]=Arrays.copyOf(num,4);
    System.out.println("\nMetodo copyOf ");
    System.out.println("Al copiar los valores del array \"num\" hasta la cuarta posicion en el array \"num2\" tenemos que \"num2\" = ");
// Lo recorremos para ver que lo realiza correctamente 
    muestraArray(num2);     

// Copia el array num de la tercera a la octava posicion, devuelve un array
    int num3[]=Arrays.copyOfRange(num,2,7);
    System.out.println("\nMetodo copyOfRange ");
    System.out.println("Al copiar los valores del array \"num\" desde la Tercera hasta la Octava posicion en el array \"num3\" tenemos que \"num3\" = ");
    muestraArray(num3);

// Compara si num1 y num2 no son iguales
    System.out.println("\nMetodo equals: ");
    System.out.println("Al realizar la verificacion de que array \"num\" tiene el mismo contenido que array \"num2\" tenemos que es: " + Arrays.equals(num,num2));

// Llena todo el arreglo con un valor especifico
    System.out.println("\nMetodo fill");
    Arrays.fill(num3,5);
    System.out.println("Al rellenar el array \"num3\" con el valor 5 tenemos que \"num3\" = ");
    muestraArray(num3);

// Se convierte el array a String
    System.out.println("\nMetodo toString");
    System.out.println("Al convertir todos los arrays a String tenemos que:");
    System.out.println("Array \"num\" = " +Arrays.toString(num));
    System.out.println("Array \"num2\" = " +Arrays.toString(num2));
    System.out.println("Array \"num3\" = " +Arrays.toString(num3));
    
// ---------------------ARRAYS MULTIDIMENSIONALES----------------------------
    System.out.println("\n---------ARREGLOS MULTIDIMENCIONALES---------");
// Se declara un array de 3 Filas x 5 Columnas
    int arreglo[][]={{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};

    System.out.println("El Array \"arreglo\" esta compuesto de los siguientes valores: ");

// Se recorre el array multidimensional
    for (int i=0;i<arreglo.length;i++){ // Se recorren las filas con este bucle
        for (int j=0;j<arreglo[0].length;j++){
            System.out.print(arreglo[i][j] + " ");    
        }
        System.out.print("\n");
    }
}


// Funcion para imprimir el contenido de un arreglo
public static void muestraArray(int num[]){
    for (int i=0;i<num.length;i++){
        System.out.println(num[i]);
    }
}
}
