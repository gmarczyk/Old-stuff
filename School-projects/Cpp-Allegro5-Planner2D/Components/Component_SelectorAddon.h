#pragma once
#include "Component_MainComponent.h"
#include "Component_Selector.h"

class Component_SelectorAddon:public Component_Selector
{
private:
	vector<bool> addonVec;
public:

	void SetAddonAmount(int amountOfAddons)
	{
		this->addonVec.clear();
		for (int i = 0; i < amountOfAddons; i++)	this->addonVec.push_back(false);
	}
	void SetAddonValue(int position, bool _bool)
	{
		vector<bool>::iterator it;
		it = it + position - 1;
		*it = _bool;
	}
	void SetAddonWithVector(vector<bool> addons) { this->addonVec = addons; }
	vector<bool> GetAddonVec() { return this->addonVec; }

	void DrawComponent();
	virtual ComponentOperationCode ClickEvent(const float &mouseX, const float &mouseY);


	Component_SelectorAddon* Clone()
	{
		return new Component_SelectorAddon(*this);
	}
	// Constructor & destructor
	Component_SelectorAddon(string _name, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _listofobjects, float _bordersize, ALLEGRO_COLOR _bordercolor);
	~Component_SelectorAddon();
};

