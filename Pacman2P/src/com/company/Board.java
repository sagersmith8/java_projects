package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Sage on 4/26/2014.
 */
public class Board extends JPanel {
    BufferedImage bufferedImage = new BufferedImage(1000,665, BufferedImage.TYPE_3BYTE_BGR);
    int counter = 0;
    public Board(){
        try {
            bufferedImage = ImageIO.read(new File("Board.jpg"));
        }
        catch (Exception ex){
           ex.printStackTrace();
        }

        this.setPreferredSize(new Dimension(bufferedImage.getWidth(),bufferedImage.getHeight()));
    }
    public void paintComponent(Graphics g){
        g.drawImage(bufferedImage,0,0,null);
        //Pacman
        g.setColor(Color.yellow);
        g.fillArc(50, 50, 30, 30, 30, 300);
        g.fillArc(100, 50, 30, 30, 0, 360);
        //Circle
        g.setColor(Color.white);
        g.fillOval(50, 100, 10, 10);
        g.fillOval(95, 95, 20, 20);
    }
}
