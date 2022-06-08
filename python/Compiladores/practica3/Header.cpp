#include <bits/stdc++.h>
#include <iostream>
#include <algorithm>
#include <sstream>
#include <iterator>
#include <sstream>
#include "Header.hpp"


void LSintact::gTab(){

    cabezera.push_back("M");
    for (auto const &t : terminales){
        if (t != "ε")
            cabezera.push_back(t);
    }
    map<pair<string, string>, string> mp;
    cabezera.push_back("$");
    tabla.push_back(cabezera);
    map<pair<string, string>, string> conjuntosEnTabla;

    for (auto const &cp : conjunto_primeros){
        if (VerNTerm(cp.first)){
            if (cp.first == "E"){
                for (auto const &prodD : cp.second){
                    conjuntosEnTabla[make_pair(cp.first, prodD)] = "E -> T E’";
                }
            }
            else if (cp.first == "E’"){
                for (auto const &prodD : cp.second){
                    if (prodD == "ε"){
                        conjuntosEnTabla[make_pair(cp.first, prodD)] = "E’ -> ε";
                        if (!conjunto_siguientes[cp.first].empty()){
                            for (auto const &cs : conjunto_siguientes[cp.first]){
                                conjuntosEnTabla[make_pair(cp.first, cs)] = "E’ -> ε";
                            }
                        }
                    }
                    else{
                        conjuntosEnTabla[make_pair(cp.first, prodD)] = "E’ -> + T E’";
                    }
                }
            }
            else if (cp.first == "T"){
                for (auto const &prodD : cp.second){
                    conjuntosEnTabla[make_pair(cp.first, prodD)] = "T -> F T’";
                }
            }
            else if (cp.first == "T’"){
                for (auto const &prodD : cp.second){
                    if (prodD == "ε"){
                        conjuntosEnTabla[make_pair(cp.first, prodD)] = "T’ -> ε";
                        if (!conjunto_siguientes[cp.first].empty()){
                            for (auto const &cs : conjunto_siguientes[cp.first]){
                                conjuntosEnTabla[make_pair(cp.first, cs)] = "E’ -> ε";
                            }
                        }
                    }
                    else{
                        conjuntosEnTabla[make_pair(cp.first, prodD)] = "T’ -> * F T’";
                    }
                }
            }
            else if (cp.first == "F"){
                for (auto const &prodD : cp.second){
                    if (prodD == "id") {
                        conjuntosEnTabla[make_pair(cp.first, prodD)] = "F -> id";
                    }
                    else{
                        conjuntosEnTabla[make_pair(cp.first, prodD)] = "F -> ( E )";
                    }
                }
            }
        }
    }
    pc = {
        "M","E","E’","T","T’","F"};
    for (int i = 0; i < 6; i++){
        for (int j = 0; j < 7; j++){
            if (j == 0){
                if (i == 0) tabla[i][j] = pc[i];
                if (i == 1) TF[i][j] = pc[i];
                if (i == 2) TF[i][j] = pc[i];
                if (i == 3) TF[i][j] = pc[i];
                if (i == 4) TF[i][j] = pc[i];
                if (i == 5) TF[i][j] = pc[i];
            }
            if (i == 0){
                if (j > 0)
                    TF[i][j] = cabezera[j];
            }
            else if (i > 0 && j > 0){
                string v1 = pc[i];
                string v2 = cabezera[j];
                if (v2 == "$"){
                    v2 = "ε";
                }
                auto pairV1V2 = make_pair(v1, v2);
                if (!conjuntosEnTabla[pairV1V2].empty()){
                    TF[i][j] = conjuntosEnTabla[pairV1V2];
                }
                else{
                    TF[i][j] = "error";
                }
            }
        }
    }
}

bool LSintact::VerNTerm(string simbolo){
    bool esTerminal = false;
    for (auto const &t : noTerminales){
        if (t == simbolo) {
            esTerminal = true;
            break;
        }
    }
    return esTerminal;
}

void LSintact::primeros(const vector<string> &elementoAbuscarPrimero, vector<string> &conjuntoRetorno)
{

    for (auto const &eAP : elementoAbuscarPrimero){
        if (!VerNTerm(eAP)){ 
            bool band_existeE = false;
            for (auto const &cR : conjuntoRetorno)
            {
                if (cR == eAP)
                {
                    band_existeE = true;
                }
            }
            if (!band_existeE)
            {
                conjuntoRetorno.push_back(eAP);
            }
        }
        else if (eAP == "ε") {
            bool band_existeE = false;
            for (auto const &cR : conjuntoRetorno)
            {
                if (cR == "ε")
                {
                    band_existeE = true;
                }
            }
            if (!band_existeE)
            {
                conjuntoRetorno.push_back("ε");
            }
        } else if (VerNTerm(eAP)){
            if (conjunto_primeros.find(eAP) != conjunto_primeros.end()) {
                for (auto const &cpf : conjunto_primeros[eAP]){
                    bool band_existeE = false;
                    for (auto const &cR : conjuntoRetorno){
                        if (cR == cpf){
                            band_existeE = true;
                        }
                    }if (!band_existeE){
                        conjuntoRetorno.push_back(cpf);
                    }
                }
            }
            else
            {
                for (auto const &pNC : primerosNoConjunto) {
                    if (eAP == pNC.first) {
                        primeros(pNC.second, conjuntoRetorno);
                    }
                }
            }
        }
    }
}

vector<string> LSintact::verificaPrimero(string simbolo){
    for (auto p : conjunto_primeros){
        if (p.first == simbolo && !p.second.empty()){
            return p.second;
        }
    }
    vector<string> r{};
    return r;

} 

void LSintact::PrimerosOb(){
    int cont = 0;
    vector<string> r{};

    for (auto const &t : noTerminales){
        ostringstream vts;
        copy(v_gram[cont].begin(), v_gram[cont].end() - 1, ostream_iterator<string>(vts, " "));
        vts << v_gram[cont].back(); 
        string vts_s = vts.str();        
        vector<string> delimitadores{"->", " "};
        vector<string> sentencias{}; 
        leftTrim(vts_s, delimitadores[0]); 
        sentencias = split(vts_s, '|');
        for (auto &&i : sentencias){
            if (i[0] == ' ')
                i = i.substr(1, i.size());
            if (i.size() > 2)
                rightTrim(i, delimitadores[1]);
            i.erase(remove(i.begin(), i.end(), ' '), i.end());
        }

        primerosNoConjunto.push_back(make_pair(t, sentencias)); 
        cont++;
    }

    for (auto const &pnc : primerosNoConjunto)  {
        vector<string> cr{};
        primeros(pnc.second, cr);
        conjunto_primeros[pnc.first] = cr;
    }
    for (auto const &ctp : terminales){
        vector<string> cr{};
        cr.push_back(ctp);
        conjunto_primeros[ctp] = cr;
    }
}

void LSintact::AsignaTermino(){
    for (auto const &simbolo : gram){
        stringstream ss(simbolo);
        istream_iterator<std::string> begin(ss);
        istream_iterator<std::string> end;
        vector<std::string> vector_gram(begin, end);
        noTerminales.push_back(vector_gram[0]);
        v_gram.push_back(vector_gram);
    }
    for (auto const &i : gram_sO){
        stringstream ss_sO(i);
        istream_iterator<std::string> begin2(ss_sO);
        istream_iterator<std::string> end2;
        vector<std::string> vector_gram_sO(begin2, end2);
        v_sO_gram.push_back(vector_gram_sO);
    }

    bool banderaTerminalEncontrado = false;
    for (auto const &vector : v_gram) {
        for (auto const &string_simbolo : vector) {
            if (!VerNTerm(string_simbolo)) {
                if (string_simbolo != "->" && string_simbolo != "|"){
                    for (auto const &nt : noTerminales){
                        if (string_simbolo == nt) {
                            banderaTerminalEncontrado = true;
                            break;
                        }
                    }
                    if (!banderaTerminalEncontrado) {
                        terminales.push_back(string_simbolo);
                    }
                    banderaTerminalEncontrado = false;
                }
            }
        }
    }
}


void LSintact::SigOb(){
    vector<vector<string>> cv_gram = v_gram;
    vector<pair<string, vector<vector<string>>>> conjuntoParBusqueda{};
    vector<pair<string, pair<vector<string>, vector<string>>>> paresPorSimbolo{}; 
    int cont = 0;
    for (auto const &nt : noTerminales) {
        vector<vector<string>> conjuntoBusqueda{};
        for (auto &vg : cv_gram){
            cont = 0;
            for (auto const &vgc : vg){
                if (cont > 1 && vgc == nt) {
                    conjuntoBusqueda.push_back(vg);
                    break;
                }
                cont++;
            }
        }
        conjuntoParBusqueda.push_back(make_pair(nt, conjuntoBusqueda));
    }
    for (auto const &conjuntoParesBS : conjuntoParBusqueda) {
        vector<string> sentencias_validas{}; 
        vector<string> valorOrigen{};
        for (auto const &i : conjuntoParesBS.second) {

            ostringstream vts;
            copy(i.begin(), i.end() - 1, ostream_iterator<string>(vts, " "));
            vts << i.back();          
            string vts_s = vts.str(); 

            vector<string> delimitadores{"->", " "};
            vector<string> sentencias{}; 
            leftTrim(vts_s, delimitadores[0]); 
            sentencias = split(vts_s, '|');
            for (auto &&s : sentencias){
                if (s[0] == ' ')
                    s = s.substr(1, s.size());
                if (s[s.size() - 1] == ' ')
                    s = s.substr(0, s.size() - 1);

                vector<string> busquedaDondeNoEsta = split(s, ' '); 
                if (any_of(busquedaDondeNoEsta.begin(), busquedaDondeNoEsta.end(), [&](const string &elem)
                           { return elem == conjuntoParesBS.first; }))
                { 
                    sentencias_validas.push_back(s);
                    valorOrigen.push_back(i[0]);
                }
            }
        }
        paresPorSimbolo.push_back(make_pair(conjuntoParesBS.first, make_pair(valorOrigen, sentencias_validas)));
    }

    for (auto &&pps : paresPorSimbolo){
        vector<string> vectoresSiguiente{};
        vector<pair<string, string>> vectorSiguienteClean{};
        int contParOrigen = 0;
        for (auto &&ppsv : pps.second.second){
            vector<string> vectoresPorStringDondeSeEncontro = split(ppsv, ' ');
            auto ret = std::find_if(vectoresPorStringDondeSeEncontro.begin(), vectoresPorStringDondeSeEncontro.end(),
                                    [&](string s)
                                    { return s == pps.first; });
            int contVectoresBuenos = 0;
            for (auto const &vsS : vectoresPorStringDondeSeEncontro){

                if (contVectoresBuenos > ret - vectoresPorStringDondeSeEncontro.begin() && ret != vectoresPorStringDondeSeEncontro.end()){
                    vectoresSiguiente.push_back(vsS);
                    vectorSiguienteClean.push_back(make_pair(pps.second.first[contParOrigen], vsS));
                }
                else if (ret + 1 == vectoresPorStringDondeSeEncontro.end()){
                    string nada = "";
                    vectorSiguienteClean.push_back(make_pair(pps.second.first[contParOrigen], nada));
                }

                contVectoresBuenos++;
            }
            contParOrigen++;
        }

        std::sort(vectorSiguienteClean.begin(), vectorSiguienteClean.end());
        auto last = std::unique(vectorSiguienteClean.begin(), vectorSiguienteClean.end());
        vectorSiguienteClean.erase(last, vectorSiguienteClean.end());

        sigNoConjunto[pps.first] = vectorSiguienteClean;
    }

    for (auto const &nt : noTerminales){
        for (auto const &doomie : sigNoConjunto[nt]){
            vector<string> r{};
            siguientes(nt, r);
            std::sort(r.begin(), r.end());
            auto last = std::unique(r.begin(), r.end());
            r.erase(last, r.end());
            conjunto_siguientes[nt] = r;
        }
    }
}

void LSintact::siguientes(const string &busqueda, vector<string> &vectorRet){
    if (cont_s == 0){
        vectorRet.push_back("$");
        cont_s++;
    }
    for (auto const &snc : sigNoConjunto[busqueda]){
        bool band_sigE = false;
        if (!conjunto_siguientes[busqueda].empty()) {
            for (auto const &i : conjunto_siguientes[busqueda]){
                vectorRet.push_back(i);
            }
        }
        else if (!snc.second.empty()){
            for (auto const &cp : conjunto_primeros[snc.second]){
                if (cp != "ε"){
                    vectorRet.push_back(cp);
                }
                else
                {
                    band_sigE = true;
                }
            }
        }
        else if (snc.second.empty()) {
            if (busqueda != snc.first){
                siguientes(snc.first, vectorRet);
            }
        }
        if (band_sigE){
            siguientes(snc.first, vectorRet);
        }
        band_sigE = false;
    }
}

