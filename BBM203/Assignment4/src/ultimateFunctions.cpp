//
// Created by Husrev on 9.12.2021 to make his life easier.
//

#include <iostream>
#include <vector>
#include <sstream>

using namespace std;


void strip(string& str, char del = ' ') {
    while (true) {
        if (str[0] == del) str = str.substr(1);
        else if (str[str.length() - 1] == del) str = str.substr(0,str.length() - 1);
        else break;
    }
}


vector<string> split(string str, char del = ' ') {
    vector<string> splitted;
    string part;
    stringstream splitter(str);
    while (getline(splitter, part, del)) splitted.push_back(part);
    return splitted;
}


string repeat(string sub, int count) {
    string toReturn;
    for (int i = 0; i < count; ++i) {
        toReturn += sub;
    }
    return toReturn;
}