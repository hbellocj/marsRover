package com.thought.works.mars.rover;

import java.util.Arrays;
import java.util.List;

public class MarsRover {

    private int x;
    private int y;
    private Cardinals heading;
    private String instructions;

    public MarsRover(int x, int y, char heading) {
        this.x = x;
        this.y = y;
        this.heading = Cardinals.convertCharToCardinal(heading);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public Cardinals getHeading() {
        return heading;
    }

    public void setHeading(Cardinals heading) {
        this.heading = heading;
    }

    /**
     * Function to get the final output of the rover
     *
     * @return output
     */
    public String getResponse() {
        return String.format("%d %d %s", x, y, heading);
    }

    public boolean isSizeValid(int xSize, int ySize) {
        if (x > xSize || y > ySize) {
            return false;
        }
        return true;
    }

    /**
     * Function to rotate the mars rover
     *
     * @param left would be true if it have to rotate left
     */
    public void rotateHeading(boolean left) {
        List<Cardinals> cardinals = Arrays.asList(Cardinals.N, Cardinals.E, Cardinals.S, Cardinals.W);
        for (Cardinals cardinal : cardinals) {
            if(heading.equals(cardinal)){
                heading = Cardinals.getRotatedCardinal(cardinal, left);
                break;
            }
        }
    }

    /**
     * Function to move forward one grid point
     */
    public void moveRover() {
        switch (heading) {
            case N:
                y++;
                break;
            case S:
                y--;
                break;
            case E:
                x++;
                break;
            case W:
                x--;
                break;
        }
    }
}
