package numPrimos;
import java.io.*;
import java.net.*;


public class Server_A {
    static class Calculadora extends Thread{
        Socket cl = null;
        
        Calculadora(Socket cl){
            this.cl = cl;
        } // Constructor Calculadora
        
        // Procedimiento a realizar en el hilo
        public void run(){
            try{
                DataInputStream dis = new DataInputStream(cl.getInputStream()); // Flujo de entrada
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Flujo de salida
                System.out.println("\n-- Conexion con Cliente Iniciada -- IP: " + cl.getInetAddress());
                
                String respuesta = "NO DIVIDE"; 
                long n; 
                long numero = dis.readLong(); // Numero de URL
                long numero_inicial = dis.readLong(); // Limite inferior
                long numero_final = dis.readLong(); // Limite Superior
                
                // Bucle para realizar todas las divisiones dentro del rango
                for(n = numero_inicial; n < numero_final+1; n++){
                    if(numero%n == 0){ // Verifica si hay una division exacta
                        respuesta = "DIVIDE";
                        break;
                    } // if
                } // for
                System.out.println("Respuesta final obtenida: " + respuesta);
                
                dos.writeUTF(respuesta); // Se envia respuesta a Servidor HTTP
                System.out.println("-- Conexion con Cliente Finalizada --");
                System.out.println("\nEsperando conexion de otro cliente... ");
            }catch(IOException e){
                System.err.println(e);
            } // catch
        } // run()  
    } // Calculadora
    
    public static void main(String[] args) throws Exception {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int puerto; // Almacena el puerto para abrir el Servidor
        
        do{
            System.out.println("\n\nPuerto para esta instancia del Servidor A (4000 - 4003): ");
            puerto = Integer.parseInt(entrada.readLine()); // Lee puerto desde teclado
        }while(puerto<4000 && puerto>4003);
       
        ServerSocket s = new ServerSocket(puerto); // Se crea socket para Servidor
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 1 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** SERVIDOR INICIADO *** Puerto: " + s.getLocalPort() );
        System.out.println("Esperando conexion de un cliente...");
        for(;;){
            try (Socket cl = s.accept() // Se conecta un cliente
            ) {
                Calculadora cal = new Calculadora(cl); // Se crea una clase para hilo
                cal.start(); // Se inicia el hilo
                cal.join();
            } // Se crea una clase para hilo
        } // for
    } // main()
}
