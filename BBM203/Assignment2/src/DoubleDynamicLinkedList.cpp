#include "DoubleDynamicLinkedList.h"

using namespace std;

DoubleDynamicLinkedList::~DoubleDynamicLinkedList() {
    Node* temp = head;
    if (isEmpty()) {}
    else {
        while (temp->next != nullptr) {
            temp = temp->next;
            delete temp->prev->data;
            delete temp->prev;
        }
        delete temp->data;
        delete temp;
    }
}


bool DoubleDynamicLinkedList::isEmpty() {return (size == 0);}


void DoubleDynamicLinkedList::add(Employee* object) {
    Node* temp = new Node();
    if (isEmpty()) {
        temp->data = object;
        head = temp;
    }
    else if (object->getAppointmentDate() < head->data->getAppointmentDate()) {
        temp->next = head;
        head->prev = temp;
        head = temp;
    }
    else {
        temp = head;
        while (true) {
            if (temp->next == nullptr) {
                Node* temp2 = new Node();
                temp2->data = object;
                temp->next = temp2;
                temp2->prev = temp;
                break;
            }
            else if (object->getAppointmentDate() < temp->next->data->getAppointmentDate()) {
                Node* temp2 = new Node();
                temp2->data = object;
                temp2->next = temp->next;
                temp->next->prev = temp2;
                temp->next = temp2;
                temp2->prev = temp;
                break;
            }
            temp = temp->next;
        }
    }
    size += 1;
}


void DoubleDynamicLinkedList::remove(int number) {
    Node* temp = head;
    if (head->data->getId() == number) {
        size -= 1;
        head = nullptr;
        delete temp;
    }
    else {
        while (temp->next != nullptr) {
            if (temp->next->data->getId() == number) {
                size -= 1;
                Node* temp2 = temp->next;
                temp->next = temp2->next;
                if (temp2->next != nullptr) {temp2->next->prev = temp;}
                delete temp2->data;
                delete temp2;
                break;
            }
            temp = temp->next;
        }
    }
}


Employee *DoubleDynamicLinkedList::find(int number) {
    Node* temp = head;
    while (temp != nullptr) {
        if (temp->data->getId() == number) {
            return temp->data;
        }
        temp = temp->next;
    }
    return nullptr;
}


std::ostream& operator << (std::ostream &os, const DoubleDynamicLinkedList& ddll) {
    DoubleDynamicLinkedList::Node* temp = ddll.head;
    while (temp != nullptr) {
        os << *(temp->data);
        temp = temp->next;
    }
    return (os << std::endl);
}

DoubleDynamicLinkedList::Node *DoubleDynamicLinkedList::getHead() const {
    return head;
}

int DoubleDynamicLinkedList::getSize() const {
    return size;
}
