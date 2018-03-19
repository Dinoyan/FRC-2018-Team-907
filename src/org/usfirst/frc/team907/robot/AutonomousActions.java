package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutonomousActions {

	public static void driveForward(Drivetrain drivetrain, SensorHandler sensorHandler, double distance) {

		double angle1 = sensorHandler.getCurrentAngle() + RobotConstant.DRIVE_MAX_ANGLE;
		double angle2 = sensorHandler.getCurrentAngle() - RobotConstant.DRIVE_MAX_ANGLE;
		
		while (sensorHandler.getRightDistance() < distance) {

			double curr_angle = sensorHandler.getCurrentAngle();
			if (curr_angle > angle1) {
				// turn it to the left
				drivetrain.moveRobot(-RobotConstant.DRIVE_TURNING_SPEED2, -RobotConstant.DRIVE_TURNING_SPEED2);
			} else if (curr_angle < angle2) {
				// turn it to the right
				drivetrain.moveRobot(RobotConstant.DRIVE_TURNING_SPEED2, RobotConstant.DRIVE_TURNING_SPEED2);
			} else {
				// move straight
				 
				drivetrain.moveRobot(RobotConstant.DRIVE_DRIVING_SPEED, -RobotConstant.DRIVE_DRIVING_SPEED);
			}
		}
		// stop the robot
		drivetrain.moveRobot(RobotConstant.DRIVE_ZERO_SPEED, RobotConstant.DRIVE_ZERO_SPEED);
	}

	public static void turnRight(Drivetrain drivetrain, SensorHandler sensorHandler, double angle) {

		while (angle > sensorHandler.getCurrentAngle()) {
			drivetrain.moveRobot(RobotConstant.DRIVE_TURNING_SPEED, RobotConstant.DRIVE_TURNING_SPEED);
		}

		// stop the robot
		drivetrain.moveRobot(RobotConstant.DRIVE_ZERO_SPEED, RobotConstant.DRIVE_ZERO_SPEED);
	}

	public static void turnLeft(Drivetrain drivetrain, SensorHandler sensorHandler, double angle) {

		while (angle < sensorHandler.getAhrs().getAngle()) {
			drivetrain.moveRobot(-RobotConstant.DRIVE_TURNING_SPEED, -RobotConstant.DRIVE_TURNING_SPEED);
		}

		// stop the robot
		drivetrain.moveRobot(RobotConstant.DRIVE_ZERO_SPEED, RobotConstant.DRIVE_ZERO_SPEED);
	}

	public static void dropCube(Intake intake) {
		intake.vomitCube();
	}

	public static void liftCubeSwitch(Elevator elev) {
		elev.switchPosition();
		

	}
	
	public static void liftCubeScale(Elevator elev) {
		elev.scalePosition();
	}
	
	public static void dropIntake(Intake intake) {
		intake.dropIntake();		
	}
	
	public static void JustMove(Drivetrain drive, Timer timer) {
		timer.start();
		while (timer.get() < 2) {
			drive.autoMove();
		}
		drive.moveRobot(0.0, 0.0);
	}
	

}
