#pragma once
#include <iostream>
#include <list>
#include <chrono>

/**
* @author Grzegorz Marczyk
*/

/** CHANGELOG
*
*/

class Opponent {

public:


    virtual ~Opponent()
    {
        std::cout << "Opponent is deleted" << std::endl;
    }

};
