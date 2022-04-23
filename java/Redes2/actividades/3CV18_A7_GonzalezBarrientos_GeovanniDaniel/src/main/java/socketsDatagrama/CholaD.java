package socketsDatagrama;
import java.net.*;
import java.io.*;

public class CholaD {
    
    public static void main(String[] args){
        try{
            try (DatagramSocket cl = new DatagramSocket()) { // Se inicializa socket para cliente
                System.out.print("***** Cliente Iniciado ***** \nA continuacion escriba un mensaje: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Se inicializa buffer para leer texto del usuario
                String mensaje = br.readLine(); // Se realiza la lectura de texto desde el teclado
                byte[] b = mensaje.getBytes(); // Se realiza la conversion del mensaje en bytes para su posterior envio al servidor
                String dst ="127.0.0.1"; // Se indica la direccion del servidor
                int pto = 2000; // Se indica el puerto del servidor
                DatagramPacket p = new DatagramPacket(b,b.length,InetAddress.getByName(dst),pto); // Se crea el paquete a enviar
                cl.send(p); // Se envia el paquete al servidor
                cl.close(); // Se cierra el socket del cliente 
            }
        }catch(IOException e){
        }// catch
    }// main
}
