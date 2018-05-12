#pragma once
#include <iostream>
#include <list>
#include "Opponent.h"
#include <chrono>

/**
* @author Grzegorz Marczyk
*/

/** CHANGELOG
*
*/

class Round {

public:

    std::list<Opponent*> opponentList;
    bool isRoundActive;

    Round(){}

    Round(int level)
    {
        switch (level)
        {
        case 1:
            setupLevel_1();
            break;
        case 2:
            setupLevel_2();
            break;
        default:
            std::string Exception = "Level doesnt exist, class [Round]";
            throw Exception;
        }

        isRoundActive = false;
    }

    void deleteOpponent(Opponent *op)
    {
        (this->opponentList).remove(op);
        isRoundActive = !(this->opponentList.empty());
        op->~Opponent();
    }


private:

    void setupLevel_1()
    {
        for (size_t i = 0; i < 5; i++)
        {
            Opponent *op = new Opponent();
            this->opponentList.push_front(op);
        }
    }

    void setupLevel_2()
    {
        for (size_t i = 0; i < 10; i++)
        {
            Opponent *op = new Opponent();
            this->opponentList.push_front(op);
        }
    }

};
