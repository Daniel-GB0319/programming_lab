package packcalculaedad;
public class packcalculaedad {
    public static void main(String[] args) {
        System.out.println("\n\n***** Gonzalez Barrientos Geovanni Daniel *****");
        System.out.println("***** Actividad 13: Clases Ejercicio *****");

        CalculaEdad miEdad = new CalculaEdad("Pedrito",1945);
        
        System.out.println("\nHola "+ miEdad.getNombre() + ", tu edad es de "+ miEdad.calculadorEdad() + " años.");
        miEdad.setAnioNacimiento(1940);

        System.out.println("Hola "+ miEdad.getNombre() + ", tu edad es de "+ miEdad.calculadorEdad() + " años.");
        System.out.println();

        CalculaAlago miAlago1= new CalculaAlago("Pablo","Hombre",35);
            CalculaAlago miAlago2= new CalculaAlago("Martha","Mujer",45);
            CalculaAlago miAlago3= new CalculaAlago("Joselito","Masculino",65);
            CalculaAlago miAlago4= new CalculaAlago("Carmen","Femenino",70);

        System.out.println(miAlago1.calculaAlagador());
            System.out.println(miAlago2.calculaAlagador());
            System.out.println(miAlago3.calculaAlagador());
            System.out.println(miAlago4.calculaAlagador());
    }   
}