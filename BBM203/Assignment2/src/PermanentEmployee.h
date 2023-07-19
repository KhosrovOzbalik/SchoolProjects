#ifndef ASSIGNMENT2_PERMANENTEMPLOYEE_H
#define ASSIGNMENT2_PERMANENTEMPLOYEE_H

#include "Employee.h"


class PermanentEmployee : public Employee{
public:
    PermanentEmployee(const int id, const int type, const std::string &name, const std::string &surname,
                      const std::string &title, double coefficient, const Date &birthDate, const Date &appointmentDate)
            : Employee(id, type, name, surname, title, coefficient, birthDate, appointmentDate) {}

    PermanentEmployee(const int id, const int type, const std::string &name, const std::string &surname,
                      const std::string &title, double coefficient, const Date &birthDate, const Date &appointmentDate,
                      int serviceLength) : Employee(id, type, name, surname, title, coefficient, birthDate,
                                                    appointmentDate, serviceLength) {}

private:
    friend std::ostream& operator << (std::ostream &os, const Employee& e);
};

#endif //ASSIGNMENT2_PERMANENTEMPLOYEE_H
