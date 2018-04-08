package com.thought.works.mars.rover;

import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    private Logger LOGGER = (Logger) LoggerFactory.getLogger(Controller.class);

    private String response;
    private List<MarsRover> rovers;
    private Cardinals cardinals;
    private int xSize;
    private int ySize;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private void initRovers() {
        try {
            response = bufferedReader.readLine();
            xSize = response.toCharArray()[0];
            ySize = response.toCharArray()[2];
            rovers = new ArrayList<>();
            while ((response = bufferedReader.readLine()) != null) {
                int x = response.toCharArray()[0];
                int y = response.toCharArray()[2];
                char heading = response.toCharArray()[4];
                response = bufferedReader.readLine();
                MarsRover marsRover = new MarsRover(x, y, heading);
                marsRover.setInstructions(response);
                rovers.add(marsRover);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception in read line", e);
        }
    }

    public int executeRover(MarsRover rover) {
        String instructions = rover.getInstructions();
        char[] sequence = instructions.toCharArray();
        for (char c : sequence) {
            switch (c) {
                case 'L':

            }

        }
    }

    private void checkRoverValues(int x, int y, char heading) throws IOException {
        if (x > xSize) {
            LOGGER.info("X value incorrect, please, enter a new one: ");
            x = Integer.parseInt(bufferedReader.readLine());
            checkRoverValues(x, y, heading);
        } else if (y > ySize) {
            LOGGER.info("Y value incorrect, please, enter a new one: ");
            y = Integer.parseInt(bufferedReader.readLine());
            checkRoverValues(x, y, heading);
        } else if (cardinals.isInCardinalsEnum(String.valueOf(heading))) {
            LOGGER.info("Cardinal value incorrect, please, enter a new one (N, S, E, O): ");
            heading = bufferedReader.readLine().toCharArray()[0];
            checkRoverValues(x, y, heading);
        }
    }
}
