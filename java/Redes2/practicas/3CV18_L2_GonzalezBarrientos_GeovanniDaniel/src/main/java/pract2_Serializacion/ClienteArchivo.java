package pract2_Serializacion;
import java.awt.HeadlessException;
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
        Estudiante e1; // Se inicializa la variable de tipo Estudiante
        e1 = new Estudiante("Daniel",22,2020123456,9.75,"3CV18",true); // Se le asignan valores a e1
        
        // Se imprimen los atributos de e1 antes de serializacion
        System.out.print("\nLos datos de la clase Estudiante son los siguientes:\n");
        System.out.print("\nNombre: " + e1.nombre);
        System.out.print("\nEdad: " + e1.edad);
        System.out.print("\nBoleta: " + e1.boleta);
        System.out.print("\nCalificaciones: " + e1.calificaciones);
        System.out.print("\nGrupo: " + e1.grupo);
        System.out.print("\nInscrito: " + e1.inscrito);
        
        // Se realiza procedimiento de serializacion
        try{
            BufferedReader entrada;
            // Se inicializa flujo de salida para el objeto que residira en un archivo de texto
            try (FileOutputStream fout = new FileOutputStream("ejemplo.txt"); ObjectOutputStream out = new ObjectOutputStream(fout)) {
                out.writeObject(e1); // Se almacena objeto serializado en fichero
                System.out.println("\n!!! Objeto Serializado !!!");
                System.out.println("\nContenido dentro del nuevo fichero con el objeto serializado\n");
                entrada = new BufferedReader(new FileReader(new File("ejemplo.txt")));
                String ejemplo = null;
                while( (ejemplo = entrada.readLine()) != null){
                    System.out.println(ejemplo + "\n");
                }   out.flush();
            }
            entrada.close();
            
            // Se inicia procedimiento para realizar el envio del fichero con objeto serializado
            JFileChooser jf = new JFileChooser(); // Se inicializa el selector de archivos
            jf.setDialogTitle("Seleccione el Archivo que desea enviar ... "); // Establece el titulo de la ventana
            jf.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo aceptara archivos
            System.out.printf("\nA continuacion debera seleccionar el archivos a enviar... ");
            int r = jf.showOpenDialog(null); // Muestra la ventana del selector

            if(r == JFileChooser.APPROVE_OPTION){ // Procedimiento cuando se haya confirmado los archivos a enviar
                File f = jf.getSelectedFile(); // Variable para almacenar los archivos seleccionados
                long tam = f.length(); // Total de los Archivos a enviar
                    try{
                        DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Se inicializa el flujo de salida
                        DataInputStream dis; // Se declara el que sera el flujo de entrada    
                        System.out.printf("\nEspere mientras enviamos los archivos...  \n");
                        dos.writeLong(tam); // Envia numero de archivos seleccionados
                        dos.flush();


                        String pathArch = f.getAbsolutePath(); // Almacena la ruta absoluta del archivo a enviar
                        String nameArch = f.getName() ; // Almacena el nombre del archivo a enviar

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

                    }catch (IOException e){
                    }// Termina catch Envio archivos
            }// Termina if jFile

        }catch(HeadlessException | IOException e){
        
        }
        
         
    }

    // Funcion para finalizar conexion con servidor
    public static void desconexion(Socket cl) throws IOException{
        cl.close();
        System.out.print("\n********* CONEXION CON SERVIDOR FINALIZADA *********");
    }
}