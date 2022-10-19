package multMatriz;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultMatrices {
    static class MultiplicarMatrices extends Thread{ // Clase para Hilos
        Socket conexionWeb = null; // Socket para el cliente en el navegador web
        String name; // Nombre del hilo en cuestion
        static int[][] A; // Matriz A
        static int[][] B; // Matriz B
        static int[][] C; // Resultado de la multiplicacion entre A y B
        
        static Object obj = new Object(); // Objeto para sincronizar los hilos
        
        MultiplicarMatrices(Socket conexionWeb, String name){ // Constructor Servidor
            this.conexionWeb = conexionWeb; // Se asigna socket del cliente en navegador web
            this.name = name; // Nombre del hilo actual
        } // constructor Servidor
        
        // Procedimientos a realizar en los hilos
        public void run(){ 
            try{
                Thread.currentThread().setName(name);
                if("Nodo-0".equals(Thread.currentThread().getName())){ // Procedimiento para el nodo 0
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                    

                    MultiplicarMatrices nodos[] = new MultiplicarMatrices[4]; // Sockets para servidores A
                    int j=0;

                    synchronized(obj){ // Se guarda numero a trabajar por los hilos
                 
                    } // synchronized

          
                    nodos[j] = new MultiplicarMatrices(conexionWeb,"Hilo-SA"+j);
                    

                    for(int i=0; i<4;i++){ //Se inician los hilos para los servidores A
                        nodos[i].start();
                    } // for

                    for(int i=0; i<4;i++){ //Se crea barrera de hilos
                        nodos[i].join();
                    } // for  

                
                    
                }else{ // Procedimiento para nodos del 1 al 4
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
                
                    cl.close(); // Se cierra conexion con Servidor A  
                } //else procedimiento para Servidor A
            }catch(IOException | NumberFormatException e){
            } catch (InterruptedException ex) {
                Logger.getLogger(MultMatrices.class.getName()).log(Level.SEVERE, null, ex);
            } // catch // catch // catch // catch
        } // run
    } // clase Servidor 
    
    // Funcion main
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(4000); // Socket para servidor HTTP
        MultiplicarMatrices nodo = null ; // Se inicializa el objeto para thread para el navegador web
       
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 3 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** PROGRAMA INICIADO ***");
        System.out.print("Puerto: " + s.getLocalPort() + " / IP: " + s.getInetAddress() );
       
        // PEDIR QUE NODO ES PARA TRABAJAR
        
        for(;;){
            
            // CREAR HILO
            
                // DENTRO DE RUN SEPAR EN DOS OPCIONES CON UN IF, CUANDO ES NODO 0 Y CUANDO ES DEL 1 AL 4
                // EN NODO 0 FUNGIR COMO CLIENTE CON REINTEBNTOS DE CONEXION Y GENERAR MATRICES. TAMBIEN CONECTARSE A SERVIDORES POR SOCKETS Y ENVIAR MATRIZ POR RANGOS
                    // CADA SOCKET CLIENTE CONECTARSE CON RESPECTIVO IP DE LAS MAQUINAS VIRTUALES y esperar a que todos envien el resultado
                // EN NODO 1 - 4 REALIZR LAS FORMULAS PARA MULTIPLICACION Y CHECKSUM PARCIAL. RECIBIR Aj y B1 , B2, B3, B4, REGRESAR Cj y checsum parcial
            System.out.println("Esperando conexion de un cliente desde el navegador... ");
            Socket conexionWeb = s.accept(); 
            System.out.println("\n-- Conexion con Cliente Iniciada -- IP: " + conexionWeb.getInetAddress());
            
            nodo = new MultiplicarMatrices(conexionWeb,"Hilo-WEB");
                
            nodo.start();
            nodo.join();
            
            System.out.println("-- Conexion con Cliente Finalizada --");
            System.out.println("\nEsperando conexion de otro cliente desde el navegador... ");
        } // for
    } // main
}




/*
package multMatriz;
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
*/




