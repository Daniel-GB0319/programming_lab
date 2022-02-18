package packcalculaedad;
public class CalculaEdad{

// Atributos de la Clase CalculaEdad.class    
    private String nombre;
    private int anioNacimiento;

// Metodos de la clase CalculaEdad.class
    public CalculaEdad(String nombre, int anioNacimiento){
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
    }

    public String getNombre (){
        return nombre;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public int getAnioNacimiento(){
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int calculadorEdad(){
        int result = 0;
        result = 2021-this.anioNacimiento;
        return result;
    }
}