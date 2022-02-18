package packcalculaedad;
public class CalculaAlago {
    
// Atributos de la clase CalculaAlago.class
    private String nombre;
    private String genero;
    private int edad;

// Metodos de la clase CalculaAlago.class
    public CalculaAlago(String nombre, String genero, int edad){
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero (String genero){
        this.genero = genero;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public String calculaAlagador(){
        String result = "";
        
        if(this.getGenero()=="Mujer" || this.getGenero()=="Femenino"){
            if(this.getEdad()<60){
                result = "Hola "+ this.getNombre() + ", eres menor de 60 años.";
            }else{
                result = "Hola "+ this.getNombre() + ", eres como el buen vino, mejoras con los años.";
            }

        } else if(this.getGenero()=="Hombre" || this.getGenero()=="Masculino"){
            if(this.getEdad()<60){
                result = "Hola "+ this.getNombre() + ", eres menor de 60 años.";
            } else{
                result = "Hola "+ this.getNombre() + ", eres como el buen vino, mejoras con los años.";
            }
        } 
        return result;
    }
}
