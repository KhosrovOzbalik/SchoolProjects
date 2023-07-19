#include "Date.h"


bool Date::operator == (const Date& d) const {return ((year == d.year) && (month == d.month) && (day == d.day));}
bool Date::operator < (const Date& d) const {return (year < d.year) ? 1 : (year > d.year) ? 0 : (month < d.month) ? 1 : (month > d.month) ? 0 : (day < d.day);}
bool Date::operator > (const Date& d) const {return (year > d.year) ? 1 : (year < d.year) ? 0 : (month > d.month) ? 1 : (month < d.month) ? 0 : (day > d.day);}
bool Date::operator <= (const Date& d) const {return operator<(d) || operator==(d);}
bool Date::operator >= (const Date& d) const {return operator>(d) || operator==(d);}
bool Date::operator != (const Date& d) const {return !(operator==(d));}


int Date::getDay() const {return day;}
int Date::getMonth() const {return month;}
int Date::getYear() const {return year;}


std::ostream& operator << (std::ostream &os, Date date) {
    return (os << date.day << "-" << date.month << "-" << date.year);
}
