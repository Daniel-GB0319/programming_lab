package practL5;
import java.text.*;
import java.util.*;
import java.lang.*;


public class PracticaVectores extends Thread {

    //Metodo para definir el nombre del hilo
    public PracticaVectores(String str){ 
        super(str);
    } 
    
    // Metodo para definir operaciones a ejecutar en el hilo
    public void run(){
        DecimalFormat decimal = new DecimalFormat(); // Sirve para manejar el formato de decimales
        Random rnd = new Random(); // Genera numeros aleatorios
        float promedio = 0;
        double cuadrados = 0, auxD = 0;
        int i = 0, suma = 0,auxI, x = 0;
        int vector[]; // Se declara el vector o matriz
        
        x = rnd.nextInt(3,21); // Genera numero aleatorio para vector entre 3 - 20
        vector = new int[x]; // Crea vector 
        
        System.out.print("Vector creado en hilo \""+ getName() +"\": Vector["+x+"]\n\n");
        
        // For para realizar operaciones
        for(i = 0; i < x; i++){
            vector[i] =(int) rnd.nextInt(101); // Asigna valor aleatorio entre 0 - 100 a la posicion actual
            System.out.print("\""+ getName() +"\" | Vector["+ i +"] == "+ vector[i] +"\n");

            auxI = vector[i];
            suma = suma + auxI; // Realiza la suma de cada elemento

            auxD = Math.pow(vector[i], 2);
            cuadrados = cuadrados + auxD; // Realiza la suma de los cuadrados de cada elemento
        } // for i
        
        decimal.setMaximumFractionDigits(4); // Define el numero de digitos maximo para decimales
        decimal.setMinimumFractionDigits(2); // Define el numero de digitos minimo para decimales
        
        promedio =(float) suma / x ; // Calcula promedio
        System.out.print("\nTOTALES hilo \"" + getName()+"\" ("+x+" Elementos) | SUMA = "+suma+" | SUMA CUADRADOS = "+cuadrados+" | PROMEDIO = "+decimal.format(promedio)+"\n");
        
        System.out.print("\n!!! Termina thread \""+ getName()+"\" !!!\n"); // Indica que se termino la ejecucion del hilo
    }// run
    
    //Metodo main
    public static void main(String[] args) {
        new PracticaVectores("VECTOR_#1").start(); // Inicia el hilo para trabajar un vector
        new PracticaVectores("VECTOR_#2").start(); // Inicia el hilo para trabajar otro vector
        
        System.out.println("\n!!! Termina thread main (Thread) !!!\n"); // Indica que ejecucion del main ha finalizado
    }
}
