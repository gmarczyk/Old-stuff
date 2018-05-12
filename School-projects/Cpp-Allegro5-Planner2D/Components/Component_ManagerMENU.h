#pragma once
#include "../Components/Component_Manager.h"
#include <stack>
#include <allegro5/allegro_font.h>
#include "../List/List_List.h"
#include "../Element/Elem_Element.h"

class Display_MainWindow;

class Component_ManagerMENU : public Component_Manager
{
private:

	// Data & initialization/setting methods
	string name_Equipment, name_Construction, name_EquipmentTypeSelector,name_EquipmentItemSelector, name_FurnitAddonSelector, name_CreateButton, name_CreateWallButton;

	vector<string> eqSelectorOptions;
	vector<string> addonSelectorOptions;

	ALLEGRO_COLOR eqFlasherBordercolor;
	ALLEGRO_COLOR eqFlasherLightcolor;
	float eqBorderSize;

        Display_MainWindow * ptrToDsp;
	List_List * listptr;

	ALLEGRO_COLOR color_ConstrWallButtonLIGHT;
	ALLEGRO_COLOR color_ConstrWallButtonBorder;
	float size_ConstrWallButtonBorder;

	
	void InitializeConstrButton()
	{
	    this->color_ConstrWallButtonLIGHT  = al_map_rgb(225, 225, 225); 
	    this->color_ConstrWallButtonBorder = al_map_rgb(0, 0, 0);
	    this->size_ConstrWallButtonBorder  = 1;
	}
	void InitializeEqFlasherBordercolor() { this->eqFlasherBordercolor = al_map_rgb(0, 0, 0); }	// Initialization needs to be done outside constructor
	void InitializeEqFlasherLightColor()  { this->eqFlasherLightcolor = al_map_rgb(225, 225, 225);    }
	void InitializeFont() { this->font8 = al_create_builtin_font(); }

	// MENU manager methods
	void MENUComponentOperator(string _name, ComponentOperationCode _code);


public:

	ALLEGRO_BITMAP* tmpBmp;
        static ALLEGRO_FONT *font8;
	
	// Creating & setting methods
	void InitializeAllegroThings() { this->InitializeEqFlasherBordercolor(); this->InitializeEqFlasherLightColor(); this->InitializeFont(); this->InitializeConstrButton(); }
	void InitializeStringVectors(vector<List_Element*> *vecDecorationList, vector<List_ElementAddon*> *vecFurnitureList);
	void CreateStandardMenuComponents();

	// MENU manager methods
	void CheckAllMouseEvents(const float &mouseX, const float &mouseY);

	void CheckIfConstructionWall();
	void ConstrWallButtonOff(); void ConstrWallButtonOn();

	void SetActualBmp();
	Elem_Element * CreateElement();

	void CreateConstructionButton();

	void SetMenuOn_Decoration(string deconame);
	void SetMenuOn_Furniture(string furnitname, vector<bool> addons);
	void SetMenuOn_Item(string _name);
	

	Component_ManagerMENU* Clone()
	{
		return new Component_ManagerMENU(*this);
	}
	
	// Constructor && destructor
	Component_ManagerMENU(List_List *listptr, Display_MainWindow *dspPtr);
	~Component_ManagerMENU();
};


