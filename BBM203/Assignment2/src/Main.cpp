#include <iostream>
#include <sstream>
#include "CircularArrayLinkedList.h"
#include "DoubleDynamicLinkedList.h"
#include "Date.h"
#include "Employee.h"
#include "TemporaryEmployee.h"
#include "PermanentEmployee.h"

using namespace std;

void printConsole() {
    cout << "____ Employee Recording System ____\n";
    cout << "Please select for the following Menu Operation:\n";
    cout << "1) Appointment of a new employee\n";
    cout << "2) Appointment of a transferred employee\n";
    cout << "3) Updating the title and salary coefficient of an employee\n";
    cout << "4) Deletion of an employee\n";
    cout << "5) Listing the information of an employee\n";
    cout << "6) Listing employees ordered by employee number\n";
    cout << "7) Listing employees ordered by appointment date\n";
    cout << "8) Listing employees appointed after a certain date\n";
    cout << "9) Listing employees assigned in a given year\n";
    cout << "10) Listing employees born before a certain date\n";
    cout << "11) Listing employees born in a particular month\n";
    cout << "12) Listing the information of the last assigned employee with a given title\n";
}


void printNumberOrder(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList) {
    CircularArrayLinkedList order = CircularArrayLinkedList(tempList.getSize() + permList.getSize());
    int tempTemp = tempList.getHead();
    for (int i = 0; i < tempList.size; ++i) {
        Employee tempEmp = *tempList.array[tempTemp].data;
        order.add(&tempEmp);
        tempTemp = tempList.array[tempTemp].next;
    }

    DoubleDynamicLinkedList::Node* tempPerm = permList.head;
    for (int i = 0; i < permList.getSize(); ++i) {
        Employee tempEmp = *tempPerm->data;
        order.add(&tempEmp);
        tempPerm = tempPerm->next;
    }
    if (order.size > 0) {cout << order;}
}


void printAppointmentOrder(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList) {
    DoubleDynamicLinkedList order = DoubleDynamicLinkedList();
    int tempTemp = tempList.getHead();
    for (int i = 0; i < tempList.size; ++i) {
        Employee tempEmp = *tempList.array[tempTemp].data;
        order.add(&tempEmp);
        tempTemp = tempList.array[tempTemp].next;
    }

    DoubleDynamicLinkedList::Node* tempPerm = permList.head;
    for (int i = 0; i < permList.getSize(); ++i) {
        Employee tempEmp = *tempPerm->data;
        order.add(&tempEmp);
        tempPerm = tempPerm->next;
    }
    if (order.size > 0) {cout << order;}
}


void printAfterDate(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, Date date) {
    DoubleDynamicLinkedList order = DoubleDynamicLinkedList();
    int tempTemp = tempList.getHead();
    for (int i = 0; i < tempList.size; ++i) {
        if (tempList.array[tempTemp].data->getAppointmentDate() > date) {
            Employee tempEmp = *tempList.array[tempTemp].data;
            order.add(&tempEmp);
        }
        tempTemp = tempList.array[tempTemp].next;
    }

    DoubleDynamicLinkedList::Node* tempPerm = permList.head;
    for (int i = 0; i < permList.getSize(); ++i) {
        if (tempPerm->data->getAppointmentDate() > date) {
            Employee tempEmp = *tempPerm->data;
            order.add(&tempEmp);
        }
        tempPerm = tempPerm->next;
    }
    if (order.size > 0) {cout << order;}
}


void printBeforeDate(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, Date date) {
    CircularArrayLinkedList order = CircularArrayLinkedList(tempList.getSize() + permList.getSize());
    int tempTemp = tempList.getHead();
    for (int i = 0; i < tempList.size; ++i) {
        if (tempList.array[tempTemp].data->getAppointmentDate() < date) {
            Employee tempEmp = *tempList.array[tempTemp].data;
            order.add(&tempEmp);
        }
        tempTemp = tempList.array[tempTemp].next;
    }

    DoubleDynamicLinkedList::Node* tempPerm = permList.head;
    for (int i = 0; i < permList.getSize(); ++i) {
        if (tempPerm->data->getAppointmentDate() < date) {
            Employee tempEmp = *tempPerm->data;
            order.add(&tempEmp);
        }
        tempPerm = tempPerm->next;
    }
    if (order.size > 0) {cout << order;}
}


void printAtYear(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, int year) {
    DoubleDynamicLinkedList order = DoubleDynamicLinkedList();
    int tempTemp = tempList.getHead();
    for (int i = 0; i < tempList.size; ++i) {
        if (tempList.array[tempTemp].data->getAppointmentDate().getYear() == year) {
            Employee tempEmp = *tempList.array[tempTemp].data;
            order.add(&tempEmp);
        }
        tempTemp = tempList.array[tempTemp].next;
    }

    DoubleDynamicLinkedList::Node* tempPerm = permList.head;
    for (int i = 0; i < permList.getSize(); ++i) {
        if (tempPerm->data->getAppointmentDate().getYear() == year) {
            Employee tempEmp = *tempPerm->data;
            order.add(&tempEmp);
        }
        tempPerm = tempPerm->next;
    }
    if (order.size > 0) {cout << order;}
}


void printAtMonth(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, int month) {
    CircularArrayLinkedList order = CircularArrayLinkedList(tempList.getSize() + permList.getSize());
    int tempTemp = tempList.getHead();
    for (int i = 0; i < tempList.size; ++i) {
        if (tempList.array[tempTemp].data->getAppointmentDate().getMonth() == month) {
            Employee tempEmp = *tempList.array[tempTemp].data;
            order.add(&tempEmp);
        }
        tempTemp = tempList.array[tempTemp].next;
    }

    DoubleDynamicLinkedList::Node* tempPerm = permList.head;
    for (int i = 0; i < permList.getSize(); ++i) {
        if (tempPerm->data->getAppointmentDate().getMonth() == month) {
            Employee tempEmp = *tempPerm->data;
            order.add(&tempEmp);
        }
        tempPerm = tempPerm->next;
    }
    if (order.size > 0) {cout << order;}
}


void printLastAssignByTitle(CircularArrayLinkedList tempList, DoubleDynamicLinkedList permList, string title) {
    Employee* last = nullptr;
    int tempTemp = tempList.getHead();
    for (int i = 0; i < tempList.size; ++i) {
        if (tempList.array[tempTemp].data->getTitle() == title && last == nullptr) {
            last = tempList.array[tempTemp].data;
        }
        else {
            if (tempList.array[tempTemp].data->getTitle() == title &&
            tempList.array[tempTemp].data->getAppointmentDate() > last->getAppointmentDate()) {
                last = tempList.array[tempTemp].data;
            }
        }
        tempTemp = tempList.array[tempTemp].next;
    }

    DoubleDynamicLinkedList::Node* tempPerm = permList.head;
    for (int i = 0; i < permList.getSize(); ++i) {
        if (tempPerm->data->getTitle() == title && last == nullptr) {
            last = tempPerm->data;
        }
        else {
            if (tempPerm->data->getTitle() == title &&
            tempPerm->data->getAppointmentDate() > last->getAppointmentDate()) {
                last = tempPerm->data;
            }
        }
        tempPerm = tempPerm->next;
    }
    if (last != nullptr) {cout << *last;}
}


int main() {
    CircularArrayLinkedList tempList = CircularArrayLinkedList(20);
    DoubleDynamicLinkedList permList = DoubleDynamicLinkedList();


    while (true) {
        printConsole();
        int command;
        cin >> command;
        switch (command) {
            // Appointment of an employee, who started working for the first time, to the institution
            case 1: {
                int number;
                cout << "Type the employee number" << endl;
                cin >> number;
                Employee* tempE = tempList.find(number);
                Employee* permE = permList.find(number);
                if (tempE == nullptr && permE == nullptr) {
                    int type;
                    cout << "Type the employee type" << endl;
                    cin >> type;
                    string name, surname, title;
                    cout << "Type the name" << endl;
                    cin >> name;
                    cout << "Type the surname" << endl;
                    cin >> surname;
                    cout << "Type the title" << endl;
                    cin >> title;
                    double coefficient;
                    cout << "Type the salary coefficient" << endl;
                    cin >> coefficient;
                    string birth, appointment;
                    cout << "Type the birth date" << endl;
                    cin >> birth;
                    cout << "Type the appointment date" << endl;
                    cin >> appointment;
                    Date birthDate = Date(stoi(birth.substr(0,2)),stoi(birth.substr(3,2)),stoi(birth.substr(6,4)));
                    Date appointmentDate = Date(stoi(appointment.substr(0,2)),stoi(appointment.substr(3,2)),stoi(appointment.substr(6,4)));
                    if (type == 0) {
                        Employee* employee = new TemporaryEmployee(number,type,name,surname,title,coefficient,birthDate,appointmentDate);
                        tempList.add(employee);
                    }
                    else if (type == 1) {
                        Employee* employee = new PermanentEmployee(number,type,name,surname,title,coefficient,birthDate,appointmentDate);
                        permList.add(employee);
                    }
                }
                break;
            }
            // Appointment of an employee, who transferred from another institution, to the institution
            case 2: {
                int number;
                cout << "Type the employee number" << endl;
                cin >> number;
                Employee* tempE = tempList.find(number);
                Employee* permE = permList.find(number);
                if (tempE == nullptr && permE == nullptr) {
                    int type;
                    cout << "Type the employee type" << endl;
                    cin >> type;
                    string name, surname, title;
                    cout << "Type the name" << endl;
                    cin >> name;
                    cout << "Type the surname" << endl;
                    cin >> surname;
                    cout << "Type the title" << endl;
                    cin >> title;
                    double coefficient;
                    cout << "Type the salary coefficient" << endl;
                    cin >> coefficient;
                    string birth, appointment;
                    cout << "Type the birth date" << endl;
                    cin >> birth;
                    cout << "Type the appointment date" << endl;
                    cin >> appointment;
                    Date birthDate = Date(stoi(birth.substr(0,2)),stoi(birth.substr(3,2)),stoi(birth.substr(6,4)));
                    Date appointmentDate = Date(stoi(appointment.substr(0,2)),stoi(appointment.substr(3,2)),stoi(appointment.substr(6,4)));
                    int serviceLength;
                    cout << "Type length of service in other institutions" << endl;
                    cin >> serviceLength;
                    if (type == 0) {
                        Employee* employee = new TemporaryEmployee(number,type,name,surname,title,coefficient,birthDate,appointmentDate,serviceLength);
                        tempList.add(employee);
                    }
                    else if (type == 1) {
                        Employee* employee = new PermanentEmployee(number,type,name,surname,title,coefficient,birthDate,appointmentDate,serviceLength);
                        permList.add(employee);
                    }
                }
                break;
            }
            // Updating the title and salary coefficient of an employee
            case 3: {
                int number;
                cout << "Type the employee number" << endl;
                cin >> number;
                Employee* tempE = tempList.find(number);
                Employee* permE = permList.find(number);
                if (tempE != nullptr) {
                    string title;
                    cout << "Type the title" << endl;
                    cin >> title;
                    tempE->setTitle(title);
                    double coefficient;
                    cout << "Type the salary coefficient" << endl;
                    cin >> coefficient;
                    tempE->setCoefficient(coefficient);
                }
                else if (permE != nullptr) {
                    string title;
                    cout << "Type the title" << endl;
                    cin >> title;
                    permE->setTitle(title);
                    double coefficient;
                    cout << "Type the salary coefficient" << endl;
                    cin >> coefficient;
                    permE->setCoefficient(coefficient);
                }
                break;
            }
            //  Deletion of an employee
            case 4: {
                int number;
                cout << "Type the employee number" << endl;
                cin >> number;
                tempList.remove(number);
                permList.remove(number);
                break;
            }
            // Listing the information of an employee
            case 5: {
                int number;
                cout << "Type the employee number" << endl;
                cin >> number;
                Employee* tempE = tempList.find(number);
                Employee* permE = permList.find(number);
                if (tempE != nullptr) {
                    cout << *tempE;
                }
                else if (permE != nullptr) {
                    cout << *permE;
                }
                break;
            }
            // Listing of all employees in the order of employee number
            case 6: {
                printNumberOrder(tempList,permList);
                break;
            }
            // Listing of all employees in the order of their appointment to the institution
            case 7: {
                printAppointmentOrder(tempList,permList);
                break;
            }
            // Listing the employees assigned to the institution after a certain date
            case 8: {
                string dateString;
                cout << "Type the appointment date" << endl;
                cin >> dateString;
                Date date = Date(stoi(dateString.substr(0, 2)), stoi(dateString.substr(3, 2)), stoi(dateString.substr(6, 4)));
                printAfterDate(tempList, permList, date);
                break;
            }
            // Listing the employees assigned to the institution in a given year
            case 9: {
                int year;
                cout << "Type the year" << endl;
                cin >> year;
                printAtYear(tempList, permList, year);
                break;
            }
            // Listing of employees born before a certain date
            case 10: {
                string dateString;
                cout << "Type the appointment date" << endl;
                cin >> dateString;
                Date date = Date(stoi(dateString.substr(0, 2)), stoi(dateString.substr(3, 2)), stoi(dateString.substr(6, 4)));
                printBeforeDate(tempList, permList, date);
                break;
            }
            // Listing of employees born in a particular month
            case 11: {
                int month;
                cout << "Type the month" << endl;
                cin >> month;
                printAtMonth(tempList, permList, month);
                break;
            }
            // Listing the information of the employee with a certain title, who was last assigned to the institution
            case 12: {
                string title;
                cout << "Type the title" << endl;
                cin >> title;
                printLastAssignByTitle(tempList, permList, title);
                break;
            }
        }
        if (command == -1) break;
    }
    return 0;
}