package com.thought.works.mars.rover;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.when;

public class ControllerTest {
    private static final String GRID_SIZE = "5 5";
    private static final String ROVER_NUMBERS = "1";
    private static final String POSITION = "1 2 N";
    private static final String INSTRUCTIONS = "LMLMLMLMM";
    private static final String RESPONSE = "1 3 N";

    @Mock
    private Controller mController = Mockito.mock(Controller.class);

    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

    @Test
    public void GC_executeRovers_goodResponse() throws IOException {
        mController = new Controller();
        when(bufferedReader.readLine()).thenReturn(GRID_SIZE, ROVER_NUMBERS, POSITION, INSTRUCTIONS);

        Assert.assertEquals(mController.runRovers(), RESPONSE);
    }

    @Test
    public void BC_executeRovers_exception() throws IOException {
        mController = new Controller();
        when(bufferedReader.readLine()).thenThrow(new IOException());
        Assert.assertEquals("Error initializing rovers.", mController.runRovers());
    }
}