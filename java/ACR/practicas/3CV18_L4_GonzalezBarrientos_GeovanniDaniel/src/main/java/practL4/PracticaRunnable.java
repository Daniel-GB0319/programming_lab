package practL4;


public class PracticaRunnable implements Runnable {
    
    // Metodo para realizar operaciones en el hilo actual
    public void run(){
        int i; // Variable para almacenar los numeros del 1 al 10
        
        // Sentencia if para indicar si se imprimen los pares o impares
        if( (Thread.currentThread().getName() == "Pares1") || (Thread.currentThread().getName() == "Pares2") ){ 
            // Procedimiento para hilo que imprimira los numeros pares
            i = 2;
            do{
                System.out.print(i+" "+ Thread.currentThread().getName()+"\n");
                i = i+2;
            }while(i <= 10);
        
        }else{ // Procedimiento para hilo que imprimira los numeros impares
            i = 1;
            do{
                System.out.print(i+" "+ Thread.currentThread().getName()+"\n");
                i = i+2;
            }while(i <= 10);
        } 
        
        System.out.print("Termina thread "+ Thread.currentThread().getName()+"\n"); // Indica que se termino la ejecucion del hilo
    }
    
    // Metodo main
    public static void main(String[] args){
        new Thread(new PracticaRunnable(), "Pares1").start(); // Crea el hilo para trabajar con los numeros pares
        new Thread(new PracticaRunnable(), "Impares1").start(); // Crea el hilo para trabajar con los numeros impares
        
        new Thread(new PracticaRunnable(), "Pares2").start(); // Crea el hilo para trabajar con los numeros pares
        new Thread(new PracticaRunnable(), "Impares2").start(); // Crea el hilo para trabajar con los numeros impares
        
        System.out.print("Termina thread main (Runnable)\n"); // Se indica que funcion main se ha ejecutado correctamente
    }
    
}
