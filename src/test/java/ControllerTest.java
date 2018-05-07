import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ControllerTest {
    private static final String GRID_SIZE = "5 5";
    private static final String BAD_GRID_SIZE = "1";
    private static final String ROVER_NUMBER = "1";
    private static final String POSITION = "1 2 N";
    private static final String INSTRUCTIONS = "LMLMLMLMM";
    private static final String BAD_INSTRUCTIONS = "HBJ";
    private static final String INSTRUCTIONS_OUT_OF_BOUNDS = "LMMMMM";
    private static final String RESPONSE = "1 3 N";

    private int counter;
    private boolean badInstructions;

    Controller controller = new Controller(new MyBufferedReader(new InputStreamReader(System.in)));

    @Test
    public void GC_executeRovers_goodResponse() throws IOException {
        String response = controller.runRovers();
        Assert.assertEquals(response, RESPONSE);
    }

    @Test
    public void GC_executeRovers_badGridSize() throws IOException {
        counter = 20;
        String response = controller.runRovers();
        Assert.assertEquals(response, RESPONSE);
    }

    @Test
    public void GC_executeRovers_badInstructions() throws IOException {
        badInstructions = true;
        String response = controller.runRovers();
        Assert.assertEquals(response, RESPONSE);
    }

    @Test
    public void BC_executeRovers_exception() throws IOException {
        counter = 10;
        Assert.assertEquals("Error initializing rovers.", controller.runRovers());
    }

    public class MyBufferedReader extends BufferedReader {

        public MyBufferedReader(Reader in) {
            super(in);
        }

        @Override
        public String readLine() throws IOException {
            counter++;
            switch (counter) {
                case 1:
                    return GRID_SIZE;
                case 2:
                    return ROVER_NUMBER;
                case 3:
                    return POSITION;
                case 4:
                    if (badInstructions) {
                        badInstructions = false;
                        counter = 30;
                        return BAD_INSTRUCTIONS;
                    }
                    return INSTRUCTIONS;
                case 11:
                    throw new IOException();
                case 21:
                    counter = 0;
                    return BAD_GRID_SIZE;
                case 31:
                    counter = 3;
                    return INSTRUCTIONS_OUT_OF_BOUNDS;
                default:
                    return null;
            }
        }
    }
}