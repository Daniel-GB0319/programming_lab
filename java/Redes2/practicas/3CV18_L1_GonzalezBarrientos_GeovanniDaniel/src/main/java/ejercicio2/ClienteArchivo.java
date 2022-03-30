package ejercicio2;
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class ClienteArchivo {
    public static void main(String [] arc) throws IOException{
        Socket cl = null; // Inicializa socket cliente
        cl = conexion(cl); // Crea conexion con servidor
        enviar(cl); // Envia los archivos
        desconexion(cl); // Finaliza conexion con servidor
        
    } // Termina main
    
    // Funcion para realizar conexion con servidor
    public static Socket conexion(Socket cl){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la direccion del servidor: ");
            String host = br.readLine();
            System.out.printf("\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());
         
            cl = new Socket(host,pto); // Crea conexion con servidor segun direccion y puerto
            
            return cl;
        }catch(IOException | NumberFormatException e){
        }
        return cl;
    }
    
    // Funcion para enviar archivos
    public static void enviar(Socket cl){
        JFileChooser jf = new JFileChooser(); // Se inicializa el selector de archivos
        jf.setDialogTitle("Seleccione el Archivo que desea enviar ... "); // Establece el titulo de la ventana
        jf.setMultiSelectionEnabled(true); // Permite seleccion multiple de archivos
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo aceptara archivos
        System.out.printf("\nA continuacion debera seleccionar los archivos a enviar... ");
        int r = jf.showOpenDialog(null); // Muestra la ventana del selector

        if(r == JFileChooser.APPROVE_OPTION){ // Procedimiento cuando se haya confirmado los archivos a enviar
            File[] f = jf.getSelectedFiles(); // Array para almacenar los archivos seleccionados
            String pathArch = f[0].getAbsolutePath(); // Directorio de archivos
            String nameArch; // Nombres de archivos
            nameArch = f[0].getName() ;
            long tam = f.length; // Tamaño total de los Archivos

            System.out.printf("\nEspere mientras enviamos los archivos... ");
            try{
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Envia flujo de bytes
                DataInputStream dis = new DataInputStream(new FileInputStream(pathArch)); // Recibe flujo de bytes 

                dos.writeUTF(pathArch); // Envia el directorio de trabajo
                dos.flush();
                
                dos.writeUTF(nameArch); // Envia nombres de archivos
                dos.flush();

                dos.writeLong(tam); // Envia el tamano total de los archivos a enviar
                dos.flush();

                byte[] b = new byte[1024];
                long enviados = 0;
                int porcentaje;
                int n = 0;
                
                while(enviados<tam){ // Bucle para enviar archivos
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    
                    enviados = enviados + n;
                    porcentaje = (int)((enviados*100)/tam);
                    System.out.print("\nEnviado: " + porcentaje + "%\r");
                } // Termina while

                System.out.print("\n!!! Archivo(s) Enviado(s) !!!");

                dos.close();
                dis.close();
                
            }catch (IOException e){
            }// Termina catch
        } // Termina if jFile
    }

    // Funcion para finalizar conexion con servidor
    public static void desconexion(Socket cl) throws IOException{
        cl.close();
        System.out.print("\n!!! Conexion con Servidor Finalizado !!!\n");
    }
}