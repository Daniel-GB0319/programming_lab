import java.io.*;

class Demo{
    private int datos;
    public Demo() {datos=0;}
    protected void finalize()
    {
        System.out.println("\nFin de Objeto Demo");
    }
}

class Prueba{
    private double x;
    public Prueba() {x=-1.0;}
    protected void finalize()
    {
        System.out.println("\nFin de Objeto Prueba");
    }
}

public class probarDemo{
    public static void main(String[] args) {
        System.out.println("\n\n***** Gonzalez Barrientos Geovanni Daniel *****");
        System.out.println("***** Actividad 14: Recolector de Objetos *****");
        Demo d1, d2;
        Prueba p1, p2;
        System.out.println("\n\nDEMO1");
        d1=new Demo();
        System.out.println("\n\nPRUEBA1");
        p1=new Prueba();
        System.out.println("\n\nGC1");
        System.gc(); // No se libera ningun objeto
        System.out.println("\n\nPRUEBA2");
        p1=null;
        System.out.println("\n\nDEMO2");
        d1=new Demo();
        System.out.println("\n\nGC2");
        System.gc(); // Se liberan dos objetos
        System.out.println("\n\nDEMO3");
        d2=new Demo();    
    } // Se liberan los objetos restantes
}