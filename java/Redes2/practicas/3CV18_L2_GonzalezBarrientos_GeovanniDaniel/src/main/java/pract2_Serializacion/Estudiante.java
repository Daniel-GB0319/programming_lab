package pract2_Serializacion;
import java.io.Serializable;

public class Estudiante implements Serializable{ // Se crea clase serializable
    // Atributos
    
    String nombre;
    int edad;
    transient long boleta; // Con transient se indica que el atributo no es serializable.
    double calificaciones;
    transient String grupo;
    transient boolean inscrito;
    
    // Metodos
    public Estudiante(String nombre,int edad,long boleta,double calificaciones,String grupo,boolean inscrito){
        this.nombre = nombre;
        this.edad = edad;
        this.boleta = boleta;
        this.calificaciones = calificaciones;
        this.grupo = grupo;
        this.inscrito = inscrito;
    }
}
