#pragma once
#include "Elem_Element.h"

class Elem_Equipment: public Elem_Element
{
private:

protected:
	ALLEGRO_BITMAP * bmp_ElemBitmap;

	float bmp_width, bmp_height;
public:

	void SetPosition(float X, float Y);
	float GetPositionX();  float GetPositionY();

	void LightElement();

	void DrawYourself();
	void DrawOnPosition(float X, float Y);

	bool CollisionChecking();
	void CollisionSetting(bool _bool);

	// Constructor & destructor
	Elem_Equipment(string _name);
	~Elem_Equipment();
};

