#include "Elem_Decoration.h"



Elem_Decoration::Elem_Decoration(string _name, List_List * listptr) : Elem_Equipment(_name)
{
	this->ptrToElementOnList = listptr->GetDecorationFromList(_name);
	this->bmp_ElemBitmap = this->ptrToElementOnList->mainBitmap;
	cout << " Elem_Decoration GOT CREATED -------------------------" << endl;

	this->bmp_height = al_get_bitmap_height(this->bmp_ElemBitmap);
	this->bmp_width = al_get_bitmap_width(this->bmp_ElemBitmap);
}

Elem_Decoration::~Elem_Decoration()
{
	cout << " Elem_Decoration GOT DESTROYED -------------------------" << endl;
}


