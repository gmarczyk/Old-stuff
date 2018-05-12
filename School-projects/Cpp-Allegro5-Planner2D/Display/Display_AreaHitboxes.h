#pragma once
#include "../Element/Elem_Element.h"
#include <Windows.h>
#include <vector>

class Hitbox
{
private:
protected:
public:

	Elem_Element * ptrElement;
	bool collision;

	Hitbox()
	{
		this->collision = false;
		this->ptrElement = NULL;
	}
};

struct Index
{
	int x, y;
};


class Display_AreaHitboxes
{
private:

	float areaWidth, areaHeight;
	float linewidth;

	Hitbox ** Hitboxes;
	float HbxWidth, HbxHeight;
	vector<Index> vecTempCollision;

	void InitializeHitboxes();

protected:
public:

	bool ActualCollisionDrawing;

	// To Display position
	float ToDspPos_AreaIndex_X(int index);
	float ToDspPos_AreaIndex_Y(int index);

	// To Area position
	float ToAreaPos_DspPos_X(float x);
	float ToAreaPos_DspPos_Y(float x);

	float ToAreaPos_AreaIndex_X(int x);
	float ToAreaPos_AreaIndex_Y(int x);

	// To Area index
	int ToAreaIndex_DspPos_X(float x);
	int ToAreaIndex_DspPos_Y(float x);

	int ToAreaIndex_AreaPos_X(float index);
	int ToAreaIndex_AreaPos_Y(float index);

	// Methods
	void InitializeHitboxesAfterWindow();

	void DrawHitboxes();
	void DrawTmpCollision();	void ResetTmpCollision() { this->vecTempCollision.clear(); }
	void DrawActualCollision();

	bool CheckCollision_FromAreaPos(float xStart,float xEnd,float yStart,float yEnd);
	void SetCollision_FromAreaPos(float xStart, float xEnd, float yStart, float yEnd, bool _bool);

	bool CheckCollision_FromAreaIndex(int x, int y);
	void SetCollision_FromAreaIndex(int x, int y, bool _bool) { this->Hitboxes[x][y].collision = _bool;}

	void SetPointers_FromAreaPos(float xStart, float xEnd, float yStart, float yEnd, Elem_Element *ptr);
	void SetPointer_FromAreaIndex(int x, int y, Elem_Element * ptr) { this->Hitboxes[x][y].ptrElement = ptr; }

	Elem_Element * GetElemPointer(int X, int Y) { return this->Hitboxes[X][Y].ptrElement; }

	float GetHbxWidth()  { return this->HbxWidth;  }
	float GetHbxHeight() { return this->HbxHeight; }
	float GetLineWidth()  { return this->linewidth;  }

	void SetBorders();

	Display_AreaHitboxes();
	~Display_AreaHitboxes();
};

