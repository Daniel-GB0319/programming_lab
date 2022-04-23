package socketsDatagrama;
import java.net.*;
import java.io.*;

public class SholaD {

    public static void main(String[] args) {
        try{
            try (DatagramSocket s = new DatagramSocket(2000)) { // Se inicializa el socket para el servidor
                System.out.println("***** Servidor Iniciado ***** \nEsperando a un cliente... \n");
                
                for(;;){ // Bucle para atender a los clientes
                    DatagramPacket p = new DatagramPacket(new byte[2000],2000);
                    s.receive(p);// Se recibe el paquete desde el cliente
                    System.out.println("Datagrama recibido desde " + p.getAddress() + " : " + p.getPort() + "\n");
                    String msj = new String(p.getData(),0,p.getLength()); // Se guarda el mensaje en variable para imprimir en pantalla
                    System.out.println("Mensaje recibido : " + msj);
                }// for
            }
        }catch(IOException e){
        }// catch
    }// main
}