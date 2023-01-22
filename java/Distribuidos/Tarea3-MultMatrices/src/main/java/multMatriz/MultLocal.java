package multMatriz;

public class MultLocal {
    public static void main(String[] args){
        int N = 12;
        float[][] A = new float[N][N];
        float[][] B = new float[N][N];
        float[][] C = new float[N][N];        
        float checksum = 0;
        
        // Se inicializan las matrices
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++){
                A[i][j] = i + 3*j;
                B[i][j] = 2*i - j;
                C[i][j] = 0;
            } // for
        
        // Se imprimen matrices A y B generadas solo si N<15
        if(N < 15){ // Se puede imprimir
                System.out.println("\n\nMatriz A: ");
                for(int i=0; i<N ;i++){
                    for(int j=0; j<N ; j++){
                        System.out.print(A[i][j] + "  ");           
                    } 
                    System.out.println();
                } // for

            }else{ // No se puede imprimir 
                System.out.println("!! Matriz demasiado grande, no se puede imprimir !! N="+N);
            } // else
        
        if(N < 15){ // Se puede imprimir
                System.out.println("\n\nMatriz B: ");
                for(int i=0; i<N ;i++){
                    for(int j=0; j<N ; j++){
                        System.out.print(B[i][j] + "  ");           
                    } 
                    System.out.println();
                } // for

            }else{ // No se puede imprimir
                System.out.println("!! Matriz demasiado grande, no se puede imprimir !! N="+N);
            } // else
        
        
        // Se realiza traspuesta de matriz B para localidad espacial (lectura de renglones en cache)
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++){
                float x = B[i][j];
                B[i][j] = B[j][i];
                B[j][i] = x;
            }// for
            
        // Se multiplican matrices
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                for(int k=0;k<N;k++){
                    C[i][j] = C[i][j] + (A[i][k]*B[k][j]);
                }// for
        
        // Se calcula checksum
        checksum = 0;
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++){
                checksum = checksum + C[i][j];
            }// for
        
        // Se imprime matriz C solo si N<15
        if(N < 15){ // Se puede imprimir
                System.out.println("\n\nMatriz C: ");
                for(int i=0; i<N ;i++){
                    for(int j=0; j<N ; j++){
                        System.out.print(C[i][j] + "  ");           
                    } 
                    System.out.println();
                } // for

            }else{ // No se puede imprimir
                System.out.println("!! Matriz demasiado grande, no se puede imprimir !! N="+N);
            } // else
            
        System.out.println("\n\nCHECKSUM = "+ checksum);
    }//main
}
