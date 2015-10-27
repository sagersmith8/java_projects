package JavaIITask;
import java.io.Serializable;

/**
 * Created by Sage on 4/9/2014.
 * Employee object
 * I added a compare to method for sorting purposes
 */

public class Employee implements Serializable, Comparable<Employee>{

    private static final long serialVersionUID = 1L;
    private int employeeId;
    private String name;
    private String phone;
    private String email;
    private String department;
    private int supervisorId;

    /**
     * Creates blank employee
     */
    public Employee() {

    }

    /**
     *
     * @param employee Employee to compare this employee with
     * @return int -1 if employee is bigger, 1 if employee is smaller, 0 if they are equal
     */
    @Override
    public int compareTo(Employee employee){
       return this.getDepartment().compareTo(employee.getDepartment());
    }

    /**
     *Paramaters are self explanatory. Constructer takes them sets employees attributes
     * based on the giveen parameters
     * @param employeeId int
     * @param name String
     * @param phone String
     * @param email String
     * @param department String
     * @param supervisorId int
     */
    public Employee(int employeeId, String name, String phone, String email,
                    String department, int supervisorId) {
        super();
        this.employeeId = employeeId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.supervisorId = supervisorId;
    }

    /**
     * This method returns the employee id for the employee
     * @return int
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * This method sets the employee id to inputed integer
     * @param employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * This method returns the employees name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the employees name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * this method returns the employees phone number
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method sets the employees phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This emthod returns the employees email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * this method sets the employees email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method returns the employees departments
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method sets the employees department
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method returns the employees supervisorId
     * @return supervisorId
     */
    public int getSupervisorId() {
        return supervisorId;
    }

    /**
     * This method sets the employees supervisorId
     * @param supervisorId
     */
    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    /**
     * Returns the serial version id
     * @return serialVersionUID
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * To string method overridden
     *
     * @return employee to string
     */
    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name
                + ", phone=" + phone + ", email=" + email + ", department="
                + department + ", supervisorId=" + supervisorId + "]";
    }

}
