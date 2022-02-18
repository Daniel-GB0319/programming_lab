package polimorfismo;

public class polimorfismo{
    public static void main(String[] args) {
        System.out.println("\n\n***** Gonzalez Barrientos Geovanni Daniel *****");
        System.out.println("***** Actividad 16: Polimorfismo *****");

//###### Invocacion del Metodo Virtual ######   
System.out.println("\n\n--- INVOCACION DEL METODO VIRTUAL ---");     
    // Declaracion de Objetos por medio de Constructores
        empleado e=new empleado();
        gerente g=new gerente();   
        empleado emp=new gerente(); // Objeto tipo "empleado" con constructor "gerente"

    // Se imprimen los resultados de realizar la herencia de Clases
        System.out.println("\n1) Resultado de metodo \"getDetalles()\" en Clase \"empleado.class\":");
        System.out.println(e.getDetalles());

        System.out.println("\n2) Resultado de metodo \"getDetalles()\" en SubClase \"gerente.class\":");
        System.out.println(g.getDetalles());
    
    // Los resultados de getDetalles() son del tipo real del objeto, es decir gerente
        System.out.println("\n3) Resultado de metodo \"getDetalles()\"  para objeto tipo \"empleado\" con constructor \"gerente\":");
        System.out.println(emp.getDetalles());

//##### COLECCIONES HETEROGENEAS #####
        System.out.println("\n\n--- COLECCIONES HETEROGENEAS ---");
    // Se declara array para la coleccion heterogenea
        empleado [] personal = new empleado[4];
    // Se crean los objetos para llenar el array
        personal[0]=new gerente();
        personal[1]=new empleado();
        personal[2]=new empleado();
        personal[3]=new gerente();
    // Se imprimen resultados de la coleccion heterogenea
            System.out.println(personal[0].getDetalles());
            System.out.println(personal[1].getDetalles());
            System.out.println(personal[2].getDetalles());
            System.out.println(personal[3].getDetalles());

// ##### ARGUMENTOS POLIMORFICOS #####
        System.out.println("\n\n--- ARGUMENTOS POLIMORFICOS ---");
    // El metodo "buscarTasaImpuesto" es valido para tipo "Gerente" ya que deriva de "empleado"
        double t = e.buscarTasaImpuesto(g);
        System.out.println("La Tasa de Impuesto es: "+t);        

        
// ###### OPERADOR INSTANCEOF Y CONVERSION DE OBJETOS ######
        System.out.println("\n\n--- OPERADOR INSTANCEOF Y CONVERSION DE OBJETOS ---");
        e.hazAlgo(e);
    }
}