import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class MarsRoverApplication {

    private static Controller controller;
    private static Logger LOGGER = Logger.getLogger(MarsRoverApplication.class.getName());

    public static void main(String[] args) {
        controller = new Controller(new BufferedReader(new InputStreamReader(System.in)));
        LOGGER.info(controller.runRovers());
    }
}
