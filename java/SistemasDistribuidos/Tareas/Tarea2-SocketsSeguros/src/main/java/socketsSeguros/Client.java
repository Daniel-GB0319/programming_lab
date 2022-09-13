package socketsSeguros;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    static class Cliente extends Thread{ // Clase para Hilos
        Socket cl = null;
        String arch; // Nombre del archivo a enviar
        static Object obj = new Object();
        
        Cliente(String arch){ // Constructor Servidor
            this.arch = arch; // Nombre del hilo actual
        } // constructor Cliente
        
        // Procedimientos a realizar en los hilos
        public void run(){ 
            try{           
                File f = new File("./Cliente/"+arch); // Archivo a enviar   
                String pathArch = f.getCanonicalPath(); // Almacena la ruta canonica del archivo a enviar
                String nameArch = f.getName() ; // Almacena el nombre del archivo a enviar
                int paquete = (int) f.length(); // Almacena el tamaño del archivo a enviar
                
                // Se implementa los reintentos de conexion
                 for(;;){
                    try{
                        cl = new Socket("localhost",4000);
                        break;
                    }catch(IOException e){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } // catch
                }// for

                System.out.println("\n-- Conexion con Servidor establecido --");
                System.out.println("Espere mientras enviamos "+nameArch+" al Servidor... ");
                
                    synchronized(obj){
                        DataOutputStream  dos; // Flujo salida Servidor
                        DataInputStream dis = new DataInputStream(new FileInputStream(new File("./Cliente/"+nameArch))); // Flujo entrada Servidor
                        
                        dos = new DataOutputStream(cl.getOutputStream()); // Flujo salida Servidor
                        // Se envian datos necesarios para envio de ficheros
                        dos.writeUTF(nameArch); // Envia el nombre del archivo seleccionado al cliente
                        dos.flush();
                        dos.writeUTF(pathArch); // Envia la ruta relativa del archivo seleccionado al cliente
                        dos.flush();
                        dos.writeInt(paquete); // Envia el tamaño del archivo seleccionado al cliente
                        dos.flush();

                        // Se inicia envio de archivos en paquetes
                        byte[] b = new byte[1024]; // 100 KB
                        long enviados = 0;
                        int n = 0;
                        while(enviados<paquete){ // Bucle para enviar bytes
                            n = dis.read(b);
                            dos.write(b,0,n);
                            dos.flush();

                            enviados = enviados + n;
                        } // Termina while
                        dos.flush();

                        dis = new DataInputStream(cl.getInputStream()); // Flujo de entrada desde servidor
                        String resp_servidor = dis.readUTF(); // Se recibe respuesta de Servidor
                        System.out.println("Respuesta del Servidor: "+resp_servidor);
                        dis.close();
                        dos.close();  
                    } // synchronized
                    
                cl.close(); // Se cierra conexion con Servidor

            }catch(IOException | NumberFormatException e){
            } // catch
        } // run
    } // clase Cliente
    
    
    // Funcion main
    public static void main(String[] args) throws Exception {
        Cliente hilos[] = new Cliente[10] ; // Se inicializa el objeto para los threads 
       
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 2 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** CLIENTE INICIADO ***");
        System.out.println("Espere mientras se inicia la conexion con Servidor... ");
            
       
        
        for(int i=0; i<args.length ; i++){
            hilos[i] = new Cliente(args[i]);
            hilos[i].start(); // Se inicia el hilo para enviar archivos
        }// for

        for(int i=0; i<args.length ; i++){
            hilos[i].join(); // Se espera a que todos los hilos finalicen
        }// for
        
        System.out.println("\n-- Conexion con Servidor Finalizado --");
    } // main
}
