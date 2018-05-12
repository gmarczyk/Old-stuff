#include "Elem_Equipment.h"
#include "../Display/Display_MainWindow.h"

Elem_Equipment::Elem_Equipment(string _name): Elem_Element(_name)
{
	this->posX = Display_MainWindow::drawingArea_Xpos;
	this->posY = Display_MainWindow::drawingArea_Ypos;

	cout << " Elem_Equipment GOT CREATED -------------------------" << endl;
}

Elem_Equipment::~Elem_Equipment()
{
//	al_destroy_bitmap(this->bmp_ElemBitmap);
	cout << " Elem_Equipment GOT DESTROYED -------------------------" << endl;
}

void Elem_Equipment::DrawOnPosition(float X, float Y)
{
	al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());

	if (this->bmp_ElemBitmap != NULL)
	{
		al_draw_bitmap(this->bmp_ElemBitmap, X, Y, 0);
		this->posX = X;
		this->posY = Y;
		if (this->lights == true) this->LightElement();
	}

	al_set_target_backbuffer(Display_MainWindow::GetDisplay());

	cout << "Elem_Equipment::Draw was done --------------------- DRAWING" << endl;
}
bool Elem_Equipment::CollisionChecking()
{
	float tX = this->posX;
	float tY = this->posY;
	float tXe = this->posX + this->bmp_width;
	float tYe = this->posY + this->bmp_height;

	bool retValue = (Display_MainWindow::hitboxes->CheckCollision_FromAreaPos(tX, tXe, tY, tYe));
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
	//Display_MainWindow::hitboxes->DrawTmpCollision();
}
void Elem_Equipment::CollisionSetting(bool _bool)
{
	float tX = this->posX;
	float tY = this->posY;
	float tXe = this->posX + this->bmp_width;
	float tYe = this->posY + this->bmp_height;

	Display_MainWindow::hitboxes->SetCollision_FromAreaPos(tX, tXe, tY, tYe, _bool);

	if      (_bool == true )  Display_MainWindow::hitboxes->SetPointers_FromAreaPos(tX, tXe, tY, tYe, this);
	else if (_bool == false)  Display_MainWindow::hitboxes->SetPointers_FromAreaPos(tX, tXe, tY, tYe, NULL);
}

void Elem_Equipment::DrawYourself()
{
	if (this != NULL)
	{
		if (this->bmp_ElemBitmap != NULL)
		{
			al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());
			al_draw_bitmap(this->bmp_ElemBitmap, this->posX, this->posY, 0);
			if (this->lights == true) this->LightElement();
			al_set_target_backbuffer(Display_MainWindow::GetDisplay());
		}
	}
}

void Elem_Equipment::SetPosition(float X, float Y)
{
	this->posX = X - ((this->bmp_width) / 2);
	this->posY = Y - ((this->bmp_height) / 2);
}

float Elem_Equipment::GetPositionX()
{
	return this->posX + ((this->bmp_width) / 2);
}

float Elem_Equipment::GetPositionY()
{
	return this->posY  + ((this->bmp_height) / 2);
}

void Elem_Equipment::LightElement()
{
	al_draw_rectangle(this->posX, this->posY, this->posX + this->bmp_width, this->posY + this->bmp_height, this->lightColor, this->lightBorderSize);
}
