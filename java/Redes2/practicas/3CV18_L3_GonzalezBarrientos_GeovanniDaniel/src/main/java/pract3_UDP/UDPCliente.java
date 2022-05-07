package pract3_UDP;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class UDPCliente { // Se crea clase serializable
    public final static int PUERTO = 7; // inicializa el puerto
    public final static int LIMITE = 100; // inicializa el limite de datos a enviar
    
    public static void main(String[] args){
        boolean bandera = false; // fija la bandera en false para ejecutar el programa
        SocketAddress remoto = new InetSocketAddress("127.0.0.1" , PUERTO); // inicializa el socket destino
        
        try{
            DatagramChannel canal = DatagramChannel.open(); // crea el datagrama de canal
            
            canal.configureBlocking(false);
            canal.connect(remoto); // se conecta al canal destino
            
            Selector selector = Selector.open(); // inicializa el selector
            
            canal.register(selector, SelectionKey.OP_WRITE); // inicializa el registro para escritura
            
            ByteBuffer buffer = ByteBuffer.allocateDirect(4); // inicializa el buffer para los datos
            int n = 0;
            
            while(true){
                selector.select(5000);
                Set sk = selector.selectedKeys();
                 
                if(sk.isEmpty() && n == LIMITE || bandera ){ // verifica si no hay mas contenido en el selector o se ha llegado al limite de interaciones
                    canal.close();
                    break;
                }else{
                    Iterator it = sk.iterator();
                    
                    while(it.hasNext()){ // verifica si existe otro elemento
                        SelectionKey key = (SelectionKey) it.next();
                        it.remove();
                        
                        if(key.isWritable()){ // verifica si se puede escribir en el elemento actual
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            canal.write(buffer);
                            System.out.println("Escribiendo el dato: " + n);
                            n++;
                            
                            if(n == LIMITE){ 
                                // Todos los paquetes han sido escritos
                                buffer.clear();
                                buffer.putInt(1000);
                                buffer.flip();
                                canal.write(buffer);
                                bandera = true;
                                key.interestOps(SelectionKey.OP_READ);
                                break;
                            }// if 3
                        } // if
                    } // while 2
                }// else
            }// while
                
        }catch(IOException e){
            System.err.println(e);
        }// catch
    }
}    