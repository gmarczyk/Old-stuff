#pragma once
#include <string>
#include <vector>
#include <list>
#include "List_Element.h"
#include "List_ElementAddon.h"


enum FindItem { decoFound = 0, furnitFound = 1 };

class List_List
{
private:
protected:

public:
	
	// Data
	vector<List_Element*>	   vecDecorationList;
	vector<List_ElementAddon*> vecFurnitureList;

	vector<string> vecDecorationNames;
	vector<string> vecFurnitureNames;
	
	// Getters 
	vector<string> ReturnAddonNames(string furnitureName);

	List_Element      *  GetDecorationFromList(string name);
	List_ElementAddon *  GetFurnitureFromList(string name);

	FindItem WhereIsItemName(string name)
	{
		vector<string>::iterator it;
		for (it = this->vecDecorationNames.begin(); it != this->vecDecorationNames.end(); ++it)
		{
			if ((*it) == name) return decoFound;
		}
		for (it = this->vecFurnitureNames.begin(); it != this->vecFurnitureNames.end(); ++it)
		{
			if ((*it) == name) return furnitFound;
		}
	}

	// Constructor & destructor
	List_List();
	~List_List();
};

