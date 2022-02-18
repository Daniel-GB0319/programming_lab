package polimorfismo;

// ###### Reglas de los Metodos Sobrescritos ######
public class hijo extends padre { // Clase "hijo.class" deriva de "padre.class"
    private void hazAlgo(){ // Metodo con nivel de acceso privado
        System.out.println("Soy un Hijo");
    }
}