#pragma once

#include <Windows.h>
#include <iostream>
#include <fstream>
#include <string>

#include <allegro5/allegro.h>
#include <allegro5/allegro_native_dialog.h>
#include "allegro5/allegro_primitives.h"

#include "../Components/Component_MainComponent.h"
#include "../Components/Component_Manager.h"
#include "../Components/Component_ManagerMENU.h"

#include "../Element/Elem_Element.h"

#include "Display_AreaHitboxes.h"

using namespace std;

class Display_MainWindow
{
private:
	// Private Data 
	string windowSettingsFileName;

	static float dspWidth, dspHeight;

	static ALLEGRO_DISPLAY	* dspPtr;
	static ALLEGRO_BITMAP   * dspBitmap;
	static ALLEGRO_BITMAP   * dspMenuBitmap;
	static ALLEGRO_BITMAP   * dspDrawinAreaBitmap;
	static ALLEGRO_BITMAP   * dspToolbarBitmap;

	static ALLEGRO_COLOR color_Menu,color_DrawArea, color_Toolbar, color_MaskAlpha;

	List_List               * BitmapList;
	Component_ManagerMENU   * dspMenuManager;

	vector<Elem_Element*> vecActiveElements;

	// Toolbar thingies
	RECT ExitButton;

	// Private Methods
	void LoadWindowSettings();

protected:

public:

	// Data
	const static float menuWidthScale, menuHeightScale; // Initialized in the .cpp file cuz compiler doesnt support non-integral variables to be initialized here ;o
														// menuWidthScale = 0.25 means that width of the menu is 25% of the display width
	const static int hitboxAmount;

	static Display_AreaHitboxes * hitboxes;
	static float menu_Xpos, menu_Ypos, drawingArea_Xpos, drawingArea_Ypos;
	static float menu_XposEnd, menu_YposEnd, drawingArea_XposEnd, drawingArea_YposEnd;
	
	Elem_Element * ptrActualFocus;

	// Getters & setters	
	static ALLEGRO_DISPLAY * GetDisplay()	     { return dspPtr; };
	static ALLEGRO_BITMAP  * GetMenuBmp()		 { return dspMenuBitmap; };
	static ALLEGRO_BITMAP  * GetDrawingAreaBmp() { return dspDrawinAreaBitmap; };

	static float getdspWidth()		  		     { return dspWidth; }
	static float getdspHeight() 	             { return dspHeight; }
	static ALLEGRO_COLOR getColorDrawArea()      { return color_DrawArea; }
	static ALLEGRO_COLOR getMaskAlphaColor()	 { return color_MaskAlpha; }

	static void RefreshDisplay();

	void SetMenuColor(ALLEGRO_COLOR _color)      { this->color_Menu = _color;     }
	void SetAreaColor(ALLEGRO_COLOR _color)      { this->color_DrawArea = _color; }
	void SetToolbarColor(ALLEGRO_COLOR _color)   { this->color_Toolbar = _color; }
	void SetMaskAlphaColor(ALLEGRO_COLOR _color) { this->color_MaskAlpha = _color; }

	// Methods
	void MouseEventMenuComponents(const float &mouseX, const float &mouseY);

	void DrawMenuComponents();
	void DrawAreaThings();
	void DrawActiveElements();
	void DrawToolbarThings();

	void CreateNewElement();

	void SettleElement();
	void UnsettleElement(Elem_Element * ptr);
	void ClearActualFocus();

	void SetMenuAccordingToItem(string itemname); // NOT USED

	bool CheckIfExitClicked(float x, float y);
	bool CheckIfToolbarClicked(float x, float y);

	// Moving display
	float mousePosX, mousePosY;
	float mouseDX, mouseDY;
	float ResolutionHeight, ResolutionWidth;

	void CalcMouse()
	{
		int x, y;
		al_get_window_position(this->GetDisplay(), &x, &y);
		x += this->mousePosX;
		y += this->mousePosY;
	}

	// Constructor & Destructor
	Display_MainWindow();
	~Display_MainWindow();
};

