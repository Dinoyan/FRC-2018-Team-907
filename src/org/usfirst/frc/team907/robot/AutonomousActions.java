package org.usfirst.frc.team907.robot;

public class AutonomousActions {

	public static void driveForward(Drivetrain drivetrain, SensorHandler sensorHandler) {
		System.out.println("Test: Drive Forward Working");
		
		while (sensorHandler.getRightDistance() < 30) {
			if (sensorHandler.getAhrs().getAngle() > 5) {
				drivetrain.moveRobot(-0.1, -0.1);
			} else if (sensorHandler.getAhrs().getAngle() < -5) {
				drivetrain.moveRobot(0.1, 0.1);
			} else {
				drivetrain.moveRobot(0.5, -0.55);
			}
		}
	}

	public static void turn(Drivetrain drivetrain, SensorHandler sensorHandler, double angle) {
		
		if(angle > 0 ) {
			while( angle > sensorHandler.getAhrs().getAngle()) {
				drivetrain.moveRobot(0.1, 0.1);
			}
		}else if(angle < 0) {
			while(angle < sensorHandler.getAhrs().getAngle()) {
				drivetrain.moveRobot(-0.1, -0.1);
			}
		}
		
		// reset after the turing is done
		sensorHandler.driveEncReset();

	}

	public static void dropCube() {

	}

	public static void liftCube() {
		
		
	}

}
