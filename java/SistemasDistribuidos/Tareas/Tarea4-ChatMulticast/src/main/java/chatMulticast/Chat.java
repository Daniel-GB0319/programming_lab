package chatMulticast;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Chat {
    static class Worker extends Thread{ // Clase para hilos
        public void run(){ // Procedimientos a realizar en los hilos
            try{ 
                MulticastSocket socket = new MulticastSocket(10000); // Socket para multicast
                InetSocketAddress grupo = new InetSocketAddress(InetAddress.getByName("239.10.10.10"),10000); // IP multicast
                NetworkInterface netInter = NetworkInterface.getByName("em1"); 
                byte[] mensaje; // Mensaje recibido
                
                socket.joinGroup(grupo,netInter); // Uniendose a grupo multicast
                while(true){ // Bucle para recibir mensajes
                    mensaje = recibe_mensaje_multicast(socket,40*8); // Recibe un mensaje
                    System.out.print("\n" +new String(mensaje,StandardCharsets.UTF_8)+ "\nMensaje : ");
                } // for
                
//                socket.leaveGroup(grupo, netInter);
//                socket.close();
            }catch (IOException ex) {  
            } // catch
        } // run
    } // clase MultiplicarMatrices
    
    // Envia los mensajes ingresados por el usuario
    static void envia_mensaje_multicast(byte[] buffer, String ip, int puerto) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        socket.send(new DatagramPacket(buffer, buffer.length,InetAddress.getByName(ip),puerto));
        socket.close();
    } // envia_mensaje_multicast
    
    // Recibe los mensajes del grupo multicast
    static byte[] recibe_mensaje_multicast(MulticastSocket socket, int longitud_mensaje) throws IOException {
        byte[] buffer = new byte[longitud_mensaje];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        return paquete.getData();
    } // recibe_mensaje_multicast
    
    
    // Funcion main
    public static void main(String[] args) throws IOException {
        System.setProperty("java.net.preferIPv4Stack", "true"); // Se utilizan sockets IPv4
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String nombre = args[0]; // Nombre de usuario pasado como parametro
        String mensaje; // Mensaje ingresado desde teclado
        new Worker().start(); // Hilo cliente multicast
        
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 4 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** PROGRAMA INICIADO *** (Usuario: "+nombre+")\n\n");
        mensaje = "!!! Usuario "+nombre+ " conectado !!!";
        envia_mensaje_multicast(mensaje.getBytes(StandardCharsets.UTF_8),"239.10.10.10",10000);
        System.out.print("Mensaje: ");
        
        while(true){
            mensaje = nombre+ " :- " +entrada.readLine(); // Lee mensaje desde teclado
            System.out.println("TEST LOCAL : "+mensaje);
            envia_mensaje_multicast(mensaje.getBytes(StandardCharsets.UTF_8),"239.10.10.10",10000); // Envia mensaje
        }// for
    } // main
}
