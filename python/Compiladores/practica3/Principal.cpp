#include <bits/stdc++.h>
#include <iostream>
using namespace std;
#include "Header.hpp"

LSintact * SinT = new LSintact();

//Cadenas de prueba, tomadas del documento
//--------------------------------------------------------
vector<vector<string>> sen = {

        {"(", "id", "+", "id", ")", "$"},  //(id+id)
        {"id", "+", "id", "$"},            //id+id
        {"id", "+", "id", "*", "id", "$"}, //id+id*id
        {"id", "*", "id", "$"},            //id*id
        {"id", "+", "id", "$"},            //id+id
        {"(", "id", ")", "$"},             //(id)
        {"(", "id", "+", "id", ")", "*", "id", "$"},  //(id+id)*id
        {"id", "+", "(", "id", "*", "id", ")", "$"},  //id+(id*id)
        {")", "id", "$"},                  //)id
        {"id", "+", "id", "*", "$"}};      //id+id*
//--------------------------------------------------------

int IngCadena(vector<string> cad){
    cout << "|_____________________________|" << endl;
    cout << "|  Cadena" << "      ";
    for (auto const &x : cad){
        cout << x;            //Mostramos en pantalla la sentencia que se esta evaluando
    }
    cout << endl;
    stack<string> pila{};
    pila.push("$");
    pila.push("E");
    bool cadena_valida = false;
    int c = 0;
    int prueba = 0;
    while (c < cad.size()){
        string tbl;
        int indice_cabe = 0;
        if (cadena_valida == true) break;
        for (const auto &c2 : SinT->cabezera) {
            if (c2 == cad[c]) break;
            else indice_cabe++;}
        int indice_pc = 0;
        for (const auto &c3 : SinT->pc){
            if (pila.top() == c3) break;
            indice_pc++; }
        if (pila.empty()){  
            cout << "|-----------------------------|" << endl;
            cout << "|        No aceptada         |" << endl; //Mostramos las sentencias que no son aceptadas
            cout << cad[c] << endl;
            cadena_valida = true;
            break;}
        if (cad[c] == pila.top() && pila.top() == "$"){  
            cout << "|-----------------------------|" << endl;
            cout << "|           Aceptada          |" << endl; //Mostramos las sentencias que son aceptadas
            break;
        }else if (cad[c] == pila.top()){
            c++;
            if (pila.empty()){
                cadena_valida = true;
                break;
            } else pila.pop();
        } else {
            string simbolo_mayor = ">";
            tbl = SinT->TF[indice_pc][indice_cabe];
            SinT->corte(tbl, simbolo_mayor);
            auto sen = SinT->split(tbl, ' '); 
            reverse(sen.begin(), sen.end());
            pila.pop();
            for (const auto &vat : sen) {
                if (vat == "ε") continue; 
                else if (vat == "rror" || vat.size() < 1){
               	    cout << "|-----------------------------|" << endl;
                    cout << "|         No aceptada         |" << endl; //Mostramos las sentencias que no son aceptadas
                    cout << "|         Error en "<< cad[c]<< "          |" << endl;
                    cadena_valida = true;
                    break;
                }else if (vat != " ") pila.push(vat);
            }
        } prueba++;
    }return 0;
}

int main(){         
    for (auto const &cad : sen){
        IngCadena(cad);           //Llamamos a la funcion IngCadena, con nuestras sentencias de prueba  
    }
    cout << "|_____________________________|" << endl;
    return 0;
}
