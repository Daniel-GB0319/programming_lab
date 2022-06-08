#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class LSintact
{
private:
    int cont_s = 0;
    vector<vector<string>> v_sO_gram{};
    vector<vector<string>> tabla;
    vector<pair<string, vector<string>>> primerosNoConjunto;
    map<string, vector<pair<string, string>>> sigNoConjunto;
    map<string, vector<string>> conjunto_primeros;
    map<string, vector<string>> conjunto_siguientes;
    vector<string> terminales;
    vector<string> noTerminales;
    vector<vector<string>> v_gram{};
    vector<string> gram = {
        "E -> T E’","E’ -> + T E’ | ε", "T -> F T’", "T’ -> * F T’ | ε","F -> id | ( E )"}; 
    vector<string> gram_sO = {
        "E -> T E’","E’ -> + T E’","E’ -> ε","T -> F T’","T’ -> ε","T’ -> ε","F -> id","F -> ( E )"};

public:
    string TF[6][7];
    vector<string> cabezera{};
    vector<string> pc{};
    LSintact(){
        AsignaTermino();
        PrimerosOb();
        SigOb();
        gTab();
    }
    void PrimerosOb();
    void SigOb();
    void AsignaTermino();
    void primeros(const vector<string> &, vector<string> &);
    void siguientes(const string &, vector<string> &);
    void gTab();
    void ProbarCadena(string);
    void GetTerminales();
    void GetnoTerminales();
    vector<string> verificaPrimero(string);
    bool VerNTerm(string);
    void imprimeGramatica() {
        for (auto const &g : gram){
            cout << g << endl;
        }
    }
    
    vector<string> split(const string &s, char seperator) {
        vector<std::string> output;
        string::size_type prev_pos = 0, pos = 0;

        while ((pos = s.find(seperator, pos)) != string::npos) {
            string substring(s.substr(prev_pos, pos - prev_pos));
            output.push_back(substring);
            prev_pos = ++pos;
        }
        output.push_back(s.substr(prev_pos, pos - prev_pos)); 
        return output;
    }
    string &leftTrim(string &str, string const &chars){
        str.erase(0, str.find_first_of(chars) + 3); 
        return str;
    }
    string &rightTrim(string &str, string const &chars){
        str.erase(str.find_first_of(chars) + 1);
        return str;
    }

    string &corte(string &str, string const &chars){
        str.erase(0, str.find_first_of(chars) + 2);
        return str;
    }

};
