package numPrimos;
import java.io.*;
import java.net.*;
import java.util.*;

public class HTTP_Server {
    static class Servidor extends Thread{ // Clase para Hilos
        Socket conexionWeb = null; // Socket para el cliente en el navegador web
        int puerto; // Puerto para conectarse a un Servidor A
        static String primo; // Respuesta que se enviara a navegador web
        static Object obj = new Object(); // Objeto para sincronizar los hilos
        static int hilos; // Sirve para imprimir resultado final al finalizar todos los hilos
        
        Servidor(Socket conexionWeb, int puerto){ // Constructor Servidor
            this.conexionWeb = conexionWeb; // Se asigna socket del cliente en navegador web
            this.puerto = puerto; // Puerto para conectarse a un Servidor A
        } // constructor Servidor
        
        // Procedimientos a realizar en los hilos
        public void run(){ 
            try{
                BufferedReader entrada = new BufferedReader(new InputStreamReader(conexionWeb.getInputStream()));
                PrintWriter salida = new PrintWriter(conexionWeb.getOutputStream());
                String url = entrada.readLine(); // URL ingresada
                
                if(url.startsWith("GET /es_primo?n=")){ // Se verifica que URL es correcta
                    Socket cl = new Socket("localhost", puerto); // Se crea conexion con un Servidor A
                    System.out.println("SOCKET CREADO EN SERVER-A CON (PUERTO :"+cl.getPort()+" // HILO: "+ Thread.currentThread().getName());
                    DataInputStream dis = new DataInputStream(cl.getInputStream() ); // Flujo entrada Servidor A
                    DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Flujo salida Servidor A
                    String resp_server_a = url.substring(url.indexOf("=")+1, url.lastIndexOf("H")-1); // Respuesta del Servidor A
                    long numero = Long.parseLong(resp_server_a);
                    long num_inicial; // Limite inferior del rango en Servidor A
                    long num_final; // Limite SUPERIOR del rango en Servidor A
          
                    switch (cl.getPort()) {  // Se asignan rangos segun el Servidor A que se atiende
                        case 4000 -> {
                            num_inicial = 2;
                            num_final = numero/4;
                        }
                        case 4001 -> {
                            num_inicial = (numero/4)+1;
                            num_final = (numero/4)*2;
                        }
                        case 4002 -> {
                            num_inicial = ((numero/4)*2)+1;
                            num_final = (numero/4)*3;
                        }
                        default -> {
                            num_inicial = ((numero/4)*3)+1;
                            num_final = numero-1;
                        }
                    }// switch
                  
                    // Se envian el numero ingresado en URL y el rango al Servidor A
                    dos.writeLong(numero);
                    dos.writeLong(num_inicial);
                    dos.writeLong(num_final);
                    
                    resp_server_a = dis.readUTF(); // Se recibe respuesta de Servidor A
                    System.out.println("RESPUESTA RECIBIDA DE SERVER-A ("+cl.getInetAddress()+" // "+cl.getPort()+") : "+ resp_server_a);
                    synchronized(obj){
                        if("NO DIVIDE".equals(resp_server_a) && !"NO ES PRIMO".equals(primo)){
                            primo = "ES PRIMO";
                        }else{
                            primo = "NO ES PRIMO";
                        } // ELSE
                        System.out.println("Primo : "+primo);
                        System.out.println("HILOS ENTRADA : "+hilos);
                        if(hilos==3){ // Verifica si ya terminaron todos los hilos
                            String resp_web = "<html><button onclick='alert(\""+primo+"\")'>Ver Resultado</button></html>";
                            salida.println("HTTP/1.1 200 OK");
                            salida.println("Content-type: text/html; charset=utf-8");
                            salida.println("Content-lengh: "+resp_web.length());
                            salida.println();
                            salida.flush();
                            salida.println("Server: Servidor_HTTP.java");
                            salida.flush();
                            salida.println("Date: "+ new Date());
                            salida.flush();
                            salida.println(conexionWeb.getRemoteSocketAddress().toString() );
                            salida.flush();
                            salida.println(resp_web);
                            salida.flush();
                        }else{
                            hilos++;
                            System.out.println("HILOS SALIDA : "+hilos);
                        } // else
                    } // synchronized
                    cl.close(); // Se cierra conexion con Servidor A
                    System.out.println("SOCKET FINALIZADO EN SERVER-A CON (PUERTO :"+cl.getPort()+" // HILO: "+ Thread.currentThread().getName());
                }else{
                    salida.println("HTTP/1.1 404 File Not Found");
                    salida.flush();
                } // else

            }catch(IOException | NumberFormatException e){
            } // catch
        } // run
    } // clase Servidor 
    
    // Funcion main
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(8080); // Socket para servidor HTTP
        Servidor server[] = new Servidor[4]; // Sockets para servidores A
          int j=0;
        
        System.out.println("\n\n*** SERVIDOR HTTP INICIADO *** Puerto: " + s.getLocalPort() );
        System.out.println("Esperando conexion de un cliente desde el navegador... ");
        for(;;){
            Socket conexionWeb = s.accept();
            System.out.println("-- Conexion con Cliente Iniciada -- IP: " + conexionWeb.getInetAddress());

            j=0;
            for(int i=4000; i<4004;i++){
                server[j] = new Servidor(conexionWeb,i);
                j++;
            } // for

            for(int i=0; i<4;i++){
                server[i].start();
            } // for

            for(int i=0; i<4;i++){
                server[i].join();
                System.out.println("HILO #"+i+" ESPERANDO");
            } // for  

            conexionWeb.close();
            System.out.println("-- Conexion con Cliente Finalizada --");
            System.out.println("Esperando conexion de otro cliente desde el navegador... ");
        } // for
    } // main
}
