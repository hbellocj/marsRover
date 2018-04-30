package com.thought.works.mars.rover;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarsRoverTest {

    private static final int XCOMPONENT = 1;
    private static final int YCOMPONENT = 3;
    private static final char HEADING = 'N';

    private static final int SIZE = 5;
    private static final int WRONG_SIZE = 0;

    private static final String RESPONSE = String.valueOf(XCOMPONENT) + " " + String.valueOf(YCOMPONENT) + " " + String.valueOf(HEADING);

    @Test
    public void GC_createBasicObject_getProperties_shouldReturnAllProperties() {
        MarsRover marsRover = MarsRover();
        Assert.assertEquals("Checking X component", XCOMPONENT, marsRover.getX());
        Assert.assertEquals("Checking Y component", YCOMPONENT, marsRover.getY());
        Assert.assertEquals("Checking heading component", HEADING, marsRover.getHeading().toString().charAt(0));
    }

    @Test
    public void GC_isSizeValid_setCorrectSize_returnTrue() {
        assertTrue(MarsRover().isSizeValid(SIZE, SIZE));
    }

    @Test
    public void BC_isSizeValid_setWrongSize_returnFalse() {
        assertFalse(MarsRover().isSizeValid(WRONG_SIZE, SIZE));
        assertFalse(MarsRover().isSizeValid(SIZE, WRONG_SIZE));
        assertFalse(MarsRover().isSizeValid(WRONG_SIZE, WRONG_SIZE));
    }

    @Test
    public void GC_getResponse_correctValues_returnCorrectString() {
        assertEquals("Get expected response", RESPONSE, MarsRover().getResponse());
    }

    @Test
    public void GC_rotateHeading_rotateLeft() {
        MarsRover marsRover = MarsRover();
        marsRover.rotateHeading(true);
        assertEquals(Cardinals.W, marsRover.getHeading());
    }

    @Test
    public void GC_rotateHeading_rotateRight() {
        MarsRover marsRover = MarsRover();
        marsRover.rotateHeading(false);
        assertEquals(Cardinals.E, marsRover.getHeading());
    }

    @Test
    public void GC_moveRover_moveNorth() {
        MarsRover marsRover = MarsRover();
        marsRover.moveRover();
        assertEquals(1, marsRover.getX());
        assertEquals(4, marsRover.getY());
        assertEquals(Cardinals.N, marsRover.getHeading());
    }


    @Test
    public void GC_moveRover_moveEst() {
        MarsRover marsRover = MarsRover();
        marsRover.rotateHeading(false);
        marsRover.moveRover();
        assertEquals(2, marsRover.getX());
        assertEquals(3, marsRover.getY());
        assertEquals(Cardinals.E, marsRover.getHeading());
    }


    @Test
    public void GC_moveRover_moveWest() {
        MarsRover marsRover = MarsRover();
        marsRover.rotateHeading(true);
        marsRover.moveRover();
        assertEquals(0, marsRover.getX());
        assertEquals(3, marsRover.getY());
        assertEquals(Cardinals.W, marsRover.getHeading());
    }

    @Test
    public void GC_moveRover_moveSouth() {
        MarsRover marsRover = new MarsRover(1, 3, 'S');
        marsRover.moveRover();
        assertEquals(1, marsRover.getX());
        assertEquals(2, marsRover.getY());
        assertEquals(Cardinals.S, marsRover.getHeading());
    }

    @Test
    public void BC_isSizeValid_moveOutOfGrid() {
        MarsRover marsRover = MarsRover();
        for (int i = 3; i < 7; i++) {
            marsRover.moveRover();
        }
        assertFalse(marsRover.isSizeValid(SIZE, SIZE));
        assertNotEquals(6, marsRover.getY());
        assertEquals(Cardinals.N, marsRover.getHeading());
    }

    @Test
    public void GC_getter_valid(){
        MarsRover marsRover = new MarsRover(2, 3, 'E');
        assertEquals(2, marsRover.getX());
        assertEquals(3, marsRover.getY());
        assertEquals(Cardinals.E, marsRover.getHeading());
    }

    @Test
    public void GC_setter_valid(){
        MarsRover marsRover = new MarsRover(2, 3, 'W');
        marsRover.setX(1);
        marsRover.setY(2);
        marsRover.setHeading(Cardinals.S);
        marsRover.setInstructions("LMLMLMLMM");
        assertEquals(1, marsRover.getX());
        assertEquals(2, marsRover.getY());
        assertEquals(Cardinals.S, marsRover.getHeading());
        assertEquals("LMLMLMLMM", marsRover.getInstructions());
    }

    @Test
    public void BC_getterAndSetter_wrongHeading(){
        MarsRover marsRover = new MarsRover(2, 3, 'H');
        assertNotEquals(Cardinals.N, marsRover.getHeading());
        assertNotEquals(Cardinals.S, marsRover.getHeading());
        assertNotEquals(Cardinals.E, marsRover.getHeading());
        assertNotEquals(Cardinals.W, marsRover.getHeading());
        assertEquals(null, marsRover.getHeading());
    }


    /**
     * Basic method to create MarsRover object
     *
     * @return new MarsRover with parameters
     */
    private MarsRover MarsRover() {
        return new MarsRover(XCOMPONENT, YCOMPONENT, HEADING);
    }

}