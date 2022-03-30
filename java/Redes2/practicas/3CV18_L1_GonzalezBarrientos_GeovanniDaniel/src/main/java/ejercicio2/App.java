package ejercicio2;
import java.net.*;
import java.io.*;

public class App{
    public static void main(String [] arc){
        try{
            // Se inicializa el socket del servidor
            ServerSocket s = new ServerSocket(7000);
            Socket cl = null;
            
            System.out.println("\n!!! Esperando conexion de un cliente !!!");
            for(;;){
                cl = conexion(s,cl);
                recibir(cl);
                cl.close();
                System.out.println("\n!!! Esperando conexion con otro cliente !!!");
            }
         }catch(IOException e){
        } // Termina catch 
    } // Termina main
    
    
    public static Socket conexion(ServerSocket s, Socket cl){
        try{
            // Se inicializa el socket del cliente
            cl = s.accept(); // Se asigna socket a un nuevo cliente
            System.out.println("\nConexion establecida desde " + cl.getInetAddress() + " : " + cl.getPort());
            return cl;
            
        }catch(IOException e){
        } // Termina catch
        return cl;
    }
    
    
    public static void recibir(Socket cl){        
        String nameArchivos;
        String directorio;
        byte[] b = new byte[1024];
        
        try{
            try (cl;DataInputStream dis = new DataInputStream(cl.getInputStream())) {
                directorio = dis.readUTF();
                nameArchivos = dis.readUTF();
                System.out.println("!!! Recibimos: " + nameArchivos + " desde " + directorio + " !!!");
                long tam = dis.readLong();
                try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nameArchivos))) {
                    long recibidos = 0;
                    int n = 0;
                    int porcentaje;
                    
                    while(recibidos<tam){
                        n = dis.read(b);
                        dos.write(b,0,n);
                        dos.flush();
                        recibidos = recibidos + n;
                        porcentaje = (int)((recibidos*100)/tam);
                        
                        System.out.print("\nRecibido: " + porcentaje + "%\r");
                    } // Termina while
                    
                    System.out.print("\n!!! Archivo(s) Recibido(s) !!!");
                    System.out.println("\n!!! Conexion con Cliente Finalizado!!!\n");
                }
            }
        }catch(IOException e){
        } // Termina catch
    }
    
}