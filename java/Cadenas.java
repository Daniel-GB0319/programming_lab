
public class Cadenas {
    public static void main (String[]args){
        System.out.println("");
        System.out.println("Actividad 7: Cadenas");
        System.out.println("Gonzalez Barrientos Geovanni Daniel");

// Ejemplo 1
        System.out.println("\n***Ejemplo 1 - Declaracion de Variables tipo String***");
    
    //Se declaran variables
        String a = ""; // Se declara variable "a" como cadena vacia
        String saludo = "Hola"; // Se declara una variable de tipo cadena y se le asigna directamente un valor
        String ciudad; // Se declara solo la variable de tipo cadena
        ciudad = "Monterrey"; // Se le asigna un valor a una variable tipo cadena previamente declarada

        System.out.println("Cadena vacia = " + a);
        System.out.println("Cadena declarada con valor asignado directamente  = " + saludo);
        System.out.println("Cadena declarada con valor asignado por separado = " + ciudad);

// Ejemplo 2
        System.out.println("\n***Ejemplo 2 - Concatenacion de Cadenas***");
        
     // Se declaran variables
        String nombre = "Sierra de Horche"; // Se declara variable tipo cadena y se le asigna valor directamente 
        String frase; // Se declara variable auxiliar 
        frase = nombre + " es muy bella"; // Se concatena variable "nombre" y texto predefinido para ser asignado como valor para variable frase

        System.out.println("Variable \"nombre\" = " + nombre);
        System.out.println("Variable \"frase\" despues de concatenar valor de \"nombre\" y texto predefinido = " + frase);

// Ejemplo 3
        System.out.println("\n***Ejemplo 3 - Cadenas Inmutables***");

     // Se declaran variables
        String pueblo = "Viesca"; // Se declara variable "pueblo" y se le asigna valor
        System.out.println("Variable \"pueblo\" con valor original asignado = " + pueblo);

        pueblo = "Pueblo de : " + pueblo; // Se concatena el valor originar de "pueblo" con texto predefinido
        System.out.println("Variable \"pueblo\" despues de concatenar su valor original con nuevos valores = " + pueblo);

// Ejemplo 4
        System.out.println("\n***Ejemplo 4 - Longitud de Cadenas***");

     // Se declara variable 
        String saludo2 = "Hola Juan"; // Se declara cadena y se le asigna valor
        System.out.println("Variable \"saludo2\" = " + saludo2);
        System.out.println("La longitud de \"saludo2\" es = " + saludo2.length() );

// Ejemplo 5
        System.out.println("\n***Ejemplo 5 - Comparador de Cadenas***");

     // Se declaran variables
        String prueba = "hola"; // Se declara variable de prueba 
        System.out.println("Variable \"prueba\" = " + prueba);
        System.out.println("El resultado de comparar la variable \"prueba\" con texto \"hola\" es = " + prueba.equals("hola") );
        System.out.println("El resultado de comparar la variable \"prueba\" con texto \"HOLA\" es = " + prueba.equals("HOLA") );
        System.out.println("El resultado de comparar la variable \"prueba\" con texto \"HoLa\" sin importar mayusculas y minusculas es = " + prueba.equalsIgnoreCase("HoLa") );

    }
}