#include "Employee.h"

Employee::Employee(const int id, const int type, std::string name, std::string surname, std::string title,
         double coefficient, const Date &birthDate, const Date &appointmentDate) :
        id(id), type(type), name(std::move(name)), surname(std::move(surname)), title(std::move(title)), coefficient(coefficient), birthDate(birthDate),
        appointmentDate(appointmentDate) {}
Employee::Employee(const int id, const int type, std::string name, std::string surname, std::string title,
         double coefficient, const Date &birthDate, const Date &appointmentDate, int serviceLength) :
        id(id), type(type), name(std::move(name)), surname(std::move(surname)), title(std::move(title)), coefficient(coefficient), birthDate(birthDate),
        appointmentDate(appointmentDate), serviceLength(serviceLength) {}


int Employee::getId() const {return id;}
int Employee::getType() const {return type;}
std::string Employee::getName() const {return name;}
std::string Employee::getSurname() const {return surname;}
std::string Employee::getTitle() const {return title;}
void Employee::setTitle(const std::string title) {Employee::title = title;}
double Employee::getCoefficient() const {return coefficient;}
void Employee::setCoefficient(double coefficient) {Employee::coefficient = coefficient;}
Date Employee::getBirthDate() const {return birthDate;}
Date Employee::getAppointmentDate() const {return appointmentDate;}
int Employee::getServiceLength() const {return serviceLength;}
void Employee::setServiceLength(int serviceLength) {Employee::serviceLength = serviceLength;}


std::ostream& operator << (std::ostream &os, const Employee& e) {
    os << "Id: " << e.id << " " << std::endl;
    os << "Type: " << e.type << " " << std::endl;
    os << "Name: " << e.name << " " << std::endl;
    os << "Surname: " << e.surname << " " << std::endl;
    os << "Title: " << e.title << " " << std::endl;
    os << "Coefficient: " << e.coefficient << " " << std::endl;
    os << "Birth date: " << e.birthDate << " " << std::endl;
    os << "Appointment date: " << e.appointmentDate << " " << std::endl;
    if (e.getServiceLength() != NULL) {os << "Service Length: " << e.getServiceLength();}
    return (os << std::endl);
}

