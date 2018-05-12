#include "Component_Flasher.h"
#include "..\Display\Display_MainWindow.h"
#include "allegro5/allegro_font.h"


Component_Flasher::Component_Flasher(string _name, float _posX, float _posY, float _sizeX, float _sizeY, ALLEGRO_COLOR _lightcolor, ALLEGRO_COLOR _bordercolor, float _bordersize)
	: Component_Button(_name,  _posX,  _posY,  _sizeX,  _sizeY,  _bordercolor,  _bordersize), lightcolor(_lightcolor)
{
	this->lights = false;
	this->componentType = Flasher;
	this->allowEvent = true;
}

Component_Flasher::~Component_Flasher()
{}

ALLEGRO_FONT* Component_ManagerMENU::font8;
void Component_Flasher::DrawComponent()
{
	if (lights == false)
	{
		al_draw_rectangle(
			this->posX,
			this->posY,
			this->posX + this->sizeX,
			this->posY + this->sizeY,
			al_map_rgb(0, 0, 0),
			this->bordersize);

		al_draw_text(Component_ManagerMENU::font8, al_map_rgb(64, 64, 64), this->posX+5, this->posY+5, 0, this->componentName.c_str());
	}
	else if (lights == true)
	{
		al_draw_filled_rectangle(
			this->posX,
			this->posY,
			this->posX + this->sizeX,
			this->posY + this->sizeY,
			this->lightcolor
			);
		al_draw_rectangle(
			this->posX,
			this->posY,
			this->posX + this->sizeX,
			this->posY + this->sizeY,
			this->bordercolor,
			this->bordersize);
		al_draw_text(Component_ManagerMENU::font8, al_map_rgb(64, 64, 64), this->posX + 5, this->posY + 5, 0, this->componentName.c_str());
	}
}

ComponentOperationCode Component_Flasher::ClickEvent(const float &mouseX, const float &mouseY)
{
	if (this->allowEvent == true)
	{
		if ((mouseX > this->posX + Display_MainWindow::menu_Xpos)
			&& (mouseX < this->posX + this->sizeX + Display_MainWindow::menu_Xpos)
			&& (mouseY > this->posY + Display_MainWindow::menu_Ypos)
			&& (mouseY < this->posY + this->sizeY + Display_MainWindow::menu_Ypos)
			)
		{
			lights = !lights;

			if (lights == true) return eqFlasherClicked;
			else if (lights == false) return eqFlasherUnclicked;
		}
	}
	
	return NothingCpOperation;
}
