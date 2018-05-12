#pragma once
#include <string>

/**
* @author Grzegorz Marczyk
*/

class SettingsParameter {

public:
     std::string name = "";
     std::string value = "";

     SettingsParameter()
     {}

     SettingsParameter(std::string name, std::string value) : name(name), value(value)
     {}
};