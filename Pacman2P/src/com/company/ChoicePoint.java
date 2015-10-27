package com.company;

/**
 * Created by Sage on 4/26/2014.
 */
public class ChoicePoint {
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    int x = 0;
    int y = 0;

    public ChoicePoint(){}

    public ChoicePoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public ChoicePoint(int x, int y, boolean left, boolean right, boolean up, boolean down){
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }
}
