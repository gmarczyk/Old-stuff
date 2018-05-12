#pragma once
#include "List_Element.h"
#include <vector>

class List_ElementAddon:public List_Element
{
private:
	void SetMaskAlpha(ALLEGRO_COLOR color)
	{
		vector<ALLEGRO_BITMAP*>::iterator it;
		for (it = vecAddonBitmap.begin(); it != vecAddonBitmap.end(); ++it)
		{
			al_convert_mask_to_alpha((*it), color);
		}
	}

public:

	vector<ALLEGRO_BITMAP*> vecAddonBitmap;
	vector<string> vecAddonNames;

	// Constructor & destructor
	List_ElementAddon(string _name, string _elementFolderPath, string _filename, vector<string> _addonFilenames );
	~List_ElementAddon();
};

