package JavaIITask;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sage on 4/9/2014.
 * Creates a JPanel with needed directory information listed
 */
public class EmployeePanel extends JPanel {
    JLabel department = new JLabel();
    JLabel name = new JLabel();
    JLabel phone = new JLabel();
    JLabel email = new JLabel();

    /**
     *
     * @param employee whose information you want displayed
     * * Creates a JPanel with needed directory information listed
     */
    public EmployeePanel(Employee employee){
        this.setLayout(new GridLayout(1,4));
        this.add(department);
        this.add(name);
        this.add(phone);
        this.add(email);
        department.setText(employee.getDepartment());
        name.setText(employee.getName());
        phone.setText(employee.getPhone());
        email.setText(employee.getEmail());
    }
}
