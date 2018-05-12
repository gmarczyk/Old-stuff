#include "Component_MainComponent.h"


Component_MainComponent::Component_MainComponent(string _name, float _posX, float _posY,float _sizeX, float _sizeY, float _bordersize, ALLEGRO_COLOR _bordercolor)
	: componentName(_name), posX(_posX), posY(_posY), sizeX(_sizeX), sizeY(_sizeY), bordersize(_bordersize), bordercolor(_bordercolor)
{}


Component_MainComponent::~Component_MainComponent()
{}
