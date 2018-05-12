#pragma once
#include <iostream>
#include "allegro5\allegro.h"

using namespace std;

class Elem_Element
{
private:
protected:

	float posX, posY;
	string nameOfElement;
	bool lights;
	ALLEGRO_COLOR lightColor;
	float lightBorderSize;

public:

	void MovePositionFor(float X, float Y) { this->posX += X; this->posY += Y; }
	virtual void SetPosition(float X, float Y) { this->posX = X; this->posY = Y; }
	virtual	float GetPositionX() { return this->posX; }; virtual float GetPositionY() { return this->posY; }

	virtual void LightElement() = 0;

	virtual void DrawOnPosition(float X, float Y) = 0;
	virtual void DrawYourself() = 0;

	virtual bool CollisionChecking() = 0;
	virtual void CollisionSetting(bool _bool)  = 0;

	// Setting & getting
	void SetLightColor(ALLEGRO_COLOR _color) { this->lightColor = _color; }
	void SetLightsOnOff(bool _bool) {this->lights = _bool; }

	bool GetLightOnOff() { return this->lights; }
	string GetName() { return this->nameOfElement; }
	
	// Constructor & destructor
	Elem_Element(string _name);
	virtual ~Elem_Element();
};

