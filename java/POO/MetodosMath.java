public class MetodosMath {
    public static void main(String[] args) {
        System.out.println("Gonzalez Barrientos Geovanni Daniel");
        System.out.println("Actividad 7: Metodos Matematicos en Java");
	
// Se declaran variables a utilizar
	double num1=25.5;
        double num2=15.21;
	double num3=0;
//Se procede a imprimir resultados de algunas funciones matematicas en Java         
        System.out.println("Funcion ceil de "+ num1 +" = " + Math.ceil(num1)); // Devuelve 26.0 
	System.out.println("Funcion arcos de "+ num3 +" = " + Math.acos(num3)); // Devuelve 1.5707963267948966
	System.out.println("Funcion asin de "+ num3 +" = " + Math.asin(num3)); // Devuelve 0
 	System.out.println("Funcion sqrt de "+ num1 +" = " + Math.sqrt(num1)); // Devuelve 5.049752469181039  
        System.out.println("Funcion floor de "+ num2 +" = " + Math.floor(num2)); //Devuelve 15.0
        System.out.println("Funcion pow de "+ num1 + "^" + num2 +" = " + Math.pow(num1, num2)); // Devuelve 2.474435537975361E21
        System.out.println("Funcion max de "+ num1 + " y " + num2 +" = " +Math.max(num1, num2)); //Devuelve 25.5
    }
}