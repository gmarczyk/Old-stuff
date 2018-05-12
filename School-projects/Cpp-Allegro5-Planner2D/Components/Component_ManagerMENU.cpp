#include "Component_ManagerMENU.h"
#include "../Display/Display_MainWindow.h"
#include "Component_Selector.h"
#include "../Element/Elem_Construction.h"
#include "../Element/Elem_Decoration.h"
#include "../Element/Elem_Equipment.h"
#include "../Element/Elem_Furniture.h"
#include "../Components/Component_SelectorAddon.h"
Component_ManagerMENU::Component_ManagerMENU(List_List *listptr, Display_MainWindow *dspPtr)
{
	this->ptrToDsp = dspPtr;
	this->listptr = listptr;
	this->eqBorderSize = 1;

	this->name_Equipment             =	"Equipment";
	this->name_Construction          =	"Construction";
	this->name_EquipmentTypeSelector =	"eqSelector";
	this->name_FurnitAddonSelector   =	"addonSelector";
	this->name_EquipmentItemSelector =	"itemSelector";
	this->name_CreateButton          =	"Create element";
	this->name_CreateWallButton      =	"Create wall";

	this->eqSelectorOptions.push_back("Furniture");
	this->eqSelectorOptions.push_back("Decoration");
}

Component_ManagerMENU::~Component_ManagerMENU()
{}

void Component_ManagerMENU::InitializeStringVectors(vector<List_Element*> *vecDecorationList, vector<List_ElementAddon*> *vecFurnitureList)
{}

void Component_ManagerMENU::CreateStandardMenuComponents()
{
	this->CreateFlasher(this->name_Equipment,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqFlasherLightcolor,
		this->eqFlasherBordercolor,
		this->eqBorderSize);

	this->CreateFlasher(this->name_Construction,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.11,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqFlasherLightcolor,
		this->eqFlasherBordercolor,
		this->eqBorderSize);
}

void Component_ManagerMENU::CheckAllMouseEvents(const float &mouseX, const float &mouseY)
{
	list<Component_MainComponent*>::iterator it;
	ComponentOperationCode code;

	for (it = this->vecComponents.begin(); it != this->vecComponents.end(); ++it)
	{
		// Clicking event AND passing return value to MENUCODEOPERATOR
		code = (*it)->ClickEvent(mouseX, mouseY);
		if (code != NothingCpOperation)
		{
			MENUComponentOperator((*it)->componentName, code);	
			break;
		}	
	}
}

void Component_ManagerMENU::CheckIfConstructionWall()
{
	Component_MainComponent * temp;   temp = (this->FindComponent(this->name_Construction));
	Component_Flasher *tmpFlasher;  tmpFlasher = dynamic_cast < Component_Flasher *>(temp);
	if (tmpFlasher != NULL)
	{
		if (tmpFlasher->GetLight() == true && tmpFlasher != NULL)
		{
			temp = (this->FindComponent(this->name_CreateWallButton));
			Component_Flasher *tmpFlasher;  tmpFlasher = dynamic_cast < Component_Flasher *>(temp);
			if (tmpFlasher->GetLight() == true && tmpFlasher != NULL)
			{
				this->ptrToDsp->CreateNewElement();
			}
		}
	}
}

void Component_ManagerMENU::ConstrWallButtonOff()
{
	Component_MainComponent * temp;   temp = (this->FindComponent(this->name_CreateWallButton));
	Component_Flasher *tmpFlasher;  tmpFlasher = dynamic_cast < Component_Flasher *>(temp);
	if (tmpFlasher != NULL)
	{
		if (tmpFlasher->GetLight() == true && tmpFlasher != NULL)
		{
			tmpFlasher->SetLight(false);
		}
	}
}

void Component_ManagerMENU::ConstrWallButtonOn()
{
	Component_MainComponent * temp;   temp = (this->FindComponent(this->name_CreateWallButton));
	Component_Flasher *tmpFlasher;  tmpFlasher = dynamic_cast < Component_Flasher *>(temp);
	if (tmpFlasher != NULL)
	{
		if (tmpFlasher->GetLight() == false)
		{
			tmpFlasher->SetLight(true);
		}
	}
}

Elem_Element * Component_ManagerMENU::CreateElement()
{
		Elem_Element * tmpEl;
		Component_MainComponent * temp;   temp = (this->FindComponent(this->name_Equipment));


		// Check flasher if is on Equipment
		Component_Flasher *tmpFlasher;  tmpFlasher = dynamic_cast < Component_Flasher *>(temp);
		if (tmpFlasher->GetLight() == true)
		{
			temp = (this->FindComponent(this->name_EquipmentTypeSelector));
			Component_Selector *tmpSelector;  tmpSelector = dynamic_cast <Component_Selector *>(temp);

			if (tmpSelector != NULL && tmpSelector->ReturnActualItem() == "Furniture")
			{
				temp = (this->FindComponent(this->name_EquipmentItemSelector));
				Component_Selector *tmpSelector;
				tmpSelector = dynamic_cast <Component_Selector *>(temp);

				if (tmpSelector != NULL)
				{
					if (tmpSelector->ReturnActualItem() != "")
					{
						temp = (this->FindComponent(this->name_FurnitAddonSelector));
						Component_SelectorAddon *tmpSelectorAddon;
						tmpSelectorAddon = dynamic_cast <Component_SelectorAddon *>(temp);

						tmpEl = new Elem_Furniture(tmpSelector->ReturnActualItem(), this->listptr, tmpSelectorAddon->GetAddonVec());
						return tmpEl;
					}
					else
					{
						return NULL;
					}
				}
				else
				{
					return NULL;
				}
			}
			else if (tmpSelector != NULL && tmpSelector->ReturnActualItem() == "Decoration")
			{
				temp = (this->FindComponent(this->name_EquipmentItemSelector));
				Component_Selector *tmpSelector;
				tmpSelector = dynamic_cast <Component_Selector *>(temp);


				if (tmpSelector != NULL)
				{
					if (tmpSelector->ReturnActualItem() != "")
					{
						tmpEl = new Elem_Decoration(tmpSelector->ReturnActualItem(), this->listptr);
						return tmpEl;
					}
					else
					{
						return NULL;
					}
				}
				else
				{
					return NULL;
				}
			}
			else
			{
				return NULL;
			}
		}
		else
		{
		    temp = (this->FindComponent(this->name_Construction));
			Component_Flasher *tmpFlasher;  tmpFlasher = dynamic_cast < Component_Flasher *>(temp);
			// Check if flasher is on Construction
			if (tmpFlasher->GetLight() == true)
			{
				tmpEl = new Elem_Construction("Wall", al_map_rgb(0,0,0));

				return tmpEl;

			}
			else return NULL;
		}
}

void Component_ManagerMENU::CreateConstructionButton()
{
	this->vecComponents.push_back(
		new Component_Flasher(
			this->name_CreateWallButton,
			Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
			Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.20,
			Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
			Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
			this->color_ConstrWallButtonLIGHT,
			this->color_ConstrWallButtonBorder,
			this->size_ConstrWallButtonBorder)
		);
}

void Component_ManagerMENU::SetActualBmp()
{
	if(this->tmpBmp != NULL) al_destroy_bitmap(tmpBmp);

	Component_MainComponent * temp;   temp = (this->FindComponent(this->name_EquipmentTypeSelector));
	Component_Selector *tmpSelector;  tmpSelector = dynamic_cast < Component_Selector *>(temp);

	if (tmpSelector != NULL && tmpSelector->ReturnActualItem() == "Furniture")
	{
		temp = (this->FindComponent(this->name_EquipmentItemSelector));
		Component_Selector *tmpSelector;
		tmpSelector = dynamic_cast < Component_Selector *>(temp);

		if (tmpSelector != NULL)
		{
			if (tmpSelector->ReturnActualItem() != "")
			{

				temp = (this->FindComponent(this->name_FurnitAddonSelector));
				Component_SelectorAddon *tmpSelectorAddon;
				tmpSelectorAddon = dynamic_cast < Component_SelectorAddon *>(temp);

				vector<bool> vecActiveadds; vecActiveadds = tmpSelectorAddon->GetAddonVec();
				tmpBmp = al_clone_bitmap(this->listptr->GetFurnitureFromList(tmpSelector->ReturnActualItem())->mainBitmap);
				
				al_set_target_bitmap(tmpBmp);
				vector<bool>::iterator it;
				for (it = vecActiveadds.begin(); it != vecActiveadds.end(); ++it)
				{
					if ((*it) == true)
					{
						int i = it - vecActiveadds.begin();
						al_draw_bitmap(this->listptr->GetFurnitureFromList(tmpSelector->ReturnActualItem())->vecAddonBitmap[i], 0, 0, 0);;
					}
				}
				al_set_target_backbuffer(Display_MainWindow::GetDisplay());
			}
		}
	}
	else if (tmpSelector != NULL && tmpSelector->ReturnActualItem() == "Decoration")
	{
		temp = (this->FindComponent(this->name_EquipmentItemSelector));
		Component_Selector *tmpSelector;
		tmpSelector = dynamic_cast < Component_Selector *>(temp);

		if (tmpSelector != NULL)
		{
			if (tmpSelector->ReturnActualItem() != "")
			{
				tmpBmp = al_clone_bitmap(this->listptr->GetDecorationFromList(tmpSelector->ReturnActualItem())->mainBitmap);
			}
		}
	}
}

void Component_ManagerMENU::MENUComponentOperator(string _name, ComponentOperationCode _code)
{
	Component_MainComponent * temp;
	Component_Flasher *tmpEqFlasher;
	temp = NULL;

	if (_code != NothingCpOperation) this->ptrToDsp->ClearActualFocus();

	if(_name == this->name_Equipment)
	{
		switch (_code)
		{
		case eqFlasherClicked:

			this->DeleteComponent(this->name_EquipmentTypeSelector);
			this->DeleteComponent(this->name_FurnitAddonSelector);
			this->DeleteComponent(this->name_EquipmentItemSelector);
			this->DeleteComponent(this->name_CreateButton);
			this->DeleteComponent(this->name_CreateWallButton);
			al_destroy_bitmap(this->tmpBmp);
			this->tmpBmp = NULL;

			temp = (this->FindComponent(this->name_Construction));
			tmpEqFlasher = dynamic_cast < Component_Flasher *>(temp);
			tmpEqFlasher->SetLight(false);

			this->CreateSelector(this->name_EquipmentTypeSelector,
				Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
				Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.17,
				Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
				Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
				this->eqSelectorOptions, this->eqFlasherBordercolor, this->eqBorderSize);

			break;
		case eqFlasherUnclicked:

			temp = (this->FindComponent(this->name_Construction));
			tmpEqFlasher = dynamic_cast < Component_Flasher *>(temp);
			tmpEqFlasher->SetLight(false);

			this->DeleteComponent(this->name_EquipmentTypeSelector);
			this->DeleteComponent(this->name_FurnitAddonSelector);
			this->DeleteComponent(this->name_EquipmentItemSelector);
			this->DeleteComponent(this->name_CreateButton);
			this->DeleteComponent(this->name_CreateWallButton);
			al_destroy_bitmap(this->tmpBmp);
			this->tmpBmp = NULL;
			break;
		}
	}
	else if (_name == this->name_Construction)
	{
		switch (_code)
		{
		case eqFlasherClicked:

			temp = (this->FindComponent(this->name_Equipment));
			tmpEqFlasher = dynamic_cast < Component_Flasher *>(temp);
			tmpEqFlasher->SetLight(false);

			this->DeleteComponent(this->name_EquipmentTypeSelector);
			this->DeleteComponent(this->name_FurnitAddonSelector);
			this->DeleteComponent(this->name_EquipmentItemSelector);
			this->DeleteComponent(this->name_CreateButton);
			this->DeleteComponent(this->name_CreateWallButton);
			al_destroy_bitmap(this->tmpBmp);
			this->tmpBmp = NULL;


			this->CreateConstructionButton();
			break;
		case eqFlasherUnclicked:

			this->DeleteComponent(this->name_EquipmentTypeSelector);
			this->DeleteComponent(this->name_FurnitAddonSelector);
			this->DeleteComponent(this->name_EquipmentItemSelector);
			this->DeleteComponent(this->name_CreateButton);
			this->DeleteComponent(this->name_CreateWallButton);
			al_destroy_bitmap(this->tmpBmp);
			this->tmpBmp = NULL;

			break;
		}
	}
	else if (_name == this->name_EquipmentTypeSelector)
	{
		al_destroy_bitmap(this->tmpBmp);
		this->tmpBmp = NULL;

		switch (_code)
		{
		case selectorClicked:

			temp = (this->FindComponent(this->name_EquipmentTypeSelector));
			Component_Selector *tmpSelector;
			tmpSelector = dynamic_cast < Component_Selector *>(temp);

			if (tmpSelector->ReturnActualItem() == "Furniture")
			{
				this->DeleteComponent(this->name_EquipmentItemSelector);
				this->CreateSelector(this->name_EquipmentItemSelector,
					Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
					Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.23,
					Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
					Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
					this->listptr->vecFurnitureNames, this->eqFlasherBordercolor,this->eqBorderSize);
				this->DeleteComponent(this->name_CreateButton);
			}
			else if (tmpSelector->ReturnActualItem() == "Decoration")
			{
				this->DeleteComponent(this->name_EquipmentItemSelector);
				this->DeleteComponent(this->name_FurnitAddonSelector);
				this->CreateSelector(this->name_EquipmentItemSelector,
					Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
					Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.23,
					Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
					Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
					this->listptr->vecDecorationNames, this->eqFlasherBordercolor, this->eqBorderSize);
				this->DeleteComponent(this->name_CreateButton);
			}
			break;
		}
	}
	else if (_name == this->name_EquipmentItemSelector)
	{
		switch (_code)
		{
		case selectorClicked:

			this->DeleteComponent(this->name_CreateButton);
			this->CreateButtonToCreate();

			temp = (this->FindComponent(this->name_EquipmentTypeSelector));
			Component_Selector *tmpSelector;
			tmpSelector = dynamic_cast < Component_Selector *>(temp);

			if (tmpSelector->ReturnActualItem() == "Furniture")
			{
				temp = (this->FindComponent(this->name_EquipmentItemSelector));
				Component_Selector *tmpSelector;
				tmpSelector = dynamic_cast < Component_Selector *>(temp);

				this->DeleteComponent(this->name_FurnitAddonSelector);
				this->CreateSelectorAddon(this->name_FurnitAddonSelector,
					Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
					Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.29,
					Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
					Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
					this->listptr->ReturnAddonNames(tmpSelector->ReturnActualItem()), this->eqFlasherBordercolor, this->eqBorderSize);
			}
			

			Display_MainWindow::RefreshDisplay();
			this->SetActualBmp();

			break;
		}
	}
	else if (_name == this->name_FurnitAddonSelector)
	{
		Display_MainWindow::RefreshDisplay();
		this->SetActualBmp();
	}
	else if (_name == this->name_CreateButton)
	{
		switch (_code)
		{
		case buttonClicked:
			this->ptrToDsp->CreateNewElement();
			
			break;
		case NothingCpOperation:
			break;
		default:
			break;
		}
	}
	else if (_name == this->name_CreateWallButton)
	{
		switch (_code)
		{
		case eqFlasherClicked:
			this->ptrToDsp->CreateNewElement();
			break;
		case eqFlasherUnclicked:
			this->ptrToDsp->ClearActualFocus();
			this->ConstrWallButtonOff();
			break;
		case NothingCpOperation:
			break;
		default:
			break;
		}
	}

}

void Component_ManagerMENU::SetMenuOn_Decoration(string deconame)
{
	this->DeleteAllComponents();

	Component_Flasher * tmpEq = new Component_Flasher(this->name_Equipment,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqFlasherLightcolor,
		this->eqFlasherBordercolor,
		this->eqBorderSize);
	tmpEq->SetLight(true);
	tmpEq->Set_allowEvent(true);


	Component_Flasher * tmpConstr = new Component_Flasher(this->name_Construction,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.11,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqFlasherLightcolor,
		this->eqFlasherBordercolor,
		this->eqBorderSize);
	tmpConstr->SetLight(false);
	tmpConstr->Set_allowEvent(false);


	Component_Selector * tmpTypeSelector = new Component_Selector(this->name_EquipmentTypeSelector,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.17,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqSelectorOptions, this->eqBorderSize, this->eqFlasherBordercolor);
	tmpTypeSelector->SetActualString("Decoration");


	Component_Selector * tmpItemSelector = new Component_Selector(this->name_EquipmentItemSelector,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.23,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->listptr->vecDecorationNames, this->eqBorderSize, this->eqFlasherBordercolor);

	
	this->vecComponents.push_back(tmpEq);
	this->vecComponents.push_back(tmpConstr);
	this->vecComponents.push_back(tmpTypeSelector);
	this->vecComponents.push_back(tmpItemSelector);

	this->SetMenuOn_Item(deconame);
}

void Component_ManagerMENU::SetMenuOn_Furniture(string furnitname, vector<bool> addons)
{
	this->DeleteAllComponents();

	Component_Flasher * tmpEq = new Component_Flasher(this->name_Equipment,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqFlasherLightcolor,
		this->eqFlasherBordercolor,
		this->eqBorderSize);
	tmpEq->SetLight(true);
	tmpEq->Set_allowEvent(true);


	Component_Flasher * tmpConstr = new Component_Flasher(this->name_Construction,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.11,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqFlasherLightcolor,
		this->eqFlasherBordercolor,
		this->eqBorderSize);
	tmpConstr->SetLight(false);
	tmpConstr->Set_allowEvent(false);


	Component_Selector * tmpTypeSelector = new Component_Selector(this->name_EquipmentTypeSelector,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.17,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->eqSelectorOptions, this->eqBorderSize,this->eqFlasherBordercolor);
	tmpTypeSelector->SetActualString("Furniture");


	Component_Selector * tmpItemSelector = new Component_Selector(this->name_EquipmentItemSelector,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.23,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->listptr->vecFurnitureNames, this->eqBorderSize, this->eqFlasherBordercolor);

	this->vecComponents.push_back(tmpEq);
	this->vecComponents.push_back(tmpConstr);
	this->vecComponents.push_back(tmpTypeSelector);
	this->vecComponents.push_back(tmpItemSelector);


	Component_SelectorAddon * tmpAddonSelector = new Component_SelectorAddon(this->name_FurnitAddonSelector,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.2,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.29,
		Display_MainWindow::getdspWidth()  * Display_MainWindow::menuWidthScale	* 0.6,
		Display_MainWindow::getdspHeight() * Display_MainWindow::menuHeightScale* 0.05,
		this->listptr->ReturnAddonNames(furnitname), this->eqBorderSize, this->eqFlasherBordercolor);
	tmpAddonSelector->SetAddonWithVector(addons);


	this->vecComponents.push_back(tmpAddonSelector);

	this->SetMenuOn_Item(furnitname);



}

void Component_ManagerMENU::SetMenuOn_Item(string _name)
{
	Component_MainComponent * temp;   temp = (this->FindComponent(this->name_EquipmentItemSelector));
	Component_Selector *tmpSelector;  tmpSelector = dynamic_cast < Component_Selector *>(temp);

	tmpSelector->SetActualString(_name);
	this->SetActualBmp();

}

