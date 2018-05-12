package pl.marczyk.patterns.factory;

import pl.marczyk.patterns.factory.employees.Employee;
import pl.marczyk.patterns.factory.employees.programmers.*;


/**
 * Concrete implementation of software design patters called Abstract factory. In this case is implementation 
 * of factory that creates employees in family of PROGRAMMERS - concrete classes of employee abstract class.
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class ProgrammersFactory {

    public Employee createEmployee(String professionName) {  
        
        Employee employeeToReturn = null;
        
        switch(professionName){
            case "JUNIOR PROGRAMMER":
                employeeToReturn = new JuniorProgrammer();
                break;
            case "MID PROGRAMMER":
                employeeToReturn = new MidProgrammer();
                break;
            case "SENIOR PROGRAMMER":
                employeeToReturn = new SeniorProgrammer();
                break;
        }
        
        return employeeToReturn;
    }
    
}
