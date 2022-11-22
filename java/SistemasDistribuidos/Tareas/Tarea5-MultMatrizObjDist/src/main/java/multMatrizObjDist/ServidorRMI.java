//package multMatrizObjDist;
import java.io.*;
import java.rmi.Naming;

public class ServidorRMI {
    // Funcion main
    public static void main(String[] args) throws IOException {
        String url = "rmi://localhost/multMatriz";
        ClaseRMI obj = new ClaseRMI();
        
        //Registra la instancia en el rmiregistry
        Naming.rebind(url,obj);
    } // main
} // Servidor
