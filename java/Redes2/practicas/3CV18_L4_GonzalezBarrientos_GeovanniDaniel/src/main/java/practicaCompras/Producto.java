package practicaCompras;
import java.io.Serializable;

public class Producto implements Serializable {
    int id;
    String name;
    float price;
    int stock;
   
    public Producto(int id,String name,float price,int stock){
       this.id = id;
       this.name = name;
       this.price = price;
       this.stock = stock;   
    }
    
    public void setID(int id){
       this.id = id;
    }
    
    public void setName(String name){
       this.name = name;
    }
    
    public void setPrice(float price){
       this.price = price;
   }
    
    public void setStock(int stock){
       this.stock = stock;
    }
}
