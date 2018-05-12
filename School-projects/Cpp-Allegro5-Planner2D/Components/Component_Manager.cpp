#include "Component_Manager.h"
#include "../Display/Display_MainWindow.h"
#include "../Components/Component_Selector.h"
#include "Component_SelectorAddon.h"

Component_Manager::Component_Manager()
{
	this->name_CreateButton = "Create element";
}

Component_Manager::~Component_Manager()
{
	list<Component_MainComponent*>::iterator it;
	for (it = vecComponents.begin(); it != vecComponents.end(); ++it)
	{
		(*it)->~Component_MainComponent();
	}
	this->vecComponents.clear();
}

void Component_Manager::CreateFlasher(string nameOf, float _posX, float _posY, float _sizeX, float _sizeY, ALLEGRO_COLOR _lightcolor, ALLEGRO_COLOR _bordercolor, float _bordersize)
{
		this->vecComponents.push_back(
			new Component_Flasher(
				nameOf,
				_posX, _posY,
				_sizeX, _sizeY,
				_lightcolor, _bordercolor,
				_bordersize)
			);
}
void Component_Manager::CreateSelector(string nameOf, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _vec, ALLEGRO_COLOR _bordercolor, float _bordersize)
{
	this->vecComponents.push_back(
		new Component_Selector(
			nameOf,
			_posX, _posY,
			_sizeX, _sizeY,
			_vec,
			_bordersize,
			_bordercolor)
		);
}
void Component_Manager::CreateSelectorAddon(string nameOf, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _vec, ALLEGRO_COLOR _bordercolor, float _bordersize)
{
	this->vecComponents.push_back(
		new Component_SelectorAddon(
			nameOf,
			_posX, _posY,
			_sizeX, _sizeY,
			_vec,
			_bordersize, _bordercolor)
		);
}

void Component_Manager::CreateButtonToCreate()
{
	this->vecComponents.push_back(
		new Component_Button(
			this->name_CreateButton,
			Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
			Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.80,
			Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
			Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
			al_map_rgb(0,0,0),
			2)
		);
}


void Component_Manager::DeleteComponent(string nameOf)
{
	Component_MainComponent *tmp = this->FindComponent(nameOf);
	this->vecComponents.remove(this->FindComponent(nameOf));
	if(tmp!= NULL) tmp->~Component_MainComponent();
}

void Component_Manager::DrawAllComponents()
{
	list<Component_MainComponent*>::iterator it;

	for (it = this->vecComponents.begin(); it != this->vecComponents.end(); ++it)
	{
		(*it)->DrawComponent();
	//	cout << "Component_Manager::DrawAllComponents" << " " << (*it)->componentName << " was drawn by DrawAllComponents" << endl;
	}
}

void Component_Manager::CheckAllMouseEvents(const float &mouseX, const float &mouseY)
{
	list<Component_MainComponent*>::iterator it;

	for (it = this->vecComponents.begin(); it != this->vecComponents.end(); ++it)
	{
		(*it)->ClickEvent(mouseX, mouseY);
	}
}

Component_MainComponent * Component_Manager::FindComponent(string _name)
{
	list<Component_MainComponent*>::iterator it;

	for (it = this->vecComponents.begin(); it != this->vecComponents.end(); ++it)
	{
		if ((*it)->componentName == _name)
		{
			return (*it);
		}
		
	}

	return NULL;
}
