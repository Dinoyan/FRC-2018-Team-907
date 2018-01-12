package org.usfirst.frc.team907.robot;

public class Drivetrain {

	public void driveRobot(JoystickHandler joystickHandler, MultiSpeedController multiSpeedController) {

		double driverX = -joystickHandler.getDriveStick().getRawAxis(4);
		double driverY = joystickHandler.getDriveStick().getRawAxis(1);

		double leftOutput = driverY + driverX;
		double rightOutput = driverX - driverY;

		multiSpeedController.getlDrive1().set(leftOutput);
		multiSpeedController.getlDrive2().set(leftOutput);
		multiSpeedController.getlDrive3().set(leftOutput);

		multiSpeedController.getrDrive1().set(rightOutput);
		multiSpeedController.getrDrive2().set(rightOutput);
		multiSpeedController.getrDrive3().set(rightOutput);
	}
	
	public void moveRobot(MultiSpeedController multiSpeedController, Double leftOutput, Double rightOutput) {
		
		multiSpeedController.getlDrive1().set(leftOutput);
		multiSpeedController.getlDrive2().set(leftOutput);
		multiSpeedController.getlDrive3().set(leftOutput);

		multiSpeedController.getrDrive1().set(rightOutput);
		multiSpeedController.getrDrive2().set(rightOutput);
		multiSpeedController.getrDrive3().set(rightOutput);
		
	}

}
