package practicaCompras;
import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;
import java.util.*;

public class ClienteCarrito {
    
// Funcion Principal
    public static void main(String [] arc) throws IOException{        
        try{
            Socket cl = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Se inicializa buffer para leer datos desde teclado
            System.out.println("##### Practica 1 - Carrito de Compras #####\n");
            System.out.println("----- Gonzalez Barrientos Geovanni Daniel 3CV18 Aplicaciones para Comunicaciones en Red -----\n\n");

            System.out.println("**** CLIENTE INICIADO ****\n");
            System.out.printf("Escriba la direccion del servidor: ");
            String host = br.readLine(); // Se lee la direccion del servidor
            System.out.printf("\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine()); // Se lee el puerto del servidor
         
            cl = new Socket(host,pto); // Crea conexion con servidor segun direccion y puerto
            System.out.printf("\n!!! CONEXION CON SERVIDOR ESTABLECIDO !!!");
            
            menuCliente(cl);
          
        }catch (IOException | NumberFormatException e){
        }// catch
    }// main
    
    
    // Funcion para enviar archivos
    public static void menuCliente(Socket cl){
        try{
            Producto[] catalogo = new Producto[20]; // Se crea array para almacenar el catalogo
            Producto[] carrito = new Producto[20]; // Se crea array para almacenar el carrito
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int menu;
            
             for(int i=0;i<catalogo.length;i++){ // Se inicializa el arreglo con valores predeterminados
                catalogo[i] = new Producto(0,"",0,0);
                carrito[i] = new Producto(0,"",0,0);
            }
            catalogo = downloadCatalogo(catalogo); // se descarga el catalogo y se asigna al arreglo
            
            do{// Menu principal del cliente
                System.out.print("**** MENU PRINCIPAL ****");
                System.out.print("1) Ver Catalogo de productos \n2) Ver Carrito \n3) Agregar producto a Carrito \n4) Eliminar producto de carrito \n");
                System.out.print("5) Vaciar Carrito \n6) Comprar productos del carrito \n0) Salir del programa \n\n");
                
                System.out.print("Ingrese el numero correspondiente al inciso y pulse Enter: ");
                menu = Integer.parseInt(br.readLine());
                
                switch(menu){
                    case 1:
                        verCatalogo(catalogo,carrito,1);
                        break;
                        
                    case 2:
                        verCatalogo(catalogo,carrito,2);
                        break;
                        
                    case 3:
                        gestionarCarrito{catalogo,carrito,3};
                        break;
                        
                    case 4:
                        gestionarCarrito{catalogo,carrito,4};
                        break;
                        
                    case 5:
                        gestionarCarrito{catalogo,carrito,5};
                        break;
                        
                    case 6:
                        // DIRECTAMENTE PONER IMPRESION DE PDF 
                        break;
                        
                    default:
                }
                
                
            }while(menu != 0);// while menu principal
            
            uploadCatalogo(catalogo); // Se actualiza catalogo
        
        }catch(IOException | NumberFormatException e){
        }// catch  
    }// menuCliente

    
    // Metodo para ver catalogo y carrito
    public static void verCatalogo(Producto[] catalogo, Producto[] carrito,int s){
        System.out.print("--------- Catalogo de Productos ---------\n");
        
        if (s == 1){ // Muestra el catalogo de productos
            for(int i=0; i<catalogo.length; i++){
                if(catalogo[i].id >= 100){
                    System.out.print("ID del producto: " + catalogo[i].id + "\nNombre del Producto: " + catalogo[i].name + "\n");
                    System.out.print("Precio del Producto: " + catalogo[i].price + "\nCantidad Disponible: " + catalogo[i].stock + "\n\n");
                }// if
            }// for
        
        }else{ // Muestra el carrito del cliente
            for(int i=0; i<carrito.length; i++){
                if(carrito[i].id >= 100){
                    System.out.print("ID del producto: " + carrito[i].id + "\nNombre del Producto: " + carrito[i].name + "\n");
                    System.out.print("Precio del Producto: " + carrito[i].price + "\nCantidad Disponible: " + carrito[i].stock + "\n\n");
                }// if
            }// for
        }// else
        
        System.out.print("----------------------------------------\n");
        System.out.print("Presione Enter para Continuar... ");  
    }// ver catalogo y carrito
    
    
    public static Producto[] gestionarCarrito(Producto[] catalogo, Producto[] carrito,int s){
        // crear if o switch para las operaciones del carrito
    }// gestionarCarrito
    
    
    // Funcion para descargar el catalogo de productos desde el fichero serializado
    public static Producto[] downloadCatalogo(Producto[] catalogo){
        try{        
            String nameArchivos; // Variable para almacenar el nombre de los archivos entrantes
            String directorio; // Variable para almacenar la ruta absoluta de los archivos entrantes
            byte[] b = new byte[1024];
            DataInputStream dis = new DataInputStream(cl.getInputStream()); // Se inicializa el flujo de entrada 
            DataOutputStream dos; // Se declara el que sera el flujo de salida
            long tam = dis.readLong(); // Se lee la cantidad de archivos que enviara el cliente
            long i = 0;

            while(i < tam){ // Bucle para recibir cada uno de los archivos del cliente
                directorio = dis.readUTF(); // Se recibe la ruta absoluta del archivo entrante
                nameArchivos = dis.readUTF(); // Se recibe el nombre del archivo entrante 
                System.out.println("\n!!! El Servidor desea enviar: " + nameArchivos + " desde " + directorio + " !!!");

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

            // Se inicializan los flujos de entrada para deserializar el objeto
            FileInputStream fin = new FileInputStream("catalogoCliente.txt"); 
            ObjectInputStream in = new ObjectInputStream(fin);
            catalogo = (Producto[]) in.readObject();

            // Se imprime en consola los atributos del objeto deserializado
            System.out.println("\n!!! Objeto Deserializado !!!");
            
            return catalogo;
        }catch(Exception e){  
        }// catch
    }// downloadCatalogo
 
    
    // Funcion para escribir el catalogo de productos en el fichero
    public static void uploadCatalogo(Producto[] catalogo){
        try{
            // Se inicializa flujo de salida para el objeto que residira en un archivo de texto
            FileOutputStream fout = new FileOutputStream("catalogoCliente.txt"); 
            ObjectOutputStream oos = new ObjectOutputStream(fout); 
            oos.writeObject(list); // Se almacena objeto serializado en fichero
            System.out.println("\n!!! Catalogo leido y serializado en catalogoCliente.txt !!!"); 
            oos.flush();
            
             // Se inicia procedimiento para realizar el envio de ficheros
            JFileChooser jf = new JFileChooser(); // Se inicializa el selector de archivos
            jf.setDialogTitle("Seleccione los Archivos que desea enviar ... "); // Establece el titulo de la ventana
            jf.setMultiSelectionEnabled(true); // Permite seleccion multiple de archivos
            jf.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo aceptara archivos
            System.out.printf("\nA continuacion debera seleccionar los archivos a enviar... ");
            int r = jf.showOpenDialog(null); // Muestra la ventana del selector

            if(r == JFileChooser.APPROVE_OPTION){ // Procedimiento cuando se haya confirmado los archivos a enviar
                File[] f = jf.getSelectedFiles(); // Array para almacenar los archivos seleccionados
                long tam = f.length; // Total de los Archivos a enviar
                int i = 0;
                
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
            } // Termina if jFile
        }catch(Exception e){
        }// catch
    } // writeCatalogo
}
