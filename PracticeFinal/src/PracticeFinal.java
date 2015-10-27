import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by Sage on 6/2/2014.
 */
public class PracticeFinal extends JFrame implements ActionListener {
    JPanel centerPanel = new JPanel(new GridLayout(2,1));
    JPanel boxPanel = new JPanel();
    JPanel gridPanel = new JPanel(new GridLayout(1,2));
    JButton switchButton = new JButton("Switch");
    JLabel boxLabel = new JLabel("Box Layout");
    JLabel gridLabel = new JLabel("Grid Layout");
    JComboBox<String> boxColorBox = new JComboBox<String>();
    JComboBox<String> gridColorBox = new JComboBox<String>();
    String [] colorStrings = {"red", "green", "blue", "black", "white", "magenta"};
    Color [] colors = {Color.red, Color.green, Color.blue, Color.black, Color.white, Color.magenta};
    HashMap<String, Color> colorHashMap = new HashMap<String, Color>();

    public PracticeFinal(){
        this.setTitle("Practice Final");
        this.setBounds(0,0,500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(int i = 0; i < colors.length; i++){
            colorHashMap.put(colorStrings[i],colors[i]);
            boxColorBox.addItem(colorStrings[i]);
            gridColorBox.addItem(colorStrings[i]);
        }

        switchButton.addActionListener(this);
        boxPanel.add(boxLabel);
        boxPanel.add(Box.createHorizontalGlue());
        gridPanel.add(gridLabel);
        this.setLayout(new BorderLayout());
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));
        gridPanel.add(gridColorBox);
        boxPanel.add(boxColorBox);
        centerPanel.add(gridPanel);
        centerPanel.add(boxPanel);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(switchButton, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(switchButton==e.getSource()){
            Color boxColor = colorHashMap.get(boxColorBox.getSelectedItem());
            Color gridColor = colorHashMap.get(gridColorBox.getSelectedItem());
            boxPanel.setBackground(boxColor);
            gridPanel.setBackground(gridColor);
            boxLabel.setForeground(gridColor);
            gridLabel.setForeground(boxColor);
        }
    }

    public static void main(String [] args){
        new PracticeFinal();
    }
}
