package herencia;
import java.sql.Date;

public class gerente1 { // Clase con atributos y metodos similares a Clase Padre

// Atributos de la Clase Gerente1
    public String name="Nombre Gerente";
    public double salario=200;
    public Date cumpleanios;
    protected String departamento="Departamento Gerente #1";
    
// Metodo de la Clase Gerente1
    public String getDetalles(){
        return "NOMBRE: "+name+" SALARIO: "+salario+" GERENTE DE: "+departamento;
    }
}
