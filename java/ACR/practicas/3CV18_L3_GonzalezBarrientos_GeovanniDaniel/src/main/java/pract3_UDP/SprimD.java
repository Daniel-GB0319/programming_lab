package pract3_UDP;
import java.net.*;
import java.io.*;

public class SprimD{
    // Funcion Principal
    public static void main(String [] arc) throws ClassNotFoundException{
        try{
            DatagramSocket s = new DatagramSocket(2000); // Se inicializa el socket del servidor
            byte[] b = new byte[2000];
            System.out.println("!!! SERVIDOR INICIADO !!! \nEsperando un cliente... \n");
            
            for(;;){
                DatagramPacket p = new DatagramPacket(b, b.length); // Se inicializa el paquete que se recibira
                
                s.receive(p); // Se recibe el paquete del cliente
                
                System.out.println("!!! CLIENTE CONECTADO !!!"); 
                System.out.println("Datagrama recibido desde: " + p.getAddress() + " : " + p.getPort() );
                
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData())); // Se inicializa el flujo de datos de entrada
                
                int x = dis.readInt(); // Se asigna valor entero recibido del cliente a variable x
                float f = dis.readFloat(); // Se asigna valor flotante recibido del cliente a variable f
                long z = dis.readLong(); // Se asigna valor largo recibido del cliente a variable z
                
                System.out.println("Entero: " + x + " | Flotante: " + f + " | Entero Largo: "+ z);
                
                System.out.println("!!! CONEXION CON CLIENTE FINALIZADO !!! \n\nEsperando a otro cliente... ");
            }// for        
            //s.close(); // Se cierra el socket del servidor
        }catch(IOException e){
        }// catch
    }// main
            
}