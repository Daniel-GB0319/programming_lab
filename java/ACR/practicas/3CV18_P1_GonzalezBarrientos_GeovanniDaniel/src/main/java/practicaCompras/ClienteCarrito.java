package practicaCompras;
import java.io.*; // Flujo de entrada y salida
import java.net.*; // Inicializacion de Sockets
import java.time.*; // Herramientas para trabajar con fechas y horas
import java.time.format.DateTimeFormatter; // Sirve para trabajar con diferentes formatos y zonas horarias
import com.itextpdf.kernel.pdf.PdfDocument;   // Librerias para el manejo de 
import com.itextpdf.kernel.pdf.PdfWriter;     // archivos pdf, ademas de permitir la
import com.itextpdf.layout.Document;          // modificacion y escritura de estos
import com.itextpdf.layout.element.Paragraph; // documetos.

public class ClienteCarrito {
// Funcion Principal
    public static void main(String [] arc) throws IOException, ClassNotFoundException{        
        try{
            Socket cl = null; // Se declara el socket para el cliente
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Se inicializa buffer para leer datos desde teclado
            System.out.println("\t\t\t##### Practica 1 - Carrito de Compras - CLIENTE #####\n");
            System.out.println("----- Gonzalez Barrientos Geovanni Daniel 3CV18 Aplicaciones para Comunicaciones en Red -----\n\n");

            System.out.println("**** CLIENTE INICIADO ****\n");
            System.out.printf("Escriba la direccion del servidor: ");
            String host = br.readLine(); // Se lee la direccion del servidor
            System.out.printf("\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine()); // Se lee el puerto del servidor
         
            cl = new Socket(host,pto); // Crea conexion con servidor segun direccion y puerto ingresados
            System.out.printf("\n!!! CONEXION CON SERVIDOR ESTABLECIDO !!! \n\nEsperando catalogo desde el servidor... ");
            
            menuCliente(cl); // Funcion para realizar operaciones en carrito
            System.out.printf("\n!!! CONEXION CON SERVIDOR FINALIZADO !!!");      
        }catch (IOException | NumberFormatException e){
        }// catch
    }// main
    
    
    // Funcion para realizar operaciones en catalogo
    public static void menuCliente(Socket cl) throws ClassNotFoundException{
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Buffer para realizar lecturas desde teclado
            int menu = -1, auxUser = 0, idUser = 0, cantUser = 0, flag = 0, i = 0, j = 0;
            float subtotal = 0,total = 0; // Variables para realizar calculos al momento de comprar    
            Producto[] catalogo = new Producto[20]; // Se crea array para almacenar el catalogo
            Producto[] carrito = new Producto[20]; // Se crea array para almacenar el carrito del cliente
            Producto auxProd = new Producto(0,"",0,0,""); // Se inicializa auxiliar con valores predeterminados
               
            // Se inicializan los arreglos Carrito y Catalogo con valores predeterminados
            for(i=0;i<catalogo.length;i++){ 
                catalogo[i] = new Producto(0,"",0,0,"");
                carrito[i] = new Producto(0,"",0,0,"");
            } // for
            
            // Se descargan el fichero "DBClient.txt" para asignarlo al array catalogo y las demas imagenes de los productos ofertados
            for(i = 0; i < 6; i++){
                catalogo = downloadCatalogo(cl, catalogo);
            } // for
             
            do{// Menu principal del cliente   
                System.out.print("\n\n\n\n\n\n\n\n\n\n********** MENU PRINCIPAL **********\n");
                System.out.print("1) Ver Catalogo de productos \n2) Ver Carrito \n3) Agregar producto a Carrito \n4) Modificar productos del carrito \n");
                System.out.print("5) Eliminar producto de carrito \n6) Vaciar Carrito \n7) Comprar productos del carrito \n0) Salir del programa \n\n");
                System.out.print("Ingrese el numero correspondiente al inciso y pulse Enter: ");
                menu = Integer.parseInt(br.readLine());
                
                switch(menu){
                    case 1: // Ver Catalogo de Productos
                        verCatalogo(catalogo,carrito,1); // Muestra el catalogo/carrito
                        System.out.print("Presione Enter para Continuar... ");  
                        br.readLine();
                        break;
                        
                    case 2: // Ver Carrito
                        verCatalogo(catalogo,carrito,2); // Muestra el catalogo/carrito
                        System.out.print("Presione Enter para Continuar... ");  
                        br.readLine();
                        break;
                        
                    case 3: // Agregar Producto a Carrito
                        verCatalogo(catalogo,carrito,1);
                        do{
                            System.out.print("Ingrese el ID del producto que desea agregar a su carrito: ");
                            idUser = Integer.parseInt(br.readLine());
                        }while((idUser < 100) || (idUser > 119) );
                        
                        flag = 0;
                        // Bucle para obtener los datos del producto seleccionado en catalogo
                        for(i = 0; i < catalogo.length; i++){
                            if(catalogo[i].id == idUser){ // busca el producto por id
                                auxProd.id = catalogo[i].id;
                                auxProd.name = catalogo[i].name;
                                auxProd.price = catalogo[i].price;
                                auxProd.stock = catalogo[i].stock;
                                auxProd.description = catalogo[i].description;

                                catalogo[i].stock--; // Resta una unidad del stock para agregarlo al carrito

                                if(catalogo[i].stock == 0){ // Verifica si ya no hay existencias del producto en catalogo
                                    catalogo[i] = new Producto(0,"",0,0,""); // Elimina producto de catalogo si ya no hay existencias
                                } // if
                                
                                flag = 3; // Producto ha sido encontrado en catalogo
                                break; 
                            }//if
                        }//for
                        
                        if(flag == 3){ // Producto existe en catalogo
                            flag = 0;
                            // Bucle para ingresar el producto al carrito
                            for(i = 0; i < carrito.length; i++){
                                if(carrito[i].id == auxProd.id){ // ya existe el producto en carrito
                                        carrito[i].stock++;
                                        flag = 1; // Se ha encontrado el producto existente en carrito
                                        break;
                                }else{ // Se encuentra el primer espacio vacio en carrito
                                    if(carrito[i].id == 0 && flag < 1){
                                        auxUser = i; // Almacena posicion de espacio vacio
                                        flag = 2; // Ya se ubico el primer espacio vacio en carrito. Util en caso de no existir producto 
                                    } // if
                                }//else
                            }//for

                            if(flag == 2){ // Producto seleccionado no existe en carrito
                                carrito[auxUser].id = auxProd.id;
                                carrito[auxUser].name = auxProd.name;
                                carrito[auxUser].price = auxProd.price;
                                carrito[auxUser].stock = 1;
                                carrito[auxUser].description = auxProd.description;
                            } // if
                            System.out.print("!!! Producto ha sido agregado al carrito !!!\n");
                        
                        }else{ // Producto seleccionado no existe en catalogo
                            System.out.print("!!! Producto con id \""+idUser+"\" no existe en catalogo.!!! Intente con otro ID.\n");
                        } // else
                          
                        System.out.print("Presione Enter para Continuar... ");  
                        br.readLine();
                        break;
                        
                    case 4: // Modificar Productos
                        verCatalogo(catalogo,carrito,2);
                        System.out.print("Que operacion desea realizar? ( 1) Aumentar cantidad / 2) Restar cantidad ): ");
                        auxUser = Integer.parseInt(br.readLine());
                        
                        switch(auxUser){
                            case 1: // Aumentar cantidad
                                do{
                                    System.out.print("Ingrese el ID del producto que desea modificar su carrito (100 - 119): ");
                                    idUser = Integer.parseInt(br.readLine()); // Lee ID de producto seleccionado
                                }while((idUser < 100) || (idUser > 119) );
                                do{
                                    System.out.print("Ingrese la cantidad de unidades que desea agregar de ese producto (0 = Cancelar operacion): ");
                                    cantUser = Integer.parseInt(br.readLine()); // Lee cantidad a agregar
                                }while(cantUser < 0 );

                                flag = 0;
                                // Bucle para obtener los datos del producto seleccionado en catalogo
                                for(i=0; i<catalogo.length; i++){
                                    if( (catalogo[i].id == idUser ) && (cantUser <= catalogo[i].stock) ){ // busca el producto por id
                                        catalogo[i].stock = catalogo[i].stock - cantUser; // Resta una unidad del stock para agregarlo al carrito
                                        if(catalogo[i].stock <= 0){ // Verifica si ya no hay existencias del producto en catalogo
                                            catalogo[i] = new Producto(0,"",0,0,"");
                                        }// if

                                        // Bucle para ingresar el producto al carrito
                                        for(i = 0; i < carrito.length; i++){
                                            if(carrito[i].id == idUser){ // Busca ID de producto en carrito
                                                carrito[i].stock = carrito[i].stock + cantUser;
                                                flag = 3; // Producto ha sido encontrado y modificado
                                                break;
                                            }// if
                                        }//for
                                        break;
                                    }//if
                                }//for

                                if(flag == 3 && cantUser != 0){ // cantidad ha sido aumentada
                                    System.out.print("!!! Cantidad aumentada con exito !!!\n");
                                    
                                }else{ // Cantidad de producto en catalogo insuficiente o ID no existe 
                                    System.out.print("!!! Cantidad no modificada !!! Verifique que ID exista en carrito y/o exista cantidad suficiente en catalogo.\n");
                                }
                                System.out.print("Presione Enter para Continuar... ");  
                                br.readLine();
                                break;
                                
                            case 2: // Restar Cantidad
                                do{
                                    System.out.print("Ingrese el ID del producto que desea modificar en su carrito (100 - 119): ");
                                    idUser = Integer.parseInt(br.readLine());
                                }while((idUser < 100) || (idUser > 119) );
                                
                                do{
                                    System.out.print("Ingrese la cantidad de unidades que desea restar de ese producto (0 = Cancelar Operacion): ");
                                    cantUser = Integer.parseInt(br.readLine());
                                }while(cantUser < 0);

                                flag = 0;
                                // Bucle para obtener los datos del producto seleccionado en carrito
                                for(i=0; i<carrito.length; i++){
                                    if( (carrito[i].id == idUser ) && (cantUser <= carrito[i].stock) ){ // busca el producto por id
                                        auxProd.id = carrito[i].id;
                                        auxProd.name = carrito[i].name;
                                        auxProd.price = carrito[i].price;
                                        auxProd.stock = carrito[i].stock;
                                        auxProd.description = carrito[i].description;
                                        
                                        carrito[i].stock = carrito[i].stock - cantUser; // Resta las unidades seleccionadas del producto en carrito

                                        if(carrito[i].stock <= 0){ // Verifica si ya no hay existencias del producto en carrito
                                            carrito[i] = new Producto(0,"",0,0,""); // borra el producto del carrito
                                        }// if

                                        flag = 0;
                                        // Bucle para ingresar el producto al catalogo
                                        for(i = 0; i < catalogo.length; i++){
                                            if(catalogo[i].id == auxProd.id){ // ya existe el producto en catalogo
                                                catalogo[i].stock = catalogo[i].stock + cantUser;
                                                flag = 1; // Se ha encontrado existencia de producto en catalogo
                                                break;
                                            }else{
                                                if((catalogo[i].id == 0) && (flag < 1) ){
                                                auxUser = i; // Guarda la posicion del espacio vacio. Util por si producto no existe en catalogo
                                                flag = 2; // Indica que se ha encontrado el primer espacio vacio
                                                }//if
                                            }// else 
                                        }//for
                                        if(flag == 2 ){ // Verifica si producto es nuevo en catalogo
                                            catalogo[auxUser].id = auxProd.id;
                                            catalogo[auxUser].name = auxProd.name;
                                            catalogo[auxUser].price = auxProd.price;
                                            catalogo[auxUser].stock = cantUser;
                                            catalogo[auxUser].description = auxProd.description;
                                        } // if
                                        flag = 3; // Producto ha sido encontrado y modificado
                                        break;
                                    }//if
                                }//for
                                
                                if(flag == 3 && cantUser != 0){ // Cantidad ha sido modificada
                                    System.out.print("!!! Cantidad reducida con exito !!!\n");  
                                }else{ // Producto no existe o cantidad es insuficiente
                                    System.out.print("!!! Cantidad no modificada !!! Verifique que ID y/o cantidad suficiente en carrito existan.\n");
                                }// else
                                System.out.print("Presione Enter para Continuar... ");  
                                br.readLine();
                                break;
                                
                            default:
                                System.out.print("!!! Ingreso una opcion invalida !!!\n");  
                                System.out.print("Presione Enter para reintentar... ");  
                                br.readLine();
                        }// switch
                        break;
                        
                    case 5: // Eliminar Producto de Carrito
                        verCatalogo(catalogo,carrito,2);
                        do{
                            System.out.print("Ingrese el ID del producto que desea eliminar del carrito (100 - 119): ");
                            idUser = Integer.parseInt(br.readLine());
                        }while((idUser < 100) || (idUser > 119) );
                        
                        flag = 0;
                        // Bucle para obtener los datos del producto seleccionado en carrito
                        for(i=0; i<carrito.length; i++){
                            if(carrito[i].id == idUser){ // busca el producto por id
                                // almacena la informacion del producto temporalmente    
                                auxProd.id = carrito[i].id;
                                auxProd.name = carrito[i].name;
                                auxProd.price = carrito[i].price;
                                auxProd.stock = carrito[i].stock;
                                auxProd.description = carrito[i].description;

                                // Elimina los datos del producto en carrito
                                carrito[i] = new Producto(0,"",0,0,"");
                                flag = 3;
                                break;    
                            }//if
                        }//for
                        
                        if(flag == 3){ // Producto existe en carrito
                            flag = 0;        
                            // Bucle para ingresar el producto al catalogo
                            for(i=0; i<catalogo.length; i++){
                                if(catalogo[i].id == auxProd.id){ // ya existe el producto en catalogo
                                    catalogo[i].stock = catalogo[i].stock + auxProd.stock; 
                                    flag = 1; // El producto ya existe en catalogo 
                                    break;
                                }else{
                                    if((catalogo[i].id == 0) && (flag < 1) ){
                                    auxUser = i; // Guarda posicion de primer espacio vacio, en caso de que producto no exista en catalogo
                                    flag = 2; // Indica que encontro un espacio vacio por si producto no existe en catalogo
                                    } // if
                                } // else 
                            }//for

                            if(flag == 2 ){ // Producto es nuevo en catalogo
                                catalogo[auxUser].id = auxProd.id;
                                catalogo[auxUser].name = auxProd.name;
                                catalogo[auxUser].price = auxProd.price;
                                catalogo[auxUser].stock = auxProd.stock;
                                catalogo[auxUser].description = auxProd.description;
                            } // if
                            System.out.print("!!! Producto ha sido eliminado del carrito !!!\n");
                        
                        }else{ // Producto con ID no existe
                            System.out.print("!!! Producto con id \""+idUser+"\" no existe en carrito.!!! Intente con otro ID.\n");
                        }
   
                        System.out.print("Presione Enter para Continuar... ");  
                        br.readLine();
                        break;
                        
                    case 6: // Vacia Carrito
                        verCatalogo(catalogo,carrito,2);
                        do{
                            System.out.print("Desea eliminar todos los productos de su carrito? ( 1 == SI // 2 == NO ): ");
                            auxUser = Integer.parseInt(br.readLine());
                        }while((auxUser < 1) || (auxUser > 2));
                        
                        if(auxUser == 1){ // Se acepta vaciar carrito
                            // Bucle para vaciar carrito
                            for(i = 0; i < carrito.length; i++){
                                if(carrito[i].id >= 100){ // verifica la existencia de un producto   
                                    auxProd.id = carrito[i].id;
                                    auxProd.name = carrito[i].name;
                                    auxProd.price = carrito[i].price;
                                    auxProd.stock = carrito[i].stock;
                                    auxProd.description = carrito[i].description;

                                    // Elimina los datos del producto en carrito
                                    carrito[i] = new Producto(0,"",0,0,"");        
                                    flag = 0;
                                    
                                    // Bucle para ingresar el producto al catalogo
                                    for (j = 0; j < catalogo.length; j++){
                                        if (catalogo[j].id == auxProd.id){ // ya existe el producto en catalogo
                                            catalogo[j].stock = catalogo[j].stock + auxProd.stock;
                                            flag = 1; // Indica que producto existe en catalogo
                                        }else{
                                            if ((catalogo[j].id == 0) && (flag < 1)){
                                            auxUser = j; // Guarda primera posicion vacia por si producto no existe en catalogo
                                            flag = 2; // Indica que producto no existe
                                            } // if
                                        } // else
                                    } //for

                                    if(flag == 2 ){ // Producto es nuevo en catalogo
                                        catalogo[auxUser].id = auxProd.id;
                                        catalogo[auxUser].name = auxProd.name;
                                        catalogo[auxUser].price = auxProd.price;
                                        catalogo[auxUser].stock = auxProd.stock;
                                        catalogo[auxUser].description = auxProd.description;
                                    } // if
                                }//if
                            }//for
                            System.out.print("!!! Carrito se ha vaciado correctamente !!!\n");  
                            
                        }else{ // No se desea vaciar carrito
                            System.out.print("!!! Operacion Cancelada !!!\n");
                        } // else
                        
                        System.out.print("Presione Enter para Continuar... ");  
                        br.readLine();
                        break;
                        
                    case 7: // Comprar productos del carrito
                        total = 0;
                        verCatalogo(catalogo,carrito,2);
                        do{
                            System.out.print("Desea comprar todos los productos de su carrito? ( 1 == SI // 2 == NO ): ");
                            auxUser = Integer.parseInt(br.readLine());
                        }while((auxUser < 1) || (auxUser > 2));
                        
                        if(auxUser == 1){ // Se realiza compra
                            System.out.print("\n\n!!! Procesando su compra !!! Por favor espere... \n");  
                            System.out.print("Generando ticket de compra \"ticket.pdf\"... \n\n");  
                            try{ 
                                String auxiliar; // Guarda el texto a imprimir en PDF
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm:ss"); // Formato de Fecha y Hora
                                final PdfWriter pdfWriter = new PdfWriter("./Cliente/ticket.pdf"); // Archivo PDF destino
                                final PdfDocument pdfDocument = new PdfDocument(pdfWriter); // Flujo de salida a PDF

                                try (Document document = new Document(pdfDocument)){ // Apertura de documento para escribir datos
                                    document.add(new Paragraph("####################### TICKET DE COMPRA #######################"));
                                    document.add(new Paragraph("Fecha de Compra: " + dtf.format(LocalDateTime.now())));

                                    for(i=0; i<carrito.length; i++){ // Bucle para llenar los datos hasta llegar al final de fichero
                                        if(carrito[i].id >= 100){
                                            auxiliar = "ID: " + String.valueOf(carrito[i].id) + "\nProducto: " + carrito[i].name;
                                            auxiliar = auxiliar + "\nPrecio Unitario: $" + String.valueOf(carrito[i].price);
                                            auxiliar = auxiliar + "\nCantidad: " + String.valueOf(carrito[i].stock) + "\nDescripcion: " + carrito[i].description;

                                            subtotal = carrito[i].stock * carrito[i].price;
                                            total = total + subtotal;
                                            auxiliar = auxiliar + "\nSubtotal: $" + String.valueOf(subtotal) + "\n\n";

                                            document.add(new Paragraph(auxiliar));

                                            // Elimina los datos del producto en carrito
                                            carrito[i] = new Producto(0,"",0,0,"");
                                        }// if
                                    }// for
                                    document.add(new Paragraph("Total de la compra: $" + String.valueOf(total)));
                                    document.add(new Paragraph("################# !!! Gracias por su compra !!! ################"));
                                    document.add(new Paragraph("\n*Gonzalez Barrientos Geovanni Daniel\n*Practica 1 \"Carrito de Compras\" \n*3CV18"));
                                    document.close();
                                } // try
                            }catch(NumberFormatException e){
                            }// catch
                            System.out.print("!!! Compra realizada con exito !!! Gracias por realizar su compra.\n");
                            
                        }else{ // Se cancela la compra
                            System.out.print("!!! Operacion Cancelada !!!\n");
                        } // else
                              
                        System.out.print("Presione Enter para continuar... ");  
                        br.readLine();
                        break;
                        
                    case 0:
                        System.out.print("!!! Gracias por utilizar el programa !!!\n");  
                        
                        // Bucle para vaciar carrito
                        for(i = 0; i < carrito.length; i++){
                            if(carrito[i].id >= 100){ // verifica la existencia de un producto    
                                auxProd.id = carrito[i].id;
                                auxProd.name = carrito[i].name;
                                auxProd.price = carrito[i].price;
                                auxProd.stock = carrito[i].stock;
                                auxProd.description = carrito[i].description;

                                // Elimina los datos del producto en carrito
                                carrito[i] = new Producto(0,"",0,0,"");        
                                
                                auxUser = 0;
                                flag = 0;
                                // Bucle para ingresar el producto al catalogo
                                for (j = 0; j < catalogo.length; j++) {
                                    if (catalogo[j].id == auxProd.id){ // ya existe el producto en catalogo
                                        catalogo[j].stock = catalogo[j].stock + auxProd.stock;
                                        flag = 1; // Indica que producto existe en catalogo
                                    }else {
                                        if ((catalogo[j].id == 0) && (flag < 1)) {
                                        auxUser = j; // Guarda posicion vacia en caso de no existir producto
                                        flag = 2; // Indica que producto no existe en catalogo
                                        } // if
                                    } // else
                                } //for

                                if(flag == 2){ // Producto es nuevo en catalogo
                                    catalogo[auxUser].id = auxProd.id;
                                    catalogo[auxUser].name = auxProd.name;
                                    catalogo[auxUser].price = auxProd.price;
                                    catalogo[auxUser].stock = auxProd.stock;
                                    catalogo[auxUser].description = auxProd.description;
                                } // if
                            }//if
                        }//for
                        break;
                        
                    default:
                        System.out.print("!!! Ha ingresado una opcion invalida !!!\n");  
                        System.out.print("Presione Enter para reintentar... ");  
                        br.readLine();
                }// switch menu principal
            }while(menu != 0);// while menu principal
            
            uploadCatalogo(cl, catalogo); // Se actualiza catalogo y se envia al servidor
        }catch(IOException | NumberFormatException e){
        }// catch  
    }// menuCliente

    
    // Metodo para ver catalogo y carrito
    public static void verCatalogo(Producto[] catalogo, Producto[] carrito,int s){        
        if (s == 1) {
            // Muestra el catalogo de productos
            System.out.print("\n--------- Catalogo de Productos ---------\n");
            for (Producto catalogo1 : catalogo) {
                if (catalogo1.id >= 100) {
                    System.out.print("ID: " + catalogo1.id + "  || Producto: " + catalogo1.name + "\n");
                    System.out.print("Precio: $" + catalogo1.price + " || Disponible: " + catalogo1.stock + " unidades\n");
                    System.out.print("Descripcion: " + catalogo1.description + "\n\n");
                } // if
            } // for
            System.out.print("----------------------------------------\n\n");
        } else {
            // Muestra el carrito del cliente
            System.out.print("\n---------------- Carrito ----------------\n");
            for (Producto carrito1 : carrito) {
                if (carrito1.id >= 100) {
                    System.out.print("ID: " + carrito1.id + "  || Producto: " + carrito1.name + "\n");
                    System.out.print("Precio: $" + carrito1.price + " || Cantidad: " + carrito1.stock + " unidades\n");
                    System.out.print("Descripcion: " + carrito1.description + "\n\n");
                } // if
            } // for
            System.out.print("----------------------------------------\n\n");
        } // else
    }// ver catalogo y carrito 
    
    
    // Funcion para descargar el catalogo de productos desde el fichero serializado
    public static Producto[] downloadCatalogo(Socket cl, Producto[] catalogo) throws ClassNotFoundException{
        try{        
            String nameArchivos; // Variable para almacenar el nombre de los archivos entrantes
            String directorio; // Variable para almacenar la ruta absoluta de los archivos entrantes
            byte[] b = new byte[1024];
            DataInputStream dis = new DataInputStream(cl.getInputStream()); // Se inicializa el flujo de entrada
            DataOutputStream dos; // Se declara el flujo de salida
            long tam = dis.readLong(); // Se lee la cantidad de archivos que enviara el servidor
            long i = 0, paquete = 0;
            
            while(i < tam){ // Bucle para recibir archivos del servidor
                directorio = dis.readUTF(); // Se recibe la ruta absoluta del archivo entrante
                nameArchivos = dis.readUTF(); // Se recibe el nombre del archivo entrante
                paquete = dis.readLong();

                // Se inicializa el flujo de salida, indicando el path donde seran escritos los archivos recibidos
                dos = new DataOutputStream(new FileOutputStream(new File("./Cliente/" + nameArchivos))); // Se inicializa el flujo de salida
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
                } // if
                
                //System.out.print("Recibido: " + porcentaje + "%\r");
                System.out.print("\n!!! Archivo \"" + nameArchivos + "\" Recibido desde: " + directorio + " !!!");
                i= i+1;
                dos.flush();
                //dos.close();
            } // termina while
            
            // Se deserializa el objeto para trabajar el catalogo en cliente
            FileInputStream fin = new FileInputStream(new File("./Cliente/DBClient.txt")); 
            ObjectInputStream in = new ObjectInputStream(fin);
            catalogo = (Producto[]) in.readObject();
            return catalogo;
        }catch(IOException e){  
        }// catch
        return catalogo;
    }// downloadCatalogo
 
    
    // Funcion para escribir el catalogo de productos en el fichero
    public static void uploadCatalogo(Socket cl, Producto[] catalogo) throws IOException{
        // Se serializa el catalogo actualizado para ser guardado en un fichero
        FileOutputStream fout = new FileOutputStream(new File("./Cliente/DBClient.txt")); 
        ObjectOutputStream oos = new ObjectOutputStream(fout); 
        oos.writeObject(catalogo); // Se almacena objeto serializado en fichero 
        oos.flush();

        // Se inicia procedimiento para realizar el envio de ficheros
        File f = new File("./Cliente/DBClient.txt"); // Se selecciona el archivo para enviar a servidor
        long tam = 1; // Indica que solo se enviara un archivo
        int i = 0;
        try{
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Se inicializa el flujo de salida
            DataInputStream dis = null; // Se declara el flujo de entrada    
            System.out.printf("\nEspere mientras enviamos el catalogo actualizado al servidor...  \n");
            dos.writeLong(tam); // Envia numero de archivos a enviar
            dos.flush();

            for(i = 0; i < tam ; i++){ // Bucle para enviar archivos seleccionados
                String pathArch = f.getCanonicalPath(); // Almacena la ruta absoluta del archivo a enviar
                String nameArch = f.getName() ; // Almacena el nombre del archivo a enviar
                long paquete = f.length();
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
                } // Termina while
                System.out.print("!!! Archivo \"" + nameArch + "\" enviado al servidor !!!\n");
                dos.flush();
            } //termina for
        }catch (IOException e){
        }// Termina catch
    } // writeCatalogo

}// Cliente carrito
