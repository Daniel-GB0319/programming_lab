.model small                                         

org 100H

.data ;Variables para el programa

msj1 db 0ah, 0dh, '!! Cadenas Iguales !!' ,'$'
msj2 db 0ah, 0dh, '!! Cadenas Diferentes !!' ,'$'
msj3 db 0ah, 0dh, 'FIN DEL PROGRAMA' ,'$'                                                 
vec1 db 10 dup('3', '1'), '$' ; array de 10 elementos para almacenar la cadena
vec2 db 10 dup('3', '1'), '$' ; dup() sirve para llenar el array con una secuencia especifica de elementos, en este caso la cadena de 10 elementos es '3 1 3 1 3 1 3 1 3 1'                                                 
 
.code ; Comienza el desarrollo principal del programa

mov ax , @data 
mov ds, ax
lea si , vec1 ;Se asignan valores de los arrays a los registros necesarios para la comparacion
lea di , vec2  

cmpsw ; Compara las 2 cadenas
je same ; Si cadenas son iguales, se llama a la funcion same
jne diff ; Si cadenas son diferentes, se llama a la funcion diff    
    
same:    
mov dx , offset msj1 ; Imprime el mensaje de igualdad en las cadenas
mov ah, 9
int 21h
jmp fin       
    
diff:
mov dx , offset msj2 ; Imprime el mensaje de diferencia en las cadenas
mov ah, 9
int 21h   
jmp fin

fin:
mov dx , offset msj3 ; Imprime el mensaje de programa finalizado
mov ah, 9
int 21h
end