#pragma once
#include "Component_MainComponent.h"
#include <vector>


class Component_Selector:public Component_MainComponent
{
protected:
	int selectedObjectIndex;
	vector<string> vecOfStrings;
	vector<string>::iterator vecIter;
	bool firstTime;
public:

	virtual void DrawComponent();
	virtual ComponentOperationCode ClickEvent(const float &mouseX, const float &mouseY);

	// Setters & getters
	void SetVecOfSTrings(vector<string> _vecof) { this->vecOfStrings.clear(); this->vecOfStrings = _vecof; }
	string ReturnActualItem() 
	{ 
		if (firstTime == false)
		{
			string s;
			s = *this->vecIter;
			return s;
		}
	    else return "";
	}
	void SetActualString(string name)
	{
		vector<string>::iterator it;
		for (it = this->vecOfStrings.begin(); it != this->vecOfStrings.end(); ++it)
		{
			if ((*it) == name) this->vecIter = it;
		}
		this->firstTime = false;
	}

	
	Component_Selector* Clone()
	{
		return new Component_Selector(*this);
	}
	// Constructor & destructor
	Component_Selector(string _name, float _posX, float _posY, float _sizeX, float _sizeY, vector<string> _listofobjects, float _bordersize, ALLEGRO_COLOR _bordercolor);
	~Component_Selector();
};

