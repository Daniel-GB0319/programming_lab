package ejemplosHilos;


public class EjemploThread extends Thread {

    //Metodo para definir el valor de entrada del hilo
    public EjemploThread(String str){ 
        super(str);
    } 
    
    // Metodo para definir operaciones a ejecutar en el hilo
    public void run(){
        for(int i = 0; i<10;i++){ // Ciclo para imprimir 10 veces el nombre ingresado para el hilo
            System.out.print(i+" "+ getName()+"\n");
        }
        System.out.print("Termina thread "+ getName()+"\n"); // Indica que ejecucion del hilo ha finalizado
    }
    
    //Metodo main
    public static void main(String[] args) {
        new EjemploThread("Luis").start(); // Inicia el hilo para trabajar con dato LUIS 
        new EjemploThread("Enrique").start(); // Inicia el hilo para trabajar con dato ENRIQUE
        System.out.println("Termina thread main (Thread)\n"); // Indica que ejecucion del main ha finalizado
    }
}
