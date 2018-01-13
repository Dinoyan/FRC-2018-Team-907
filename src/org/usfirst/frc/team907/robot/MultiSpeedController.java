package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Talon;

public class MultiSpeedController {

	public Talon rDrive1;
	public Talon rDrive2;
	
	public Talon lDrive1;
	public Talon lDrive2;
	
	public MultiSpeedController() {
		rDrive1 = new Talon(RobotMap.RIGHT_DRIVE1);
		rDrive2 = new Talon(RobotMap.RIGHT_DRIVE2);
		
		lDrive1 = new Talon(RobotMap.LEFT_DRIVE1);
		lDrive2 = new Talon(RobotMap.LEFT_DRIVE2);
		
	}

	public Talon getrDrive1() {
		return rDrive1;
	}

	public Talon getrDrive2() {
		return rDrive2;
	}

	

	public Talon getlDrive1() {
		return lDrive1;
	}

	public Talon getlDrive2() {
		return lDrive2;
	}

	

}
