#include "Elem_Furniture.h"
#include "../Display/Display_MainWindow.h"


Elem_Furniture::Elem_Furniture(string _name, List_List * listptr, vector<bool> vecAddons)
	:Elem_Equipment(_name), vecActiveAddons(vecAddons)
{
	this->ptrToElementOnList = listptr->GetFurnitureFromList(_name);
	this->bmp_ElemBitmap = al_clone_bitmap(this->ptrToElementOnList->mainBitmap);
	this->UpdateAddons();

	this->bmp_height = al_get_bitmap_height(this->bmp_ElemBitmap);
	this->bmp_width = al_get_bitmap_width(this->bmp_ElemBitmap);

	cout << " Elem_Furniture GOT CREATED -------------------------" << endl;
}


Elem_Furniture::~Elem_Furniture()
{
	cout << " Elem_Furniture GOT DESTROYED -------------------------" << endl;
}


void Elem_Furniture::SetAddons(vector<bool> vecOfAddons)
{

}

void Elem_Furniture::UpdateAddons()
{
	al_set_target_bitmap(this->bmp_ElemBitmap);
	al_draw_bitmap(this->ptrToElementOnList->mainBitmap,0,0,0);
	
	vector<bool>::iterator it;
	for (it = this->vecActiveAddons.begin(); it != this->vecActiveAddons.end(); ++it)
	{
		if ((*it) == true)
		{
			int i = it - this->vecActiveAddons.begin();
			ALLEGRO_BITMAP * tmpbmp = al_clone_bitmap(this->ptrToElementOnList->vecAddonBitmap[i]);
			al_convert_mask_to_alpha(tmpbmp,Display_MainWindow::getMaskAlphaColor());
			al_draw_bitmap(this->ptrToElementOnList->vecAddonBitmap[i], 0, 0, 0);;
		}

	
	}
	al_set_target_backbuffer(Display_MainWindow::GetDisplay());
}
