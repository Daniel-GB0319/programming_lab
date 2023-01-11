package herencia;

public class herencia{
    public static void main(String[] args) {
        System.out.println("\n\n***** Gonzalez Barrientos Geovanni Daniel *****");
        System.out.println("***** Actividad 15: Herencia *****");

    // Declaracion de Objetos por medio de Constructores
        empleado emp=new empleado();
        gerente1 ger1=new gerente1();
        gerente2 ger2=new gerente2();
        director dir=new director();

    // Se imprimen los resultados de realizar la herencia de Clases
        System.out.println("\n1) Resultados de usar metodo \"getDetalles()\" en Clase Padre \"empleado.class\":");
        System.out.println(emp.getDetalles());

        System.out.println("\n2) Resultados de usar metodo \"getDetalles()\" en subclase \"gerente1.class\":");
        System.out.println(ger1.getDetalles());

        System.out.println("\n3) Resultados de usar metodo \"departamentoGerente()\" en subclase \"gerente2.class\":");
        System.out.println(ger2.departamentoGerente());

        System.out.println("\n4) Resultados de usar metodo \"aumentoPermitido()\" en subsubclase\"director.class\":");
        System.out.println(dir.aumentoPermitido());
    }
}