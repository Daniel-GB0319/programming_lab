package herencia;

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
}
