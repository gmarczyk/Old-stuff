#pragma once
#include <iostream>
#include "Gameplay.h"
#include "View.h"
#include "Menu.h"
#include "Shop.h"
#include "Opponent.h"

/**
 * @author Grzegorz Marczyk
 */

/** CHANGELOG
 * (GM 17.04) switchMode(), initializing gameplayLastRefresh when switching to this mode. Updating time how long is gameplay running in updateView()
 * (GM 17.04) updateGameplayState(), firstly checks if round is active, if yes - updates timer of gameplay and its state like collisions, positions of enemies, damaging enemies, etc.
              Also checks if the round ended or not. If the round is not active (gameplay->updateState changes it to inactive if all enemies are dead), switches to SHOP and prepares next round, if it is last one, ends game.
 */

enum Mode { MENU, GAMEPLAY, SHOP };

class MainController {

public:

    Gameplay * gameplay;
    Menu * menu;
    Shop * shop;
    View * view;

    Mode currentMode;

    MainController(Gameplay* gameplay, Menu* menu, Shop* shop, View* view)
        : gameplay(gameplay), menu(menu), shop(shop), view(view)
    {
        this->switchMode(Mode::MENU);
    }

    void run()
    {
        while (view->mainWindow->isOpen())
        {     
            sf::Event singleEvent;
            while (view->mainWindow->pollEvent(singleEvent))
            {
                if (singleEvent.type == sf::Event::KeyPressed && singleEvent.key.code == sf::Keyboard::Z)
                    this->switchMode(Mode::MENU);
                else if (singleEvent.type == sf::Event::KeyPressed && singleEvent.key.code == sf::Keyboard::X)
                    this->switchMode(Mode::GAMEPLAY);
                else if (singleEvent.type == sf::Event::KeyPressed && singleEvent.key.code == sf::Keyboard::C)
                    this->switchMode(Mode::SHOP);
                else
                    handleEvents(currentMode, singleEvent);
            }

            updateView(currentMode);         
        }
    }


private:

    void switchMode(Mode switchTo)
    {
        this->currentMode = switchTo;
        if (switchTo == Mode::GAMEPLAY)
        {
            gameplay->setLastRefresh();
        }
    }

    void handleEvents(Mode currentMode, sf::Event ev)
    {
        switch (currentMode)
        {
        case Mode::GAMEPLAY:
            gameplayEventHandling(ev);
            break;
        case Mode::MENU:
            menuEventHandling(ev);
            break;
        case Mode::SHOP:
            shopEventHandling(ev);
            break;
        default:
            std::string Exception = "No such mode, MainController.handleEvents";
            throw Exception;
        }
    }

    void menuEventHandling(sf::Event ev)
    {
        switch (ev.type)
        {
        case sf::Event::KeyPressed:
            if (ev.key.code == sf::Keyboard::J)
                std::cout << "MenuEventHandling J" << std::endl;
            break;
        default:
            break;
        }
    }

    void gameplayEventHandling(sf::Event ev)
    {
        switch (ev.type)
        {
        case sf::Event::KeyPressed:
            if (ev.key.code == sf::Keyboard::J)
            {
                std::cout << "gameplayEventHandling    deletingOpponents" << std::endl;
                std::list<Opponent*>::iterator itB = this->gameplay->currentRound.opponentList.begin();
                std::list<Opponent*>::iterator itE = this->gameplay->currentRound.opponentList.end();
                while(itB!=itE)
                {
                    this->gameplay->currentRound.deleteOpponent(*(itB++));

                   // (*itB)->~Opponent();
                }
            }
            break;
        default:
            break;
        }
    }

    void shopEventHandling(sf::Event ev)
    {
        switch (ev.type)
        {
        case sf::Event::KeyPressed:
            if (ev.key.code == sf::Keyboard::J)
            {
                std::cout << "shopEventHandling nextround setting to active" << std::endl;
                this->gameplay->currentRound.isRoundActive = true;
               // this->gameplay->currentRound = Round(Gameplay::roundIndex);
               // switchMode(Mode::GAMEPLAY);
            }
            break;
        default:
            break;
        }
    }

    void updateView(Mode currentMode)
    {
        std::list<ToDrawElement> textureList;
        switch (currentMode)
        {
        case Mode::GAMEPLAY:
            updateGameplayState();
            textureList = gameplay->getView();
            break;
        case Mode::MENU:
            textureList = menu->getView();
            break;
        case Mode::SHOP:
            textureList = shop->getView();
            break;
        default:
            std::string Exception = "No such mode, MainController.handleEvents";
            throw Exception;
        }

        drawAndFreeTextures(textureList);
    }

    void updateGameplayState()
    {
        if ((gameplay->currentRound).isRoundActive)
        {
            gameplay->updateState();

            gameplay->updateTime();
            gameplay->setLastRefresh();

        }
        else
        {
            if (Gameplay::roundIndex < 2)
            {
                switchMode(Mode::SHOP);
                gameplay->currentRound = Round(++(Gameplay::roundIndex));
            }
            else
            {
                std::cout << "END OF GAME" << std::endl;
                switchMode(Mode::MENU);
            }
        }
    }

    void drawAndFreeTextures(std::list<ToDrawElement> texList)
    {
        view->drawScene(texList);
        gameplay->freeTextureList(texList);
    }

};

