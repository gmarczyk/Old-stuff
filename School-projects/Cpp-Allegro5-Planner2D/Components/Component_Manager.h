#pragma once
#include "../Components/Component_MainComponent.h"
#include "../Components/Component_Flasher.h"
#include <list>
#include <vector>

class Component_Manager
{
private:
	string  name_CreateButton;

protected:

	list<Component_MainComponent*> vecComponents;

public:

	list<Component_MainComponent*> ReturnVecComponentsBackup()
	{
		list<Component_MainComponent*>::iterator it;
		list<Component_MainComponent*> backup;

		Component_MainComponent * tmp;
		for (it = this->vecComponents.begin(); it != this->vecComponents.end();++it)
		{
			tmp = (*it)->Clone();
			backup.push_back(tmp);
		}
		return backup;
	}
	void SetVecComponents(list<Component_MainComponent*> abc)
	{
		this->vecComponents = abc;
	}
	void DeleteAllComponents()
	{
		list<Component_MainComponent*>::iterator it;
		for (it = this->vecComponents.begin(); it != this->vecComponents.end(); ++it)
		{
			(*it)->~Component_MainComponent();
		}
		this->vecComponents.clear();
	}

	// Create component methods
	void CreateFlasher(string nameOf, float _posX, float _posY, float _sizeX, float _sizeY, ALLEGRO_COLOR _lightcolor, ALLEGRO_COLOR _bordercolor, float _bordersize);
	void CreateSelector(string nameOf, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _vec, ALLEGRO_COLOR _bordercolor, float _bordersize);
	void CreateSelectorAddon(string nameOf, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _vec, ALLEGRO_COLOR _bordercolor, float _bordersize);
	void CreateButtonToCreate();

	void DeleteComponent(string nameOf);

	// Manager methods
	void DrawAllComponents();
	Component_MainComponent * FindComponent(string _name);
	virtual void CheckAllMouseEvents(const float &mouseX, const float &mouseY);

	// Constructor & destructor
	Component_Manager();
	virtual ~Component_Manager();

	

};

