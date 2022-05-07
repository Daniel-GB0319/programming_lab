package practicaCompras;
import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;

public class ServidorCarrito {
    
// Funcion Principal
    public static void main(String [] arc) throws IOException{        
        try{
            ServerSocket s = new ServerSocket(3080); // Inicia el servidor
            Socket cl = null;
            System.out.println("##### Practica 1 - Carrito de Compras #####\n");
            System.out.println("----- Gonzalez Barrientos Geovanni Daniel 3CV18 Aplicaciones para Comunicaciones en Red -----\n\n");

            System.out.println("**** SERVIDOR INICIADO (" + s.getInetAddress() + " : " + s.getLocalPort() + " ) ****\n");
            System.out.println("Esperando un cliente... \n");

            while(true){ // Menu Principal del servidor
                cl = s.accept(); // Inicializa el socket para atender al cliente
                System.out.println("*** CONEXION ESTABLECIDA DESDE " + cl.getInetAddress() + " : " + cl.getPort() + " ***\n");
                atenderCliente(cl);
                cl.close(); // Funcion para finalizar conexion con cliente
                System.out.print("*** CONEXION CON CLIENTE FINALIZADA ***\n");
                System.out.println("Esperando conexion con otro cliente... \n");
            }// while
        }catch (IOException e){
        }// catch
    }// main
    
    // Funcion para enviar archivos
    public static void atenderCliente(Socket cl){
        try{
            Producto[] list = new Producto[20]; // Se crea array para gestionar el catalogo internamente
            for(int i=0;i<list.length;i++){ // Se inicializa el arreglo con valores predeterminados
                list[i] = new Producto(0,"",0,0);
            }
            
            list = readCatalogo(list);
            
            // Se inicializa flujo de salida para el objeto que residira en un archivo de texto
            FileOutputStream fout = new FileOutputStream("catalogoCliente.txt"); 
            ObjectOutputStream oos = new ObjectOutputStream(fout); 
            oos.writeObject(list); // Se almacena objeto serializado en fichero
            System.out.println("\n!!! Catalogo leido y serializado en catalogoCliente.txt !!!"); 
            oos.flush();
          
            enviarCatalogo(cl);
            
            recibirCatalogo(cl);
            
            // Se inicializan los flujos de entrada para deserializar el objeto
            FileInputStream fin = new FileInputStream("catalogoCliente.txt"); 
            ObjectInputStream ois = new ObjectInputStream(fin);
            list = (Producto[]) ois.readObject();

            // Se imprime en consola los atributos del objeto deserializado
            System.out.println("\n!!! Objeto Deserializado !!!");
            writeCatalogo(list);
            
        }catch(IOException | ClassNotFoundException e){
        }// catch
    }// enviarCatalogo

    
    public static void enviarCatalogo(Socket cl){
        // Se inicia procedimiento para realizar el envio de ficheros
        //File[] arch = null;
        //arch.;
        JFileChooser jf = new JFileChooser(); // Se inicializa el selector de archivos
        
        jf.setCurrentDirectory(new File("."));
        //jf.setSelectedFiles(arch);
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
    }// enviarCatalogo
    
    public static void recibirCatalogo(Socket cl){
        String nameArchivos; // Variable para almacenar el nombre de los archivos entrantes
        String directorio; // Variable para almacenar la ruta absoluta de los archivos entrantes
        byte[] b = new byte[1024];

        try{
            DataInputStream dis = new DataInputStream(cl.getInputStream()); // Se inicializa el flujo de entrada 
            DataOutputStream dos; // Se declara el que sera el flujo de salida
            long tam = dis.readLong(); // Se lee la cantidad de archivos que enviara el cliente
            long i = 0;

            while(i < tam){ // Bucle para recibir cada uno de los archivos del cliente
                directorio = dis.readUTF(); // Se recibe la ruta absoluta del archivo entrante
                nameArchivos = dis.readUTF(); // Se recibe el nombre del archivo entrante 
                System.out.println("\n!!! El Cliente desea enviar: " + nameArchivos + " desde " + directorio + " !!!");

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
                System.out.print("\n!!! Archivo " + nameArchivos + " Recibido !!!\n");
                i= i+1;
            } // termina while
        }catch(IOException e){
        } // Termina catch
    }// recibirCatalogo
    
    // Funcion para leer el catalogo de productos desde el fichero
    public static Producto[] readCatalogo(Producto[] list){
        try{
            try (BufferedReader fin = new BufferedReader(new FileReader(new File("catalogo.txt"))) // Se realiza la lectura desde el fichero
            ) {
                if (! (new File("catalogo.txt")).exists()){ // Verifica existencia de fichero catalogo
                    System.out.println("!!! No se ha podido acceder a \"catalogo.txt\" ");
                    System.exit(0);
                }else{
                    String line = null;
                    int i = 0; // Auxiliar para recorrido del array
                    
                    line = fin.readLine();
                    while(line != null){ // Bucle para llenar los datos hasta llegar al final de fichero
                        list[i].setID(Integer.parseInt(line)); // Ingresa el id
                        
                        line = fin.readLine();
                        list[i].setName(line); // Ingresa el nombre
                        
                        line = fin.readLine();
                        list[i].setPrice(Float.parseFloat(line)); // Ingresa el precio
                        
                        line = fin.readLine();
                        list[i].setStock(Integer.parseInt(line)); // Ingresa la cantidad existente
                        
                        fin.readLine(); // Elimina la linea en blanco
                        line = fin.readLine(); // Busca el id de otro producto
                        i++;  
                    }// while
                    return list;
                }// else
            }
        }catch(IOException | NumberFormatException e){
        }// catch
        return list;
    }// readCatalogo
 
    
    // Funcion para escribir el catalogo de productos en el fichero
    public static void writeCatalogo(Producto[] list){
        try{
            int i = 0; // Auxiliar para recorrido del array
            try (BufferedWriter fout = new BufferedWriter(new FileWriter(new File("catalogo.txt"))) // Se realiza la lectura desde el fichero
            ) {
                if (! (new File("catalogo.txt")).exists()){ // Verifica existencia de fichero catalogo
                    System.out.println("!!! No se ha podidio acceder a \"catalogo.txt\" ");
                    System.exit(0);
                    
                }else{
                    for(i=0; i<list.length; i++){ // Bucle para llenar los datos hasta llegar al final de fichero
                        fout.write(list[i].id);
                        fout.newLine();
                        
                        fout.write(list[i].name);
                        fout.newLine();
                        
                        fout.write( String.valueOf(list[i].price));
                        fout.newLine();
                        
                        fout.write(list[i].stock);  
                        fout.newLine();
                        fout.newLine();
                    }// for
                }// else
            }
            
        }catch(IOException | NumberFormatException e){
        }// catch
    } // writeCatalogo
}
