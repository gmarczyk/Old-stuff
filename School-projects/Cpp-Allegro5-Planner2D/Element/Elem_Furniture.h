#pragma once
#include "Elem_Equipment.h"
#include "../List/List_List.h"

class Elem_Furniture: public Elem_Equipment
{
private:
protected:

	List_ElementAddon* ptrToElementOnList;
	vector<bool> vecActiveAddons;
	void SetAddons(vector<bool> vecOfAddons);

public:

	void UpdateAddons();

	vector<bool> GetAddons() { return this->vecActiveAddons; }
	void SetAddonVec(vector<bool> addons) { this->vecActiveAddons = addons; }

	// Constructor & destructor
	Elem_Furniture(string _name, List_List * listptr, vector<bool> vecAddons);
	~Elem_Furniture();
};

