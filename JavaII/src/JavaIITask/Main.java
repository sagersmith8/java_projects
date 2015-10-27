package JavaIITask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Sage
 * This is the driver class for Java Task II
 * This class deserializes the employees
 * This class organizes the employees in an easy to read directory
 * This class provides capability to search employee by id
 * This class finds and prints out the longest managment chain
 */

public class Main extends JFrame implements ActionListener{
    /*
        Declare needed variable
     */

    ArrayList<Employee> employeeArrayList = new ArrayList<Employee>();
    JPanel titles = new JPanel(new GridLayout(1,4));
    JScrollPane jScrollPane = new JScrollPane();
    JTabbedPane jTabbedPane = new JTabbedPane();
    JPanel container = new JPanel();
    JLabel department = new JLabel("DEPARTMENT");
    JLabel name = new JLabel("NAME");
    JLabel phone = new JLabel("PHONE");
    JLabel email = new JLabel("EMAIL");
    JPanel jPanelSearch = new JPanel(new GridLayout(3,1));
    JPanel jPanelSearchEmployee = new JPanel(new GridLayout(1,2));
    JLabel jLabelSearchEmployee = new JLabel("Search Employee ID: ");
    JTextField jTextFieldSearchEmployee = new JTextField(20);
    JTextArea jTextAreaSearchResults= new JTextArea();
    JTextArea jTextAreaLongestManagemnetChain= new JTextArea();
    HashMap<String, Color> hashMap = new HashMap<String,Color>();
    int longestChain = 0, longestChainIndex = 0;

    /**
     * Constructor:
     *      organizes employees in nice gui
     *      calls methods to sort and find employees and longest management chain
     */
    public Main(){
        jTextFieldSearchEmployee.addActionListener(this);
        jPanelSearchEmployee.add(jLabelSearchEmployee);
        jPanelSearchEmployee.add(jTextFieldSearchEmployee);
        jPanelSearch.add(jPanelSearchEmployee);
        jPanelSearch.add(new JPanel());
        jPanelSearch.add(jTextAreaSearchResults);
        this.setLayout(new GridLayout(2,1));
        readEmployees();

        /*
            Goes through every employee and finds the longest management chain
         */
        for(int i = 60; i > 1; i-- ){
            int length = findLongestManagementChain(i, 0);

            if(longestChain< length){
                longestChainIndex = i;
                longestChain = length;
            }
        }

        jTextAreaLongestManagemnetChain.setText("The longest management chain is " + longestChain + "\n \n" + findSupervisors(longestChainIndex, new String()));
        assignColors();
        new Heapsort(employeeArrayList);
        titles.add(department);
        titles.add(name);
        titles.add(phone);
        titles.add(email);
        container.setLayout(new GridLayout(employeeArrayList.size()+1,1));
        container.add(titles);

        /*
            Color codes the directory
         */
        for(int i = 0; i < employeeArrayList.size(); i++){
            EmployeePanel employeePanel = new EmployeePanel(employeeArrayList.get(i));
            employeePanel.setBackground(hashMap.get(employeeArrayList.get(i).getDepartment()));
            container.add(employeePanel);
        }

        jScrollPane.setViewportView(container);
        this.add(jScrollPane);
        jTabbedPane.addTab("Search Employee", jPanelSearch);
        jTabbedPane.addTab("Longest Management Chain", jTextAreaLongestManagemnetChain);
        this.add(jTabbedPane);
        this.setLocation(0, 0);
        this.setSize(800,500);
        this.setTitle("Directory");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**
        @param e- ActionEvent default for all ActionListener
        this method listens for user input to find employee and prints the result
     */
    public void actionPerformed(ActionEvent e){

        /*
            checks to see if event was fired by jTextFieldSearchEmployee
         */
        if(e.getSource()== jTextFieldSearchEmployee){
            int id = Integer.parseInt(jTextFieldSearchEmployee.getText());
            String str = findSupervisors(id, new String());
            jTextAreaSearchResults.setText(str);
        }
    }

   /**
        @param id- employee with ID that user is searching for
        goes through all employees and returns the employee with the specified id
    */
    public Employee searchEmployee(int id){
        for(Employee employee : employeeArrayList){
            if(employee.getEmployeeId()==id){
                return employee;
            }
        }

        return null;
    }


    /**
     *
     * @param id - employeeID that user would like to find the manages for
     * @param managers- String of already found managers
     * @return String of original employee and all managers
     *
     * This method recursively locates all managers of an employee with specified employee ID
     */
    public String findSupervisors(int id, String managers){
        for(Employee employee : employeeArrayList) {
            if(employee.getEmployeeId() == searchEmployee(id).getSupervisorId())
            {
                return findSupervisors(employee.getEmployeeId(), managers + searchEmployee(id).getName()+" is managed by: " + employee.getName() + "\n" );
            }
        }

        return managers;
    }

    /**
     *
     * @param id- current employee id
     * @param size - current length of list
     * @return int longest management chain length
     *
     * This method recursively locates the length of management chain starting with specified employee id
     */
    public int findLongestManagementChain(int id,int size){
        for(Employee employee : employeeArrayList) {
            if(employee.getEmployeeId() == searchEmployee(id).getSupervisorId())
            {
                size++;
                return findLongestManagementChain(employee.getEmployeeId(), size);
            }
        }

        return size;
    }

    /**
     * This method goes through all employees and assigns colors to all of them
     */
    public void assignColors(){
        for(Employee employee : employeeArrayList) {
            if (!hashMap.containsKey(employee.getDepartment())) {
                hashMap.put(employee.getDepartment(), randomColor());
            }
        }
    }

    /**
     * This method generates new random colors, all colors have to be not black
     * @return Color generated random color.
     */
    public Color randomColor(){
       return new Color((int)(Math.random()*200)+55,(int)(Math.random()*200)+55,(int)(Math.random()*200)+55);
    }

    /**
     * This method deserializes all employee from OrnareEmployees.xml
     */
    public void readEmployees(){
        try {
            XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream("OrnareEmployees.xml"));
            int length = (Integer)xmlDecoder.readObject();

            /*
                adds all employees to list
             */
            for(int i = 0; i < length; i++){
                employeeArrayList.add((Employee) xmlDecoder.readObject());
            }

            xmlDecoder.close();
        }

        catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
