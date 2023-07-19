#include <iostream>
#include <fstream>
#include <sstream>
#include "DPDA.h"

using namespace std;

vector<string> split(string str, char del = ' ');
void strip(string& str, char del = ' ');


int main(int argc, char *argv[]) {
    ifstream dpdaFile(argv[1]);
    ifstream inputFile(argv[2]);
    ofstream outputFile(argv[3]);


    vector<std::string> states;
    string initialState;
    vector<string> finalStates;
    vector<string> alphabet;
    vector<string> symbols;
    vector<vector<std::string>> functions;


    // Creating DPDA
    string dps;
    while (getline(dpdaFile, dps)) {
        if (dps[0] == 'Q') {
            int eq = dps.find("=>");
            int cl = dps.find(')');

            string statesString = dps.substr(2, eq - 3);
            states = split(statesString, ',');

            initialState = dps.substr(eq + 4, cl - eq - 4);
            string finalString = dps.substr(cl + 2, dps.length());
            finalStates = split(finalString, ',');
            for ( string& state : finalStates) {
                strip(state, '[');
                strip(state, ']');
            }
        }
        else if (dps[0] == 'A') {
            alphabet = split(dps.substr(2, dps.length() - 2), ',');
        }
        else if (dps[0] == 'Z') {
            symbols = split(dps.substr(2, dps.length() - 2), ',');
        }
        else if (dps[0] == 'T') {
            functions.push_back(split(dps.substr(2, dps.length() - 2), ','));
        }
    }
    try  {
        DPDA dpda = DPDA(states, initialState, finalStates, alphabet, symbols, functions);
        string line;
        while (getline(inputFile, line)) {
            outputFile << dpda.start(line);
            outputFile << '\n';
            dpda.reset(initialState);
        }

    }
    catch (InvalidDPDADescription& e) {
        outputFile << e.what();
    }


    dpdaFile.close();
    inputFile.close();
    outputFile.close();
    return 0;

}
