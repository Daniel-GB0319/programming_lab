package practicacarrito;
import java.io.Serializable;

public class Producto implements Serializable {
    // Estructura de los datos del producto
    int id;
    String name;
    float price;
    int stock;
    String description;
    
    // inicializa los productos
    public Producto(int id,String name,float price,int stock,String description){
       this.id = id;
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.description = description;
    } // producto
    
}
