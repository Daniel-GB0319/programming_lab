package pract2_Serializacion;
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class ClienteArchivo {
    // Funcion Principal
    public static void main(String [] arc) throws IOException{
        Socket cl = null; // Inicializa socket cliente
        cl = conexion(cl); // Crea conexion con servidor
        enviar(cl); // Envia los archivos
        desconexion(cl); // Finaliza conexion con servidor
        
    } // Termina main
    
    // Funcion para realizar conexion con servidor
    public static Socket conexion(Socket cl){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Se inicializa buffer para leer datos desde teclado
            System.out.printf("Escriba la direccion del servidor: ");
            String host = br.readLine(); // Se lee la direccion del servidor
            System.out.printf("\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine()); // Se lee el puerto del servidor
         
            cl = new Socket(host,pto); // Crea conexion con servidor segun direccion y puerto
            System.out.printf("\n ********* CONEXION CON SERVIDOR INICIADA ********* ");
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
            long tam = f.length; // Total de los Archivos a enviar
            int i = 0;
                try{
                    DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Se inicializa el flujo de salida
                    DataInputStream dis; // Se declara el que sera el flujo de entrada    
                    System.out.printf("\nEspere mientras enviamos los archivos...  \n");
                    dos.writeLong(tam); // Envia numero de archivos seleccionados
                    dos.flush();

                    for(i = 0; i < tam ; i++){ // Bucle para enviar cada uno de los archivos seleccionados
                        String pathArch = f[i].getAbsolutePath(); // Almacena la ruta absoluta del archivo a enviar
                        String nameArch = f[i].getName() ; // Almacena el nombre del archivo a enviar

                        dis = new DataInputStream(new FileInputStream(pathArch)); // Inicializa el flujo de entrada
                        dos.writeUTF(pathArch); // Envia la ruta absoluta del archivo seleccionado al servidor
                        dos.flush();

                        dos.writeUTF(nameArch); // Envia el nombre del archivo seleccionado al servidor
                        dos.flush();

                        byte[] b = new byte[1024];
                        long enviados = 0;
                        int porcentaje;
                        int n = 0;
                        
                        while(enviados<tam){ // Bucle para enviar bytes
                            n = dis.read(b);
                            dos.write(b,0,n);
                            dos.flush();

                            enviados = enviados + n;
                            porcentaje = (int)((enviados*10)/tam);
                            System.out.print("\nEnviado: " + porcentaje + "%\r");
                        } // Termina while
                        System.out.print("\n!!! Archivo " + nameArch + " Enviado !!!\n");
                        //dis.close();
                    } //termina for
                 
                }catch (IOException e){
                }// Termina catch
        } // Termina if jFile
    }

    // Funcion para finalizar conexion con servidor
    public static void desconexion(Socket cl) throws IOException{
        cl.close();
        System.out.print("\n********* CONEXION CON SERVIDOR FINALIZADA *********");
    }
}