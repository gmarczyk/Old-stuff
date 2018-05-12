#include "List_ElementAddon.h"
#include "../Display/Display_MainWindow.h"



List_ElementAddon::List_ElementAddon(string _name, string _foldername, string _filename, vector<string> _addonFilenames)
	: List_Element(_name,_foldername,_filename)
{
	this->vecAddonNames = _addonFilenames;
	ALLEGRO_PATH *path = al_get_standard_path(ALLEGRO_RESOURCES_PATH);
	al_append_path_component(path, _foldername.c_str());

	vector<string>::iterator it;

	for (it = _addonFilenames.begin(); it != _addonFilenames.end(); ++it)
	{
		al_set_path_filename(path, (*it).c_str());
		this->vecAddonBitmap.push_back(al_load_bitmap(al_path_cstr(path, '/')));
	}
	this->SetMaskAlpha(Display_MainWindow::getMaskAlphaColor());

	al_destroy_path(path);
}


List_ElementAddon::~List_ElementAddon()
{
	vector<ALLEGRO_BITMAP*>::iterator it;
	for (it = vecAddonBitmap.begin(); it != vecAddonBitmap.end(); ++it)
	{
		al_destroy_bitmap(*it);
	}
	this->vecAddonBitmap.clear();
}

