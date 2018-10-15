package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Drivetrain {

	private Talon rDrive1;
	private Talon rDrive2;

	private Talon lDrive1;
	private Talon lDrive2;
	
	private JoystickHandler joystickHandler;
	
	double left, right;
	double zero = RobotConstant.DRIVE_ZERO_SPEED;
	
	public Drivetrain(JoystickHandler joystickHandler) {
		rDrive1 = new Talon(RobotMap.RIGHT_DRIVE1);
		rDrive2 = new Talon(RobotMap.RIGHT_DRIVE2);

		lDrive1 = new Talon(RobotMap.LEFT_DRIVE1);
		lDrive2 = new Talon(RobotMap.LEFT_DRIVE2);
		
		this.joystickHandler = joystickHandler;
	}

	public void driveRobot() {
		// TANK DRIVING IMPLEMENTATION
		 double right = joystickHandler.getDriveStick().getRawAxis(5);
		 double left = -joystickHandler.getDriveStick().getRawAxis(1);
		 
		 // moves the robot
		 moveRobot(left, right);
		
		 
	}

	public void moveRobot(Double left, Double right) {
		// sets the speed to power the drive motors
		lDrive1.set(left);
		lDrive2.set(left);

		rDrive1.set(right);
		rDrive2.set(right);

	}
	
	public void autoMove() {
		moveRobot(0.5,-0.5);
	}

	/*
	public double skim(double v) {
		if (v > 1.0) {
			return -((v - 1.0) * RobotConstant.TURNING_GAIN);
		} else if (v < -1.0) {
			return -((v + 1.0) * RobotConstant.TURNING_GAIN);
		}
		return 0;
	}*/

}
