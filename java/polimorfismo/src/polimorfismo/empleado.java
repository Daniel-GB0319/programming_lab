package polimorfismo;
import java.sql.Date;

public class empleado { // Clase Padre
// Atributos de la Clase Padre
    protected String name="Nombre Empleado";
    protected double salario=100;
    protected Date cumpleanios;
// Metodo de la Clase Padre
    public String getDetalles(){
        return "NOMBRE: "+name+" SALARIO: "+salario;
    }

// ##### ARGUMENTOS POLIMORFICOS #####
    // Este metodo es valido para empleado y gerente ya que es un derivado
    public double buscarTasaImpuesto (empleado e){ // Recibe valor de "salario"
        return salario*.30;
    }

// ###### OPERADOR INSTANCEOF Y CONVERSION DE OBJETOS ###### 
    public void hazAlgo(empleado e){
        if(e instanceof gerente){ // Se comprueba si objeto e es de la clase gerente
        // Se restaura la funcionalidad del Objeto al convertirse
            gerente g = (gerente) e;
            System.out.println("Este es el Gerente de "+g.getDepartamento());
        }
    }        
}
