package socketsDatagrama;
import java.net.*;
import java.io.*;

public class CholaD {
    
    public static void main(String[] args){
        try{
            DatagramSocket cl = new DatagramSocket();  // Se inicializa socket para cliente
            System.out.print("***** CLIENTE INICIADO ***** \nA continuacion escriba un mensaje: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Se inicializa buffer para leer texto del usuario

            String mensaje = br.readLine(); // Se realiza la lectura de texto desde el teclado

            byte[] b = mensaje.getBytes(); // Convierte el mensaje en bytes para su posterior envio al servidor

            InetAddress dst = InetAddress.getByName("localhost"); // Se indica la direccion del servidor
            int pto = 2000; // Se indica el puerto del servidor

            DatagramPacket p = new DatagramPacket(b,b.length,dst,pto); // Se crea el paquete a enviar
            
            System.out.println("!!! Datagrama enviado a " + dst + " : " + pto + " !!!\n");
            
            cl.send(p); // Se envia el paquete al servidor
            
            System.out.print("A continuacion el servidor reenviara el mensaje para verificar su integridad.\n");
            
            cl.receive(p);// Se recibe el paquete enviado desde el servidor para funcionalidad eco
            
            String msjServer = new String(p.getData(),0,p.getLength()); // Se guarda el mensaje recibido en variable para imprimir en pantalla
            System.out.println("Mensaje recibido desde el servidor : " + msjServer + "\n");
            cl.close(); // Se cierra el socket del cliente 

            System.out.print("***** CONEXION FINALIZADA *****");
        }catch(IOException e){
        }// catch
    }// main
}
