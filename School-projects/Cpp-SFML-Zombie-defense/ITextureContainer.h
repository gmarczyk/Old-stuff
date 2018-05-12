#pragma once
#include <iostream>
#include <list>
#include "ToDrawElement.h"

/**
* @author Grzegorz Marczyk
*/

/** CHANGELOG
*
*/

class ITextureContainer {

public:

    virtual std::list<ToDrawElement> getView() = 0;

    virtual void freeTextureList(std::list<sf::Texture*> textures) 
    {
        std::list<sf::Texture*>::iterator iter = textures.begin();
        std::list<sf::Texture*>::iterator endIter = textures.end();
        for (iter; iter != endIter; iter++)
        {
            delete *iter;
        }
    }

    virtual void freeTextureList(std::list<ToDrawElement> textures)
    {
        std::list<ToDrawElement>::iterator iter = textures.begin();
        std::list<ToDrawElement>::iterator endIter = textures.end();
        for (iter; iter != endIter; iter++)
        {
            delete (iter->texture);
        }
    }
};