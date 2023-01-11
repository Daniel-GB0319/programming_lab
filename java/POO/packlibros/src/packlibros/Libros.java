package packlibros; // Indicando que Libros.class pertenecera a paqueteria "packlibros"

public class Libros {
// Definiendo atributos de Libros.class
    private String titulo;
    private String autor;
    private int anio;

// Definiendo metodo de asignacion de valores    
    Libros(String t, String a, int d){ // Indicando que valores recibira el metodo al ser invocado
        titulo = t;
        autor = a;
        anio = d;
    }

// Definiendo metodo de impresion de valores     
    void mostrar(){
        System.out.println(titulo);
        System.out.println(autor);
        System.out.println(anio);
        System.out.println();
    }
}
