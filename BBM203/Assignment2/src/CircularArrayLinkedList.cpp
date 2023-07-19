#include "CircularArrayLinkedList.h"

using namespace std;

CircularArrayLinkedList::CircularArrayLinkedList(const int maxSize) : maxSize(maxSize) {
    array = new Node[maxSize];
    //array = (Node*) malloc(maxSize*sizeof(Node));
    for (int i = 0; i < maxSize; ++i) {
        Node temp = Node();
        array[i] = temp;
    }
}


bool CircularArrayLinkedList::isEmpty() {return (size == 0);}
bool CircularArrayLinkedList::isFull() {return (size == maxSize);}


void CircularArrayLinkedList::add(Employee* object) {
    if (isFull()) {}
    else if (isEmpty()) {
        size += 1;
        array[0].data = object;
        array[0].next = 0;
        head = 0;
        tail = 0;
    }
    else {
        size += 1;

        // Put to the array
        int index;
        for (int i = 0; i < maxSize; ++i) {
            if (array[i].next == -1) {
                array[i].data = object;
                index = i;
                break;
            }
        }
        // Sort
        if (object->getId() < array[head].data->getId()) {
            array[index].next = head;
            array[tail].next = index;
            head = index;
        }
        else {
            int temp = head;
            while (true) {
                if (array[temp].next == head) {
                    array[temp].next = index;
                    array[index].next = head;
                    tail = index;
                    break;
                } else if (object->getId() < array[array[temp].next].data->getId()) {
                    array[index].next = array[temp].next;
                    array[temp].next = index;
                    break;
                }
                temp = array[temp].next;
            }
        }
    }
}
void CircularArrayLinkedList::remove(int number) {
    int temp = head;
    if (isEmpty()) {}
    else if (array[head].data->getId() == number) {
        if (size == 1) {
            array[head].next = -1;
            head = -1;
        }
        else {
            int temp = head;
            while (array[temp].next != head) {
                temp = array[temp].next;
            }
            array[temp].next = array[head].next;
            array[head].next = -1;
            head = array[temp].next;
        }
        size -= 1;
    }
    else {
        temp = head;
        while (true) {
            if (array[array[temp].next].data->getId() == number) {
                int temp2 = array[temp].next;
                array[temp].next = array[temp2].next;

                break;
            }
            else if(array[temp].next == head) {break;}
            temp = array[temp].next;
        }
        size -= 1;
    }
}

Employee* CircularArrayLinkedList::find(int number) {
    int temp = head;
    if (isEmpty()) {return nullptr;}
    Employee* tempE = array[head].data;
    if ((tempE->getId() == number)) {return array[head].data;}
    while (array[temp].next != -1) {
        if (array[temp].next == head) {return nullptr;}
        if (tempE->getId() == number) {
            return array[temp].data;
        }
        temp = array[temp].next;
        tempE = array[temp].data;
    }
    return nullptr;
}



CircularArrayLinkedList::~CircularArrayLinkedList() {
    delete[] array;
}


std::ostream &operator<<(std::ostream &os, CircularArrayLinkedList call) {
    int temp = call.head;
    for (int i = 0; i < call.size; ++i) {
        os <<  *(call.array[temp].data);
        temp = call.array[temp].next;
    }
    return (os);
}


int CircularArrayLinkedList::getSize() const {
    return size;
}

const int CircularArrayLinkedList::getMaxSize() const {
    return maxSize;
}

int CircularArrayLinkedList::getHead() const {
    return head;
}
