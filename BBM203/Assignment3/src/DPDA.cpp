//
// Created by Husrev on 9.12.2021.
//

#include "DPDA.h"

using namespace std;

vector<string> split(string str, char del = ' ');
void strip(string& str, char del = ' ');


DPDA::DPDA(const vector<string> &states, const string &currentState,
           const vector<string> &finalStates, const vector<string> &alphabet,
           const vector<string> &symbols, const vector<vector<string>> &functions)
        : currentState(currentState), states(states), finalStates(finalStates), alphabet(alphabet), symbols(symbols), functions(functions) {
    for (string state : finalStates) {
        if (find(states.begin(), states.end(), state) == states.end()) {
            throw InvalidDPDADescription("Error [1]:DPDA description is invalid!\n");
        }
    }
    for (vector<string> func : functions) {
        if (find(states.begin(), states.end(), func[0]) == states.end()) {
            throw InvalidDPDADescription("Error [1]:DPDA description is invalid!\n");
        }
        if (find(alphabet.begin(), alphabet.end(), func[1]) == alphabet.end() && func[1] != "e") {
            throw InvalidDPDADescription("Error [1]:DPDA description is invalid!\n");
        }
        if (find(symbols.begin(), symbols.end(), func[2]) == symbols.end() && func[2] != "e") {
            throw InvalidDPDADescription("Error [1]:DPDA description is invalid!\n");
        }
        if (find(states.begin(), states.end(), func[3]) == states.end()) {
            throw InvalidDPDADescription("Error [1]:DPDA description is invalid!\n");
        }
        if (find(symbols.begin(), symbols.end(), func[4]) == symbols.end() && func[4] != "e") {
            throw InvalidDPDADescription("Error [1]:DPDA description is invalid!\n");
        }
    }
};


string DPDA::start(string input) {
    if (input == "") return "ACCEPT\n";

    string output;


    // Initial State
    for (vector<string> func : functions) {
        // starting state, input symbol, expected top symbol to pop, new state, push symbol
        if (func[0] == currentState && func[1] == "e" && func[2] == "e") {
            currentState = func[3];
            if (func[4] != "e") myStack.push(func[4]);
            output += func[0] + "," + func[1] + "," + func[2] + " => " +
                      func[3] + "," + func[4] + " [STACK]:" + stackToString() + '\n';
            break;
        }
    }



    int i = 0;
    vector<string> l = split(input, ',');
    l.push_back("e");
    while (i != l.size()) {
        vector<string> tempFunc;
        bool b1 = true;
        for (vector<string> func : functions) {
            // starting state, input symbol, expected top symbol to pop, new state, push symbol
            if (func[0] == currentState && func[1] == l[i] && (func[2] == "e" ||func[2] == top())) {
                tempFunc = func;
                break;
            }
            else if (func[0] == currentState && func[1] == "e" && (func[2] == "e" ||func[2] == top())) {
                tempFunc = func;
            }
            /*cout << func[0] << " " << func[1] << endl;
            cout << l[i] << top() << endl;
            cout << (func[0] == currentState) << (func[1] == "e" || func[1] == l[i]) << (func[2] == "e" ||func[2] == top()) << endl;*/

        }
        if (tempFunc.empty()) return output + "REJECT\n";
        currentState = tempFunc[3];
        if (tempFunc[4] != "e") myStack.push(tempFunc[4]);
        if (tempFunc[2] != "e") myStack.pop();
        if (tempFunc[1] == l[i]) i += 1;
        output += tempFunc[0] + "," + tempFunc[1] + "," + tempFunc[2] + " => " +
                  tempFunc[3] + "," + tempFunc[4] + " [STACK]:" + stackToString() + '\n';
    }
    bool isFinal = false;
    for (string state : finalStates) if (state == currentState) isFinal = true;
    if (top() == "$" && isFinal) output += "ACCEPT\n";
    else output += "REJECT\n";


    return output;
}


void DPDA::reset(string initialState) {
    while (!myStack.empty()) myStack.pop();
    currentState = initialState;
}


std::string DPDA::top() {
    if (myStack.empty()) return "$";
    return myStack.top();
}

string DPDA::stackToString() {
    stack<string> temp;
    string myBeautifulString;
    while (!myStack.empty()) {
        temp.push(myStack.top());
        myStack.pop();
    }
    while (!temp.empty()) {
        myStack.push(temp.top());
        myBeautifulString += temp.top() + ",";
        temp.pop();
    }
    strip(myBeautifulString, ',');
    return myBeautifulString;
}
