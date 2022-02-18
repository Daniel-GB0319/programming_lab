package herencia;

public class gerente2 extends empleado{ // SubClase que hereda de la Clase Padre "Empleado.class"

// Atributo propio de SubClase Gerente2
    private String departamento="Departamento Gerente #2";

// Metodo Propio de SubClase Gerente2
    public String departamentoGerente(){
    // Se utiliza palabra clave "super" para referenciar metodo de Clase Padre
        return super.getDetalles() + " GERENTE DE: "+departamento; 
    }
}