package pract3;
import java.net.*;
import java.io.*;

public class CprimD{
    // Funcion Principal
    public static void main(String [] arc) throws ClassNotFoundException{
        try{
            System.out.println("!!! CLIENTE INICIADO !!! \nEnviando datos al servidor... \n");
            int pto = 2000; // Se declara el puerto destino
            InetAddress dst = InetAddress.getByName("localhost"); // Se declara la direccion del servidor
            try (DatagramSocket cl = new DatagramSocket() ){ // Se inicializa el socket del cliente
                ByteArrayOutputStream baos = new ByteArrayOutputStream(); // Se declara el array de bits de salida
                DataOutputStream dos = new DataOutputStream(baos); // Se declara el flujo de salida
               
                System.out.println("Valor Entero a enviar: 4");
                dos.writeInt(4); //Se escribe un valor entero en el flujo de salida 
                dos.flush(); // Se limpia la variable dos

                System.out.println("Valor Flotante a enviar: 4.1");
                dos.writeFloat(4.1f); //Se escribe un valor flotante en el flujo de salida
                dos.flush(); // Se limpia la variable dos

                System.out.println("Valor Long a enviar: 720000");
                dos.writeLong(720000); //Se escribe un valor largo en el flujo de salida
                dos.flush(); // Se limpia la variable dos

                byte[] b = baos.toByteArray(); // Se asigna el valor de baos en un arreglo de bytes que seran enviados 

                DatagramPacket p = new DatagramPacket(b,b.length,dst,pto); // Se prepara el paquete a enviar con el contenido y los datos del servidor
                
                cl.send(p); // Se envia el paquete
                System.out.println("Datagrama enviado a: " + dst + " : " + pto + "\n");
                
                cl.close();// Se cierra el socket del cliente
                System.out.println("!!! CONEXION CON SERVIDOR FINALIZADO !!!");
            } // try                   
        }catch(IOException e){
        }// catch
    }// main
            
}