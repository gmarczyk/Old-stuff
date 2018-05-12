#pragma once
#include "Component_Button.h"


class Component_Flasher : public Component_Button
{
private:
	bool lights;

protected:
	ALLEGRO_COLOR lightcolor;

public:

	void SetLight(bool _bool)			{ this->lights = _bool; }
	bool GetLight()					    { return lights; }
	void SetLightColor(ALLEGRO_COLOR _color) { this->lightcolor = _color; }
	void SetBorderColor(ALLEGRO_COLOR _color) { this->bordercolor = _color; }


	void DrawComponent();
        ComponentOperationCode ClickEvent(const float &mouseX, const float &mouseY);

	Component_Flasher* Clone()
	{
		return new Component_Flasher(*this);
	}
	
	// Constructor & Destructor
	Component_Flasher(string _name, float _posX, float _posY, float _sizeX, float _sizeY, ALLEGRO_COLOR _lightcolor, ALLEGRO_COLOR _bordercolor, float _bordersize);
	~Component_Flasher();
};

