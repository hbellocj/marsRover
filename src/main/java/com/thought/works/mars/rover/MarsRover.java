package com.thought.works.mars.rover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarsRover {

    private int x;
    private int y;
    private char heading;
    private String instructions;

    public MarsRover(int x, int y, char heading){
        this.x=x;
        this.y=y;
        this.heading = heading;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getHeading() {
        return heading;
    }

    public void setHeading(char heading) {
        this.heading = heading;
    }

    public void rotateHeading(boolean left){
        int i = 0;
        List<Cardinals> cardinals = Arrays.asList(Cardinals.N, Cardinals.S, Cardinals.E, Cardinals.O);
        for(Cardinals cardinal : cardinals){
            heading = cardinal.equals(cardinals.get(i)) ? heading
        }
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
