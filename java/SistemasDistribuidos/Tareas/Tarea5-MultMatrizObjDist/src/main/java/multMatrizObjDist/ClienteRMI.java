//package multMatrizObjDist;

import java.io.*;
import java.net.*;
import java.rmi.*;

public class ClienteRMI{
    static double[][] C; // Matriz C
    
    static class HiloCliente extends Thread{
        String url; // url objeto remoto
        String name; // Nombre del nodo
        double[][] A1, A2, B1, B2, B3, B4, B5, B6; // Matrices parciales
        int N, M; // Tamaño de matriz NxM
        static Object obj = new Object();
        
        HiloCliente(String url,String name,int N,int M,double[][] A1,double[][] A2,double[][] B1,double[][] B2,double[][] B3,double[][] B4,double[][] B5,double[][] B6){
            this.url = url;
            this.name = name;
            this.N = N;
            this.M = M;
            this.A1 = A1;
            this.A2 = A2;
            this.B1 = B1;
            this.B2 = B2;
            this.B3 = B3;
            this.B4 = B4;
            this.B5 = B5;
            this.B6 = B6;        
        } // Constructor HiloCliente
        
        @Override
        public void run(){
            try{
                Thread.currentThread().setName(name);
                // Se obtiene referencia al objeto remoto
                InterfaceRMI r = (InterfaceRMI) Naming.lookup(url);
                
                // Se realizan las multiplicaciones correspondientes
                double[][] C1 = r.multiplica_matrices(A1, B1, N, M);
                double[][] C2 = r.multiplica_matrices(A1, B2, N, M);
                double[][] C3 = r.multiplica_matrices(A1, B3, N, M);
                double[][] C4 = r.multiplica_matrices(A1, B4, N, M);
                double[][] C5 = r.multiplica_matrices(A1, B5, N, M);
                double[][] C6 = r.multiplica_matrices(A1, B6, N, M);
                
                double[][] C7 = r.multiplica_matrices(A2, B1, N, M);
                double[][] C8 = r.multiplica_matrices(A2, B2, N, M);
                double[][] C9 = r.multiplica_matrices(A2, B3, N, M);
                double[][] C10 = r.multiplica_matrices(A2, B4, N, M);
                double[][] C11 = r.multiplica_matrices(A2, B5, N, M);
                double[][] C12 = r.multiplica_matrices(A2, B6, N, M);
                
                // Se juntan las partes de C resultantes 
                synchronized(obj){
                    if("nodo1".equals(Thread.currentThread().getName())){ // nodo1
                        acomoda_matriz(C, C1, (2*N)/6, 0, N, M);
                        acomoda_matriz(C, C2, (2*N)/6, M/6, N, M);
                        acomoda_matriz(C, C3, (2*N)/6, (2*M)/6, N, M);
                        acomoda_matriz(C, C4, (2*N)/6, (3*M)/6, N, M);
                        acomoda_matriz(C, C5, (2*N)/6, (4*M)/6, N, M);
                        acomoda_matriz(C, C6, (2*N)/6, (5*M)/6, N, M);
                        
                        acomoda_matriz(C, C7, (3*N)/6, 0, N, M);
                        acomoda_matriz(C, C8, (3*N)/6, M/6, N, M);
                        acomoda_matriz(C, C9, (3*N)/6, (2*M)/6, N, M);
                        acomoda_matriz(C, C10, (3*N)/6, (3*M)/6, N, M);
                        acomoda_matriz(C, C11, (3*N)/6, (4*M)/6, N, M);
                        acomoda_matriz(C, C12, (3*N)/6, (5*M)/6, N, M);
                    
                    }else{ // nodo2
                        acomoda_matriz(C, C1, (4*N)/6, 0, N, M);
                        acomoda_matriz(C, C2, (4*N)/6, M/6, N, M);
                        acomoda_matriz(C, C3, (4*N)/6, (2*M)/6, N, M);
                        acomoda_matriz(C, C4, (4*N)/6, (3*M)/6, N, M);
                        acomoda_matriz(C, C5, (4*N)/6, (4*M)/6, N, M);
                        acomoda_matriz(C, C6, (4*N)/6, (5*M)/6, N, M);
                        
                        acomoda_matriz(C, C7, (5*N)/6, 0, N, M);
                        acomoda_matriz(C, C8, (5*N)/6, M/6, N, M);
                        acomoda_matriz(C, C9, (5*N)/6, (2*M)/6, N, M);
                        acomoda_matriz(C, C10, (5*N)/6, (3*M)/6, N, M);
                        acomoda_matriz(C, C11, (5*N)/6, (4*M)/6, N, M);
                        acomoda_matriz(C, C12, (5*N)/6, (5*M)/6, N, M);
                    } // else
                } // synchronized
            }catch(MalformedURLException | NotBoundException | RemoteException e){
            } // CATCH
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
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        double[][] A,B,BT;
        double checksum;
        
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
        BT = new double[N][M];
        C = new double[N][N];
        
        for(int i=0; i<N;N++)
            for(int j=0; j<M ; j++){
                A[i][j]= 3*i + 2*j;
                BT[i][j] = 0;
                //C[i][j]=0;
            } //for
        
        for(int i=0; i<M;i++)
            for(int j=0; j<N ; j++){
                B[i][j] = 2*i - 3*j;
            } // for
        
        // Se imprime la matriz A y B en caso de ser un valor pequeño para M y N
        if(N<15 && M<15){
            imprime_matriz(A,N,M);
            imprime_matriz(B,M,N);
        } // if
        
        // Se calcula la traspuesta de B
        for(int i=0; i<N; i++)
            for(int j=0; j<M ; j++)
                BT[i][j] = B[j][i];
        
        // Se calculan las partes de A y B
        double[][] A1 = separa_matriz(A,0,N,M);
        double[][] A2 = separa_matriz(A,N/6,N,M);
        double[][] A3 = separa_matriz(A,(2*N)/6,N,M);
        double[][] A4 = separa_matriz(A,(3*N)/6,N,M);
        double[][] A5 = separa_matriz(A,(4*N)/6,N,M);
        double[][] A6 = separa_matriz(A,(5*N)/6,N,M);
        
        double[][] B1 = separa_matriz(BT,0,N,M);
        double[][] B2 = separa_matriz(BT,N/6,N,M);
        double[][] B3 = separa_matriz(BT,(2*N)/6,N,M);
        double[][] B4 = separa_matriz(BT,(3*N)/6,N,M);
        double[][] B5 = separa_matriz(BT,(4*N)/6,N,M);
        double[][] B6 = separa_matriz(BT,(5*N)/6,N,M);
          
        // Se obtiene localmente C1, C2, ... , C12
        ClaseRMI rmi = new ClaseRMI();
        
        double[][] C1 = rmi.multiplica_matrices(A1, B1, N, M);
        double[][] C2 = rmi.multiplica_matrices(A1, B2, N, M);
        double[][] C3 = rmi.multiplica_matrices(A1, B3, N, M);
        double[][] C4 = rmi.multiplica_matrices(A1, B4, N, M);
        double[][] C5 = rmi.multiplica_matrices(A1, B5, N, M);
        double[][] C6 = rmi.multiplica_matrices(A1, B6, N, M);

        double[][] C7 = rmi.multiplica_matrices(A2, B1, N, M);
        double[][] C8 = rmi.multiplica_matrices(A2, B2, N, M);
        double[][] C9 = rmi.multiplica_matrices(A2, B3, N, M);
        double[][] C10 = rmi.multiplica_matrices(A2, B4, N, M);
        double[][] C11 = rmi.multiplica_matrices(A2, B5, N, M);
        double[][] C12 = rmi.multiplica_matrices(A2, B6, N, M);
        
        // Se acomodan las partes de matriz C 
        acomoda_matriz(C, C1, 0, 0, N, M);
        acomoda_matriz(C, C2, 0, M/6, N, M);
        acomoda_matriz(C, C3, 0, (2*M)/6, N, M);
        acomoda_matriz(C, C4, 0, (3*M)/6, N, M);
        acomoda_matriz(C, C5, 0, (4*M)/6, N, M);
        acomoda_matriz(C, C6, 0, (5*M)/6, N, M);

        acomoda_matriz(C, C7, N/6, 0, N, M);
        acomoda_matriz(C, C8, N/6, M/6, N, M);
        acomoda_matriz(C, C9, N/6, (2*M)/6, N, M);
        acomoda_matriz(C, C10, N/6, (3*M)/6, N, M);
        acomoda_matriz(C, C11, N/6, (4*M)/6, N, M);
        acomoda_matriz(C, C12, N/6, (5*M)/6, N, M);
        
        // Se crean hilos para conexion a nodos utilizando ip privada de cada VM
        //HiloCliente h1 = new HiloCliente("rmi://20.163.119.191/multMatriz","nodo1",N,M,A3,A4,B1,B2,B3,B4,B5,B6);
        //HiloCliente h2 = new HiloCliente("rmi://20.125.118.195/multMatriz","nodo2",N,M,A5,A6,B1,B2,B3,B4,B5,B6);
        HiloCliente h1 = new HiloCliente("rmi://localhost/multMatriz","nodo1",N,M,A3,A4,B1,B2,B3,B4,B5,B6);
        HiloCliente h2 = new HiloCliente("rmi://localhost/multMatriz","nodo2",N,M,A5,A6,B1,B2,B3,B4,B5,B6);
        
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        
        // Se imprime la matriz C en caso de ser un valor pequeño para M y N
        if(N<15 && M<15){
            imprime_matriz(C,N,N);
        } // if
        
        // Se calcula checksum
        checksum = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                checksum = checksum + C[i][j];
        
        System.out.println("Checksum calculado = "+checksum);
    } // main
} // Cliente