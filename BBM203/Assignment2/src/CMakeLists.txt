cmake_minimum_required(VERSION 3.20)
project(assignment2)

set(CMAKE_CXX_STANDARD 11)

add_executable(assignment2 main.cpp CircularArrayLinkedList.h Employee.h TemporaryEmployee.h PermanentEmployee.h Date.h DoubleDynamicLinkedList.h CircularArrayLinkedList.cpp DoubleDynamicLinkedList.cpp Date.cpp Employee.cpp)
