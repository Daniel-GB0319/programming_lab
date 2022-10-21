package multMatriz;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultMatrices {
    static class MultiplicarMatrices extends Thread{ // Clase para Hilos
        String ipAddress; // Direccion IP del nodo para socket
        int numNodo; // Nodo en cuestion
        static int N; // Tamaño de la matriz cuadrada
        static float[][] A; // Matriz A
        static float[][] B; // Matriz B
        static float[][] C; // Resultado de la multiplicacion entre A y B
        float checksum = 0; // Suma para verificar que el resultado es correcto
        static Object obj = new Object(); // Objeto para sincronizar los hilos
        
        MultiplicarMatrices(String ipAddress, int numNodo){ // Constructor Servidor
            this.ipAddress = ipAddress; // Se asigna socket del cliente en navegador web
            this.numNodo = numNodo; // Nombre del hilo actual
        } // constructor Servidor
        
        // Procedimientos a realizar en los hilos
        public void run(){ 
            try{   
                switch(numNodo){
                    case 0: // Nodo que genera matrices
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); // Lectura de Teclado
                        int i = 0,j = 0; // Variables para utilizar en sentencias for
                        
                        do{ // Se lee el tamaño de la matriz a trabajar
                            System.out.println("\n\n Ingrese el tamaño de la matriz cuadrada (Solo numeros divisibles entre 4): ");
                            N = Integer.parseInt(entrada.readLine()); // Lee el tamaño de la matriz desde teclado
                        }while(N%4 != 0);
                        
                        // Se crean las matrices de tamaño "N"
                        A = new float[N][N];
                        B = new float[N][N];
                        C = new float[N][N];
                        
                        // Se inicializan las matrices A y B
                        for(i=0; i<N ;i++)
                            for(j=0; j<N ; j++){
                                A[i][j] = i + 3*j ;
                                B[i][j] = 2*i - j;
                                C[i][j] = 0;
                            } // for inicializar matrices

                        // Se imprimen las matrices generadas solo si N < 20
                        if(N < 20){
                            System.out.println("Matriz \"A\" Generada:");
                            for(i=0; i<N ;i++){
                                for(j=0; j<N ; j++){
                                    System.out.print(A[i][j] + " ");           
                                } 
                                System.out.println();
                            } // for imprimir matriz A
                        
                            System.out.println("Matriz \"B\" Generada:");
                            for(i=0; i<N ;i++){
                                for(j=0; j<N ; j++){
                                    System.out.print(B[i][j] + " ");           
                                } 
                                System.out.println();
                            } // for imprimir matriz B
                        } // if impirmir matrices
                        
                        // Se transpone la matriz B
                        for(i=0; i<N ;i++)
                            for(j=0; j<N ; j++){
                                float x = B[i][j];
                                B[i][j] = B[j][i];
                                B[j][i] = x;
                            } // for matriz transpuesta
                        
                        //Se crean los hilos para conexion con los demas nodos
                        MultiplicarMatrices hilosConexion[] = new MultiplicarMatrices[3]; // Hilos
                        
                        // Se inicializan los hilos
                        hilosConexion[0] = new MultiplicarMatrices("1.1.1.1",5);
                        hilosConexion[1] = new MultiplicarMatrices("1.1.1.1",5);
                        hilosConexion[2] = new MultiplicarMatrices("1.1.1.1",5);
                        hilosConexion[3] = new MultiplicarMatrices("1.1.1.1",5);
                        
                        for(i=0; i<4;i++){ //Se inicia la ejecucion de hilos
                            hilosConexion[i].start();
                        } // for
                        for(i=0; i<4;i++){ //Se crea barrera de hilos
                            hilosConexion[i].join();
                        } // for  
                        
                        // Se calcula checksum de matriz C. Tambien se imprime matriz en caso de N < 20
                        checksum = 0;
                        System.out.println("Resultado obtenido de A*B con N = "+ N +": ");
                            for(i=0; i<N ;i++){
                                for(j=0; j<N ; j++){
                                    checksum = checksum + C[i][j]; // Se calcula el checksum
                                    if(N < 20){ // Se imprime matriz C solo si es un numero pequeño
                                        System.out.print(C[i][j] + " ");
                                    } // if impirmir matriz C
                                } // for 
                                System.out.println();
                            } // for imprimir / calcular checksum
                        System.out.println("Checksum = "+ checksum);
                        break;
                        
                    case 5: // Se envian las partes de la matriz A y B para cada nodo remoto 
                        // ADAPTAR LINEAS PARA CONEXION ENTRE NODOS 
                        InetAddress ip = InetAddress.getByName(ipAddress); // Se convierte string a formato ip 
                        Socket cl = new Socket(ip, 4000); // Se crea conexion con un Servidor A
                        DataInputStream dis = new DataInputStream(cl.getInputStream() ); // Flujo entrada nodo 0
                        DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Flujo salida nodo 0
   
                        int nodoID = dis.readInt(); // Recibe numero de nodo que tiene el servidor remoto 
                        
                        // Se establecen rangos para dividir Matriz B en 4 partes iguales y se almacenan en paquetes
                        
                        
                        ByteBuffer paqueteB1 = ByteBuffer.allocate( (N*(N/4)) * 4 );// FALTA DEFINIR RANGO
                        ByteBuffer paqueteB2 = ByteBuffer.allocate((N*(N/4)) * 4);// FALTA DEFINIR RANGO
                        ByteBuffer paqueteB3 = ByteBuffer.allocate((N*(N/4)) * 4);// FALTA DEFINIR RANGO
                        ByteBuffer paqueteB4 = ByteBuffer.allocate((N*(N/4)) * 4);// FALTA DEFINIR RANGO
                        ByteBuffer paqueteA1 = ByteBuffer.allocate((N*(N/4)) * 4);// FALTA DEFINIR RANGO
                        
                        for(i=0;i<N/4;i++){ // Se ingresan numeros de B1 en paquete
                            for(j=0;j<N;j++){
                                paqueteB1.putFloat(B[i][j]);
                            }
                        }
                        
                        for(i=((N/4)+1);i<(2*(N/4));i++){ // Se ingresan numeros de B2 en paquete
                            for(j=0;j<N;j++){
                                paqueteB2.putFloat(B[i][j]);
                            }
                        }
                        
                        for(i=((2*(N/4))+1);i<(3*(N/4));i++){ // Se ingresan numeros de B3 en paquete 
                            for(j=0;j<N;j++){
                                paqueteB3.putFloat(B[i][j]);
                            }
                        }
                        
                        for(i=((3*(N/4))+1);i<N;i++){ // Se ingresan numeros de B4 en paquete
                            for(j=0;j<N;j++){
                                paqueteB4.putFloat(B[i][j]);
                            }
                        }
                            
                        byte[] ArrayB1 = paqueteB1.array();
                        byte[] ArrayB2 = paqueteB2.array();
                        byte[] ArrayB3 = paqueteB3.array();
                        byte[] ArrayB4 = paqueteB4.array();
                        
                        // Se envian partes de matriz B 
                        dos.write(ArrayB1); // se envia paquete de bytes con B1
                        dos.write(ArrayB2); // se envia paquete de bytes con B2
                        dos.write(ArrayB3); // se envia paquete de bytes con B3
                        dos.write(ArrayB4); // se envia paquete de bytes con B4
                        
                        // SE ESTABLECE RANGOS CON SWITCH SEGUN N/4 y NODO RECIBIDO DESDE REMOTO
                            // AQUI SE UTILIZA BYTE BUFFER
                        switch(nodoID){
                            case 1:
                                for(i=0;i<N/4;i++){ // Se ingresan numeros de A en paquete
                                    for(j=0;j<N;j++){
                                        paqueteA1.putFloat(A[i][j]);
                                    }
                                }
                                break;
                                
                            case 2 :
                                for(i=((N/4)+1);i<(2*(N/4));i++){ // Se ingresan numeros de A en paquete
                                    for(j=0;j<N;j++){
                                        paqueteA1.putFloat(A[i][j]);
                                    }
                                }
                                break;
                                
                            case 3:
                                for(i=((2*(N/4))+1);i<(3*(N/4));i++){ // Se ingresan numeros de A en paquete 
                                    for(j=0;j<N;j++){
                                        paqueteA1.putFloat(A[i][j]);
                                    }
                                }
                                break;
                                
                            default:
                                for(i=((3*(N/4))+1);i<N;i++){ // Se ingresan numeros de A en paquete
                                    for(j=0;j<N;j++){
                                        paqueteA1.putFloat(A[i][j]);
                                    }
                                }        
                        } // switch
                        
                        byte[] ArrayA = paqueteA1.array();
                        dos.write(ArrayA); // se envia paquete de bytes con B1
                        dos.flush();
                                
                        // SE RECIBE PAQUETE DE MATRIZ C RESULTANTE Y EN SYNCHRONIZED SE GUARDA EN C[][] SEGUN NUMERO DE NODO REMODO
                        
                        synchronized(obj){ // Se guardan resultados de matriz C segun el nodo al que se tiene conexion
                 
                        } // synchronized */

                        cl.close(); // Se cierra conexion con Servidor A  
                        
                        // PARTE DE SOCKETS Y FLUJOS
                        break;
                        
                    default: // Nodos 1-4 que operan con las matrices
                        ServerSocket serverRemoto = new ServerSocket(4000); // Socket para servidor
                        
                        System.out.print("Puerto: " + serverRemoto.getLocalPort() + " / IP: " + serverRemoto.getInetAddress() );
                        
                        Socket clienteRemoto = serverRemoto.accept();
                        DataInputStream disR = new DataInputStream(clienteRemoto.getInputStream() ); // Flujo entrada nodo 1-4
                        DataOutputStream dosR = new DataOutputStream(clienteRemoto.getOutputStream()); // Flujo salida nodo 1-4
                                
                        // SE ENVIA NUMERO DE NODO ASIGNADO PARA LA MAQUINA REMOTA EN CUESTION
                        
                        // SE RECIBE LA PARTE A CON PAQUETE DE BYTES
                        
                        // SE RECIBEN PAQUETES DE MATRIZ B
                        
                        // SE HACE MULTIPLICACION PARA OBTENER PARTES DE MATRIZ C
                        
                        // SE ENVIA PAQUETE DE BYTES CON MATRIZ C RESULTANTE
                        
                        
                        
                        
                        System.out.println("-- Conexion con Cliente Finalizada --");
                        System.out.println("\nEsperando conexion de otro cliente... ");
                } // switch              
            }catch(IOException ex){
                Logger.getLogger(MultMatrices.class.getName()).log(Level.SEVERE, null, ex);
            } // catch
        } // run
    } // clase MultiplicarMatrices
    
    // Funcion main
    public static void main(String[] args) throws Exception {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int numNodo; // Almacena el nodo que representa la maquina 
        
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 3 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** PROGRAMA INICIADO ***");
        
        do{
            System.out.println("\n\n Ingrese el numero del nodo que representa esta instancia (0 - 4): ");
            numNodo = Integer.parseInt(entrada.readLine()); // Lee el nodo desde teclado
        }while(numNodo<0 && numNodo>4);
        
        for(;;){
            MultiplicarMatrices nodoPrincipal = new MultiplicarMatrices("",numNodo); // Se inicializa hilo principal para nodos
            nodoPrincipal.start();
            nodoPrincipal.join();
        } // for
    } // main
}
