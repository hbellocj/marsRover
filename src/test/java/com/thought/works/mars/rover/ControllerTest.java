package com.thought.works.mars.rover;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class ControllerTest {
    private static final String GRID_SIZE = "5 5";
    private static final String ROVER_NUMBERS = "1";
    private static final String POSITION = "1 2 N";
    private static final String INSTRUCTIONS = "LMLMLMLMM";
    private static final String RESPONSE = "1 3 N";
    @Mock
    private Controller mController;

    private int counter;

    @Test
    public void GC_initRovers_roversRegistered() throws IOException {
        when(mController.getBufferedReader().readLine()).thenAnswer((Answer) invocationOnMock -> {
            counter++;
            switch (counter % 4) {
                case 1:
                    return GRID_SIZE;
                case 2:
                    return ROVER_NUMBERS;
                case 3:
                    return POSITION;
                case 4:
                    return INSTRUCTIONS;
            }
            return null;
        });
        Assert.assertEquals(mController.runRovers(), RESPONSE);

    }


    @Test
    public void runRovers() {

    }
}