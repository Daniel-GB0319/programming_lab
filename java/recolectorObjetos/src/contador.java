void objetos(){
    Contador k, g, r, s; // Se crean cuatro objetos
    k=new Contador();
    g=new Contador();
    r=new Contador();
    s=new Contador();

    /* La siguiente asignacion hace que g referencie al mismo
    objeto que k, ademas el objeto original de g sera 
    automaticamente recolectado. */
    g=k;
    /* Ahora no se activa el recolector porque g sigue apuntando
    al objeto */
    k=null;
    /* A continuacion si se activa el recolector para el 
    objeto original de r. */
    r=new Contador();
} // Se liberan los objetos actuales apuntados por g, r, s
