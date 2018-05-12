#pragma once
#include "Elem_Element.h"


class Elem_Construction :public Elem_Element
{
private:
protected:

	ALLEGRO_COLOR constrColor;

	void SetPosOnHBX();

public:

	int indexX, indexY;
	void ShowIndex()
	{}

	void SetPosition(float X, float Y);
	float GetPositionX();  float GetPositionY();

	void LightElement();

	void DrawYourself();
	void DrawOnPosition(float X, float Y);

	bool CollisionChecking();
	void CollisionSetting(bool _bool);


	// Constructor & destructor
	Elem_Construction(string _name, ALLEGRO_COLOR _color);
	virtual ~Elem_Construction();
};

