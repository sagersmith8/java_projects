import javax.swing.*;
import java.awt.*;

/**
 * Created by Sage on 6/14/2014.
 */
public class HappyFathersDay extends JFrame{
    JPanel [][] grid = new JPanel[40][40];
    boolean dadRules = true;

    public HappyFathersDay(){
        this.setLayout(new GridLayout(40,40));
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                grid[i][j] = new JPanel();
                this.add(grid[i][j]);
            }
        }
        this.setLocation((int)(Math.random()*800),(int)(Math.random()*400));
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

       Thread runnable = new Thread() {
            @Override
            public void run() {
                while (dadRules) {
                    int r = (int) (Math.random() * 256), g = (int) (Math.random() * 256), b = (int) (Math.random() * 256);
                    for (int i = 0; i < grid.length; i++) {
                        for (int j = 0; j < grid[0].length; j++) {

                            if(i<10 && i > 2){
                                //H
                                if(j == 3 || j == 6) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==6 && j >3 && j < 6){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //A
                                else if(j == 8 || j == 11) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==3 && j >8 && j < 11||i==6 && j >8 && j < 11){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //P
                                else if(j == 13) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(j == 16 && i < 7) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==3 && j >13 && j < 16||i==6 && j >13 && j < 16){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //P
                                else if(j == 18) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(j == 21 && i < 7) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==3 && j >18 && j < 21||i==6 && j >18 && j < 21){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //Y
                                else if(j == 23&&i<7||j ==25&&i<7) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(j > 23 && j <25 && i > 5) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //else
                                else {
                                    grid[i][j].setBackground(new Color(r, g, b));
                                }
                            }

                            else if(i<20 && i > 12){
                                //F
                                if(j == 3) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==16 && j >3 && j < 6 ||i==13 && j >3 && j < 7 ){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //A
                                else if(j == 8 || j == 11) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==13 && j >8 && j < 11||i==15 && j >8 && j < 11){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //T
                                else if(j == 15||j==14) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==13 && j >12 && j < 17){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }


                                //H
                                else if(j == 18 || j == 21) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==16 && j >18 && j < 21){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //E
                                else if(j == 23) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==16 && j >23 && j < 26 ||i==13 && j >23 && j < 27||i==19 && j >23 && j < 27){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //R
                                else if(j == 28) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(j == 31 && i < 17) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(j >=28 && j <= 31 && i == 13||j >=28 && j <= 31 && i == 16) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i == 17 && j == 29){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i == 18 && j == 30){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i == 19 && j == 31){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //S
                                else if(j == 33 && i <17||j == 36 && i >15) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==16 && j >33 && j < 37 ||i==13 && j >33 && j < 37||i==19 && j >32 && j < 37){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //else
                                else {
                                    grid[i][j].setBackground(new Color(r, g, b));
                                }
                            }

                            else if(i<30 && i > 22){
                                //D
                                if(j == 3) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(j == 6 && i <29 && i >23) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==23 && j >3 && j < 6 ||i==29 && j >3 && j < 6 ){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //A
                                else if(j == 8 || j == 11) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(i==23 && j >8 && j < 11||i==26 && j >8 && j < 11){
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //Y
                                else if(j == 13&&i<27||j ==16&&i<27) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                else if(j > 13 && j <16 && i > 25) {
                                    grid[i][j].setBackground(new Color(255 - r, 255 - g, 255 - b));
                                }

                                //else
                                else {
                                    grid[i][j].setBackground(new Color(r, g, b));
                                }
                            }

                            else {
                                grid[i][j].setBackground(new Color(r, g, b));
                            }
                        }
                    }


                    try {
                        Thread.sleep(1500);
                    } catch (Exception ex) {
                    }
                }
            }
        };

        runnable.start();


    }

    public static void main(String [] args){

        int awesome = Integer.parseInt(JOptionPane.showInputDialog("On a scale from 1 to 5 how good is fathers day this year"));
        for(int i = 10-awesome; i >= 0; i--) {
            new HappyFathersDay();
        }
    }
}
