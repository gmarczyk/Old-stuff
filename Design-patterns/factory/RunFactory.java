package pl.marczyk.executables;

import java.util.Scanner;
import pl.marczyk.patterns.factory.ProgrammersFactory;
import pl.marczyk.patterns.factory.employees.Employee;

/**
 * Shows use of prepared design pattern : FACTORY
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class RunFactory {
    
    
    public static void main(String[] args){

        ProgrammersFactory programmersFactory = new ProgrammersFactory();
        
        System.out.println("Write one of the specific positions:");
        System.out.println("Junior programmer");
        System.out.println("Mid programmer");
        System.out.println("Senior programmer");
        
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNext()) {
            Employee programmer = programmersFactory.createEmployee(scanner.nextLine().toUpperCase());
            if(programmer != null){
                programmer.showProfessionRequirements();
            }
            else {
                System.out.println("Next time follow instructions...");
            }
        }


    }
}
