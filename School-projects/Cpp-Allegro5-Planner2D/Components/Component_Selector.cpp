#include "Component_Selector.h"
#include "..\Display\Display_MainWindow.h"
#include "allegro5/allegro_font.h"


Component_Selector::Component_Selector(string _name, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _listofobjects, float _bordersize, ALLEGRO_COLOR _bordercolor)
	: Component_MainComponent(_name, _posX, _posY, _sizeX, _sizeY, _bordersize, _bordercolor)
{
	this->firstTime = true;
	this->vecOfStrings = _listofobjects;
	this->vecIter = vecOfStrings.begin();
	this->selectedObjectIndex = 1;
	this->componentType = Selector;
}

Component_Selector::~Component_Selector()
{
}

void Component_Selector::DrawComponent()
{
	al_draw_rectangle(
		this->posX,
		this->posY,
		this->posX + this->sizeX,
		this->posY + this->sizeY,
		al_map_rgb(0, 0, 0),
		1);

	al_draw_rectangle(
		this->posX - 0.20* this->sizeX,
		this->posY,
		this->posX - 0.05* this->sizeX,
		this->posY + this->sizeY,
		al_map_rgb(0, 0, 0),
		1);
	al_draw_rectangle(
		this->posX + this->sizeX + 0.05* this->sizeX,
		this->posY,
		this->posX + this->sizeX + 0.20* this->sizeX,
		this->posY + this->sizeY,
		al_map_rgb(0, 0, 0),
		1);

	if (firstTime == false) al_draw_text(Component_ManagerMENU::font8, al_map_rgb(64, 64, 64), this->posX + 5, this->posY + 5, 0, vecIter->c_str());
}

ComponentOperationCode Component_Selector::ClickEvent(const float & mouseX, const float & mouseY)
{

	if ((mouseX > this->posX - 0.20* this->sizeX + Display_MainWindow::menu_Xpos)
		&& (mouseX < this->posX - 0.05* this->sizeX + Display_MainWindow::menu_Xpos)
		&& (mouseY > this->posY +  Display_MainWindow::menu_Ypos)
		&& (mouseY < this->posY + this->sizeY + Display_MainWindow::menu_Ypos)
		)
	{
		if (vecIter != vecOfStrings.end() - 1)
		{
			vecIter++;
			selectedObjectIndex++;
			firstTime = false;
		}
		else
		{
			vecIter = vecOfStrings.begin();
			selectedObjectIndex = 1;
			firstTime = false;
		}
		return selectorClicked;
	}
	else if ((mouseX >this->posX + this->sizeX + 0.05* this->sizeX + Display_MainWindow::menu_Xpos)
		&& (mouseX < this->posX + this->sizeX + 0.20* this->sizeX + Display_MainWindow::menu_Xpos)
		&& (mouseY > this->posY + Display_MainWindow::menu_Ypos)
		&& (mouseY < this->posY + this->sizeY + Display_MainWindow::menu_Ypos)
		)
	{
		if (vecIter != vecOfStrings.begin())
		{
			vecIter--;
			selectedObjectIndex--;
			firstTime = false;
		}
		else
		{
			vecIter = vecOfStrings.end()-1;
			selectedObjectIndex = vecOfStrings.size();
			firstTime = false;
		}
		return selectorClicked;
	}

	return NothingCpOperation;
}
