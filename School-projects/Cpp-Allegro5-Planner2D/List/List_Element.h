#pragma once
#include <string>
#include <allegro5/allegro.h>
using namespace std;

class List_Element
{
private:
protected:
	void SetMainBitmapAlpha(ALLEGRO_COLOR color)
	{
		al_convert_mask_to_alpha(this->mainBitmap, color);
	}
public:


	string nameItem;
	ALLEGRO_BITMAP * mainBitmap;

	// Constructor & destructor
	List_Element(string _name, string _elementFolderPath, string _filename);
	virtual ~List_Element();
};

