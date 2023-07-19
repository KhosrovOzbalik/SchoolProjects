#include "DTrie.h"
#include <stack>

using std::string;
using std::stack;
using std::cout;
using std::stringstream;

string repeat(string sub, int count);


DTrie::DTrie() {
    for (int i = 0; i < aSize; i++) {
        data.chars[i] = nullptr;
    }
    data.childNum = 0;
}


string DTrie::insertWord(string key, string value) {
    DTrie* temp = this;
    for (int i = 0; i < key.length(); i++) {
        int currIndex = charToInt(key[i]);
        if (temp->data.chars[currIndex] == nullptr) {
            temp->data.chars[currIndex] = new DTrie();
            temp->data.childNum += 1;
        }
        temp = temp->data.chars[currIndex];
        temp->data.letter = key[i];
    }
    if (temp->data.value == value) return '"' + key + "\" already exist\n";
    else if (!temp->data.value.empty()) {
        temp->data.value = value;
        return '"' + key + "\" was updated\n";
    }
    temp->data.value = value;
    return '"' + key + "\" was added\n";
}


string DTrie::deleteWord(string key) {
    if (this->data.chars[charToInt(key[0])] == nullptr) return "\"no record\"\n";
    stack<DTrie*> nodes;
    DTrie* temp = this;
    for (int i = 0; i < key.length(); ++i) {
        int currIndex = charToInt(key[i]);
        if (temp->data.chars[currIndex] == nullptr) return "\"incorrect Dothraki word\"\n";
        temp = temp->data.chars[currIndex];
        nodes.push(temp);
    }
    if (nodes.top()->data.value.empty()) return "\"not enough Dothraki word\"\n";
    nodes.top()->data.value = "";
    while (!nodes.empty()) {
        temp = nodes.top();
        nodes.pop();
        if (temp->data.childNum == 0) {
            delete temp;
            nodes.top()->data.childNum -= 1;
        }
        else break;
    }
    return '\"' + key + "\" deletion is successful\n";
}


string DTrie::searchWord(string key) {
    if (this->data.chars[charToInt(key[0])] == nullptr) return "\"no record\"\n";
    DTrie* temp = this;
    for (int i = 0; i < key.length(); i++) {
        int currIndex = charToInt(key[i]);
        temp = temp->data.chars[currIndex];
        if (temp == nullptr) {
            return "\"incorrect Dothraki word\"\n";
        }
    }
    if (!temp->data.value.empty()) return "\"The English equivalent is " + temp->data.value + "\"\n";
    else return "\"not enough Dothraki word\"\n";
}


string DTrie::list(string pBranch, int currBranch) {
    string toReturn;
    string tabs = repeat("\t", currBranch);
    string branch = pBranch;
    for (int i = 0; i < aSize; ++i) {
        if (data.chars[i] == nullptr) continue;
        DTrie* tempNode = data.chars[i];
        branch = pBranch;
        bool valueFlag = false;
        while (tempNode != nullptr) {
            branch += tempNode->data.letter;
            if (!tempNode->data.value.empty()) {
                toReturn += tabs + '-' + branch +  '(' + tempNode->data.value + ')' + '\n';
                valueFlag = true;
            }
            if (tempNode->data.childNum == 1) {
                for (int j = 0; j < aSize; ++j) {
                    DTrie* child = tempNode->data.chars[j];
                    if (child != nullptr) {
                        tempNode = child;
                        break;
                    }
                }
            }
            else if (tempNode->data.childNum == 0) {
                break;
            }
            else {
                if (!valueFlag) toReturn += tabs + '-' + branch + '\n';
                toReturn += tempNode->list(branch, currBranch + 1);
                break;
            }
        }
    }
    return toReturn;
}

// To access Nodes in the array by chars as indexes.
int DTrie::charToInt(char c) {
    return int(c) - int('a');
}
char DTrie::intToChar(int i) {
    return char(i + int('a'));
}
