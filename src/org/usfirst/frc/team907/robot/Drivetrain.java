package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	
	public Talon rDrive1;
	public Talon rDrive2;
	public Talon rDrive3;
	public Talon lDrive1;
	public Talon lDrive2;
	public Talon lDrive3;
	public Joystick driveStick;
	
	public JoystickHandler joystickHandler;
	public MultiSpeedController multiSpeedController;
	
	public Drivetrain (JoystickHandler joystickHandler, MultiSpeedController multiSpeedController) {
		this.joystickHandler = joystickHandler;
		this.multiSpeedController = multiSpeedController;
		
		storeDriveSpeedControllers();
	}
	
	public void storeDriveSpeedControllers() {
		lDrive1 = multiSpeedController.getlDrive1();
		lDrive2 = multiSpeedController.getlDrive2();
		lDrive3 = multiSpeedController.getlDrive3();
		
		rDrive1 = multiSpeedController.getrDrive1();
		rDrive2 = multiSpeedController.getrDrive2();
		rDrive3 = multiSpeedController.getrDrive3();
		
		driveStick = joystickHandler.getDriveStick();
	}
	
	public void driveRobot() {

		double driverX = -driveStick.getRawAxis(4);
		double driverY = driveStick.getRawAxis(1);

		double leftOutput = driverY + driverX;
		double rightOutput = driverX - driverY;

		lDrive1.set(leftOutput);
		lDrive2.set(leftOutput);
		lDrive3.set(leftOutput);

		rDrive1.set(rightOutput);
		rDrive2.set(rightOutput);
		rDrive3.set(rightOutput);
	}

}
