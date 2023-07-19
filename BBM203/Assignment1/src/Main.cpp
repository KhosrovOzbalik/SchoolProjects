#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>

using namespace std;


void printGrid(vector<vector<int>>&  grid, ofstream& output) {
    for (int i = 0; i < grid.size(); ++i) {
        for (int j = 0; j < grid.size(); ++j) {
            output << grid[i][j] << " ";
        }
        output << "\n";
    }
}


// A recursion function to return the linked balloons.
void getLinks(vector<vector<int>>&  grid, int size, int value, int x, int y, vector<vector<int>>&  balloons) {
    int odds[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
    grid[x][y] = 0;
    for (auto & odd : odds) {
        if ((x + odd[0]) >= 0 && x + odd[0] < size && y + odd[1] >= 0 && y + odd[1] < size) {
            if (grid[x + odd[0]][y + odd[1]] == value) {
                vector<int> coords = {x + odd[0],y + odd[1]};
                balloons.push_back(coords);
                getLinks(grid, size, value, coords[0], coords[1], balloons);
            }
        }
    }
    grid[x][y] = value;
}


// To link the balloons
void linker(vector<vector<int>>&  grid, int size, int value, int x, int y) {
    vector<vector<int>> balloons;
    getLinks(grid, size, value, x, y, balloons);
    if (balloons.size() >= 2) {
        for (auto & balloon : balloons) {
            grid[balloon[0]][balloon[1]] = 0;
        }
        grid[x][y] = ++value;
        linker(grid, size, value, x, y);
    }
}


int main(int argc, char *argv[]) {
    // INITIALIZING ARRAY
    vector<vector<int>> grid;
    int size;
    string inputText;
    ifstream file(argv[1]);
    ofstream output(argv[3]);
    getline(file, inputText);
    stringstream myStream(inputText);
    myStream >> size;
    for (int i = 0; i < size; ++i) {
        vector<int> temp;
        for (int j = 0; j < size; ++j) temp.push_back(0);
        grid.push_back(temp);
    }
    while (getline(file, inputText)) {
        int value,x,y;
        stringstream coordStream(inputText);
        coordStream >> value >> x >> y;
        grid[x][y] = value;
        // Links
        linker(grid, size, value, x, y);
    }
    file.close();
    output << "PART 1:\n";
    printGrid(grid, output);
    output << "\n";
    // Part 2 - BOOM!
    file.open("input2.txt");
    getline(file, inputText);
    stringstream strStream(inputText);
    strStream >> size;
    grid.clear();
    for (int i = 0; i < size; ++i) {
        getline(file, inputText);
        vector<int> temp;
        stringstream strStream(inputText);
        int value;
        for (int j = 0; j < size; ++j) {
            strStream >> value;
            temp.push_back(value);
        }
        grid.push_back(temp);
    }
    int point = 0;
    while (getline(file, inputText)) {
        int x, y;
        stringstream coordStream(inputText);
        coordStream >> x >> y;
        int value = grid[x][y];
        if (value == 0) continue;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if ((((abs(x - i) == abs(y - j))) || (x - i == 0) || (y - j == 0)) && (grid[i][j] == value)) {
                    grid[i][j] = 0;
                    point += value;
                }
            }
        }
    }
    file.close();
    output << "PART 2:\n";
    printGrid(grid, output);
    output << "Final Point: " << point << "p";
    output.close();
}
