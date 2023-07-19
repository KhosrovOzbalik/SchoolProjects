#ifndef ASSIGNMENT2_CIRCULARARRAYLINKEDLIST_H
#define ASSIGNMENT2_CIRCULARARRAYLINKEDLIST_H
#include <iostream>
#include "Employee.h"

class DoubleDynamicLinkedList;


class CircularArrayLinkedList {
private:
    class Node {
    public:
        Node() {};
        Node(Employee* data) : data(data) {};
        Employee* data = nullptr;
        int next = -1;
    };
public:
    explicit CircularArrayLinkedList(int maxSize);
    virtual ~CircularArrayLinkedList();


    bool isFull();
    bool isEmpty();


    void add(Employee* object);
    void remove(int number);
    Employee* find(int Number);

    int getSize() const;

    const int getMaxSize() const;

    int getHead() const;

private:
    friend std::ostream &operator<<(std::ostream &os, CircularArrayLinkedList call);
    friend void printNumberOrder(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList);
    friend void printAppointmentOrder(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList);
    friend void printAfterDate(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, Date date);
    friend void printBeforeDate(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, Date date);
    friend void printAtYear(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, int year);
    friend void printAtMonth(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, int year);
    friend void printLastAssignByTitle(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, std::string title);


    Node* array;
    int size = 0;
    int const maxSize;
    int head = -1;
    int tail = -1;
};


std::ostream &operator<<(std::ostream &os, CircularArrayLinkedList call);
#endif //ASSIGNMENT2_CIRCULARARRAYLINKEDLIST_H
