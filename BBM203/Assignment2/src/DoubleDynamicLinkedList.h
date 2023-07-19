#ifndef ASSIGNMENT2_DOUBLEDYNAMICLINKEDLIST_H
#define ASSIGNMENT2_DOUBLEDYNAMICLINKEDLIST_H
#include <iostream>
#include "Employee.h"

class CircularArrayLinkedList;


class DoubleDynamicLinkedList {
private:
    class Node {
    public:
        Node() {};
        Node(Employee* data) : data(data) {};
        Employee* data = nullptr;
        Node* next = nullptr;
        Node* prev = nullptr;
    };
public:
    virtual ~DoubleDynamicLinkedList();

    bool isEmpty();


    void add(Employee* object);
    void remove(int number);

    Node *getHead() const;

    int getSize() const;


    Employee* find(int number);
private:
    friend std::ostream& operator << (std::ostream &os, const DoubleDynamicLinkedList& ddll);
    friend void printNumberOrder(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList);
    friend void printAppointmentOrder(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList);
    friend void printAfterDate(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, Date date);
    friend void printBeforeDate(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, Date date);
    friend void printAtYear(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, int year);
    friend void printAtMonth(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, int year);
    friend void printLastAssignByTitle(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, std::string title);


    int size = 0;
    Node* head = nullptr;
};


std::ostream& operator << (std::ostream &os, const DoubleDynamicLinkedList& ddll);
#endif //ASSIGNMENT2_DOUBLEDYNAMICLINKEDLIST_H
