//package multMatrizObjDist;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ClaseRMI extends UnicastRemoteObject implements InterfaceRMI{
    // Constructor superclase
    public ClaseRMI() throws RemoteException{
        super();
    } // superclase
        
    public double[][] multiplica_matrices(double[][] A, double[][]B,int N,int M) throws RemoteException{
        double[][] C = new double[N/6][M/6];
        for(int i=0; i<(N/6); i++)
            for(int j=0; i<(M/6); j++)
                for(int k=0; k<M; k++)
                    C[i][j] += A[i][k] * B[j][k];
        return C;
        } // multiplica_matrices       
} // ClaseRMI