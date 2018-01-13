package org.usfirst.frc.team907.robot;

public class AutonomousActions {

	public static void driveForward(Drivetrain drivetrain, SensorHandler sensorHandler) {
		System.out.println("Test: Drive Forward Working");

	}

	public static void turnRight(Drivetrain drivetrain, SensorHandler sensorHandler) {

	}

	public static void turnLeft(Drivetrain drivetrain, SensorHandler sensorHandler) {

	}

	public static void defaultAuto(MultiSpeedController multiSpeedController,SensorHandler sensorHandler) {
		while(sensorHandler.getLeftEnc().getDistance() < 100) {
			multiSpeedController.getlDrive1().set(0.5);
			multiSpeedController.getlDrive2().set(0.5);

			multiSpeedController.getrDrive1().set(-0.5);
			multiSpeedController.getrDrive2().set(-0.5);
			
			if(sensorHandler.getAhrs().getAngle() > 5) {
				multiSpeedController.getlDrive1().set(0.5);
				multiSpeedController.getlDrive2().set(0.5);
			
				multiSpeedController.getrDrive1().set(-0.6);
				multiSpeedController.getrDrive2().set(-0.6);
				
			} else if (sensorHandler.getAhrs().getAngle() > -5) {
				multiSpeedController.getlDrive1().set(0.6);
				multiSpeedController.getlDrive2().set(0.6);

				multiSpeedController.getrDrive1().set(-0.5);
				multiSpeedController.getrDrive2().set(-0.5);
			}
		}
		multiSpeedController.getlDrive1().set(0);
		multiSpeedController.getlDrive2().set(0);

		multiSpeedController.getrDrive1().set(0);
		multiSpeedController.getrDrive2().set(0);
		
	}

	public static void shootCube() {

	}

}
