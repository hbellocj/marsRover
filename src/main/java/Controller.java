import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    protected Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private final static String EXCEPTION_INIT_ROVERS = "Error initializing rovers.";

    private String response;
    private String position;
    private List<MarsRover> rovers;
    private int xSize;
    private int ySize;
    private int roversNumber;
    private BufferedReader bufferedReader;

    public Controller(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    private void initRovers() throws IOException {
        LOGGER.info("-------------------Application started.--------------------");
        LOGGER.info("Input grid size:");
        setGridSizeValue();
        LOGGER.info("Enter the number of rovers you want to run:");
        setNumberOfRovers();
        rovers = new ArrayList<MarsRover>();
        for (int i = 0; i < roversNumber; i++) {
            setRoverInformation();
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
        try {
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
            position = bufferedReader.readLine();
            int x = Character.getNumericValue(position.toCharArray()[0]);
            int y = Character.getNumericValue(position.toCharArray()[2]);
            char heading = position.toCharArray()[4];
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

    private String executeRover(MarsRover rover) throws IOException {
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
                    instructions = bufferedReader.readLine();
                    rover.setInstructions(instructions);
                    executeRover(rover);
                    return rover.getResponse();
            }
        }
        if (!rover.isSizeValid(xSize, ySize)) {
            LOGGER.info("Bad instructions. Rover can not be out of bounds.");
            setRoverValues(rover);
            executeRover(rover);
        }
        return rover.getResponse();
    }

    private void setRoverValues(MarsRover rover) throws IOException {
        rover.setX(Character.getNumericValue(position.toCharArray()[0]));
        rover.setY(Character.getNumericValue(position.toCharArray()[2]));
        rover.setHeading(Cardinals.convertCharToCardinal(position.toCharArray()[4]));
        LOGGER.info("Introduce the instructions:");
        response = bufferedReader.readLine();
        rover.setInstructions(response);
    }

    public String runRovers() {
        try {
            while (true) {
                initRovers();
                for (MarsRover rover : rovers) {
                    checkRoverValues(rover);
                    return executeRover(rover);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception in read line", e);
            return EXCEPTION_INIT_ROVERS;
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
}
