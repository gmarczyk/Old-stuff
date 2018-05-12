#include "Elem_Construction.h"
#include <allegro5\allegro.h>
#include <allegro5\allegro_primitives.h>
#include "../Display/Display_MainWindow.h"


Elem_Construction::Elem_Construction(string _name, ALLEGRO_COLOR _color) : Elem_Element(_name), constrColor(_color)
{
	cout << " Elem_Construction GOT CREATED -------------------------" << endl;
}


Elem_Construction::~Elem_Construction()
{
	cout << " Elem_Construction GOT DESTROYED -------------------------" << endl;
}

void Elem_Construction::SetPosOnHBX()
{

	this->posX = Display_MainWindow::hitboxes->ToAreaPos_AreaIndex_X(Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_X(this->posX));
	this->posY = Display_MainWindow::hitboxes->ToAreaPos_AreaIndex_Y(Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_Y(this->posY));
}

void Elem_Construction::SetPosition(float X, float Y)
{
	this->posX = X; this->posY = Y;
}

float Elem_Construction::GetPositionX()
{
	return this->posX;
}

float Elem_Construction::GetPositionY()
{
	return  this->posY;
}

void Elem_Construction::LightElement()
{
	al_draw_rectangle(this->posX, this->posY, 
		this->posX + Display_MainWindow::hitboxes->GetHbxWidth(),
		this->posY + Display_MainWindow::hitboxes->GetHbxHeight(),
		this->lightColor, 
		this->lightBorderSize);
}

void Elem_Construction::DrawYourself()
{
	this->SetPosOnHBX();
	al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());
	al_draw_filled_rectangle(this->posX, this->posY,
		this->posX + Display_MainWindow::hitboxes->GetHbxWidth(),
		this->posY + Display_MainWindow::hitboxes->GetHbxHeight(),
		this->constrColor
		);
	if (this->lights == true) this->LightElement();
	al_set_target_backbuffer(Display_MainWindow::GetDisplay());

	
}

void Elem_Construction::DrawOnPosition(float X, float Y)
{

	al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());

	this->posX = Display_MainWindow::hitboxes->ToAreaPos_AreaIndex_X(Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_X(X));
	this->posY = Display_MainWindow::hitboxes->ToAreaPos_AreaIndex_Y(Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_Y(Y));
	if (this->lights == true) this->LightElement();

	al_set_target_backbuffer(Display_MainWindow::GetDisplay());
}

bool Elem_Construction::CollisionChecking()
{
	float tX = Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_X(this->posX);
	float tY = Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_Y(this->posY);

	bool retValue = (Display_MainWindow::hitboxes->CheckCollision_FromAreaIndex(tX,tY));
	static bool lights = false; // true is collision-red, false is noncollision-green

	if (retValue == false && lights == true)
	{
		lights = false;
		this->SetLightColor(al_map_rgb(0, 255, 0));
	}
	else if (retValue == true && lights == false)
	{
		lights = true;
		this->SetLightColor(al_map_rgb(255, 0, 0));
	}

	return retValue;
}

void Elem_Construction::CollisionSetting(bool _bool)
{

	float tX = Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_X(this->posX);
	float tY = Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_Y(this->posY);

	Display_MainWindow::hitboxes->SetCollision_FromAreaIndex(tX, tY,_bool);

	if (_bool == true)		  Display_MainWindow::hitboxes->SetPointer_FromAreaIndex(tX, tY, this);
	else if (_bool == false)  Display_MainWindow::hitboxes->SetPointer_FromAreaIndex(tX, tY, NULL);
}
