#pragma once
#include <iostream>
#include <list>
#include "ITextureContainer.h"
#include "ToDrawElement.h"
#include <chrono>
#include "View.h"
#include "Round.h"

/**
* @author Grzegorz Marczyk
*/

/** CHANGELOG
* (GM 17.04) added field describing how long is round running and methods/fields to calculate that (gameplayLastRefresh,timeRoundIsRunning,updateTime(), setLastRefresh()), changed MainController a bit to make it run properly.
* (GM 17.04) added Round object, contains list of enemies and information about round - if is active or not, etc. Added updateState() which checks if the round ended(empty list of opponents) and if not, does all the things like collisions, damaging enemies etc.
*/

class Gameplay : public ITextureContainer {

private:

    std::chrono::milliseconds gameplayLastRefresh;
    long long int timeRoundIsRunning;

public:

    static int roundIndex;
    Round currentRound;

    Gameplay()
    {
        this->currentRound = Round(1);
        this->currentRound.isRoundActive = true;
        this->timeRoundIsRunning = 0;
    }

    void updateTime()
    {
        this->timeRoundIsRunning += (View::getCurrentTime() - gameplayLastRefresh).count();
    }

    void setLastRefresh()
    {
        this->gameplayLastRefresh = View::getCurrentTime();
    }

    void updateState()
    {
        // NAJWAZNIEJSZA METODA
        // tutaj wszystkie kolizje i te sprawy, cala gra idzie tutaj i w event handlerze. 
        // Najpierw sprawdza czy wszystkie zombiaki zabite (pusta lista), jesli tak no to koniec rundy
        // Jesli nie, to sprawdzanie kolizji, DMG zombiakom, animacje czy cos itd. 

        if (this->currentRound.opponentList.empty())
            currentRound.isRoundActive = false;
        else
        {
            // cala obsluga gry
        }
    }

    // TESTING, JUST AN EXAMPLE
    std::list<ToDrawElement> getView() {
        //std::cout << "GameplayTime = " << this->timeRoundIsRunning << std::endl;
        Images images = Images::getInstance();
        sf::Image imidz = images.getMainCharacter();// = images.mainCharacter;

        sf::Texture *tekstura = new sf::Texture();
        tekstura->loadFromImage(imidz);

        std::list<ToDrawElement> textureList;

        ToDrawElement elem = ToDrawElement();
        elem.texture = tekstura;
        elem.posX = 50;
        elem.posY = 50;

        textureList.push_back(elem);


        sf::Image imidz2 = images.getTe2();// = images.mainCharacter;

        sf::Texture *tekstura2 = new sf::Texture();
        tekstura2->loadFromImage(imidz2);

        ToDrawElement elem2 = ToDrawElement();
        elem2.texture = tekstura2;
        elem2.posX = 150;
        elem2.posY = 150;

        textureList.push_back(elem2);

        return textureList;
    }
};

int Gameplay::roundIndex = 1;