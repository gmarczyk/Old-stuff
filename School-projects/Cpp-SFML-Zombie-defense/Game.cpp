#include <iostream>
#include <SFML/Window.hpp>
#include <SFML/Graphics.hpp>
#include "SettingsParameter.h"
#include "SettingsLoader.h"
#include "Settings.h"
#include "Images.h"
#include "Gameplay.h"
#include "Backpack.h"
#include "MainCharacter.h"
#include "MainController.h"
#include "View.h"
#include "Menu.h"
#include "Shop.h"
#include <list>


/** CHANGELOG
*
*/

int main()
{
     Images images = Images::getInstance(); // Create the singleton already, to load the images
     Settings appSettings = Settings::getInstance();
     int windowWidth = std::stoi(appSettings.getParameterByName("MainWindowWidth").value);
     int windowHeight = std::stoi(appSettings.getParameterByName("MainWindowHeight").value);

     Backpack backpack = Backpack();
     MainCharacter mainCharacter = MainCharacter();

     Gameplay game = Gameplay();
     Menu menu = Menu();
     Shop shop = Shop();

     sf::RenderWindow mainWindow(sf::VideoMode(windowWidth, windowHeight, 32), "Zombie Game");
     View view = View(&mainWindow);

     MainController mainController = MainController(&game, &menu, &shop, &view);
     mainController.run();



    // std::cout << std::endl;
    // system("PAUSE");
}