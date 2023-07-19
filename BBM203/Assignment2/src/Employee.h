#ifndef ASSIGNMENT2_EMPLOYEE_H
#define ASSIGNMENT2_EMPLOYEE_H
#include <iostream>
#include "Date.h"


class Employee {
public:
    Employee(int id, int type, std::string name, std::string surname, std::string title,
             double coefficient, const Date &birthDate, const Date &appointmentDate);
    Employee(int id, int type, std::string name, std::string surname, std::string title,
             double coefficient, const Date &birthDate, const Date &appointmentDate, int serviceLength);


    int getId() const;
    int getType() const;
    std::string getName() const;
    std::string getSurname() const;
    std::string getTitle() const;
    void setTitle(std::string title);
    double getCoefficient() const;
    void setCoefficient(double coefficient);
    Date getBirthDate() const;
    Date getAppointmentDate() const;
    int getServiceLength() const;
    void setServiceLength(int length);
private:
    friend std::ostream& operator << (std::ostream &os, const Employee& e);
    const int id; // 4 digit integer
    const int type; // 0 is temporary & 1 is permanent
    const std::string name; // 12 char
    const std::string surname; // 12 char
    std::string title; // 12 char
    double coefficient; // Real number
    const Date birthDate; // {day,month,year}
    Date appointmentDate; // {day,month,year}
    int serviceLength = NULL;
};


std::ostream& operator << (std::ostream &os, const Employee& e);
#endif //ASSIGNMENT2_EMPLOYEE_H
