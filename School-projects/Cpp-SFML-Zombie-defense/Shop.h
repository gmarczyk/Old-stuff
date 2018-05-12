#pragma once
#include "ITextureContainer.h"

/**
* @author Grzegorz Marczyk
*/

/** CHANGELOG
*
*/

class Shop : public ITextureContainer {

public:

    Shop() 
    {
    }

    // TESTING, JUST AN EXAMPLE
        std::list<ToDrawElement> getView() {
      

        Images images = Images::getInstance();
        sf::Image imidz = images.getTe2();// = images.mainCharacter;

        sf::Texture *tekstura = new sf::Texture();
        tekstura->loadFromImage(imidz);
      
        ToDrawElement elem = ToDrawElement();
        elem.posX = 250;
        elem.posY = 250;
        elem.texture = tekstura;

        std::list<ToDrawElement> textureList;
        textureList.push_back(elem);
        return textureList;
    }
};