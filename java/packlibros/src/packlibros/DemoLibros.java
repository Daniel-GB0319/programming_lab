package packlibros;
public class DemoLibros {
    public static void main(String[] args) {
        System.out.println("\n***** Gonzalez Barrientos Geovanni Daniel*****");
        System.out.println("***** Actividad 12: Clases en Java*****");

    // Creacion de  array de 5 Objetos de tipo "Libros" contenido en Libros.class
        Libros libros[]= new Libros[5];

    // Definiendo valores en el array de objetos recien creado por medio del metodo "Libros" de Libros.class    
        libros[0]= new Libros("Aprendiendo a Programar en Java","Sonia Jaramillo",2015);
        libros[1]= new Libros("Estructuras de Datos en Java","Mark Allen Weis",2013);
        libros[2]= new Libros("Fundamentos de Programacion Java","Ricardo Marcelo Villalobos",2012);
        libros[3]= new Libros("Introduccion a la Programacion Orientada a Objetos","Francisco Aragon Mesa",2014);
        libros[4]= new Libros("Java: Como Programar","Paul Deitel",2017);

    // Bucle para imprimir valores del array por medio de invocacion de metodo "mostrar" contenido en Libros.class 
        for(int i=0; i<5;i++){
            libros[i].mostrar();
        }
    } 
    
}
