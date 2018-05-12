#pragma once
#include <string>
#include <fstream>
#include <iostream>
#include "AppConfig.h"
#include <vector>

/**
* @author Grzegorz Marczyk
*/

class SettingsLoader {

private:
     std::fstream file;
     std::string settingsFilePath;

public:

     SettingsLoader()
     {
          this->settingsFilePath = appConfig::SettingsFilePath;
     }

     SettingsLoader(std::string settingsFilePath) : settingsFilePath(settingsFilePath)
     {}

     bool loadSettingsTo(std::vector<SettingsParameter> &vectorToLoad)
     {
          this->file.open(this->settingsFilePath, std::ios::in);

          if (!(file.good()) || !(file.is_open()))
               return false;

          while (!(file.eof()) && file.good())
          {
               std::string * paramWords;
               try
               {
                    paramWords = this->loadSingleParamWords();
                    if (this->areParamWordsCorrect(paramWords))
                    {
                         SettingsParameter *singleParam = new SettingsParameter();
                         (*singleParam).name = paramWords[0];
                         (*singleParam).value = this->removeQuotationMarks(&paramWords[2]);
                         vectorToLoad.push_back(*singleParam);
                    }
               }
               catch(...)
               {
                    file.close();
                    return false;
               }
          }

          file.close();
          return true;
     }

private:

     std::string* loadSingleParamWords()
     {
          std::string name, equalitysign, value;
          std::string * returnArray = new std::string[3];

          this->file >> name;
          this->file >> equalitysign;
          this->file >> value;

          returnArray[0] = name;
          returnArray[1] = equalitysign;
          returnArray[2] = value;

          return returnArray;
     }

     bool areParamWordsCorrect(std::string *param)
     {
          if(param[1] != "=")
               return false;
          
          std::string value = param[2];
          if (value.at(0) != '"' || value.at(value.length() - 1) != '"')
               return false;

          return true;
     }

     std::string removeQuotationMarks(std::string *param)
     {
          return (*param).substr(1, (*param).size() - 2);
     }
};