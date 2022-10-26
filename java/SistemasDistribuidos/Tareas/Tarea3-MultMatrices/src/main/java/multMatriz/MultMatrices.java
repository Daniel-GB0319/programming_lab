package multMatriz;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultMatrices {
    public static class Matriz implements Serializable {
        float[][] matriz; // Contenido Matriz
        String name; // Nombre de la matriz
        int M,N; // Tamaño de la matriz cuadrada
        transient float checksum = 0; // Suma para verificar resultado
        transient int i, j, k; // Auxiliares

        public Matriz(int M, int N, String name){ // Crea la matriz
           this.M = M;
           this.N = N;
           this.name = name;
           matriz = new float[M][N];
        } // matriz

        public void initMatriz(){ // Inicializa la matriz
            for(i=0; i<M ;i++)
                for(j=0; j<N ; j++){
                    switch(name){
                        case "A": // Matriz A
                            matriz[i][j] = i + 3*j; break;
                        case "B": // Matriz B
                            matriz[i][j] = 2*i - j; break;
                        default: // Matriz C
                            matriz[i][j] = 0;
                    } // switch
                } // for
        } // initMatriz
        
        public void printMatriz(){ // Imprime la matriz
            if(N < 15){ // Se puede imprimir
                System.out.println("\n\nMatriz "+name+": ");
                for(i=0; i<M ;i++){
                    for(j=0; j<N ; j++){
                        System.out.print(matriz[i][j] + "  ");           
                    } 
                    System.out.println();
                } // for

            }else{ // No se puede imprimir
                System.out.println("!! Matriz demasiado grande, no se puede imprimir !! N="+N);
            } // else
        }// printMatriz

        public void traspMatriz(){ // Calcula traspuesta de la matriz
            for(i=0; i<M ;i++)
                for(j=0; j<N ; j++){
                    float x = matriz[i][j];
                    matriz[i][j] = matriz[j][i];
                    matriz[j][i] = x;
                } // for
        } // traspuestaMatriz
        
        public void calcChecksum(){ // Calcula el checksum
            checksum = 0;
            for(i=0; i<M ;i++)
                for(j=0; j<N ; j++)
                    checksum = checksum + matriz[i][j]; 

            System.out.println("\n!! CHECKSUM CALCULADO = "+ checksum+" !!");
        } // calcChecksum
        
        public void multiplicarMatriz(Matriz M1, Matriz M2){ // Multiplica 2 matrices
            for(i=0; i<M; i++)
                for(j=0; j<N; j++)
                    for(k=0; k<N; k++)
                        matriz[i][j] = matriz[i][j] + (M1.matriz[i][k] * M2.matriz[k][j]);
        } // multiplicarMatriz 
        
        public void copiarMitad(Matriz aux, int x){ // Copia mitad de una matriz
            for(i=0; i<M ;i++)
                for(j=0; j<N ; j++)
                    switch(x){
                        case 1: // Matriz A1 y A2
                            matriz[i][j] = aux.matriz[i][j]; break;
                        default: // Matriz A3 y A4
                            matriz[i][j] = aux.matriz[((M/2)+1) + i][j];
                    } // switch
        } // copiarMitad
    }// Clase Matriz
    
    
    // Clase para hilos
    static class ThreadMatriz extends Thread{
        String ipAddress; // Direccion IP del nodo
        int numNodo; // Numero del nodo en ejecución
        static Object obj = new Object(); // Objeto para sincronizar los hilos
        Matriz A, B; // Matrices
        static Matriz C; // Matrices
        int N; // Tamaño de Matriz
        
        ThreadMatriz(String ipAddress, int numNodo, Matriz A, Matriz B, Matriz C, int N){ // Constructor Servidor
            this.ipAddress = ipAddress; // Se asigna IP para conexion con Nodo Remoto
            this.numNodo = numNodo; // Numero de nodo
            this.A = A;
            this.B = B;
            this.C = C;
            this.N = N;
        } // constructor Servidor
        
        // Procedimientos a realizar en los hilos
        public void run(){ 
            try{ 
                if(numNodo == 0){ // Instrucciones para enviar partes de matriz A y B a cada nodo remoto (1 y 2) 
                    InetAddress ip = InetAddress.getByName(ipAddress); // Se convierte string a formato ip 
                    Socket cl;
                    
                    for(;;){ // Se implementa los reintentos de conexion
                        try{
                            cl = new Socket(ip,4000); // Conexion a servidor
                            break;
                        }catch(IOException e){
                            Thread.sleep(1000); // Pausa de 1s
                        } // catch
                    }// for
                    
                    DataInputStream dis = new DataInputStream(cl.getInputStream()); // Flujo de entrada desde cliente
                    DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); // Flujo salida
                    
                    int nodoID = dis.readInt(); // Recibe numero de nodo que tiene el servidor remoto 
                    dos.writeInt(N); // Envia tamaño de matriz (N)
                    dos.flush();

                    // Se crean matrices para guardar secciones de Matriz A y C
                    Matriz auxA = new Matriz( N/2,N,"A_Parcial");
                    Matriz auxC = new Matriz( N/2,N,"C_Parcial");
                    auxA.copiarMitad(A,nodoID);
                    auxC.initMatriz();
                    
                    synchronized(obj){// Se envian matrices a nodos remotos
                        enviarMatriz(dis, dos, B); // Envia matriz B
                        enviarMatriz(dis, dos, auxA); // Envia matriz parcial A

                        // Se recibe las parte de resultado en matriz C desde nodo remoto
                        auxC = recibirMatriz(dis, dos, auxC);

                        // Se guarda resultado parcial de matriz C en Nodo Local 
                        for(int i=0; i<(N/2) ;i++)
                            for(int j=0; j<N ;j++)
                                if(nodoID == 1){
                                    C.matriz[i][j] = auxC.matriz[i][j];
                                }else{
                                        C.matriz[(N/2)+i][j] = auxC.matriz[i][j];
                                } // else
                            
                        FileOutputStream fout = new FileOutputStream(new File("./matriz.txt"));
                        ObjectOutputStream oos = new ObjectOutputStream(fout); 
                        oos.writeObject(C); // Se almacena objeto serializado en fichero
                        oos.flush();
                    } // synchronized */
                    dis.close();
                    dos.close();
                    cl.close(); // Se cierra conexion con Servidor A  
                   
                }else{ // Nodos 1 y 2 que operan con las matrices
                    ServerSocket serverRemoto = new ServerSocket(4000); // Socket para servidor
                    System.out.println("\nEsperando conexion de un cliente... ");

                    Socket clR = serverRemoto.accept(); // Se crea conexion con un Servidor A
                    DataInputStream disR = new DataInputStream(clR.getInputStream()); // Flujo de entrada desde cliente
                    DataOutputStream dosR = new DataOutputStream(clR.getOutputStream()); // Flujo salida
                    System.out.println("\n-- Conexion con Cliente Iniciada -- IP: " + clR.getInetAddress());

                    
                    dosR.writeInt(numNodo); // Recibe numero de nodo que tiene el servidor remoto 
                    dosR.flush();
                    N = disR.readInt(); // Recibe tamaño de matriz (N)

                    // Se crean matrices para guardar secciones de Matriz A y C 
                    Matriz auxA = new Matriz( N/2,N,"A_Parcial");
                    Matriz auxC = new Matriz( N/2,N,"C_Parcial");
                    auxA.initMatriz();
                    auxC.initMatriz();
                    
                    // Se reciben matrices de nodo local
                    B = recibirMatriz(disR, dosR, B);
                    auxA = recibirMatriz(disR, dosR, auxA);
                    
                    // Se realiza multiplicacion A_Parcial * B
                    auxC.multiplicarMatriz(auxA, B);
                    
                    auxA.printMatriz();
                    B.printMatriz();
                    auxC.printMatriz();
                    
                    // Se envian las partes resultantes de matriz C hacia nodo local
                    enviarMatriz(disR, dosR, auxC);

                    clR.close();
                    serverRemoto.close();
                    System.out.println("-- Conexion con Cliente Finalizada --");
                    System.out.println("\nEsperando conexion de otro cliente... ");
                }           
            }catch(IOException ex){
                Logger.getLogger(MultMatrices.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MultMatrices.class.getName()).log(Level.SEVERE, null, ex);
            } // catch
        } // run
    } // clase MultiplicarMatrices
    
    
    // Envia matrices
    public static void enviarMatriz(DataInputStream dis, DataOutputStream dos, Matriz y) throws Exception{
        // Se serializa matriz en un fichero
        File archivo = new File("./matriz.txt");
        FileOutputStream fout = new FileOutputStream(archivo);
        ObjectOutputStream oos = new ObjectOutputStream(fout); 
        oos.writeObject(y); // Se almacena objeto serializado en fichero
        oos.flush();
        int paquete = (int) archivo.length(); // Obtiene el tamaño del archivo a enviar
        
        // Se envia archivo con objeto serializado
        dis = new DataInputStream(new FileInputStream(archivo));
        dos.writeUTF(archivo.getName()); // Envia el nombre del archivo al servidor
        dos.flush();
        dos.writeInt(paquete); // Envia el tamaño del archivo al servidor
        dos.flush();

        // Se inicia envio de archivos en paquetes
        byte[] b = new byte[1024]; // Tamaño del paquete
        long enviados = 0;
        int n = 0;
        while(enviados<paquete){ // Bucle para enviar paquetes
            n = dis.read(b);
            dos.write(b,0,n);
            dos.flush();
            enviados = enviados + n;
        } // Termina while
        dos.flush();
        System.out.println("\n!! Archivo "+archivo.getName()+" enviado al Servidor. !!");
    } // enviarMatriz
    
    
    // Recibe matrices
    public static Matriz recibirMatriz(DataInputStream dis, DataOutputStream dos, Matriz y) throws Exception{
        String nameArch = dis.readUTF(); // Recibe el nombre del archivo entrante
        int paquete = dis.readInt(); // Se recibe el tamaño del archivo entrante
        dos = new DataOutputStream(new FileOutputStream(new File("./"+nameArch))); // Flujo de salida

        // Procedimiento para realizar la descarga del archivo
        byte[] b = new byte[1024]; // Tamaño de los paquetes a recibir
        int n = 0;

        for(long j = 0; j < paquete/1024;j++){ // Bucle para la descarga de paquetes
            n = dis.read(b);
            dos.write(b,0,n);
            dos.flush();
        } // Termina for

        if(paquete%1024!=0){ // Verifica si ha llegado el ultimo paquete desde cliente
            b = new byte [(int)paquete%1024];
            n = dis.read(b);
            dos.write(b,0,n);
            dos.flush();
        } // if
        dos.flush();
        
        // Recupera el objeto original previamente serializado
        FileInputStream fin = new FileInputStream(new File("./matriz.txt")); 
        ObjectInputStream ois = new ObjectInputStream(fin);
        y = (Matriz) ois.readObject();
        System.out.println("\n!! Archivo "+nameArch+" Recibido. !!");
        
        return y;
    } // recibirMatriz
    
    
    // Funcion main
    public static void main(String[] args) throws Exception {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int numNodo; // Almacena el nodo que representa la maquina 
        int N = 0; // Tamaño de Matriz Cuadrada
        int i = 0; // Variables auxiliares
        
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 3 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** PROGRAMA INICIADO ***");
        
        do{
            System.out.print("\n\n Ingrese el numero del nodo que representa esta instancia (0 - 2): ");
            numNodo = Integer.parseInt(entrada.readLine()); // Lee el nodo desde teclado
        }while(numNodo<0 && numNodo>2);
        
        for(;;){
            if(numNodo == 0){ // Instrucciones para Nodo 0 (Máquina Local)
                do{ // Se lee el tamaño de la matriz a trabajar
                    System.out.print("\n\n Ingrese el tamaño de la matriz cuadrada (Solo numeros divisibles entre 4): ");
                    N = Integer.parseInt(entrada.readLine()); // Lee el tamaño de la matriz desde teclado
                }while(N%4 != 0);

                // Se crean las matrices de tamaño "N" y se inicializan
                Matriz A = new Matriz(N,N,"A");
                Matriz B = new Matriz(N,N,"B");
                Matriz C = new Matriz(N,N,"C");
                A.initMatriz();
                B.initMatriz();
                C.initMatriz();

                // Se imprimen las matrices generadas solo si N < 15
                A.printMatriz();
                B.printMatriz();

                // Se transpone la matriz B
                B.traspMatriz();

                //Se crean los hilos para conexion con los demas nodos
                ThreadMatriz[] hiloClient = new ThreadMatriz[2]; // Inicializar Hilos
                hiloClient[0] = new ThreadMatriz("20.169.3.31",0,A,B,C,N); // Conexión nodo 1
                hiloClient[1] = new ThreadMatriz("20.172.31.62",0,A,B,C,N); // Conexión nodo 2

                for(i=0; i<2;i++) //Se inicia la ejecución de hilos
                    hiloClient[i].start();

                for(i=0; i<2;i++) //Se crea barrera de hilos
                    hiloClient[i].join();

                // Se calcula checksum de matriz C. Tambien se imprime matriz en caso de N < 20
                FileInputStream fin = new FileInputStream(new File("./matriz.txt")); 
                ObjectInputStream ois = new ObjectInputStream(fin);
                C = (Matriz) ois.readObject();
                C.printMatriz();
                C.calcChecksum();
                
            }else{ // Nodo 1 y 2
                ThreadMatriz hiloServer = new ThreadMatriz("",numNodo,null,null,null,0); // Se inicializa hilo principal para nodos
                hiloServer.start();
                hiloServer.join();
            } // else
        } // for
    } // main
}
