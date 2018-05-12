#include "Component_Button.h"
#include "..\Display\Display_MainWindow.h"


ComponentOperationCode Component_Button::ClickEvent(const float & mouseX, const float & mouseY)
{
	if (this->allowEvent == true)
	{
		if ((mouseX > this->posX + Display_MainWindow::menu_Xpos)
			&& (mouseX < this->posX + this->sizeX + Display_MainWindow::menu_Xpos)
			&& (mouseY > this->posY + Display_MainWindow::menu_Ypos)
			&& (mouseY < this->posY + this->sizeY + Display_MainWindow::menu_Ypos)
			)
		{
			this->BlinkOnce();
			return buttonClicked;
		}
		else return NothingCpOperation;
	}
	else return NothingCpOperation;

}

void Component_Button::DrawComponent()
{
	al_draw_rectangle(
		this->posX,
		this->posY,
		this->posX + this->sizeX,
		this->posY + this->sizeY,
		this->bordercolor,
		this->bordersize);

	al_draw_text(Component_ManagerMENU::font8, al_map_rgb(64, 64, 64), this->posX + 5, this->posY + 5, 0, "Create");

}

void Component_Button::BlinkOnce()
{
	al_set_target_bitmap(Display_MainWindow::GetMenuBmp());
	al_draw_rectangle(
		this->posX,
		this->posY,
		this->posX + this->sizeX,
		this->posY + this->sizeY,
		this->color_blink,
		this->bordersize);
	al_set_target_backbuffer(Display_MainWindow::GetDisplay());

	Display_MainWindow::RefreshDisplay();
	Sleep(125);


	al_set_target_bitmap(Display_MainWindow::GetMenuBmp());
	this->DrawComponent();

	al_set_target_backbuffer(Display_MainWindow::GetDisplay());
	Display_MainWindow::RefreshDisplay();

}

Component_Button::Component_Button(string _name, float _posX, float _posY, float _sizeX, float _sizeY, ALLEGRO_COLOR _bordercolor, float _bordersize)
: Component_MainComponent(_name, _posX, _posY, _sizeX, _sizeY, _bordersize, _bordercolor)
{
	this->allowEvent = true;
	this->color_blink = al_map_rgb(0, 255, 0);
}

Component_Button::~Component_Button()
{
}
