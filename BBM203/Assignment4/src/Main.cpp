#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include "DTrie.h"

using std::cout;
using std::string;
using std::ifstream;
using std::ofstream;
using std::vector;

void strip(string& str, char del = ' ');
vector<string> split(string str, char del = ' ');

int main(int argc, char *argv[]) {
    ifstream inputFile(argv[1]);
    ofstream outputFile(argv[2]);

    DTrie* head = new DTrie;


    string output;

    string line;
    while (getline(inputFile, line)) {
        string funcName = line.substr(0,3);
        if (funcName == "ins") {
            line = line.substr(line.find('(') + 1);
            strip(line,')');
            vector<string> l = split(line, ',');
            output += head->insertWord(l[0], l[1]);
        }
        if (funcName == "del") {
            line = line.substr(line.find('(') + 1);
            strip(line,')');
            output += head->deleteWord(line);
        }
        if (funcName == "sea") {
            line = line.substr(line.find('(') + 1);
            strip(line,')');
            output += head->searchWord(line);
        }
        if (funcName == "lis") output += head->list();
    }

    outputFile << output;

    inputFile.close();
    outputFile.close();
    return 0;
}