//
// Created by Husrev on 9.12.2021.
//

#ifndef UNTITLED_DPDA_H
#define UNTITLED_DPDA_H

#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include "InvalidDPDADescription.h"


class DPDA {
public:
    DPDA(const std::vector<std::string> &states, const std::string &currentState,
         const std::vector<std::string> &finalStates, const std::vector<std::string> &alphabet,
         const std::vector<std::string> &symbols, const std::vector<std::vector<std::string>> &functions);

    std::string start(std::string input);
    void reset(std::string initialState);
    std::string top();
    std::string stackToString();
private:
    std::stack<std::string> myStack = std::stack<std::string>();
    std::string currentState;

    std::vector<std::string> const states;
    std::vector<std::string> const finalStates;
    std::vector<std::string> const alphabet;
    std::vector<std::string> const symbols;
    // starting state, input symbol, expected symbol at the top of the stack (which is being popped), the resulting state, and the symbol to be written on the stack
    std::vector<std::vector<std::string>> const functions;
};


#endif //UNTITLED_DPDA_H
