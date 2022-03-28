package ejercicio2;
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;


public class ClienteArchivo {
    public static void main(String [] arc){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la direccion del servidor: ");
            String host = br.readLine();
            System.out.printf("\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());
            
            Socket cl = new Socket(host,pto);
            
            JFileChooser jf = new JFileChooser();
            int r = jf.showOpenDialog(null);
            
            if(r == JFileChooser.APPROVE_OPTION){
                File f = jf.getSelectedFile(); // Manejador
                String archivo = f.getAbsolutePath(); // Direccion
                String nombre = f.getName(); // Nombre
                long tam = f.length(); // Tamaño
                
                
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Envia flujo de bytes
                DataInputStream dis = new DataInputStream(cl.getInputStream()); // Recibe flujo de bytes 
                
                
                dos.writeUTF(nombre);
                dos.flush();
                dos.writeLong(tam);
                dos.flush();
                
                byte[] b = new byte[1024];
                long enviados = 0;
                int porcentaje, n;
                
                while(enviados<tam){
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    enviados = enviados + n;
                    porcentaje = (int)(enviados*100/tam);
                    System.out.print("Enviado: " + porcentaje + "%\r");
                } // Termina while
                
                System.out.print("\n!!! Archivo Enviado !!!");
                System.out.print("\n!!! Conexion con Servidor Finalizado !!!\n");
                
                dos.close();
                dis.close();
                cl.close();
            } // Termina if
        }catch(Exception e){
            e.printStackTrace();
        }
    } // Termina main
}
