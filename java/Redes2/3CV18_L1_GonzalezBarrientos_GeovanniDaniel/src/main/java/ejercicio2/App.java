package ejercicio2;
import java.net.*;
import java.io.*;

public class App{
    public Socket cl;
    public ServerSocket s;
    public static void main(String [] arc){
        try{
            // Se inicializa el socket del servidor
            s = new ServerSocket(7000);
            System.out.println("!!! Esperando conexion de un cliente !!!");

            for(;;){
                conexion(s);
                recibir();
            }
         }catch(Exception e){
            e.printStackTrace();
        } // Termina catch 
    } // Termina main
    
    
    public static void conexion(s){
        try{
            // Se inicializa el socket del cliente
            cl = s.accept(); // Se asigna socket a un nuevo cliente
            System.out.println("Conexion establecida desde " + cl.getInetAddress() + " : " + cl.getPort());
        }catch(Exception e){
            e.printStackTrace();
        } // Termina catch
    }
    
    
    public static void recibir(){        
        int i = 0;
        String nameArchivos = "";
        byte[] b = new byte[1024];
        
        try{
            DataInputStream dis = new DataInputStream(cl.getInputStream());
            nameArchivos = dis.readUTF();
            System.out.println("!!! Recibimos el archivo: " + nameArchivos + " !!!");    
            long tam = dis.readLong();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(nameArchivos));

            long recibidos = 0;
            int n = dis.read(b);
            int porcentaje;

            while(n! = -1){
                dos.write(b,0,n);
                dos.flush();
                recibidos = recibidos + n;
                porcentaje = (int)(recibidos*100/tam);
                n = dis.read(b);
                System.out.print("Recibido: " + porcentaje + "%\r");
            } // Termina while

            System.out.print("\n!!! Archivo Recibido !!!");
            System.out.println("!!! Conexion con Cliente Finalizado!!!\n");
            dos.close();
            dis.close();
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        } // Termina catch
    }
    
}