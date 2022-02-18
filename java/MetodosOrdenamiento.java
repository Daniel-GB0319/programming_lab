import java.util.Arrays;
public class MetodosOrdenamiento {
    // ################# MAIN ##########################
    public static void main(String args[]) {
        System.out.println("\n***Gonzalez Barrientos Geovanni Daniel***");
        System.out.println("***Actividad 10: Metodos de Ordenamiento***");
    // Se declaran las variables
        System.out.println("\n---------DECLARACION DE ARRAYS---------");
        int arreglo[] = {8,6,7,1,8,7,1,10}; // Se declara el array de prueba para los siguientes metodos.
        int arreglo2[] = {5,7,9,4,3,1,6,8}; // Se declara el array de prueba para los siguientes metodos.
        int arreglo3[] = {2,2,9,9,8,7,5,3}; // Se declara el array de prueba para los siguientes metodos.
        int arreglo4[] = {10,9,6,4,3,1,5,1}; // Se declara el array de prueba para los siguientes metodos.
        int arreglo5[] = {9,5,1,7,3,6,4,5}; // Se declara el array de prueba para los siguientes metodos.
        int arreglo6[] = {1,5,9,7,3,8,2,4}; // Se declara el array de prueba para los siguientes metodos.
    // Se imprimen los arrays
        System.out.println("El array #1 de prueba es: " + Arrays.toString(arreglo));
        System.out.println("El array #2 de prueba es: " + Arrays.toString(arreglo2));
        System.out.println("El array #3 de prueba es: " + Arrays.toString(arreglo3));
        System.out.println("El array #4 de prueba es: " + Arrays.toString(arreglo4));
        System.out.println("El array #5 de prueba es: " + Arrays.toString(arreglo5));
        System.out.println("El array #6 de prueba es: " + Arrays.toString(arreglo6));

    // ----------------- Uso del Metodo Burbuja ---------------------------
        System.out.println("\n---------Uso del Metodo Burbuja---------");
        int arregloOrdenado[] = burbuja(arreglo); // Uso del Metodo Burbuja
        System.out.println("El resultado de aplicar el Metodo Burbuja al array #1 de prueba es :");
        for(int i = 0; i < arregloOrdenado.length;i++){
            System.out.print(arregloOrdenado[i] + " ");
        }
        
    // ----------------- Uso del Metodo de Insercion ---------------------------
        System.out.println("\n\n---------Uso del Metodo de Insercion---------");
        System.out.println("El resultado de aplicar el Metodo de Insercion al array #2 de prueba es :");
        insercion(arreglo2); // Uso del Metodo de Insercion
    
     // ----------------- Uso del Metodo de Seleccion ---------------------------
        System.out.println("\n\n---------Uso del Metodo de Seleccion---------");
        System.out.println("El resultado de aplicar el Metodo de Seleccion al array #3 de prueba es :");
        seleccion(arreglo3);
    
     // ----------------- Uso del Metodo Shell Sort ---------------------------
        System.out.println("\n\n---------Uso del Metodo de Shell Sort---------");
        System.out.println("El resultado de aplicar el Metodo de Shell Sort al array #4 de prueba es :");
        shell(arreglo4);
    
    // ----------------- Uso del Metodo Merge ---------------------------
        System.out.println("\n\n---------Uso del Metodo Merge ---------");
        System.out.println("El resultado de aplicar el Metodo Merge al array #5 de prueba es :");
        merge(arreglo5);
        for(int c=0; c<arreglo5.length;c++){
            System.out.print(arreglo5[c] + " ");
        }
    
    // ----------------- Uso del Metodo Quick ---------------------------
        System.out.println("\n\n---------Uso del Metodo Quick ---------");
        System.out.println("El resultado de aplicar el Metodo Quick al array #6 de prueba es :");
        quicksort(arreglo6, 0, arreglo6.length - 1);
        for(int u=0; u<arreglo6.length;u++){
            System.out.print(arreglo6[u] + " ");
        }

    }

    // ################### METODO BURBUJA ###########################
    public static int[] burbuja(int[] arreglo)
    {
      int auxiliar;
      int[] arregloOrdenado;
      for(int i = 2; i < arreglo.length; i++)
      {
        for(int j = 0;j < arreglo.length-i;j++)
        {
          if(arreglo[j] > arreglo[j+1])
          {
            auxiliar = arreglo[j];
            arreglo[j] = arreglo[j+1];
            arreglo[j+1] = auxiliar;
          }   
        }
      }
      arregloOrdenado = arreglo;
      return arregloOrdenado;
    }

    // ################### METODO DE INSERCION ###########################
    public static void insercion(int[] arreglo2) {
        int i, k = 0;
        int numeroElementos = arreglo2.length;
        int x;
        
        // Empezamos desde el segundo elemento
        for (i = 1; i < numeroElementos; i++) {
            x = arreglo2[i];
            k = i-1;
            // Para k = – 1, hemos alcanzado el extremo izquierdo
            while (k >= 0 && x < arreglo2[k]) {
                arreglo2[k + 1] = arreglo2[k]; // hacer hueco para poder insertar
                k--;
            }
            arreglo2[k + 1] = x; // insertamos x en su lugar
        }
        for(i = 0; i < arreglo2.length;i++){
            System.out.print(arreglo2[i] + " ");
        }    
    }

    // ################### METODO DE SELECCION ###########################
    public static void seleccion(int[] arreglo3) {
        for (int i = 0; i < arreglo3.length - 1; i++) {
            for (int j = i + 1; j < arreglo3.length; j++) {
                if (arreglo3[i] > arreglo3[j]) {
                    // ...intercambiarlos, es decir, mover el actual a la derecha y el de la derecha al actual
                    int temporal = arreglo3[i];
                    arreglo3[i] = arreglo3[j];
                    arreglo3[j] = temporal;
                }
            }
        }
        for(int y=0; y< arreglo3.length;y++){
            System.out.print(arreglo3[y] + " ");
        }  
    }

    // ################### METODO DE SHELL SORT ###########################
    public static void shell (int[] arreglo4){
        int salto,aux,i;
        boolean cambios;
        for(salto=arreglo4.length/2;salto!=0;salto/=2){
            cambios=true;
            while(cambios){ // Mientras se intercambie algun elemento
                cambios=false;
                for(i=salto;i<arreglo4.length;i++){ //da una iteracion
                    if(arreglo4[i-salto]>arreglo4[i]){ // en caso de estar desordenado
                        aux=arreglo4[i]; // se reordenan
                        arreglo4[i]=arreglo4[i-salto];
                        arreglo4[i-salto]=aux;
                        cambios=true; // se marca el cambio
                    }
                }
            }
        }
        for(int w=0; w< arreglo4.length;w++){
            System.out.print(arreglo4[w] + " ");
        }
    }

     // ################### METODO MERGE ###########################
    public static void merge(int[] arreglo5){
        if(arreglo5.length<=1) return;
        int mitad= arreglo5.length/2;
        int izq[]=Arrays.copyOfRange(arreglo5, 0, mitad);
        int der[]=Arrays.copyOfRange(arreglo5, mitad, arreglo5.length);
        merge(izq);
        merge(der);       
        combinarVector(arreglo5, izq, der);
    }

    public static void combinarVector(int[] arreglo5, int[] izq,int[] der){
        int i=0;
        int j=0;
        for(int k=0;k<arreglo5.length;k++){
            if(i>=izq.length){
                arreglo5[k]=der[j];
                j++;
                continue;
            }
            if(j>=der.length){
                arreglo5[k]=izq[i];
                i++;
                continue;
            }
            if(izq[i]<der[j]){
                arreglo5[k]=izq[i];
                i++;
            }else{
                arreglo5[k]=der[j];
                j++;
            }
        }
        
    }

    // ################### METODO QUICK ###########################
  private static int particion(int[] arreglo6, int izquierda, int derecha) {
        // Elegimos el pivote, es el primero
        int pivote = arreglo6[izquierda];
        while (true) {
            // Mientras cada elemento desde la izquierda esté en orden (sea menor que el pivote) continúa avanzando el índice
            while (arreglo6[izquierda] < pivote) {
                izquierda++;
            }
            // Mientras cada elemento desde la derecha esté en orden (sea mayor que el pivote) continúa disminuyendo el índice
            while (arreglo6[derecha] > pivote) {
                derecha--;
            }
    /* Si la izquierda es mayor o igual que la derecha significa que no necesitamos hacer ningún intercambio
    de variables, pues los elementos ya están en orden (al menos en esta iteración) */
            if (izquierda >= derecha) {
                // Indicar "en dónde nos quedamos" para poder dividir el arreglo de nuevo y ordenar los demás elementos
                return derecha;
            } else {
      /* Si las variables quedaron "lejos" (es decir, la izquierda no superó ni alcanzó a la derecha)
      significa que se detuvieron porque encontraron un valor que no estaba en orden, así que lo intercambiamos */
                int temporal = arreglo6[izquierda];
                arreglo6[izquierda] = arreglo6[derecha];
                arreglo6[derecha] = temporal;
      //Ya intercambiamos, pero seguimos avanzando los índices una vez más
                izquierda++;
                derecha--;
            }
            // El while se repite hasta que izquierda >= derecha
        }
    }

    // Divide y vencerás
    public static void quicksort(int[] arreglo6, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int indiceParticion = particion(arreglo6, izquierda, derecha);
            quicksort(arreglo6, izquierda, indiceParticion);
            quicksort(arreglo6, indiceParticion + 1, derecha);
        }
    }


}
