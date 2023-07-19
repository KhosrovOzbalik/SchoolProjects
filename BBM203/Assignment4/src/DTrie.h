#ifndef ASSIGNMENT4_DTRIE_H
#define ASSIGNMENT4_DTRIE_H

#include <iostream>

// Alphabet aSize
#define aSize 26

// Trie to hold Dothraki words and their meanings
class DTrie {
public:
    struct Data {
        DTrie* chars[aSize];
        char letter;
        std::string value = "";
        int childNum;
    };


    DTrie();

    std::string insertWord(std::string key, std::string value);
    std::string deleteWord(std::string key);
    std::string searchWord(std::string key);
    std::string list(std::string pBranch = "",int currBranch = 0);

private:
    Data data;

    int charToInt(char c);
    char intToChar(int i);
};


#endif //ASSIGNMENT4_DTRIE_H
