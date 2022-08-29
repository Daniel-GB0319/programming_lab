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
                DataInputStream dis = new DataInputStream(cl.getInputStream() );
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                System.out.println("-- Conexion con Cliente Iniciada -- IP: " + cl.getInetAddress());
                
                String respuesta = "NO DIVIDE"; 
                long n;
                long numero = dis.readLong();
                long numero_inicial = dis.readLong();
                long numero_final = dis.readLong();
                
                for(n = numero_inicial; n < numero_final+1; n++){
                    if(numero%n == 0){
                        respuesta = "DIVIDE";
                        break;
                    } // if
                } // for
                System.out.println("Respuesta final obtenida: " + respuesta);
                
                dos.writeUTF(respuesta);
                //cl.close();
                System.out.println("-- Conexion con Cliente Finalizada --");
                System.out.println("\nEsperando conexion de otro cliente... ");
            }catch(IOException e){
                System.err.println(e);
            } // catch
        } // run()  
    } // Calculadora
    
    public static void main(String[] args) throws Exception {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int puerto;
        
        do{
            System.out.println("\n\nPuerto para esta instancia del Servidor A (4000 - 4003): ");
            puerto = Integer.parseInt(entrada.readLine());
        }while(puerto<4000 && puerto>4003);
       
        ServerSocket s = new ServerSocket(puerto);
        System.out.println("*** SERVIDOR INICIADO *** Puerto: " + s.getLocalPort() );
        System.out.println("Esperando conexion de un cliente...");
        for(;;){
            Socket cl = s.accept();
            Calculadora cal = new Calculadora(cl);
            cal.start();
        } // for
    } // main()
}
