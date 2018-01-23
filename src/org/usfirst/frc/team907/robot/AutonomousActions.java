package org.usfirst.frc.team907.robot;

public class AutonomousActions {

	public static void driveForward(Drivetrain drivetrain, SensorHandler sensorHandler) {
		System.out.println("Test: Drive Forward Working");

	}

	public static void turnRight(MultiSpeedController multiSpeedController, Drivetrain drivetrain, SensorHandler sensorHandler) {

	}

	public static void turnLeft(MultiSpeedController multiSpeedController, Drivetrain drivetrain, SensorHandler sensorHandler) {

	}

	public static void defaultAuto(MultiSpeedController multiSpeedController, Drivetrain drivetrain, SensorHandler sensorHandler) {
		
		while(sensorHandler.getRightDistance() < 30) {
			if(sensorHandler.getAhrs().getAngle() > 5) {
				drivetrain.moveRobot(multiSpeedController, -0.1, -0.1);
			}else if(sensorHandler.getAhrs().getAngle() < -5){
				drivetrain.moveRobot(multiSpeedController, 0.1, 0.1);
			}else {
				drivetrain.moveRobot(multiSpeedController, 0.5, -0.55);
			}
		}
	
		drivetrain.moveRobot(multiSpeedController, 0.0, 0.0);
	}

	public static void dropCube() {

	}
	
	public static void liftCube() {
		
	}

}
