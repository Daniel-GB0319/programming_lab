package polimorfismo;

public class gerente extends empleado{ // SubClase que hereda de la Clase Padre "Empleado.class"

// Atributo propio de SubClase Gerente
    private String departamento="Departamento Gerente";

// Metodo Propio de SubClase Gerente
    public String getDetalles(){
    // Se utiliza palabra clave "super" para referenciar metodo de Clase Padre
        return super.getDetalles() + " GERENTE DE: "+departamento; 
    }

// Metodo para imprimir valor de "departamento"    
    public String getDepartamento(){
        return departamento;
    }
}