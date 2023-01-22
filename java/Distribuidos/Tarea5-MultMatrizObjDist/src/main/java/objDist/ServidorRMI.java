package objDist;
import java.io.*;
import java.rmi.Naming;

public class ServidorRMI {
    // Funcion main
    public static void main(String[] args) throws IOException {
        String url = "rmi://localhost/multMatriz";
        ClaseRMI obj = new ClaseRMI();
        
        System.out.println("\n\n%% Gonzalez Barrientos Geovanni Daniel - Tarea 5 - Sistemas Distribuidos 4CV13 %%");
        System.out.println("\n*** SERVIDOR INICIADO ***");
        
        //Registra la instancia en el rmiregistry
        Naming.rebind(url,obj);
    } // main
} // Servidor
