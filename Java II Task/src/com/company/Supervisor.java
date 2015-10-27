package com.company;
import java.util.ArrayList;

/**
 * Created by Sage on 4/8/2014.
 */
public class Supervisor extends Employee {
    private ArrayList<Employee> supervisedEmployees = new ArrayList<Employee>();

    public void addEmployee(Employee employee){
        supervisedEmployees.add(employee);
    }

    public ArrayList<Employee> getSupervisedEmployees(){
        return supervisedEmployees;
    }
}
