package numPrimos;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTP_Server {
    static class Servidor extends Thread{ // Clase para Hilos
        Socket conexionWeb = null; // Socket para el cliente en el navegador web
        int puerto; // Puerto para conectarse a un Servidor A
        String name; // Nombre del hilo en cuestion
        static long numero; // Numero obtenido de URL
        static String primo; // Verifica si numero de URL es primo o no
        static Object obj = new Object(); // Objeto para sincronizar los hilos
        
        Servidor(Socket conexionWeb, int puerto, String name){ // Constructor Servidor
            this.conexionWeb = conexionWeb; // Se asigna socket del cliente en navegador web
            this.puerto = puerto; // Puerto para conectarse a un Servidor A
            this.name = name; // Nombre del hilo actual
        } // constructor Servidor
        
        // Procedimientos a realizar en los hilos
        public void run(){ 
            try{
                Thread.currentThread().setName(name);
                if("Hilo-WEB".equals(Thread.currentThread().getName())){ // Procedimiento para hilo de navegador WEB
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(conexionWeb.getInputStream()));
                    PrintWriter salida = new PrintWriter(conexionWeb.getOutputStream());
                    String url = entrada.readLine(); // URL ingresada en navegador
                    
                    if(url.startsWith("GET /es_primo?")){ // Se verifica que URL es correcta  
                        Servidor server[] = new Servidor[4]; // Sockets para servidores A
                        int j=0;
                        
                        // Se obtiene numero de URL por medio de subcadena 
                        String num_url = url.substring(url.indexOf("=")+1, url.lastIndexOf("H")-1); 
                        synchronized(obj){ // Se guarda numero a trabajar por los hilos
                            numero = Long.parseLong(num_url);
                            System.out.println("Numero recibido desde URL: "+numero);
                        } // synchronized
                                               
                        for(int i=4000; i<4004;i++){  //Se crean los hilos para los servidores A  
                            server[j] = new Servidor(conexionWeb,i,"Hilo-SA"+j);
                            j++;
                        } // for

                        for(int i=0; i<4;i++){ //Se inician los hilos para los servidores A
                            server[i].start();
                        } // for

                        for(int i=0; i<4;i++){ //Se crea barrera de hilos
                            server[i].join();
                        } // for  

                        // Mensajes del protocolo HTTP
                        String resp_web = "<html><button onclick='alert(\""+numero+" "+primo+"\")'>Ver Resultado</button></html>";
                        salida.println("HTTP/1.1 200 OK");
                        salida.println("Content-type: text/html; charset=utf-8");
                        salida.println("Content-lengh: "+resp_web.length());
                        salida.println();
                        salida.flush();
                        salida.println("Server: Servidor_HTTP.java\n");
                        salida.flush();
                        salida.println("Date: "+ new Date()+"\n");
                        salida.flush();
                        salida.println(conexionWeb.getRemoteSocketAddress().toString()+"\n" );
                        salida.flush();
                        salida.println(resp_web);
                        salida.flush();
                    }else{ // URL es incorrecta
                        salida.println("HTTP/1.1 404 File Not Found");
                        salida.flush();
                    } // else
                    
                }else{ // Procedimiento para hilos que se conectaran a SERVER A
                    Socket cl = new Socket("localhost", puerto); // Se crea conexion con un Servidor A
                    DataInputStream dis = new DataInputStream(cl.getInputStream() ); // Flujo entrada Servidor A
                    DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Flujo salida Servidor A
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
                    }// switch rangos para servidor a
                  
                    // Se envian el numero ingresado en URL y el rango al Servidor A
                    dos.writeLong(numero);
                    dos.writeLong(num_inicial);
                    dos.writeLong(num_final);
                    
                    String resp_server_a = dis.readUTF(); // Se recibe respuesta de Servidor A
                    // Se almacena resultado de cada servidor A
                    synchronized(obj){
                        if("NO DIVIDE".equals(resp_server_a) && !"NO ES PRIMO".equals(primo)){
                            primo = "ES PRIMO";
                        }else{
                            primo = "NO ES PRIMO";
                        } // ELSE
                    } // synchronized
                    cl.close(); // Se cierra conexion con Servidor A  
                } //else procedimiento para Servidor A
            }catch(IOException | NumberFormatException e){
            } catch (InterruptedException ex) {
                Logger.getLogger(HTTP_Server.class.getName()).log(Level.SEVERE, null, ex);
            } // catch
        } // run
    } // clase Servidor 
    
    // Funcion main
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(8080); // Socket para servidor HTTP
        Servidor serverWeb = null ; // Se inicializa el objeto para thread para el navegador web
       
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 1 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** SERVIDOR HTTP INICIADO *** Puerto: " + s.getLocalPort() );
        System.out.println("Esperando conexion de un cliente desde el navegador... ");
        for(;;){
            try (Socket conexionWeb = s.accept()) {
                System.out.println("\n-- Conexion con Cliente Iniciada -- IP: " + conexionWeb.getInetAddress());
                serverWeb = new Servidor(conexionWeb,0,"Hilo-WEB");
                
                // Se inicia el hilo del navegador Web
                serverWeb.start();
                serverWeb.join();
            } // try
            System.out.println("-- Conexion con Cliente Finalizada --");
            System.out.println("\nEsperando conexion de otro cliente desde el navegador... ");
        } // for
    } // main
}
