package herencia;

public class director extends gerente2{ // SubClase que hereda de otra SubClase "Gerente2.class"

// Atributo propio de la SubClase "Director"
    protected String carroPermitido="Coche Director";

// Metodo propio de la SubClase "Director"
    public String aumentoPermitido(){
    // Se utiliza palabra clave "super" para utilizar directamente metodo de Clase Padre "Empleado.class"
        return super.getDetalles()+" COCHE PERMITIDO: "+carroPermitido;
    }
}