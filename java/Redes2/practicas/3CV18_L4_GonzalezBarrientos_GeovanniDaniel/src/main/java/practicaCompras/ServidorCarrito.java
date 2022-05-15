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
            System.out.println("                          ##### Practica 1 - Carrito de Compras #####\n");
            System.out.println("----- Gonzalez Barrientos Geovanni Daniel 3CV18 Aplicaciones para Comunicaciones en Red -----\n\n");

            System.out.println("**** SERVIDOR INICIADO (Puerto: " + s.getLocalPort() + " ) ****\n");
            System.out.println("Esperando un cliente... ");

            while(true){ // Menu Principal del servidor
                cl = s.accept(); // Inicializa el socket para atender al cliente
                System.out.println("*** CONEXION ESTABLECIDA CON CLIENTE DESDE " + cl.getInetAddress() + " : " + cl.getPort() + " ***\n");
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
                list[i] = new Producto(0,"",0,0,"");
            }
            list = readCatalogo(list); // Obtiene el catalogo desde el fichero
            
            // Se inicializa flujo de salida para el objeto serializado que residira en un archivo de texto
            FileOutputStream fout = new FileOutputStream(new File("./Servidor/archCatalogoCliente.txt")); // Se indica el path del nuevo archivo
            ObjectOutputStream oos = new ObjectOutputStream(fout); 
            oos.writeObject(list); // Se almacena objeto serializado en fichero
            oos.flush();
          
            enviarCatalogo(cl); // Envia el catalogo al cliente
            
            System.out.print("Esperando a que cliente finalice su conexion... \n");
            recibirCatalogo(cl); // Recibe el catalogo actualizado cuando cliente se ha desconectado
            
            // Se inicializan los flujos de entrada para deserializar el objeto catalogo recibido del cliente
            FileInputStream fin = new FileInputStream(new File("./Servidor/archCatalogoCliente.txt")); 
            ObjectInputStream ois = new ObjectInputStream(fin);
            list = (Producto[]) ois.readObject();

            writeCatalogo(list); // Funcion para actualizar el fichero catalogo
            
        }catch(IOException | ClassNotFoundException e){
        }// catch
    }// atenderCliente

    
    // Funcion para leer el catalogo de productos desde el fichero base
    public static Producto[] readCatalogo(Producto[] list){
        try{
            try (BufferedReader fin = new BufferedReader(new FileReader(new File("./Servidor/archCatalogo.txt"))) // Se realiza la lectura desde el fichero
            ) {
                if (! (new File("./Servidor/archCatalogo.txt")).exists()){ // Verifica existencia de fichero catalogo
                    System.out.println("!!! No se ha podido acceder a \"archCatalogo.txt\" ");
                    
                }else{
                    String line = null;
                    int i = 0; // Auxiliar para recorrido del array
                    
                    line = fin.readLine();
                    while(line != null){ // Bucle para llenar los datos hasta llegar al final de fichero
                        list[i].id = Integer.parseInt(line); // Ingresa el id
                        
                        line = fin.readLine();
                        list[i].name = line; // Ingresa el nombre
                        
                        line = fin.readLine();
                        list[i].price = Float.parseFloat(line); // Ingresa el precio
                        
                        line = fin.readLine();
                        list[i].stock = Integer.parseInt(line); // Ingresa la cantidad existente
                        
                        line = fin.readLine();
                        list[i].description = line; // Ingresa la descripcion
                        
                        fin.readLine(); // Elimina la linea en blanco   
                       
                        /*System.out.print("ID del producto: " + list[i].id + "\nNombre del Producto: " + list[i].name + "\n");
                        System.out.print("Precio del Producto: " + list[i].price + "\nCantidad Disponible: " + list[i].stock + "\n");
                        System.out.print("Descripcion: " + list[i].description + "\n\n");*/
                        
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
    
    
    public static void enviarCatalogo(Socket cl){
        // Se inicia procedimiento para realizar el envio de ficheros
        JFileChooser jf = new JFileChooser(); // Se inicializa el selector de archivos
        jf.setCurrentDirectory(new File("./Servidor"));
        jf.setMultiSelectionEnabled(true); // Permite seleccion multiple de archivos
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo aceptara archivos
        System.out.printf("\nA continuacion debera seleccionar los archivos a enviar al cliente... ");
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
                    long paquete = f[i].length();
                    
                    dis = new DataInputStream(new FileInputStream(new File(pathArch))); // Inicializa el flujo de entrada
          
                    dos.writeUTF(pathArch); // Envia la ruta absoluta del archivo seleccionado al servidor
                    dos.flush();

                    dos.writeUTF(nameArch); // Envia el nombre del archivo seleccionado al servidor
                    dos.flush();

                    dos.writeLong(paquete); // Envia el nombre del archivo seleccionado al servidor
                    dos.flush();
                    byte[] b = new byte[1024]; // 100 KB
                    long enviados = 0;
                    int porcentaje = 0;
                    int n = 0;

                    while(enviados<paquete){ // Bucle para enviar bytes
                        n = dis.read(b);
                        dos.write(b,0,n);
                        dos.flush();

                        enviados = enviados + n;
                        porcentaje = (int)((enviados*100)/paquete);
                        System.out.print("\nEnviado: " + porcentaje + "%\r");
                    } // Termina while
                    System.out.print("!!! Archivo " + nameArch + " enviado al cliente !!!\n");
                    dos.flush();
                } //termina for
                //dis.close();
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
            long i = 0 , paquete = 0;

            while(i < tam){ // Bucle para recibir cada uno de los archivos del cliente
                directorio = dis.readUTF(); // Se recibe la ruta absoluta del archivo entrante
                nameArchivos = dis.readUTF(); // Se recibe el nombre del archivo entrante 
                paquete = dis.readLong();
                
                System.out.println("\n!!! El Cliente desea enviar: " + nameArchivos + " desde " + directorio + " !!!");
                
                // Se inicializa el flujo de salida, indicando el path donde seran escritos los archivos recibidos
                dos = new DataOutputStream(new FileOutputStream(new File("./Servidor/" + nameArchivos)));  
                long recibidos = 0;
                int n = 0;
                int porcentaje = 0;
                                                     
                for(long j = 0; j<paquete/1024;j++){ // Bucle para la transeferencia de bytes 
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    recibidos = recibidos + n;
                    porcentaje = (int)((recibidos*10)/paquete);
                    
                } // Termina for
                if(paquete%1024!=0){ 
                    b = new byte [(int)paquete%1024];
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    recibidos = recibidos+n;
                    porcentaje = (int)(recibidos*100/paquete);
                }
                System.out.print("Recibido: " + porcentaje + "%\r");
                
                System.out.print("\n!!! Archivo " + nameArchivos + " Recibido !!!\n");
                i= i+1;
                dos.flush();
            } // termina while
            dis.close();
        }catch(IOException e){
        } // Termina catch
    }// recibirCatalogo
 
    
    // Funcion para escribir el catalogo de productos actualizado en el fichero
    public static void writeCatalogo(Producto[] list){
        try{
            int i = 0; // Auxiliar para recorrido del array
            try ( BufferedWriter fout = new BufferedWriter(new FileWriter(new File("./Servidor/archCatalogo.txt"))) ){ // Se realiza la lectura desde el fichero
                if (! (new File("./Servidor/archCatalogo.txt")).exists()){ // Verifica existencia de fichero catalogo
                    System.out.println("!!! No se ha podido acceder a \"archCatalogo.txt\" ");
                    System.exit(0);
                    
                }else{ // Se ha accedido correctamente al catalogo
                    for(i=0; i<list.length; i++){ // Bucle para llenar los datos hasta llegar al final de fichero
                        if(list[i].id >= 100){
                            fout.write(String.valueOf(list[i].id)); // Escribe el ID en fichero
                            fout.newLine();

                            fout.write(list[i].name); // Escribe el nombre de producto en fichero
                            fout.newLine();

                            fout.write( String.valueOf(list[i].price)); // Escribe el precio de producto en fichero
                            fout.newLine();

                            fout.write(String.valueOf(list[i].stock)); // Escribe cantidad del producto en fichero
                            fout.newLine();
                            
                            fout.write(list[i].description); // Escribe descripcion de producto en fichero
                            fout.newLine();
                            fout.newLine(); // 
                        }// if
                    }// for
                }// else
            }           
        }catch(IOException | NumberFormatException e){
        }// catch
    } // writeCatalogo
    
}// Servidor carrito
