package pract3_UDP;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class UDPServidor {
    public final static int PUERTO = 7; // Se indica el puerto a utilizar
    public final static int TAM_MAXIMO = 65507; // Se indica el tamanio maximo del buffer
    
    public static void main(String[] args){
        int port = PUERTO; // Se asigna el valor de PUERTO a variable port
        
        try{
            DatagramChannel canal = DatagramChannel.open(); // Se inicializa el datagrama de canal
            
            canal.configureBlocking(false); // Se configura el bloqueo del canal en false
            
            DatagramSocket socket = canal.socket(); // Se crea el socket asociado al canal
            
            SocketAddress dir = new InetSocketAddress(port); // Se inicializa la direccion en el puerto
            
            socket.bind(dir); // Se asocia la direccion al socket
            
            Selector selector = Selector.open(); // Se inicializa un selector
            
            canal.register(selector, SelectionKey.OP_READ); // Se registra el selector y la llave de seleccion en lectura al canal
            
            ByteBuffer buffer = ByteBuffer.allocateDirect(TAM_MAXIMO); // Se inicializa un buffer de bytes
            
            while(true){
                selector.select(5000); // 
                Set sk = selector.selectedKeys(); // se almacenan las llaves seleccionadas
                Iterator it = sk.iterator(); // se inicializa el iterador
                
                while(it.hasNext()){ // se verifica dsi existen mas elementos para continuar el bucle
                    SelectionKey key = (SelectionKey)it.next(); // se asigna la llave del elemento actual
                    it.remove(); 
                    
                    if(key.isReadable()){ // se verifica si la llave puede ser leida
                        buffer.clear();
                        SocketAddress client = canal.receive(buffer); // se inicializa la direccion del socket 
                        buffer.flip();
                        int eco = buffer.getInt(); // se realiza la lectura del valor entero
                        
                        if(eco == 1000){ // Verifica si el numero leido ha llegado al maximo establecido por el programa
                            canal.close();
                            System.exit(0);
                        }else{
                            System.out.println("Dato Leido: " + eco);
                            buffer.flip();
                            canal.send(buffer,client);
                        } // else
                    }// if 
                } // while 2
            }// while
                
        }catch(IOException e){
            System.err.println(e);
        }// catch
    }
}    