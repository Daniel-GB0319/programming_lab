package ejemplosHilos;


public class EjemploRunnable implements Runnable {
    
    // Metodo para realizar operaciones en el hilo actual
    public void run(){
        for(int i=0; i<5; i++){ // se imprime 5 veces el nombre ingresado en el hilo
            System.out.print(i+" "+ Thread.currentThread().getName()+"\n");
        }
        System.out.print("Termina thread "+ Thread.currentThread().getName()+"\n"); // Indica que se termino la ejecucion del hilo
    }
    
    // Metodo main
    public static void main(String[] args){
        new Thread(new EjemploRunnable(), "Luis").start(); // Crea el hilo para trabajar con la cadena Luis
        new Thread(new EjemploRunnable(), "Enrique").start(); // Crea el hilo para trabajar con la cadena Enrique
        System.out.print("Termina thread main (Runnable)\n"); // Se indica que el la funcion main se ha ejecutado correctamente al crear los hilos
    }
    
}
