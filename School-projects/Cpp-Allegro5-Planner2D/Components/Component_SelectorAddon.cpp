#include "Component_SelectorAddon.h"
#include "..\Display\Display_MainWindow.h"
#include "allegro5/allegro_font.h"


Component_SelectorAddon::Component_SelectorAddon(string _name, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _listofobjects, float _bordersize, ALLEGRO_COLOR _bordercolor)
	: Component_Selector(_name, _posX,  _posY,  _sizeX,  _sizeY,  _listofobjects, _bordersize, _bordercolor)
{
	this->SetAddonAmount(this->vecOfStrings.size());
	this->componentType = SelectorAddon;
}

Component_SelectorAddon::~Component_SelectorAddon()
{
}
void Component_SelectorAddon::DrawComponent()
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

	string tmp;
	tmp = *vecIter;
	tmp.erase(tmp.end() - 4, tmp.end());

	al_draw_text(Component_ManagerMENU::font8, al_map_rgb(64, 64, 64), this->posX + 5, this->posY + 5, 0, tmp.c_str());
	if(this->addonVec[this->selectedObjectIndex-1] == false)
	al_draw_filled_rectangle(
		this->posX + this->sizeX +5,
		this->posY + this->sizeY + 5,
		this->posX + this->sizeX + 15,
		this->posY + this->sizeY + 15,
		al_map_rgb(255, 75, 75)
		);
	else if (this->addonVec[this->selectedObjectIndex-1] == true)
		al_draw_filled_rectangle(
			this->posX + this->sizeX + 5,
			this->posY + this->sizeY + 5,
			this->posX + this->sizeX + 15,
			this->posY + this->sizeY + 15,
			al_map_rgb(75, 255, 75)
			);
}

ComponentOperationCode Component_SelectorAddon::ClickEvent(const float & mouseX, const float & mouseY)
{

	if ((mouseX > this->posX - 0.20* this->sizeX + Display_MainWindow::menu_Xpos)
		&& (mouseX < this->posX - 0.05* this->sizeX + Display_MainWindow::menu_Xpos)
		&& (mouseY > this->posY + Display_MainWindow::menu_Ypos)
		&& (mouseY < this->posY + this->sizeY + Display_MainWindow::menu_Ypos)
		)
	{
		
		if (vecIter != vecOfStrings.end() - 1)
		{
			vecIter++;
			selectedObjectIndex++;
		}
		else
		{
			vecIter = vecOfStrings.begin();
			selectedObjectIndex = 1;
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
		}
		else
		{
			vecIter = vecOfStrings.end() - 1;
			selectedObjectIndex = vecOfStrings.size();
		}
		return selectorClicked;
	}
	else if ((mouseX >this->posX + this->sizeX + 5 +Display_MainWindow::menu_Xpos)
		&& (mouseX < this->posX + this->sizeX + 15 + Display_MainWindow::menu_Xpos)
		&& (mouseY > this->posY + this->sizeY + 5 + Display_MainWindow::menu_Ypos)
		&& (mouseY < this->posY + this->sizeY + 15 + Display_MainWindow::menu_Ypos)
		)
	{
		this->addonVec[this->selectedObjectIndex-1] = !(this->addonVec[this->selectedObjectIndex-1]);
		return selectorClicked;
	}

	return NothingCpOperation;
}
