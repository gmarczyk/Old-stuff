package pl.marczyk.patterns.factory.employees;

/**
 * Abstract class of an employee
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public abstract class Employee {

    private String  professionName;
    private Integer minYearsOfExp;
    
    /* ========================== Simple getters & setters ============================= */
    public String  getProffesionName()                 { return this.professionName;      }
    public Integer getYearsOfExp()                     { return this.minYearsOfExp;       }  
    public void    setProfessionName(String nameToSet) { this.professionName = nameToSet; }
    public void    setYearsOfExp(Integer yearsToSet)   { this.minYearsOfExp = yearsToSet; }
    
    
    public void showProfessionRequirements(){
        System.out.println("Name of profession: " + this.professionName);
        System.out.println("Minimum years of experience: " + this.minYearsOfExp);
    }
    
}
