
public class ClaseString {

public static void main(String args[]) {
    System.out.println("\n***Gonzalez Barrientos Geovanni Daniel***");
    System.out.println("***Actividad 8: Metodos Significativos de la Clase String***");
// Se declaran las variables
    String cad1 = "Coche Blanco";
    String cad2 = "MANZANA ROJA";
    String cad3 = "MaNzAnA rOjA";
    String cad4 = "telefono negro";
    String cad5 = " Cielo Azul Claro   ";
    String cad6 = "Bosque Verde";
    String cadTrim1, cadTrim2;
        
// Se imprimen los valores originales de cada variable de prueba
    System.out.println("\nLa Cadena Original #1 es : " + cad1);
    System.out.println("La Cadena Original #2 es : " + cad2);
    System.out.println("La Cadena Original #3 es : " + cad3);
    System.out.println("La Cadena Original #4 es : " + cad4);
    System.out.println("La Cadena Original #5 es : " + cad5);
    System.out.println("La Cadena Original #6 es : " + cad6);
        
// Se utilizan los metodos propuestos en el ejercicio
    // Utilizacion de charAt
    System.out.println("\n\t*** Utilizacion de charAt() ***");
    System.out.println("El caracter en la posicion #4 de \"" + cad1 + "\" es : " + cad1.charAt(3)); // Utilizacion de charAt
    
    // Utilizacion de compareTo y compareToIgnoreCase
    System.out.println("\n\t*** Utilizacion de compareTo() y compareToIgnoreCase() ***");    
    System.out.println("El Resultado de Comparar lexicograficamente \"" + cad2 + "\" y \"" + cad3 + "\"  es : " + cad2.compareTo(cad3)); // Utilizacion de compareTo
    System.out.println("El Resultado de Comparar lexicograficamente\"" + cad2 + "\" y \"" + cad3 + "\" sin importar Mayusculas o Minusculas es : " 
    + cad2.compareToIgnoreCase(cad3));
    
    // Utilizacion de endsWith
    System.out.println("\n\t*** Utilizacion de endsWith() ***"); 
    System.out.println("La verificacion de si \"" + cad4 + "\" termina en \"gro\" es : " + cad4.endsWith("gro")); // Utilizacion de endsWith para valor True
    System.out.println("La verificacion de si \"" + cad4 + "\" termina en \"zero\" es : " + cad4.endsWith("zero")); // Utilizacion de endsWith para valor False
    
    // Utilizacion de indexOf
    System.out.println("\n\t*** Utilizacion de indexOf() ***"); 
    System.out.println("La primera aparicion de la letra \"L\" en \"" + cad5 + "\" es en la Posicion # " + cad5.indexOf("l")); // Utilizacion de indexOf
    System.out.println("La ultima aparicion de la letra \"L\" en \"" + cad5 + "\" es en la Posicion # " + cad5.lastIndexOf("l")); // Utilizacion de lastIndexOf

    // Utilizacion de startsWith
    System.out.println("\n\t*** Utilizacion de startsWith() ***"); 
    System.out.println("La verificacion de si \"" + cad6 + "\" empieza con \"Bosq\" es : " + cad6.startsWith("Bosq")); // Utilizacion de startsWith para valor True
    System.out.println("La verificacion de si \"" + cad6 + "\" empieza con \"Plant\" es : " + cad6.startsWith("Plant")); // Utilizacion de startsWith para valor False
    
    // Utilizacion de toUpperCase y toLowerCase
    System.out.println("\n\t*** Utilizacion de toUpperCase() y toLowerCase() ***");
    System.out.println("La utilizacion de funcion toUpperCase en \"" + cad3 + "\" es : " + cad3.toUpperCase()); // Utilizacion de toUpperCase
    System.out.println("La utilizacion de funcion toLowerCase en \"" + cad2 + "\" es : " + cad2.toLowerCase()); // Utilizacion de toLowerCase

    // Utilizacion de trim
    cadTrim1 = "Se puede ver el" + cad5 + "En el paisaje";
    cadTrim2 = "Se puede ver el" + cad5.trim() + "En el paisaje";
    System.out.println("\n\t*** Utilizacion de trim() ***");
    System.out.println("NOTA: A la variable que almacena la palabra \"" + cad5 + "\" se le concatena al inicio la frase \"Se puede ver el\" y al final \"En el paisaje\"");
    System.out.println("El resultado de la concatenacion sin usar trim() en \"" + cad5 + "\" es : " + cadTrim1); // Prueba sin trim
    System.out.println("El resultado de la concatenacion usando trim() en \"" + cad5 + "\" es : " + cadTrim2); // Utilizacion de trim
    }
}
