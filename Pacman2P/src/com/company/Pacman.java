package com.company;

import javax.swing.*;


public class Pacman extends JFrame{
    Board board = new Board();

    public Pacman(){
        this.setTitle("Pacman");
        this.add(board);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }



    public static void main(String[] args) {
	    new Pacman();
    }
}
