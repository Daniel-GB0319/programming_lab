package practL4;


public class PracticaThread extends Thread {

    //Metodo para definir el valor de entrada del hilo
    public PracticaThread(String str){ 
        super(str);
    } 
    
    // Metodo para definir operaciones a ejecutar en el hilo
    public void run(){
         int i; // Variable para almacenar los numeros del 1 al 10
        
        // Sentencia if para indicar si se imprimen los pares o impares
        if( (getName() == "Pares1") || (getName() == "Pares2")  ){ // Procedimiento para hilo que imprimira los numeros pares
            i = 2;
            do{
                System.out.print(i+" "+ getName()+"\n");
                i = i+2;
            }while(i <= 10);
        
        }else{ // Procedimiento para hilo que imprimira los numeros impares
            i = 1;
            do{
                System.out.print(i+" "+ getName()+"\n");
                i = i+2;
            }while(i <= 10);
        } 
        
        System.out.print("Termina thread "+ getName()+"\n"); // Indica que se termino la ejecucion del hilo
        
    }
    
    //Metodo main
    public static void main(String[] args) {
        new PracticaThread("Pares1").start(); // Inicia el hilo para trabajar con numeros pares 
        new PracticaThread("Impares1").start(); // Inicia el hilo para trabajar con numeros impares
        
        new PracticaThread("Pares2").start(); // Inicia el hilo para trabajar con numeros pares 
        new PracticaThread("Impares2").start(); // Inicia el hilo para trabajar con numeros impares
        
        System.out.println("Termina thread main (Thread)\n"); // Indica que ejecucion del main ha finalizado
    }
}
