#include "Display_MainWindow.h"
#include <vector>
#include "../Element/Elem_Furniture.h"

const float Display_MainWindow::menuWidthScale = 0.22;
const float Display_MainWindow::menuHeightScale = 0.97;

const int Display_MainWindow::hitboxAmount = 50;

float Display_MainWindow::dspWidth;
float Display_MainWindow::dspHeight;

float Display_MainWindow::menu_Xpos;			float Display_MainWindow::menu_XposEnd;
float Display_MainWindow::menu_Ypos;			float Display_MainWindow::menu_YposEnd;
float Display_MainWindow::drawingArea_Xpos;		float Display_MainWindow::drawingArea_XposEnd;
float Display_MainWindow::drawingArea_Ypos;		float Display_MainWindow::drawingArea_YposEnd;

ALLEGRO_BITMAP * Display_MainWindow::dspMenuBitmap;
ALLEGRO_BITMAP * Display_MainWindow::dspBitmap;
ALLEGRO_BITMAP * Display_MainWindow::dspDrawinAreaBitmap;  Display_AreaHitboxes * Display_MainWindow::hitboxes;
ALLEGRO_BITMAP * Display_MainWindow::dspToolbarBitmap;

ALLEGRO_DISPLAY * Display_MainWindow::dspPtr;

ALLEGRO_COLOR Display_MainWindow::color_DrawArea;
ALLEGRO_COLOR Display_MainWindow::color_Menu;
ALLEGRO_COLOR Display_MainWindow::color_Toolbar;
ALLEGRO_COLOR Display_MainWindow::color_MaskAlpha;

Display_MainWindow::Display_MainWindow()
{

	this->windowSettingsFileName = "windowSettings.txt";
	this->LoadWindowSettings();

	this->SetMenuColor(al_map_rgb(255, 255, 255));
	this->SetAreaColor(al_map_rgb(235,235, 235));
	this->SetToolbarColor(al_map_rgb(245, 245, 245));
	this->SetMaskAlphaColor(al_map_rgb(245, 245, 245));

	this->BitmapList = new List_List;
	this->dspMenuManager = new Component_ManagerMENU(BitmapList, this);
	this->hitboxes = new Display_AreaHitboxes();

	Display_MainWindow::menu_Xpos = 0;
	Display_MainWindow::menu_Ypos = Display_MainWindow::dspHeight *(1.0 - Display_MainWindow::menuHeightScale);
	Display_MainWindow::drawingArea_Xpos = Display_MainWindow::dspWidth  *       Display_MainWindow::menuWidthScale;
	Display_MainWindow::drawingArea_Ypos = Display_MainWindow::dspHeight *(1.0 - Display_MainWindow::menuHeightScale);

	this->dspMenuManager->InitializeAllegroThings();
	this->dspMenuManager->CreateStandardMenuComponents();

	RECT WindowRect;												// Getting resolution of the host screen
	HWND DesktopWindow = GetDesktopWindow();
	GetWindowRect(DesktopWindow, &WindowRect);

	ResolutionWidth = (WindowRect.right - WindowRect.left);
	ResolutionHeight = (WindowRect.bottom - WindowRect.top);

	// If params are right (our window is smaller than host screen resolution and higher than 800x600)
	if ((
		((dspHeight >= 600) && (dspWidth >= 800)) &&
		((dspHeight <= ResolutionHeight) && (dspWidth <= ResolutionWidth))
		))
	{
		al_set_new_display_flags(ALLEGRO_NOFRAME);
		this->dspPtr = al_create_display(this->dspWidth, this->dspHeight);
		if (!dspPtr)	// If display wasnt created
		{
			cout << "Display not created" << endl;
			al_show_native_message_box(dspPtr, "Error - Display", "Display not created", " ", NULL, ALLEGRO_MESSAGEBOX_ERROR);
			exit(0);
		}

		this->dspBitmap = al_create_bitmap(this->dspWidth, this->dspHeight);
		al_set_target_bitmap(this->dspBitmap);
		al_clear_to_color(this->color_DrawArea);

		this->dspDrawinAreaBitmap = al_create_bitmap(this->dspWidth*(1.0- Display_MainWindow::menuWidthScale), this->dspHeight*(Display_MainWindow::menuHeightScale));
		al_set_target_bitmap(this->dspDrawinAreaBitmap);
		al_clear_to_color(Display_MainWindow::color_DrawArea);
		this->hitboxes->DrawHitboxes();

		this->dspToolbarBitmap = al_create_bitmap(this->dspWidth, this->dspHeight*(1.0-Display_MainWindow::menuHeightScale)+1);
		al_set_target_bitmap(this->dspToolbarBitmap);
		al_clear_to_color(this->color_Toolbar);
		al_draw_rectangle(1, 1, al_get_bitmap_width(this->dspToolbarBitmap)-1, al_get_bitmap_height(this->dspToolbarBitmap)-1, al_map_rgb(0, 0, 0), 2);

		this->dspMenuBitmap = al_create_bitmap(this->dspWidth*Display_MainWindow::menuWidthScale, this->dspHeight*Display_MainWindow::menuHeightScale);
		al_set_target_bitmap(this->dspMenuBitmap);
		al_clear_to_color(this->color_Menu);

		Display_MainWindow::menu_XposEnd = Display_MainWindow::menu_Xpos + al_get_bitmap_width(this->dspMenuBitmap);
		Display_MainWindow::menu_YposEnd = Display_MainWindow::menu_Ypos + al_get_bitmap_height(this->dspMenuBitmap);
		Display_MainWindow::drawingArea_XposEnd = Display_MainWindow::drawingArea_Xpos + al_get_bitmap_width(this->dspDrawinAreaBitmap);
		Display_MainWindow::drawingArea_YposEnd = Display_MainWindow::drawingArea_Ypos + al_get_bitmap_height(this->dspDrawinAreaBitmap);

		this->hitboxes->InitializeHitboxesAfterWindow();

		this->ExitButton.left = (this->dspWidth*0.96);
		this->ExitButton.right = ExitButton.left + (this->dspWidth*0.04);
		this->ExitButton.top = 0;
		this->ExitButton.bottom = ExitButton.top + ((this->dspHeight*(1 - this->menuHeightScale)))+1;

		al_set_target_backbuffer(this->dspPtr);
		al_draw_bitmap(this->dspBitmap, 0, 0, 0);
		al_draw_bitmap(this->dspMenuBitmap, 0, 0, 0);
		al_flip_display();
	}
	// If params not right
	else
	{
		cout << "Resolution params wrong" << endl;
		al_show_native_message_box(dspPtr, "Error - Display", "Parameters lower than 800x600 or higher than your screen resolution", " ", NULL, ALLEGRO_MESSAGEBOX_ERROR);
		exit(0);
	}
}

Display_MainWindow::~Display_MainWindow()
{
	al_destroy_display(this->dspPtr);
	al_destroy_bitmap(this->dspBitmap);
	al_destroy_bitmap(this->dspMenuBitmap);
	al_destroy_bitmap(this->dspDrawinAreaBitmap);
	al_destroy_bitmap(this->dspToolbarBitmap);

	vector<Elem_Element*>::iterator it;
	for (it = this->vecActiveElements.begin(); it != this->vecActiveElements.end(); ++it)
	{
		(*it)->~Elem_Element();
	}
}

void Display_MainWindow::LoadWindowSettings()
{
	fstream windowSettingsFile;
	string readBuffer;

	windowSettingsFile.open(this->windowSettingsFileName, ios::in);
	if (windowSettingsFile.good() == true)
	{
		cout << "File access granted - WindowSettings!" << endl;
	}
	else
	{
		cout << "File access denied - WindowSettings!" << endl;
		al_show_native_message_box(dspPtr, "Error - Display", "File access granted - WindowSettings!", " ", NULL, ALLEGRO_MESSAGEBOX_ERROR);
	}

	while (!windowSettingsFile.eof())
	{
		windowSettingsFile >> readBuffer;
		// At first, after checking name of data, get through the '=' (Width = 123) - load '=' to readBuffer, then load again to obtain value //

		if (readBuffer == "Width") { windowSettingsFile >> readBuffer; windowSettingsFile >> dspWidth;  cout << dspWidth << endl; }
		if (readBuffer == "Height") { windowSettingsFile >> readBuffer; windowSettingsFile >> dspHeight; cout << dspHeight << endl; }

	}
	windowSettingsFile.close();
}



void Display_MainWindow::DrawMenuComponents()
{
	al_set_target_bitmap(Display_MainWindow::dspMenuBitmap);		// Draw on specific bitmap - needs to be targetted
	al_clear_to_color(this->color_Menu);					// Refilling bitmap with color
	this->dspMenuManager->DrawAllComponents();				// Drawing everything, then drawing line on the edge

	if (this->dspMenuManager->tmpBmp != NULL)
	{
		al_draw_scaled_bitmap(this->dspMenuManager->tmpBmp,
			0,0,
			al_get_bitmap_width(this->dspMenuManager->tmpBmp), al_get_bitmap_height(this->dspMenuManager->tmpBmp),
			Display_MainWindow::dspWidth*Display_MainWindow::menuWidthScale*0.1,
			Display_MainWindow::dspHeight*Display_MainWindow::menuHeightScale*0.5 + Display_MainWindow::menu_Ypos,
			Display_MainWindow::dspWidth*Display_MainWindow::menuWidthScale*0.8,
			Display_MainWindow::dspWidth*Display_MainWindow::menuWidthScale*0.8
			,0);

		al_draw_rectangle(
			Display_MainWindow::dspWidth*Display_MainWindow::menuWidthScale*0.1,
			Display_MainWindow::dspHeight*Display_MainWindow::menuHeightScale*0.5 + Display_MainWindow::menu_Ypos,
			Display_MainWindow::dspWidth*Display_MainWindow::menuWidthScale*0.1 + Display_MainWindow::dspWidth*Display_MainWindow::menuWidthScale*0.8,
			Display_MainWindow::dspHeight*Display_MainWindow::menuHeightScale*0.5 + Display_MainWindow::menu_Ypos + Display_MainWindow::dspWidth*Display_MainWindow::menuWidthScale*0.8,
			al_map_rgb(0,0,0),3);

	}
	al_set_target_backbuffer(Display_MainWindow::dspPtr);		// Setting focus on main display again to keep everyting ok


}
void Display_MainWindow::DrawAreaThings()
{
	this->hitboxes->DrawHitboxes();
	this->DrawActiveElements();
	this->hitboxes->DrawTmpCollision();
	if(this->ptrActualFocus !=NULL) this->ptrActualFocus->DrawYourself();

	if (Display_MainWindow::hitboxes->ActualCollisionDrawing == true) this->hitboxes->DrawActualCollision();
}

void Display_MainWindow::RefreshDisplay()
{

	al_draw_bitmap(Display_MainWindow::dspBitmap, 0, 0, 0);
	al_draw_bitmap(Display_MainWindow::dspDrawinAreaBitmap, Display_MainWindow::drawingArea_Xpos, Display_MainWindow::drawingArea_Ypos, 0);
	al_draw_bitmap(Display_MainWindow::dspMenuBitmap, Display_MainWindow::menu_Xpos, Display_MainWindow::menu_Ypos, 0);
	al_draw_bitmap(Display_MainWindow::dspToolbarBitmap, 0, 0, 0);

	// Borders
	al_draw_rectangle(Display_MainWindow::menu_Xpos+1, 1, dspWidth-1, dspHeight-1, al_map_rgb(0, 0, 0), 2);	// all
	al_draw_rectangle(Display_MainWindow::menu_XposEnd+1, Display_MainWindow::drawingArea_Ypos, dspWidth-1, dspHeight-1, al_map_rgb(0, 0, 0), 1); //drw area

	al_flip_display();
}

void Display_MainWindow::MouseEventMenuComponents(const float &mouseX, const float &mouseY)
{
	{
		this->dspMenuManager->CheckAllMouseEvents(mouseX, mouseY);
	}
}

void Display_MainWindow::CreateNewElement()
{
	Elem_Element * tmp = this->dspMenuManager->CreateElement();
	if (tmp != NULL)
	{
		this->ptrActualFocus = tmp;
		this->ptrActualFocus->SetPosition(-Display_MainWindow::menu_XposEnd, -Display_MainWindow::menu_YposEnd);
		this->ptrActualFocus->SetLightsOnOff(true);
	}
}

void Display_MainWindow::DrawActiveElements()
{
	al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());

	vector<Elem_Element*>::iterator it;
	for (it = this->vecActiveElements.begin(); it != this->vecActiveElements.end(); ++it)
	{
		(*it)->DrawYourself();
	}

	al_set_target_backbuffer(Display_MainWindow::GetDisplay());
}

void Display_MainWindow::DrawToolbarThings()
{
	al_set_target_bitmap(this->dspToolbarBitmap);
	float dx = ExitButton.right - ExitButton.left;
	float dy = ExitButton.bottom - ExitButton.top;
	al_draw_filled_rectangle(ExitButton.left, ExitButton.top, ExitButton.right, ExitButton.bottom, al_map_rgb(0, 0, 0));
	al_draw_rectangle(ExitButton.left + (dx*0.25), ExitButton.top + (dy*0.25), ExitButton.right - (dx*0.25), ExitButton.bottom - (dy*0.25), al_map_rgb(255, 255, 255),2);


	al_set_target_backbuffer(this->dspPtr);
}

void Display_MainWindow::ClearActualFocus()
{
	if (this->ptrActualFocus != NULL)
	{
		this->ptrActualFocus = NULL;
		this->dspMenuManager->ConstrWallButtonOff();

		this->DrawAreaThings();
		this->DrawMenuComponents();
	
		Display_MainWindow::RefreshDisplay();
	}
}

void Display_MainWindow::SettleElement()
{
	if (this->ptrActualFocus != NULL && this->ptrActualFocus->CollisionChecking() != true)
	{
		this->ptrActualFocus->CollisionSetting(true);
		this->hitboxes->ResetTmpCollision();

		this->vecActiveElements.push_back(this->ptrActualFocus);

		this->ptrActualFocus->SetLightsOnOff(false);
		this->ptrActualFocus = NULL;

		if (this->ptrActualFocus == NULL) this->dspMenuManager->CheckIfConstructionWall();
	}
}

void Display_MainWindow::UnsettleElement(Elem_Element * ptr)
{
	ptr->CollisionSetting(false);
	this->dspMenuManager->ConstrWallButtonOff();

	vector<Elem_Element*>::iterator it;
	vector<Elem_Element*>::iterator tmp;
	for (it = this->vecActiveElements.begin(); it != this->vecActiveElements.end(); ++it)
	{
		if ((*it) == ptr)
		{
			tmp = it;
		}
	}

	this->vecActiveElements.erase(tmp);

	this->ptrActualFocus = ptr;
	this->ptrActualFocus->SetLightsOnOff(true);
	this->ptrActualFocus->SetLightColor(al_map_rgb(0, 255, 0));
}

void Display_MainWindow::SetMenuAccordingToItem(string itemname)
{

	FindItem tmpEnum = this->BitmapList->WhereIsItemName(itemname);

	Elem_Furniture * tmpFurniture; 
	vector<bool> tmpAddons; 

	switch (tmpEnum)
	{
	case decoFound:
		this->dspMenuManager->SetMenuOn_Decoration(itemname);

		break;
	case furnitFound:
		tmpFurniture = dynamic_cast <Elem_Furniture*>(this->ptrActualFocus);
		tmpAddons = tmpFurniture->GetAddons();
		this->dspMenuManager->SetMenuOn_Furniture(itemname, tmpAddons);
		break;
	}
}

bool Display_MainWindow::CheckIfExitClicked(float x, float y)
{
	if (x > ExitButton.left && x< ExitButton.right && y> ExitButton.top && y < ExitButton.bottom) return true;
	else return false;
}

bool Display_MainWindow::CheckIfToolbarClicked(float x, float y)
{
	if (x > 0 && x< this->dspWidth && y> 0 && y < this->drawingArea_Ypos) return true;
	else return false;
}
