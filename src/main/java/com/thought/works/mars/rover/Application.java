package com.thought.works.mars.rover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static Controller controller;

	public static void main(String[] args) {
		controller = new Controller();
		SpringApplication.run(Application.class, args);
		controller.runRovers();
	}
}
