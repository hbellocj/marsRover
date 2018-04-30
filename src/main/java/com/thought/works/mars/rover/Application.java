package com.thought.works.mars.rover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Application {

	private static Controller controller;

	public static void main(String[] args) {
		controller = new Controller();
		SpringApplication.run(Application.class, args);
		Logger.getLogger(Controller.class.getName()).info(controller.runRovers());
	}
}
