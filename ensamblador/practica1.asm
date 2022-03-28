


; add your code here

 org 100h
 mov cx, 0000h; 
 mov ax, 0AF1Ch;
 mov bx, 0F243h;
 add ax,bx;
 jnc jump;
 inc cl;
 jump:
 
HLT ;          ; halt!


