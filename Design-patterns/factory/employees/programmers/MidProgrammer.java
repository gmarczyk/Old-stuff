package pl.marczyk.patterns.factory.employees.programmers;

import pl.marczyk.patterns.factory.employees.Employee;

/**
 * Represents programmer with medium experience in employee abstract class
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class MidProgrammer extends Employee {
    
    public MidProgrammer(){
        this.setProfessionName("Mid programmer");                               // Using abstracts setters
        this.setYearsOfExp(new Integer(5));
    }
    
}
