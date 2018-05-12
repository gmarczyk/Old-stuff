#pragma once
#include <vector>
#include "AppConfig.h"
#include <SFML/Graphics.hpp>

/**
 * @author Grzegorz Marczyk
 */

/** CHANGELOG
 *
 */

class Images {

private:

    std::string folderPath = appConfig::TexturesFolderPath;
    sf::Color transparencyColor = sf::Color::White;

    sf::Image mainCharacter;
    sf::Image t2;

    /* Singleton */
    Images()
    {
        loadAndPrepare(&mainCharacter, folderPath + "testowy.bmp");
        loadAndPrepare(&t2, folderPath + "te2.bmp");
    }

    void loadAndPrepare(sf::Image * toObj, std::string path)
    {
        (*toObj).loadFromFile(path);
        (*toObj).createMaskFromColor(this->transparencyColor);
    }

    sf::Image getImageOf(sf::Image img)
    {
        sf::Image returnImage;
        sf::Vector2u sizeCopy = img.getSize();
        returnImage.create(sizeCopy.x, sizeCopy.y);
        returnImage.copy(img, 0, 0);
        return returnImage;
    }

public:

    static Images& getInstance()
    {
        static Images instance; // Guaranteed to be destroyed. Instantiated on first use.
        return instance;
    }

    sf::Image getMainCharacter() { return getImageOf(mainCharacter); }
    sf::Image getTe2()           { return getImageOf(t2); }

};
