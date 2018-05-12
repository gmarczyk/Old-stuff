#pragma once
#include "ITextureContainer.h"

/**
* @author Grzegorz Marczyk
*/

/** CHANGELOG
*
*/

class Menu : public ITextureContainer {

public:

    Menu() 
    {
    }

    // TESTING, JUST AN EXAMPLE
    std::list<ToDrawElement> getView()
    {
       

        Images images = Images::getInstance();
        sf::Image imidz = images.getMainCharacter();// = images.mainCharacter;

        sf::Texture *tekstura = new sf::Texture();
        tekstura->loadFromImage(imidz);

        ToDrawElement elem = ToDrawElement();
        elem.posX = 400;
        elem.posY = 400;
        elem.texture = tekstura;

        std::list<ToDrawElement> textureList;
        textureList.push_back(elem);
        return textureList;
    }

};