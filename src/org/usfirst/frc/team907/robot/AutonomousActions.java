package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutonomousActions {

	public static void driveForward(Drivetrain drivetrain, SensorHandler sensorHandler, double distance) {
		
		Timer.delay(1);

		double angle = sensorHandler.getAhrs().getAngle() + RobotConstant.DRIVE_MAX_ANGLE;

		while (sensorHandler.getRightDistance() < distance) {

			double curr_angle = sensorHandler.getAhrs().getAngle();

			if (curr_angle > angle) {
				// turn it to the left
				drivetrain.moveRobot(RobotConstant.TURNING_SPEED, RobotConstant.TURNING_SPEED);
			} else if (curr_angle < -angle) {
				// turn it to the right
				drivetrain.moveRobot(-RobotConstant.TURNING_SPEED, -RobotConstant.TURNING_SPEED);
			} else {
				// move straight
				drivetrain.moveRobot(-RobotConstant.DRIVING_SPEED, RobotConstant.DRIVING_SPEED);
			}
			
			// stop the robot
			drivetrain.moveRobot(RobotConstant.ZERO_SPEED, RobotConstant.ZERO_SPEED);
		}
	}

	public static void turnRight(Drivetrain drivetrain, SensorHandler sensorHandler, double angle) {

		while (angle > sensorHandler.getAhrs().getAngle()) {
			drivetrain.moveRobot(-RobotConstant.TURNING_SPEED, -RobotConstant.TURNING_SPEED);
		}
		
		// stop the robot
		drivetrain.moveRobot(RobotConstant.ZERO_SPEED, RobotConstant.ZERO_SPEED);
	}
	
	public static void turnLeft(Drivetrain drivetrain, SensorHandler sensorHandler, double angle) {

		while (angle < sensorHandler.getAhrs().getAngle()) {
			drivetrain.moveRobot(RobotConstant.TURNING_SPEED, RobotConstant.TURNING_SPEED);
		}
		
		// stop the robot
		drivetrain.moveRobot(RobotConstant.ZERO_SPEED, RobotConstant.ZERO_SPEED);
	}

	public static void dropCube() {

	}

	public static void liftCube() {

	}

}
