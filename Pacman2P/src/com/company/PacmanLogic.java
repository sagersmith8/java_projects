package com.company;

import java.util.ArrayDeque;

/**
 * Created by Sage on 4/28/2014.
 */
public class PacmanLogic implements Runnable {
    public  boolean running = false, mouthOpen = true;
    public int currentDirection = 0;
    public ArrayDeque<Integer> move = new ArrayDeque<Integer>();
    public final int LEFT = -1, RIGHT = 1,  UP = 2, DOWN = -2, NONE = 0;
    int x = 20, y = 20;

    public void addMove(int direction){
        move.add(direction);
        if(move.size()>1)
            move.pop();
    }

    public void checkChangeDirection(){
        currentDirection = RIGHT;
    }

    public PacmanLogic(){
        if(currentDirection == RIGHT){
            x++;
        }
    }

    public void run() {
        while (running){

        }
    }
}
