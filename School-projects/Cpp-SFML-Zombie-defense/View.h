#pragma once
#include <iostream>
#include <list>
#include <SFML/Graphics.hpp>
#include "ToDrawElement.h"
#include <chrono>

/**
* @author Grzegorz Marczyk
*/

/** CHANGELOG
*
*/

class View {

public:

    static int fps;
    static std::chrono::milliseconds lastRefreshTime;
    sf::RenderWindow * mainWindow;

    View(sf::RenderWindow * ptrWindow) 
    {
        this->mainWindow = ptrWindow;
    }

    void drawScene(std::list<ToDrawElement> textures)
    {
        std::chrono::milliseconds currentTime = getCurrentTime();
        if ((currentTime - lastRefreshTime).count() >= (1000 / this->fps))
        {
            std::cout << "FPS time: " << (currentTime - lastRefreshTime).count() << std::endl;
            lastRefreshTime = getCurrentTime();

            mainWindow->clear(sf::Color::Black);
            std::list<ToDrawElement>::iterator iter = textures.begin();
            std::list<ToDrawElement>::iterator endIter = textures.end();
            for (iter; iter != endIter; iter++)
            {

                sf::Sprite tmpSprite;
                tmpSprite.setTexture(*(iter->texture));
                tmpSprite.setPosition(iter->posX, iter->posY);

                mainWindow->draw(tmpSprite);
            }
            mainWindow->display();
        }
    }

    static std::chrono::milliseconds getCurrentTime()
    {
        return std::chrono::duration_cast<std::chrono::milliseconds>(
            std::chrono::system_clock::now().time_since_epoch());
    }

};

std::chrono::milliseconds View::lastRefreshTime = View::getCurrentTime();
int View::fps = 1;