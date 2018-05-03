import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    protected Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private final static String EXCEPTION_INIT_ROVERS = "Error initializing rovers.";

    private String response;
    private List<MarsRover> rovers;
    private int xSize;
    private int ySize;
    private int roversNumber;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private void initRovers() {
        LOGGER.info("-------------------Application started.--------------------");
        try {
            LOGGER.info("Input grid size:");
            setGridSizeValue();
            LOGGER.info("Enter the number of rovers you want to run:");
            setNumberOfRovers();
            rovers = new ArrayList<MarsRover>();
            for (int i = 0; i < roversNumber; i++) {
                setRoverInformation();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception in read line", e);
        }
    }

    private void setGridSizeValue() throws IOException {
        try {
            response = bufferedReader.readLine();
            xSize = Character.getNumericValue(response.toCharArray()[0]);
            ySize = Character.getNumericValue(response.toCharArray()[2]);
        } catch (NumberFormatException e) {
            LOGGER.info("Exception in the grid size value, introduce a new one:");
            setGridSizeValue();
        } catch (IndexOutOfBoundsException ex) {
            LOGGER.info("Exception in the grid size value, introduce a new one:");
            setGridSizeValue();
        }
    }

    private void setNumberOfRovers() throws IOException {
        try{
            roversNumber = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e) {
            LOGGER.info("Exception setting the number of rovers, introduce a new one:");
            setNumberOfRovers();
        } catch (IndexOutOfBoundsException ex) {
            LOGGER.info("Exception setting the number of rovers, introduce a new one:");
            setNumberOfRovers();
        }
    }

    private void setRoverInformation() throws IOException {
        try {
            LOGGER.info("Introduce rover position:");
            response = bufferedReader.readLine();
            int x = Character.getNumericValue(response.toCharArray()[0]);
            int y = Character.getNumericValue(response.toCharArray()[2]);
            char heading = response.toCharArray()[4];
            LOGGER.info("Introduce the instructions:");
            response = bufferedReader.readLine();
            MarsRover marsRover = new MarsRover(x, y, heading);
            marsRover.setInstructions(response);
            rovers.add(marsRover);
        } catch (NumberFormatException e) {
            LOGGER.info("Exception in the grid size value, introduce a new one:");
            setGridSizeValue();
        } catch (IndexOutOfBoundsException ex) {
            LOGGER.info("Exception in the grid size value, introduce a new one:");
            setGridSizeValue();
        }
    }

    private String executeRover(MarsRover rover) {
        String instructions = rover.getInstructions();
        char[] sequence = instructions.toCharArray();
        for (char c : sequence) {
            switch (c) {
                case 'L':
                    rover.rotateHeading(true);
                    break;
                case 'R':
                    rover.rotateHeading(false);
                    break;
                case 'M':
                    rover.moveRover();
                    break;
                default:
                    int roverNumber = rovers.indexOf(rover) + 1;
                    LOGGER.info("Bad instructions of rover " + roverNumber + ". Please, introduce a new ones: ");
                    try {
                        instructions = bufferedReader.readLine();
                        rover.setInstructions(instructions);
                        executeRover(rover);
                        return rover.getResponse();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Exception in read line", e);
                    }
            }
        }
        if (!rover.isSizeValid(xSize, ySize)) {
            try {
                LOGGER.info("Bad instructions. Rover can not be out of bounds. Introduce new instructions: ");
                instructions = bufferedReader.readLine();
                rover.setInstructions(instructions);
                executeRover(rover);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Exception in read line", e);
            }
        }
        return rover.getResponse();
    }

    public String runRovers() {
        while (true) {
            initRovers();
            for (MarsRover rover : rovers) {
                try {
                    checkRoverValues(rover);
                    return executeRover(rover);
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Exception in read line", e);
                    return EXCEPTION_INIT_ROVERS;
                }
            }
        }
    }

    private void checkRoverValues(MarsRover rover) throws IOException {
        if (rover.getX() > xSize) {
            LOGGER.info("X value incorrect, please, enter a new one: ");
            rover.setX(Integer.parseInt(bufferedReader.readLine()));
            checkRoverValues(rover);
        } else if (rover.getY() > ySize) {
            LOGGER.info("Y value incorrect, please, enter a new one: ");
            rover.setY(Integer.parseInt(bufferedReader.readLine()));
            checkRoverValues(rover);
        }
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}
