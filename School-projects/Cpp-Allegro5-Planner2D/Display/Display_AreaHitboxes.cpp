#include "Display_AreaHitboxes.h"
#include "Display_MainWindow.h"


void Display_AreaHitboxes::InitializeHitboxes()
{
	this->HbxWidth  = ((Display_MainWindow::getdspWidth()) *(1.0 - Display_MainWindow::menuWidthScale))/ Display_MainWindow::hitboxAmount;
	this->HbxHeight = ((Display_MainWindow::getdspHeight())*(Display_MainWindow::menuHeightScale))/ Display_MainWindow::hitboxAmount;

	this->Hitboxes = new Hitbox*[Display_MainWindow::hitboxAmount];
	for (int i = 0; i < Display_MainWindow::hitboxAmount; i++)
	{
		this->Hitboxes[i] = new Hitbox[Display_MainWindow::hitboxAmount];
	}
}
void Display_AreaHitboxes::InitializeHitboxesAfterWindow()
{
	this->areaWidth = al_get_bitmap_width(Display_MainWindow::GetDrawingAreaBmp());
	this->areaHeight = al_get_bitmap_height(Display_MainWindow::GetDrawingAreaBmp());
}

float Display_AreaHitboxes::ToDspPos_AreaIndex_X(int index)
{
	if (index > 0 && index < Display_MainWindow::hitboxAmount)
	{
		float posX = Display_MainWindow::drawingArea_Xpos + (index*this->HbxWidth);
		return posX;
	}
	else return 9999999.9;
}
float Display_AreaHitboxes::ToDspPos_AreaIndex_Y(int index)
{
	if (index > 0 && index < Display_MainWindow::hitboxAmount)
	{
	float posY = Display_MainWindow::drawingArea_Ypos + (index*this->HbxHeight);
	return posY;
	}
	else return 9999999.9;
}

float Display_AreaHitboxes::ToAreaPos_DspPos_X(float x)
{
	float tmp;
	tmp = x - Display_MainWindow::drawingArea_Xpos;
	return tmp;
}

float Display_AreaHitboxes::ToAreaPos_DspPos_Y(float x)
{
	float tmp;
	tmp = x - Display_MainWindow::drawingArea_Ypos;
	return tmp;
}

float Display_AreaHitboxes::ToAreaPos_AreaIndex_X(int x)
{
	float tmp;
	tmp = x*(this->HbxWidth);
	tmp += 0.01;
	return tmp;
}

float Display_AreaHitboxes::ToAreaPos_AreaIndex_Y(int x)
{
	float tmp;
	tmp = x*(this->HbxHeight);
	tmp += 0.01;
	return tmp;
}

int Display_AreaHitboxes::ToAreaIndex_DspPos_X(float x)
{
	float tmp;
	tmp = this->ToAreaPos_DspPos_X(x);
	if (tmp >= 0 && tmp < (this->areaWidth))
	{
		tmp = tmp / this->HbxWidth;
		tmp = floor(tmp);
		return tmp ;
	}
	else return -1;
}

int Display_AreaHitboxes::ToAreaIndex_DspPos_Y(float x)
{
	float tmp;
	tmp = this->ToAreaPos_DspPos_Y(x);
	if (tmp >= 0 && tmp < (this->areaHeight))
	{
		tmp = tmp / this->HbxHeight;
		tmp=floor(tmp);
		return tmp ;
	}
	else return -1;
}

int Display_AreaHitboxes::ToAreaIndex_AreaPos_X(float posX)
{
	float tmp = posX;
	if (posX >= 0 && posX < (this->areaWidth))
	{
		tmp = tmp / this->HbxWidth;
		tmp = floor(tmp);
		return tmp;
	}
	else return -1;
}

int Display_AreaHitboxes::ToAreaIndex_AreaPos_Y(float posY)
{
	float tmp = posY;
	if (posY >= 0 && posY < (this->areaHeight))
	{
		tmp = tmp / this->HbxHeight;
		tmp = floor(tmp);
		return tmp ;
	}
	else return -1;
}

void Display_AreaHitboxes::DrawHitboxes()
{
	al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());
	al_clear_to_color(Display_MainWindow::getColorDrawArea());		

	float height, width;
	height = al_get_bitmap_height(Display_MainWindow::GetDrawingAreaBmp());
	width = al_get_bitmap_width(Display_MainWindow::GetDrawingAreaBmp());

	for (int i = 0; i < Display_MainWindow::hitboxAmount; i++)
	{
		al_draw_line( i*this->HbxWidth,0, i*this->HbxWidth, height,al_map_rgba(125, 125, 125,200), this->linewidth);
	}
	for (int j = 0; j < Display_MainWindow::hitboxAmount; j++)
	{
		al_draw_line(0, j*this->HbxHeight, width, j*this->HbxHeight, al_map_rgba(125, 125, 125,200), this->linewidth);
	}
	ALLEGRO_COLOR colorx = al_map_rgb(0, 0, 0);
	al_draw_filled_rectangle(0,0,al_get_bitmap_width(Display_MainWindow::GetDrawingAreaBmp()),this->HbxHeight, colorx);
	al_draw_filled_rectangle(0, 0, this->HbxWidth, al_get_bitmap_height(Display_MainWindow::GetDrawingAreaBmp()), colorx);
	al_draw_filled_rectangle(0, al_get_bitmap_height(Display_MainWindow::GetDrawingAreaBmp()), al_get_bitmap_width(Display_MainWindow::GetDrawingAreaBmp()), al_get_bitmap_height(Display_MainWindow::GetDrawingAreaBmp()) - this->HbxHeight, colorx);
	al_draw_filled_rectangle(al_get_bitmap_width(Display_MainWindow::GetDrawingAreaBmp()), 0, al_get_bitmap_width(Display_MainWindow::GetDrawingAreaBmp()) - this->HbxWidth, al_get_bitmap_height(Display_MainWindow::GetDrawingAreaBmp()), colorx);

	al_set_target_backbuffer(Display_MainWindow::GetDisplay());
}

void Display_AreaHitboxes::DrawTmpCollision()
{
	if (this->vecTempCollision.empty() == false)
	{
		al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());

		vector<Index>::iterator it;
		for (it = this->vecTempCollision.begin(); it != this->vecTempCollision.end(); ++it)
		{
			float tX = this->ToAreaPos_AreaIndex_X(it->x);
			float tY = this->ToAreaPos_AreaIndex_Y(it->y);

			al_draw_filled_rectangle(
				tX, tY,
				tX + this->HbxWidth, tY + this->HbxHeight,
				al_map_rgba(255, 0, 0, 128)
				);
		}
		al_set_target_backbuffer(Display_MainWindow::GetDisplay());
	}
}

void Display_AreaHitboxes::DrawActualCollision()
{

		al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());

		vector<Index>::iterator it;
		for (int i = 0; i < Display_MainWindow::hitboxAmount; i++)
		{
			for (int j = 0; j < Display_MainWindow::hitboxAmount; j++)
			{
				if (this->Hitboxes[i][j].collision == true)
				{
					float tX = this->ToAreaPos_AreaIndex_X(i);
					float tY = this->ToAreaPos_AreaIndex_Y(j);

					al_draw_filled_rectangle(
						tX, tY,
						tX + this->HbxWidth, tY + this->HbxHeight,
						al_map_rgba(255, 0, 0, 128)
						);
				}
			}
		}
		al_set_target_backbuffer(Display_MainWindow::GetDisplay());	
}

bool Display_AreaHitboxes::CheckCollision_FromAreaPos(float xStart, float xEnd, float yStart, float yEnd)
{

	bool isCollision = false;
	int startX, startY;
	int endX, endY;

	vector<Index> vecCollisLocal;
	Index tmpIndex;

	startX = this->ToAreaIndex_AreaPos_X(xStart);
	endX   = this->ToAreaIndex_AreaPos_X(xEnd);

	startY = this->ToAreaIndex_AreaPos_Y(yStart);
	endY   = this->ToAreaIndex_AreaPos_Y(yEnd);

	if ((startX == (-1)) || (startY == (-1)) || (endX == (-1)) || (endY == (-1))) isCollision = true;
	if (isCollision == false)
	{
		for (int i = startX; i <= endX; i++)
		{
			for (int j = startY; j <= endY; j++)
			{

				if (i >= 0 && j >= 0 && i < Display_MainWindow::hitboxAmount && j < Display_MainWindow::hitboxAmount)
				{
					if (this->Hitboxes[i][j].collision == true)
					{
						tmpIndex.x = i;
						tmpIndex.y = j;
						vecCollisLocal.push_back(tmpIndex);
						isCollision = true;
					}
				}
				else
				{
					isCollision = true;
				}
			}
		}
	}

	if (isCollision == true)
	{
		this->vecTempCollision = vecCollisLocal;
	}
	else if(isCollision == false)
	{
		this->vecTempCollision.clear();
	}
	return isCollision;
}

void Display_AreaHitboxes::SetCollision_FromAreaPos(float xStart, float xEnd, float yStart, float yEnd, bool _bool)
{
	float startX = this->ToAreaIndex_AreaPos_X(xStart);
	float endX = this->ToAreaIndex_AreaPos_X(xEnd);

	float startY = this->ToAreaIndex_AreaPos_Y(yStart);
	float endY = this->ToAreaIndex_AreaPos_Y(yEnd);

	for (int i = startX; i <= endX; i++)
	{
		for (int j = startY; j <= endY; j++)
		{
			if (i >= 0 && j >= 0 && i < Display_MainWindow::hitboxAmount && j < Display_MainWindow::hitboxAmount)
			{
				this->Hitboxes[i][j].collision = _bool;
			}

		}
	}
}

bool Display_AreaHitboxes::CheckCollision_FromAreaIndex(int x, int y)
{
	bool isCollision = false;
	vector<Index> vecCollisLocal;
	Index tmpIndex;

	if (x == -1 || y == -1) isCollision = true;

	if (x >= 0 && y >= 0 && x < Display_MainWindow::hitboxAmount && y < Display_MainWindow::hitboxAmount)
	{
		if (this->Hitboxes[x][y].collision == true)
		{
			tmpIndex.x = x;
			tmpIndex.y = y;
			vecCollisLocal.push_back(tmpIndex);
			isCollision = true;
		}
		else
		{
			isCollision = false;
		}
	}
	return isCollision;
}
void Display_AreaHitboxes::SetPointers_FromAreaPos(float xStart, float xEnd, float yStart, float yEnd, Elem_Element *ptr)
{
	float startX = this->ToAreaIndex_AreaPos_X(xStart);
	float endX = this->ToAreaIndex_AreaPos_X(xEnd);

	float startY = this->ToAreaIndex_AreaPos_Y(yStart);
	float endY = this->ToAreaIndex_AreaPos_Y(yEnd);

	for (int i = startX; i <= endX; i++)
	{
		for (int j = startY; j <= endY; j++)
		{
			if (i >= 0 && j >= 0 && i < Display_MainWindow::hitboxAmount && j < Display_MainWindow::hitboxAmount)
			{
				if(ptr != NULL) this->Hitboxes[i][j].ptrElement = ptr;
				else this->Hitboxes[i][j].ptrElement = NULL;
			}

		}
	}
}


void Display_AreaHitboxes::SetBorders()
{
	for (size_t i = 0; i < Display_MainWindow::hitboxAmount; i++) this->Hitboxes[i][0].collision = true;
	for (size_t j = 0; j < Display_MainWindow::hitboxAmount; j++) this->Hitboxes[0][j].collision = true;
	for (size_t k = 0; k < Display_MainWindow::hitboxAmount; k++) this->Hitboxes[Display_MainWindow::hitboxAmount-1][k].collision = true;
	for (size_t m = 0; m < Display_MainWindow::hitboxAmount; m++) this->Hitboxes[m][Display_MainWindow::hitboxAmount-1].collision = true;
}

Display_AreaHitboxes::Display_AreaHitboxes(): ActualCollisionDrawing(false)
{
	this->InitializeHitboxes();
	this->linewidth = 1;
	this->SetBorders();
}

Display_AreaHitboxes::~Display_AreaHitboxes()
{
	for (int i = 0; i < Display_MainWindow::hitboxAmount; ++i)
		delete[] this->Hitboxes[i];

	delete[] this->Hitboxes;
}
