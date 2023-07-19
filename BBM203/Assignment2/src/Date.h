#ifndef ASSIGNMENT2_DATE_H
#define ASSIGNMENT2_DATE_H
#include <iostream>


class Date {
public:
    Date(int day, int month, int year) : day(day),month(month),year(year) {};


    bool operator == (const Date& d) const;
    bool operator < (const Date& d) const;
    bool operator > (const Date& d) const;
    bool operator <= (const Date& d) const;
    bool operator >= (const Date& d) const;
    bool operator != (const Date& d) const;


    int getDay() const;
    int getMonth() const;
    int getYear() const;
private:
    friend std::ostream& operator << (std::ostream &os, Date date);


    const int day;
    const int month;
    const int year;
};


std::ostream& operator << (std::ostream &os, Date date);
#endif //ASSIGNMENT2_DATE_H
