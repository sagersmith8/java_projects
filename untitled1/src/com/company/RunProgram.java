package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sage on 6/4/2014.
 */
public class RunProgram extends JFrame implements ActionListener{
    JPanel mainPanel = new JPanel();
    JButton runButton = new JButton("Run ColorBlockMaker");

    public RunProgram(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Program Execute");
        this.setBounds(0, 0, 500, 300);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(runButton);
        runButton.addActionListener(this);
        mainPanel.add(Box.createHorizontalGlue());
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        try {
           Runtime.getRuntime().exec("java ColorBlockMaker");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        this.dispose();
    }

    public static void main(String[]args){
        new RunProgram();
    }
}
