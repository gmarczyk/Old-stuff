#pragma once
#include "Component_MainComponent.h"

class Component_Button : public Component_MainComponent
{
private:
	ALLEGRO_COLOR color_blink;
protected:
	
public:

	ComponentOperationCode ClickEvent(const float &mouseX, const float &mouseY);
	void DrawComponent();
	void BlinkOnce();

	Component_Button* Clone()
	{
		return new Component_Button(*this);
	}

	Component_Button(string _name, float _posX, float _posY, float _sizeX, float _sizeY, ALLEGRO_COLOR _bordercolor, float _bordersize);
	 ~Component_Button();
};

