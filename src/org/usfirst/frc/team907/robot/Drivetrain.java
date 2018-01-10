package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	
	public void driveRobot(Joystick driveStick, Talon rDrive1, Talon rDrive2, Talon rDrive3, Talon lDrive1, Talon lDrive2, Talon lDrive3) {

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
