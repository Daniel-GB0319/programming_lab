package tarea5;
import java.net.*;
import java.io.*;

public class App { // Servidor
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(1234); // Se inicializa el socket del servidor
			
			System.out.println("Esperando cliente...");
			for(;;) {
				Socket clienteNuevo = s.accept(); // Acepta la conexion con un cliente y le asigna un socket
				System.out.println("Conexion establecida desde " + clienteNuevo.getInetAddress() + " : " + clienteNuevo.getPort());
				
                                // Envia el mensaje al socket cliente
				String mensaje = "Gonzalez Barrientos Geovanni Daniel"; // Asigna el mensaje a variable
				ObjectOutputStream pw = new ObjectOutputStream(clienteNuevo.getOutputStream()); // Se indica la salida del socket Servidor
				pw.writeObject("Mi nombre es " + mensaje); // Se envia el mensaje hacia el cliente
                                System.out.println("!!! Se ha enviado un mensaje al cliente !!! Esperando su respuesta... ");
                                
                                // Se indica la entrada del Socket Servidor para el flujo de datos
				ObjectInputStream entrada = new ObjectInputStream(clienteNuevo.getInputStream()); 
				String mensaje2 = (String)entrada.readObject(); // Se recibe el mensaje del cliente 

                                   // Se confirma la recepcion del mensaje por parte del cliente
				System.out.println("!!! Recibimos un mensaje del CLIENTE !!! ");
				System.out.println("Mensaje: " + mensaje2);
				System.out.println("!!! Conexion con el cliente Finalizado !!!");
                                
                                // Se cierra el flujo de datos y el socket del cliente
				entrada.close(); 
				pw.flush();
				pw.close();
				clienteNuevo.close();
			} // Termina el ciclo for
			
		}catch(Exception e) {
			e.printStackTrace();
		} // termina catch
	} // Termina main
}
