#pragma once
#include <string>
#include <iostream>
#include <allegro5/allegro.h>
#include <allegro5/allegro_primitives.h>
using namespace std;


// ENUM FOR THIS CLASS HIERARCHY
enum ComponentType {NothingCpType = 0, Flasher = 1, Selector = 2, SelectorAddon = 3, Button = 4};
enum ComponentOperationCode { NothingCpOperation = 0, eqFlasherClicked = 1, eqFlasherUnclicked = 2, selectorClicked = 3, buttonClicked = 4 };

class Component_MainComponent
{
private:
	
protected:

	// Default data
	float posX, posY;
	float sizeX, sizeY;
	bool allowEvent; 
	float bordersize;
	ALLEGRO_COLOR bordercolor;

public:

	string componentName;
	ComponentType componentType;

	virtual void DrawComponent() = 0;	// WARNING - there is no protection provided for situations like: drawing outside display, drawing outside bitmap etc.
        virtual ComponentOperationCode ClickEvent(const float &mouseX, const float &mouseY) = 0;

	// Setters & getters
	void Set_allowEvent(bool _bool) { this->allowEvent = _bool; }

	virtual Component_MainComponent* Clone()=0;
	// Constructor & Destructor
	Component_MainComponent(string _name, float _posX, float _posY, float _sizeX, float _sizeY, float _bordersize, ALLEGRO_COLOR _bordercolor);
	virtual ~Component_MainComponent();
};

