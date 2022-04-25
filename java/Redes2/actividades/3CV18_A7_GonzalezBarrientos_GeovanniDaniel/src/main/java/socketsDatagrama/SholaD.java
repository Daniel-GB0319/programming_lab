package socketsDatagrama;
import java.net.*;
import java.io.*;

public class SholaD {

    public static void main(String[] args) {
        try{
            DatagramSocket s = new DatagramSocket(2000);  // Se inicializa el socket para el servidor con el puerto 2000
            System.out.println("***** SERVIDOR INICIADO ***** \nEsperando paquete de un cliente... \n");

            for(;;){ // Bucle para atender a los clientes
                byte[] b = new byte[2000]; 
                int ptoCliente;
                InetAddress dirCliente;

                DatagramPacket p = new DatagramPacket(b,b.length);

                s.receive(p);// Se recibe el paquete desde el cliente

                ptoCliente = p.getPort(); // Se obtine el puerto del cliente
                dirCliente = p.getAddress(); // Se obtine la direccion del cliente

                System.out.println("!!! Datagrama recibido desde " + dirCliente + " : " + ptoCliente + " !!!\n");
                String msj = new String(p.getData(),0,p.getLength()); // Se guarda el mensaje en variable para imprimir en pantalla
                System.out.println("Mensaje recibido del cliente: " + msj + "\n");
                
                System.out.println("A continuacion se reenviara el mensaje recibido al cliente para verificar su integridad.\n");
                
                b = msj.getBytes(); // Se convierte el mensaje recibido en bytes
                
                p.setAddress(dirCliente); // Se asigna la direccion del cliente al paquete
                p.setPort(ptoCliente); // Se asigna el puerto del cliente al paquete
                p.setData(b); // Se asigna el mensaje del cliente al paquete
                p.setLength(b.length); // Se asigna la longitud del mensaje del cliente al paquete
                
                s.send(p); // Se reenvia el paquete recibido al cliente
                System.out.println("!!! Mensaje reenviado al cliente. !!!\n");
                System.out.println("***** CONEXION CON CLIENTE FINALIZADO ***** \n\nEsperando paquete de otro cliente... \n");
            }// for

        }catch(IOException e){
        }// catch
    }// main
}