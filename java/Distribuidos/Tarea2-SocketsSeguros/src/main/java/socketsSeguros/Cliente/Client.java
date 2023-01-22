package socketsSeguros.Cliente;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;

public class Client {
    static class Cliente extends Thread{ // Clase para Hilos
        SSLSocketFactory socketSeguro = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket cl = null; // Socket del cliente
        String arch; // Nombre del archivo a enviar
        static Object obj = new Object(); // Objeto para synchronized
        
        Cliente(String arch){ // Constructor Servidor
            this.arch = arch; // Nombre del archivo pasado como parametro
        } // constructor Cliente
        
        // Procedimientos a realizar en los hilos
        public void run(){ 
            try{           
                File f = new File("./"+arch); // Archivo a enviar   
                
                if(f.exists()){ // Verifica si existe el archivo seleccionado
                    synchronized(obj){
                        String nameArch = f.getName() ; // Obtiene el nombre del archivo a enviar
                        int paquete = (int) f.length(); // Obtiene el tamaño del archivo a enviar

                        for(;;){ // Se implementa los reintentos de conexion
                            try{
                                cl = socketSeguro.createSocket("localhost",4000); // Conexion a servidor
                                break;
                            }catch(IOException e){
                                Thread.sleep(1000); // Pausa de 1s
                            } // catch
                        }// for
                        
                        DataOutputStream dos; // Flujo de salida
                        DataInputStream dis = new DataInputStream(new FileInputStream(new File("./"+nameArch))); // Flujo de entrada desde archivo
                        dos = new DataOutputStream(cl.getOutputStream()); // Flujo salida hacia Servidor

                        System.out.println("\n-- Conexion con Servidor establecido --");
                        System.out.println("Espere mientras enviamos "+nameArch+" al Servidor... ");
                        
                        // Se envian datos necesarios para envio de ficheros
                        dos.writeUTF(nameArch); // Envia el nombre del archivo al servidor
                        dos.flush();
                        dos.writeInt(paquete); // Envia el tamaño del archivo al servidor
                        dos.flush();

                        // Se inicia envio de archivos en paquetes
                        byte[] b = new byte[1024]; // Tamaño del paquete
                        long enviados = 0;
                        int n = 0;
                        while(enviados<paquete){ // Bucle para enviar paquetes
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
                        cl.close(); // Se cierra conexion con Servidor   
                    } // synchronized 
                }else{ // Archivo no existe
                    System.out.println("\n!!! "+arch+" no fue encontrado !!!");
                } // else
            }catch(IOException | NumberFormatException e){
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } // catch
        } // run
    } // clase Cliente
    
    
    // Funcion main
    public static void main(String[] args) throws Exception {
        // Se indica el repositorio de confianza del cliente y password 
        System.setProperty("javax.net.ssl.trustStore","keystoreClient.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","abcd1234");
        Cliente hilos[] = new Cliente[10] ; // Se inicializa el objeto para los threads 
       
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 2 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** CLIENTE INICIADO ***");
        System.out.println("Espere mientras se inicia la conexion con Servidor... ");
            
        // Se crean los hilos necesarios para enviar archivos
        for(int i=0; i<args.length ; i++){
            hilos[i] = new Cliente(args[i]);
            hilos[i].start(); // Se inicia el hilo para enviar archivos
        }// for

        for(int i=0; i<args.length ; i++){
            hilos[i].join(); // Se espera a que todos los hilos finalicen
        }// for
        System.out.println("\n\n*** CLIENTE FINALIZADO ***\n");
    } // main
}
