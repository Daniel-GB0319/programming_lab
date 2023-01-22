package tarea5;
import java.net.*;
import java.io.*;

public class Cliente {
	public static void main(String[] args) {
		try {
                        // Se inicializa la entrada del cliente para recibir datos del puerto y direccion IP
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in)); // Se inicializa buffer de entrada
			
			System.out.printf("Escriba la direccion del servidor: ");
			String host = br1.readLine(); // Se guarda direccion IP
			
			System.out.printf("\nEscriba el puerto: ");
			int pto = Integer.parseInt(br1.readLine()); // Se guarda el puerto
			
			Socket cl = new Socket(host,pto); // Se crea el socket para conectarse con el servidor
			
                        // Se inicializa otra entrada del cliente para recibir flujo de datos del servidor
			ObjectInputStream br2 = new ObjectInputStream(cl.getInputStream()); // Se indica la entrada para recibir datos del servidor
			String mensaje = (String)br2.readObject(); // Se asigna informacion recibida del servidor a una variable
			System.out.println("!!! Recibimos un mensaje desde el servidor !!!");
			System.out.println("Mensaje: " + mensaje); // Se imprime el mensaje recibido
			
                        // Se inicializa la salida para el flujo de datos hacia el servidor
			System.out.println("!!! Ahora se reenviara el mensaje recibido hacia el servidor para la funcionalidad ECO !!!");
			ObjectOutputStream salida = new ObjectOutputStream(cl.getOutputStream()); // Se indica la salida para enviar datos al servidor
			salida.writeObject(mensaje); // Se envian los datos de la variable "mensaje" al servidor
                        System.out.println("!!! Mensaje Enviado !!! Conexion Finalizada");

			br1.close();
			br2.close();
			salida.flush();
			salida.close();
			cl.close();
		}catch(Exception e) {
			e.printStackTrace();
		} // Termina catch
	} // Termina main
}