#pragma once
#include <string>
#include <vector>

#include "SettingsLoader.h"
#include "SettingsParameter.h"

/**
* @author Grzegorz Marczyk
*/

class Settings {

private:

    std::vector<SettingsParameter> parametersVector;

    /* Singleton */
    Settings()
    {
         SettingsLoader loader;
         loader.loadSettingsTo(parametersVector);
    }

public:

     static Settings& getInstance()
     {
          static Settings instance; // Guaranteed to be destroyed. Instantiated on first use.
          return instance;
     }

     SettingsParameter getParameterByName(std::string paramName)
     {
          std::vector<SettingsParameter>::iterator iter = this->parametersVector.begin();
          std::vector<SettingsParameter>::iterator endIter = this->parametersVector.end();
          for (iter; iter != endIter; iter++)
          {
               if (iter->name == paramName) return *iter;
          }
          return SettingsParameter();
     }
};