package practicaCompras;
import java.io.Serializable;

public class Producto implements Serializable {
    int id;
    String name;
    float price;
    int stock;
    String description;
   
    public Producto(int id,String name,float price,int stock,String description){
       this.id = id;
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.description = description;
    }
}
