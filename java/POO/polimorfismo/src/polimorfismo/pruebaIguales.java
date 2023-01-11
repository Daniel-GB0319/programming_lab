package polimorfismo;

public class pruebaIguales { 
    // ##### COMPARACION DE OPERADOR == Y METODO EQUALS ######
    public static void main(String[] args) {
        System.out.println("\n\n--- COMPARACION DE OPERADOR == Y METODO EQUALS ---");
        MiFecha fecha1=new MiFecha(30,3,1976);
        MiFecha fecha2=new MiFecha(30,3,1976);
        if(fecha1==fecha2){
            System.out.println("fecha1 es identica a fecha 2");
        } else{
            System.out.println("fecha1 no es identica a fecha2");
        }
        if(fecha1.equals(fecha2)){
            System.out.println("fecha1 es igual a fecha2");
        } else{
            System.out.println("fecha1 no es igual a fecha2");
        }
        System.out.println("asignar fecha2 = fecha1");
        fecha2=fecha1;
        if(fecha1==fecha2){
            System.out.println("fecha1 es identica a fecha 2");
        } else{
            System.out.println("fecha1 no es identica a fecha2");
        }
    }
}