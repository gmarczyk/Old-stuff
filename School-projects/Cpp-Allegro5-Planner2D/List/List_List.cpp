#include "List_List.h"
#include <iostream>

List_List::List_List()
{
	this->vecDecorationList.push_back(new List_Element("Deco1", "Bitmaps/Decoration", "Deco1.bmp")); this->vecDecorationNames.push_back("Deco1");
	this->vecDecorationList.push_back(new List_Element("Deco2", "Bitmaps/Decoration", "Deco2.bmp")); this->vecDecorationNames.push_back("Deco2");
	this->vecDecorationList.push_back(new List_Element("Deco3", "Bitmaps/Decoration", "Deco3.bmp")); this->vecDecorationNames.push_back("Deco3");

	vector<string> vecTmp;

	vecTmp = { "Chair1Addon1.bmp", "Chair1Addon2.bmp", "Chair1Addon3.bmp" };
	this->vecFurnitureList.push_back(new List_ElementAddon("Chair1", "Bitmaps/Furniture/Chair1", "Chair1.bmp", vecTmp)); vecTmp.clear();
	this->vecFurnitureNames.push_back("Chair1");

	vecTmp = { "Chair2Addon1.bmp", "Chair2Addon2.bmp", "Chair2Addon3.bmp" };
	this->vecFurnitureList.push_back(new List_ElementAddon("Chair2", "Bitmaps/Furniture/Chair2", "Chair2.bmp", vecTmp)); vecTmp.clear();
	this->vecFurnitureNames.push_back("Chair2");

	vecTmp = { "Chair3Addon1.bmp", "Chair3Addon2.bmp", "Chair3Addon3.bmp" };
	this->vecFurnitureList.push_back(new List_ElementAddon("Chair3", "Bitmaps/Furniture/Chair3", "Chair3.bmp", vecTmp)); vecTmp.clear();
	this->vecFurnitureNames.push_back("Chair3");

}

List_List::~List_List()
{
}

vector<string> List_List::ReturnAddonNames(string furnitureName)
{
	vector<List_ElementAddon*>::iterator it;

	for (it = this->vecFurnitureList.begin(); it != this->vecFurnitureList.end(); ++it)
	{
		if ((*it)->nameItem == furnitureName)	return (*it)->vecAddonNames;
	}
}

List_Element* List_List::GetDecorationFromList(string name)
{
	vector<List_Element*>::iterator it;
	for (it = this->vecDecorationList.begin(); it != this->vecDecorationList.end(); ++it)
	{
		if ((*it)->nameItem == name) return (*it);
	}
	cout << "NO DECORATION WITH SUCH A NAME FOUND" << endl;
	return NULL;
}

List_ElementAddon* List_List::GetFurnitureFromList(string name)
{
	vector<List_ElementAddon*>::iterator it;
	for (it = this->vecFurnitureList.begin(); it != this->vecFurnitureList.end(); ++it)
	{
		if ((*it)->nameItem == name) return (*it);
	}
	cout << "NO DECORATION WITH SUCH A NAME FOUND" << endl;
	return NULL;
}
