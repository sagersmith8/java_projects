package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ColorBlockMaker extends JFrame implements ActionListener{
    JPanel [] jPanels = new JPanel[3];
    JLabel numLabel = new JLabel("Enter number of blocks:");
    JLabel colorLabel = new JLabel("Choose Color");
    HashMap<String, Color> colorHashMap = new HashMap<String, Color>();
    String[] colors = {"Red","Green", "Blue"};
    JComboBox<String> colorBox = new JComboBox<String>(colors);
    Canvas[] canvases;
    JFrame jFrame = new JFrame("Color Blocks");
    JPanel blockPanel = new JPanel();
    Color color;
    String col;
    int colInc;
    JButton createBlocks = new JButton("CreateBlocks");
    JTextField numField = new JTextField();

    public void actionPerformed(ActionEvent e){
        int numBlocks = Integer.parseInt(numField.getText());
        if(numBlocks<1 || numBlocks>255){
            JOptionPane.showInternalMessageDialog(this, "Num Blocks must be between 1-255");
        }

        else{
            this.setVisible(false);
            canvases = new Canvas[numBlocks];
            colInc = 255/numBlocks;
            color = colorHashMap.get(colorBox.getSelectedItem());
            col = colorBox.getSelectedItem().toString();
            colorIncrement(0);
            blockPanel.setLayout(new BoxLayout(blockPanel,BoxLayout.X_AXIS));
            jFrame.add(blockPanel);
            jFrame.setVisible(true);
        }
    }

    void colorIncrement(int colorAmount){
        Canvas canvas = new Canvas();
        canvas.setSize(40,40);
        if(col.equals(colors[0]))
        canvas.setBackground(new Color(255-colorAmount,0,0));
        else if(col.equals(colors[1]))
            canvas.setBackground(new Color(0,255-colorAmount,0));
        else if(col.equals(colors[2]))
            canvas.setBackground(new Color(0,0,255-colorAmount));
        blockPanel.add(canvas);
        colorAmount+=colInc;
        if(colorAmount<255){
            colorIncrement(colorAmount);
        }
    }

    public ColorBlockMaker(){
       this.setLayout(new BorderLayout());
       this.setBounds(0,0,500,400);
       this.setTitle("Collect Info");
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       for(int i = 0; i < jPanels.length; i++){
           jPanels[i] = new JPanel();
       }
       jFrame.setBounds(0,0,1000,900);
       jPanels[0].setLayout(new GridLayout(2, 1));
       jPanels[0].add(numLabel);
       jPanels[0].add(colorLabel);
       jPanels[1].setLayout(new GridLayout(2, 1));
       jPanels[1].add(numField);
       jPanels[1].add(colorBox);
       jPanels[2].setLayout(new GridLayout(1,2));
       jPanels[2].add(jPanels[0]);
       jPanels[2].add(jPanels[1]);
       this.add(jPanels[2], BorderLayout.CENTER);
       this.add(createBlocks, BorderLayout.SOUTH);
       colorHashMap.put(colors[0], Color.red);
       colorHashMap.put(colors[1], Color.green);
       colorHashMap.put(colors[2], Color.blue);
       createBlocks.addActionListener(this);
       this.setVisible(true);
    }

    public static void main(String[] args) {
        new ColorBlockMaker();
    }
}
