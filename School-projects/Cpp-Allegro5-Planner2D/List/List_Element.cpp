#include "List_Element.h"
#include "../Display/Display_MainWindow.h"

List_Element::List_Element(string _name,string _elementFolderPath, string _filename) : nameItem(_name)
{
	ALLEGRO_PATH *path = al_get_standard_path(ALLEGRO_RESOURCES_PATH);
	al_append_path_component(path, _elementFolderPath.c_str());

	al_set_path_filename(path, _filename.c_str());

	this->mainBitmap = al_load_bitmap(al_path_cstr(path, '/'));
	this->SetMainBitmapAlpha(Display_MainWindow::getMaskAlphaColor());
	al_destroy_path(path);
}

List_Element::~List_Element()
{
	al_destroy_bitmap(this->mainBitmap);
}
