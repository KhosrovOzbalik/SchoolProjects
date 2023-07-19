#ifndef UNTITLED_INVALIDDPDADESCRIPTION_H
#define UNTITLED_INVALIDDPDADESCRIPTION_H

#include <exception>
#include <iostream>
#include <string>


class InvalidDPDADescription : public std::exception {
    std::string msg;
public:
    InvalidDPDADescription(const std::string& msg) : msg(msg){}

    const char* what() const noexcept override
    {
        return msg.c_str();
    }
};


#endif //UNTITLED_INVALIDDPDADESCRIPTION_H
