package multMatrizObjDist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente{
    
    public class HiloCliente extends Thread{
        String url; // url objeto remoto
        int N, M;
        double[][] A, B, C;
        
        public HiloCliente (String url,int N, int M, double[][] A, double[][] B){
            this.url = url;
        } // Constructor HiloCliente
        
        public void run(){
            try{
                // Se obtiene referencia al objeto remoto
                InterfaceRMI r = (InterfaceRMI) Naming.lookup(url);
            
            }catch(MalformedURLException | NotBoundException | RemoteException e){
            }
        
        } // run
    } // HiloCliente
    
    // Separa las matrices en 6 partes
    public static double[][] separa_matriz(double[][] A, int inicio, int N, int M){
        double[][] sub = new double[N/6][M];
        for(int i=0; i<(N/6);i++)
            for(int j=0; j<M;j++)
                sub[i][j] = A[i + inicio][j];
        return sub;
    } // separa_matriz
    
    // Agrupa las matrices parciales de C en una completa
    public static void acomoda_matriz(double[][] C, double[][] A, int renglon, int columna, int N, int M){
        for(int i=0; i<(N/6);i++)
            for(int j=0; j<(M/6); j++)
                C[i+renglon][j+renglon] = A[i][j];
    }// acomoda_matriz
    
    // Imprime la matriz
    public static void imprime_matriz(double[][] A, int N, int M){
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++)
                System.out.print(A[i][j]);
        System.out.println();
        }
    } // imprime_matriz
    
    
    // Funcion main
    public static void main(String[] args) throws Exception{
        String url1 = "rmi://10.0.0.1/multMatriz"; // Objeto remoto nodo 1
        String url2 = "rmi://10.0.0.2/multMatriz"; // Objeto remoto nodo 2
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        double[][] A,B,C;
        
        // Se lee tamaño de N y M
            do{
                System.out.print("Escriba el valor de N (Divisible entre 6): ");
                N = Integer.parseInt(entrada.readLine());
                
                System.out.print("Escriba el valor de M: ");
                M = Integer.parseInt(entrada.readLine());
            }while(N%6 != 0);
        
        // Se crean e inicializan las matrices A y B
        A = new double[N][M];
        B = new double[M][N];
        
        
        
        
        // Se crean hilos para conexion a nodos
        HiloCliente h1 = new HiloCliente(url1);
        HiloCliente h2 = new HiloCliente(url2);
        
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        
        
    }
   
    
} // Cliente