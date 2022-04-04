package pract2_Serializacion;
import java.net.*;
import java.io.*;

public class App{
    // Funcion Principal
    public static void main(String [] arc) throws ClassNotFoundException{
        try{
            ServerSocket s = new ServerSocket(7000); // Se inicializa el socket del servidor
            Socket cl = null;   // Se inicializa el socket del servidor
            
            System.out.println("\nEsperando conexion de un cliente... ");
            
            for(;;){ // Bucle para que el servidor no deje de funcionar
                cl = conexion(s,cl); // Funcion para conectarse con un cliente
                recibir(cl); // Funcion para recibir archivos
                cl.close(); // Funcion para finalizar conexion con cliente
                System.out.println("\nEsperando conexion con otro cliente... ");
            }
         }catch(IOException e){
        } // Termina catch 
    } // Termina main
    
    // Funcion para establecer la conexion con el cliente
    public static Socket conexion(ServerSocket s, Socket cl){
        try{
            cl = s.accept(); // Se asigna socket al nuevo cliente
            System.out.println("\n***** CONEXION ESTABLECIDA DESDE " + cl.getInetAddress() + " : " + cl.getPort() + " *******");
            return cl; // Retorna el socket con la conexion establecida para trabajar en las otras funciones
            
        }catch(IOException e){
        } // Termina catch
        return cl;
    }
    
    // Funcion para realizar todo el proceso de recepcion de archivos
    public static void recibir(Socket cl) throws ClassNotFoundException{        
        String nameArchivo; // Variable para almacenar el nombre de los archivos entrantes
        String directorio; // Variable para almacenar la ruta absoluta de los archivos entrantes
        byte[] b = new byte[1024];
         
        
        try{
            DataInputStream dis = new DataInputStream(cl.getInputStream()); // Se inicializa el flujo de entrada 
            DataOutputStream dos; // Se declara el que sera el flujo de salida
            long tam = dis.readLong(); // Se lee la cantidad de archivos que enviara el cliente
                    
            directorio = dis.readUTF(); // Se recibe la ruta absoluta del archivo entrante
            nameArchivo = dis.readUTF(); // Se recibe el nombre del archivo entrante 
            System.out.println("\n!!! El Cliente desea enviar: " + nameArchivo + " desde " + directorio + " !!!");

            dos = new DataOutputStream(new FileOutputStream(directorio)); // Se inicializa el flujo de salida 
            long recibidos = 0;
            int n = 0;
            int porcentaje;

            while(recibidos<tam){ // Bucle para la transeferencia de bytes 
                n = dis.read(b);
                dos.write(b,0,n);
                dos.flush();
                recibidos = recibidos + n;
                porcentaje = (int)((recibidos*10)/tam);

                System.out.print("Recibido: " + porcentaje + "%\r");
            } // Termina while

            System.out.print("\n!!! Archivo " + nameArchivo + " Recibido !!!\n");
            
            // Se inicializan los flujos de entrada para deserializar el objeto
            try (FileInputStream fin = new FileInputStream(nameArchivo); ObjectInputStream in = new ObjectInputStream(fin)) {
                // Se indica que el objeto a deserializar pertenece a la clase Estudiante
                Estudiante e2 = (Estudiante) in.readObject();

                // Se imprime en consola los atributos del objeto deserializado
                System.out.println("\n!!! Objeto Deserializado !!!");
                System.out.print("Los datos de la clase Estudiante son los siguientes:\n");
                System.out.print("\nNombre: " + e2.nombre);
                System.out.print("\nEdad: " + e2.edad);
                System.out.print("\nBoleta: " + e2.boleta);
                System.out.print("\nCalificaciones: " + e2.calificaciones);
                System.out.print("\nGrupo: " + e2.grupo);
                System.out.print("\nInscrito: " + e2.inscrito);
            }
            System.out.println("\n******* CONEXION CON CLIENTE FINALIZADO *******");
        }catch(IOException e){
        } // Termina catch
    }
    
}