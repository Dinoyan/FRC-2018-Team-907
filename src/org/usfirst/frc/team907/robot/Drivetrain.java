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
	public UltrasonicHandler ultrasonicHandler;

	public Drivetrain(TalonHandler talonHandler, Joystick driveStick, UltrasonicHandler ultrasonicHandler) {
		this.driveStick = driveStick;
		this.ultrasonicHandler = ultrasonicHandler;
		this.rDrive1 = talonHandler.getrDrive1();
		this.rDrive2 = talonHandler.getrDrive2();
		this.rDrive3 = talonHandler.getrDrive3();
		this.lDrive1 = talonHandler.getlDrive1();
		this.lDrive2 = talonHandler.getlDrive2();
		this.lDrive3 = talonHandler.getlDrive3();
	}
	
	public void init() {
		this.driveStick = new Joystick(RobotMap.DRIVE_STICK);
	}

	public void driveRobot() {

		double driverX = -this.driveStick.getRawAxis(4);
		double driverY = this.driveStick.getRawAxis(1);

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
