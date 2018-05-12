#pragma once
#include "Elem_Equipment.h"
#include "../List/List_List.h"

class Elem_Decoration:public Elem_Equipment
{
private:
protected:

	List_Element* ptrToElementOnList;

public:

	// Constructor & destructor
	Elem_Decoration(string _name, List_List * listptr);
	virtual ~Elem_Decoration();
};

