#include "Elem_Element.h"


Elem_Element::Elem_Element(string _name) : nameOfElement(_name), lights(false), lightColor(al_map_rgb(0,255,0))
{
	this->lightBorderSize = 2;
	cout << " Elem_Element GOT CREATED -------------------------" << endl;
}

Elem_Element::~Elem_Element()
{
	cout << " Elem_Element GOT DESTROYED -------------------------" << endl;
}
