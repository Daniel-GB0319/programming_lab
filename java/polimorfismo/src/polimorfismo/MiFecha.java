package polimorfismo;

public class MiFecha {
    private int dia;
    private int mes;
    private int anio;

    public MiFecha(int dia, int mes, int anio){
        this.dia=dia;
        this.mes=mes;
        this.anio=anio;
    }

    public boolean equals(Object o){
        boolean resultado=false;
        if ((o !=null)&&(o instanceof MiFecha)){
            MiFecha d = (MiFecha) o; // conversion
            if((dia==d.dia)&&(mes==d.mes)&&(anio==d.anio)){
                resultado = true;
            }
        }
        return resultado;
    }
    
    public int hashCode(){
        return ((new Integer(dia).hashCode())^(new Integer(mes).hashCode())^(new Integer(anio).hashCode()));
    }
}
