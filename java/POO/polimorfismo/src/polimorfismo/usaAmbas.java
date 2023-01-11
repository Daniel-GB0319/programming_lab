package polimorfismo;

// ###### Reglas de los Metodos Sobrescritos ######
public class usaAmbas {
    public void hazOtraCosa(){
    // se crean objetos de tipo padre e hijo 
        padre p1 = new padre();
        padre p2 = new hijo();
     
    // Se hace llamado a los metodos de padre e hijo    
        p1.hazAlgo();

    // p2.hazAlgo no se puede ejecutar ya que metodo de hijo.class es privado
        p2.hazAlgo();
    }
}