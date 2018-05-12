#include "Display/Display_MainWindow.h"
#include "Components\Component_Flasher.h"
#include "List\List_Element.h"
#include "List/List_ElementAddon.h"
#include "List/List_List.h"

#include <iostream>

#include <allegro5/allegro.h>
#include <allegro5/allegro_font.h>
#include <allegro5/allegro_image.h>			
#include <allegro5/allegro_native_dialog.h>
#include <allegro5/allegro_primitives.h>


void main()
{
	
	// Allegro initialization
	al_init();
	al_init_native_dialog_addon();
	al_init_primitives_addon();
	al_init_font_addon();
	al_init_image_addon();

	// Setting up display and window 
	Display_MainWindow MainDisplay;
	MainDisplay.DrawMenuComponents();
	MainDisplay.DrawToolbarThings();
	Display_MainWindow::RefreshDisplay();

	// Mouse, Keyboard, Event initialization 
	al_install_mouse();
	al_install_keyboard();

	// Event queue initialization 
	ALLEGRO_EVENT_QUEUE *event_queue = al_create_event_queue();
	al_register_event_source(event_queue, al_get_keyboard_event_source());
	al_register_event_source(event_queue, al_get_mouse_event_source());
	al_register_event_source(event_queue, al_get_display_event_source(Display_MainWindow::GetDisplay()));

	// Main loop miscelannous 
	float mouseXpos = 0.0; float mouseYpos = 0.0; 
	bool movingDisplay = false;

	POINT pAfter;

	bool wasLighted = false;
	bool isRunning = true;
	while (isRunning)
	{
		// Wait for event to happen //
		ALLEGRO_EVENT events;
		al_wait_for_event(event_queue, &events);

		if (events.type == ALLEGRO_EVENT_KEY_DOWN)
		{
			switch (events.keyboard.keycode)
			{
			case ALLEGRO_KEY_N:

				Display_MainWindow::hitboxes->ActualCollisionDrawing = !(Display_MainWindow::hitboxes->ActualCollisionDrawing);
				MainDisplay.DrawAreaThings();
				MainDisplay.DrawMenuComponents();
				MainDisplay.DrawToolbarThings();

				Display_MainWindow::RefreshDisplay();
				break;
			case ALLEGRO_KEY_ENTER:

				if (MainDisplay.ptrActualFocus != NULL && MainDisplay.ptrActualFocus->CollisionChecking() == false)
				{
					wasLighted = true;
					MainDisplay.ptrActualFocus->SetLightColor(al_map_rgb(122, 80, 200));

					al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());
					MainDisplay.ptrActualFocus->LightElement();
					al_set_target_backbuffer(Display_MainWindow::GetDisplay());

					Display_MainWindow::RefreshDisplay();
				}
				MainDisplay.SettleElement();

				MainDisplay.DrawMenuComponents();
				Display_MainWindow::RefreshDisplay();
				break;
			case ALLEGRO_KEY_ESCAPE:

				MainDisplay.ClearActualFocus();	
				break;	
			default:
				break;
			}
		}

		else if (events.type == ALLEGRO_EVENT_MOUSE_BUTTON_DOWN)
		{
			if (events.mouse.button & 1)
			{
				// Moving the display
				if (movingDisplay == false && MainDisplay.CheckIfToolbarClicked(events.mouse.x, events.mouse.y))
				{
					movingDisplay = true;
					mouseXpos = events.mouse.x;
					mouseYpos = events.mouse.y;
				}

				
				// Exit
				if (MainDisplay.CheckIfExitClicked(events.mouse.x, events.mouse.y))
				{
					isRunning = false;
					break;
				}

				// Components
				MainDisplay.MouseEventMenuComponents(events.mouse.x,events.mouse.y);
				MainDisplay.DrawMenuComponents();

				// Area
				if (events.mouse.x > Display_MainWindow::drawingArea_Xpos &&
					events.mouse.y > Display_MainWindow::drawingArea_Ypos)
				{	
					if (MainDisplay.ptrActualFocus == NULL)
					{
						float areaX = MainDisplay.hitboxes->ToAreaIndex_DspPos_X(events.mouse.x);
						float areaY = MainDisplay.hitboxes->ToAreaIndex_DspPos_Y(events.mouse.y);

						if ((areaX >= 0) && (areaY >= 0))
						{
							if ((MainDisplay.hitboxes->GetElemPointer(areaX, areaY)) != NULL)
							{
								MainDisplay.UnsettleElement((MainDisplay.hitboxes->GetElemPointer(areaX, areaY)));
							}
						}

						MainDisplay.DrawMenuComponents();
						MainDisplay.DrawAreaThings();
					}
					else if (MainDisplay.ptrActualFocus != NULL && MainDisplay.ptrActualFocus->CollisionChecking() == false)
					{
						wasLighted = true;
						MainDisplay.ptrActualFocus->SetLightColor(al_map_rgb(122, 80, 200));
						MainDisplay.DrawAreaThings();
						al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());
						MainDisplay.ptrActualFocus->LightElement();
						al_set_target_backbuffer(Display_MainWindow::GetDisplay());


						float xa, xb,m1,m2;

						MainDisplay.ptrActualFocus->SetPosition(Display_MainWindow::hitboxes->ToAreaPos_DspPos_X(events.mouse.x), Display_MainWindow::hitboxes->ToAreaPos_DspPos_Y(events.mouse.y));
						cout << "x : " << Display_MainWindow::hitboxes->ToAreaIndex_DspPos_X(events.mouse.x) << "  y:  " << Display_MainWindow::hitboxes->ToAreaIndex_DspPos_Y(events.mouse.y) << endl;
						cout << "x : " << Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_X(MainDisplay.ptrActualFocus->GetPositionX()) << "  y:  " << Display_MainWindow::hitboxes->ToAreaIndex_AreaPos_Y(MainDisplay.ptrActualFocus->GetPositionY()) << endl;
						
						MainDisplay.SettleElement();
					}
				}
				Display_MainWindow::RefreshDisplay();
			}
		}

		else if (events.type == ALLEGRO_EVENT_MOUSE_BUTTON_UP)
		{
			movingDisplay = false;	
			mouseXpos = 0.0;
			mouseYpos = 0.0;
			pAfter.x = 0;
			pAfter.y = 0;
		}

		else if (events.type == ALLEGRO_EVENT_DISPLAY_CLOSE)
		{
			isRunning = false;
			break;
		}

		else if (events.type == ALLEGRO_EVENT_MOUSE_AXES)
		{
			if (movingDisplay == true)
			{
				GetCursorPos(&pAfter);

				double xx, yy;
				xx = pAfter.x - mouseXpos;
				yy = pAfter.y - mouseYpos;

				al_set_window_position(MainDisplay.GetDisplay(), xx, yy);				
			}

			// Handle on actual item
			if (MainDisplay.ptrActualFocus != NULL)
			{
				if ((events.mouse.x >= Display_MainWindow::drawingArea_Xpos) &&
					(events.mouse.x <= Display_MainWindow::drawingArea_XposEnd) &&
					(events.mouse.y >= Display_MainWindow::drawingArea_Ypos) &&
					(events.mouse.y <= Display_MainWindow::drawingArea_YposEnd))
				{
					MainDisplay.ptrActualFocus->SetPosition(Display_MainWindow::hitboxes->ToAreaPos_DspPos_X(events.mouse.x), Display_MainWindow::hitboxes->ToAreaPos_DspPos_Y(events.mouse.y));				
					MainDisplay.ptrActualFocus->CollisionChecking();

					MainDisplay.DrawAreaThings();
					Display_MainWindow::RefreshDisplay();

				}
			}
			//Light if mouse on item
			else if (MainDisplay.ptrActualFocus == NULL)
			{
				float areaX = MainDisplay.hitboxes->ToAreaIndex_DspPos_X(events.mouse.x);
				float areaY = MainDisplay.hitboxes->ToAreaIndex_DspPos_Y(events.mouse.y);

				if ((areaX >= 0) && (areaY >= 0))
				{
					if ((MainDisplay.hitboxes->GetElemPointer(areaX, areaY)) != NULL)
					{
						wasLighted = true;
						(MainDisplay.hitboxes->GetElemPointer(areaX, areaY))->SetLightColor(al_map_rgb(122, 80, 200));
					
						al_set_target_bitmap(Display_MainWindow::GetDrawingAreaBmp());
						(MainDisplay.hitboxes->GetElemPointer(areaX, areaY))->LightElement();
						al_set_target_backbuffer(Display_MainWindow::GetDisplay());

						Display_MainWindow::RefreshDisplay();
					}
					else 
					{
						if (wasLighted == true)
						{
							MainDisplay.DrawAreaThings();
							MainDisplay.DrawMenuComponents();
							Display_MainWindow::RefreshDisplay();
						}
					}
				}
			}


		} // else if (events.type == ALLEGRO_EVENT_MOUSE_AXES)

	}

	al_destroy_event_queue(event_queue);
}





