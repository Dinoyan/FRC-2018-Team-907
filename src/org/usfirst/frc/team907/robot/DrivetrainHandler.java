package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;

public class DrivetrainHandler {
	public Talon rDrive1;
	public Talon rDrive2;
	public Talon rDrive3;
	public Talon lDrive1;
	public Talon lDrive2;
	public Talon lDrive3;
	public Joystick driveStick;
	public Ultrasonic leftUltra;
	public Ultrasonic rightUltra;

	public DrivetrainHandler(Talon rDrive1, Talon rDrive2, Talon rDrive3, Talon lDrive1, Talon lDrive2, Talon lDrive3,
			Joystick driveStick, Ultrasonic leftUltra, Ultrasonic rightUltra) {
		this.rDrive1 = rDrive1;
		this.rDrive2 = rDrive2;
		this.rDrive3 = rDrive3;
		this.lDrive1 = lDrive1;
		this.lDrive2 = lDrive2;
		this.lDrive3 = lDrive3;
		this.driveStick = driveStick;
		this.leftUltra = leftUltra;
		this.rightUltra = rightUltra;
	}
	
	public void init() {
		leftUltra.setAutomaticMode(true);
		rightUltra.setAutomaticMode(true);
	}

	public void driveRobot() {

		double driverX = -this.driveStick.getRawAxis(4);
		double driverY = this.driveStick.getRawAxis(1);

		double leftOutput = driverY + driverX;
		double rightOutput = driverX - driverY;

		this.lDrive1.set(leftOutput);
		this.lDrive2.set(leftOutput);
		this.lDrive3.set(leftOutput);

		this.rDrive1.set(rightOutput);
		this.rDrive2.set(rightOutput);
		this.rDrive3.set(rightOutput);

	}
	
	public double leftDistance() {
		double leftUltra_Range = leftUltra.getRangeInches();
		return leftUltra_Range;
	}
	
	public double rightDistance() {
		double rightUltra_Range = rightUltra.getRangeInches();
		return rightUltra_Range;
	}

}
