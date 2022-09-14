package socketsSeguros.Servidor;
import java.io.*;
import java.net.*;
import javax.net.ssl.SSLServerSocketFactory;

public class Server {
    static class Servidor extends Thread{
        Socket cl = null; // Socket para atender cliente
        static Object obj = new Object(); // synchronized
        
        Servidor(Socket cl){
            this.cl = cl;
        } // Constructor Servidor
        
        // Procedimiento a realizar en el hilo
        public void run(){
            try{
                synchronized(obj){
                    DataInputStream dis = new DataInputStream(cl.getInputStream()); // Flujo de entrada
                    DataOutputStream dos = null; // Flujo de salida
                    
                    String nameArch = dis.readUTF(); // Recibe el nombre del archivo entrante
                    int paquete = dis.readInt(); // Se recibe el tamaño del archivo entrante
                    String respuesta; // Sirve para enviar respuesta a cliente
                    
                    // Se inicializa el flujo de salida para guardar el archivo a descargar
                    dos = new DataOutputStream(new FileOutputStream(new File("./" + nameArch))); 

                    // Procedimiento para realizar la descarga del archivo
                    byte[] b = new byte[1024]; // Variable para indicar el tamaño de los paquetes a recibir
                    int n = 0;

                    for(long j = 0; j < paquete/1024;j++){ // Bucle para la transferencia de paquetes de bytes
                        n = dis.read(b);
                        dos.write(b,0,n);
                        dos.flush();
                    } // Termina for

                    if(paquete%1024!=0){ // Condicion if para indicar si se ha enviado el ultimo paquete desde cliente
                        b = new byte [(int)paquete%1024];
                        n = dis.read(b);
                        dos.write(b,0,n);
                        dos.flush();
                    } // if
                    dos.flush();
                  
                    // Se verifica si el archivo se ha descargado y guardado con exito
                    File archivo = new File("./"+nameArch); //Sirve para verificar que archivo a descargar existe
                    if(archivo.exists() && archivo.length() == paquete){ // Descarga correcta
                        respuesta = "Archivo "+nameArch+" == OK";
                        
                    }else{ // Descarga incorrecta
                        respuesta = "El Servidor no pudo guardar el archivo "+nameArch;
                    }// else
                    System.out.println(respuesta);

                    dos = new DataOutputStream(cl.getOutputStream()); // Flujo salida hacia cliente
                    dos.writeUTF(respuesta); // Se envia respuesta a Cliente
                    dos.close();
                } // synchronized
            }catch(IOException e){
                System.err.println(e);
            } // catch
        } // run()  
    } // Servidor
    
    public static void main(String[] args) throws Exception {    
        //Se asignan propiedades del keystore y password para repositorio
        System.setProperty("javax.net.ssl.keyStore","keystoreServer.jks");
        System.setProperty("javax.net.ssl.keyStorePassword","abcd1234");
        
        // Se inicializa el socket seguro para el servidor
        SSLServerSocketFactory serverSeguro = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket s = serverSeguro.createServerSocket(4000); // Se crea socket para Servidor
        
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 2 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** SERVIDOR INICIADO *** Puerto: " + s.getLocalPort() );
        System.out.println("Esperando archivos desde un cliente...");
        
        for(;;){
            Socket cl = s.accept(); // Se conecta un cliente
            System.out.println("\n-- Conexion con Cliente Iniciada -- IP: " + cl.getInetAddress());
            
            Servidor hilo = new Servidor(cl); // Se crea una clase para hilo
            hilo.start(); // Se inicia el hilo
            hilo.join();
        } // for
    } // main()
}
