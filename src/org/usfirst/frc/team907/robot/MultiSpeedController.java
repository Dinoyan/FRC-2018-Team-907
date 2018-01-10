package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Talon;

public class MultiSpeedController {
	
	public void init(Talon rDrive1, Talon rDrive2, Talon rDrive3, Talon lDrive1, Talon lDrive2, Talon lDrive3) {
		rDrive1 = new Talon(RobotMap.RIGHT_DRIVE1);
		rDrive2 = new Talon(RobotMap.RIGHT_DRIVE2);
		rDrive3 = new Talon(RobotMap.RIGHT_DRIVE3);
		lDrive1 = new Talon(RobotMap.LEFT_DRIVE1);
		lDrive2 = new Talon(RobotMap.LEFT_DRIVE2);
		lDrive3 = new Talon(RobotMap.LEFT_DRIVE3); 
	}
}
