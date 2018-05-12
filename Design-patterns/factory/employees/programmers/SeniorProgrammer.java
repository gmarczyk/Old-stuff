package pl.marczyk.patterns.factory.employees.programmers;

import pl.marczyk.patterns.factory.employees.Employee;

/**
 * Represents programmer with medium experience in employee abstract class
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class SeniorProgrammer extends Employee {
    
    public SeniorProgrammer(){
        this.setProfessionName("Senior programmer");                            // Using abstracts setters
        this.setYearsOfExp(new Integer(10));
    }
    
}
