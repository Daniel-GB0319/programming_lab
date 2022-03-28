package ejercicio2;
import java.net.*;
import java.io.*;

public class App{
    public static void main(String [] arc){
        try{
            // Se inicializa el socket del servidor
            ServerSocket s = new ServerSocket(7000);
            System.out.println("!!! Esperando conexion de un cliente !!!");

            for(;;){
                Socket cl = s.accept(); // Se asigna socket a un nuevo cliente
                System.out.println("Conexion establecida desde " + cl.getInetAddress() + " : " + cl.getPort());
                
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                
                byte[] b = new byte[1024];
                String nombre = dis.readUTF();
                System.out.println("!!! Recibimos el archivo: " + nombre + " !!!");
                long tam = dis.readLong();
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));
                
                long recibidos = 0;
                int n,porcentaje;
                
                while(recibidos<tam){
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    recibidos = recibidos + n;
                    porcentaje = (int)(recibidos*100/tam);
                    System.out.print("\n!!! Archivo Recibido !!!");
                } // Termina while
                
                System.out.println("!!! Conexion con Cliente Finalizado!!!\n");
                dos.close();
                dis.close();
                cl.close();
            } // Termina for
        }catch(Exception e){
            e.printStackTrace();
        } // Termina catch
    } // Termina main
}