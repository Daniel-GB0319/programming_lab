package ejercicio2;
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class ClienteArchivo {
    public static void main(String [] arc){
        conexion();
        BufferedReader menu = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            enviar();
            System.out.print("Deseas enviar otro archivo? (S/N) (Escriba en MAYUSCULA): ");
            String opcion = menu.readLine();
            if(opcion == "N"){
                break;
            }   
        } // Termina while
        desconexion();
        
    } // Termina main
    
    
    public void conexion(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la direccion del servidor: ");
            String host = br.readLine();
            System.out.printf("\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());
            
            Socket cl = new Socket(host,pto);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void enviar(){
        JFileChooser jf = new JFileChooser();
        jf.setDialogTitle("Seleccione el Archivo que desea enviar ... "); // Establece el titulo de la ventana
        //jf.setMultiSelectionEnabled(true); // Permite seleccion multiple de archivos
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo aceptara archivos
        int r = jf.showOpenDialog(null);

        if(r == JFileChooser.APPROVE_OPTION){
            File f = jf.getSelectedFile(); // Array para almacenar los archivos seleccionados
            String pathArch = f.getPath(); // Array para Direcciones de archivos
            String nameArch = f.getName(); // Array para Nombres de archivos
            long tam = f.length; // Tamaño total del Array de Archivos

            try{
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(cl.getOutputStream())); // Envia flujo de bytes
                DataInputStream dis = new DataInputStream(cl.getInputStream()); // Recibe flujo de bytes 
                InputStream ins = new FileInputStream(f); 

                dos.writeUTF(nameArch);
                dos.flush();

                dos.writeLong(tam);
                dos.flush();

                byte[] b = new byte[1024];
                long enviados = 0;
                int porcentaje;

                int n = ins.read(b);
                while(n! = -1){
                    dos.write(b);
                    dos.flush();
                    n = ins.read(b);
                    enviados = enviados + n;
                    porcentaje = (int)(enviados*100/tam);
                    System.out.print("Enviado: " + porcentaje + "%\r");
                } // Termina while

                System.out.print("\n!!! Archivo Enviado !!!");

                dos.close();
                dis.close();
                
            }catch (IOException e){
                e.printStackTrace();
            }// Termina catch
        } // Termina if jFile
    }

    public void desconexion(){
        cl.close();
        System.out.print("\n!!! Conexion con Servidor Finalizado !!!\n");
    }
}