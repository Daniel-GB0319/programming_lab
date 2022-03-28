org 100h   
mov si, 0   
mov di, 1

Ciclo1:   
 mov bp, subcad[0]
 cmp cadena[si], '$'
 jz NoSubcadena
 cmp cadena[si], bp
 jne continuar    


Ciclo2:
 mov bp, subcad[di]
 mov bx, di
 cmp cadena[si+bx], bp  
 jne continuar
 inc di
 cmp subcad[di], '$'
 jz EsSubcadena

loop Ciclo2

continuar:
 inc si    

loop Ciclo1
 
EsSubcadena:
 mov dx, offset texto
 mov ah, 9
 int 21h
 ret     

NoSubcadena:
 ret
  
cadena db "tecnologico$"
subcad db "logico$" 
texto db "Es subcadena$"

hlt