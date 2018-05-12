package pl.marczyk.patterns.factory.employees.programmers;

import pl.marczyk.patterns.factory.employees.Employee;

/**
 * Represents programmer with little experience in employee abstract class
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class JuniorProgrammer extends Employee {
  
    public JuniorProgrammer(){
        this.setProfessionName("Junior programmer");                            // Using abstracts setters
        this.setYearsOfExp(new Integer(1));
    }
    
}
