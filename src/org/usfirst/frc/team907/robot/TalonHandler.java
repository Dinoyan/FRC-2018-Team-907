package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Talon;

public class TalonHandler {
	
	public Talon rDrive1;
	public Talon rDrive2;
	public Talon rDrive3;
	public Talon lDrive1;
	public Talon lDrive2;
	public Talon lDrive3;
	
	public TalonHandler(Talon rDrive1, Talon rDrive2, Talon rDrive3, Talon lDrive1, Talon lDrive2, Talon lDrive3) {
		this.lDrive1 = lDrive1;
		this.lDrive2 = lDrive2;
		this.lDrive3 = lDrive3;
		this.rDrive1 = rDrive1;
		this.rDrive2 = rDrive2;
		this.rDrive3 = rDrive3;
	}
	
	public void init() {
		rDrive1 = new Talon(RobotMap.RIGHT_DRIVE1);
		rDrive2 = new Talon(RobotMap.RIGHT_DRIVE2);
		rDrive3 = new Talon(RobotMap.RIGHT_DRIVE3);
		lDrive1 = new Talon(RobotMap.LEFT_DRIVE1);
		lDrive2 = new Talon(RobotMap.LEFT_DRIVE2);
		lDrive3 = new Talon(RobotMap.LEFT_DRIVE3); 
	}

	public Talon getrDrive1() {
		return rDrive1;
	}

	public Talon getrDrive2() {
		return rDrive2;
	}

	public Talon getrDrive3() {
		return rDrive3;
	}

	public Talon getlDrive1() {
		return lDrive1;
	}

	public Talon getlDrive2() {
		return lDrive2;
	}

	public Talon getlDrive3() {
		return lDrive3;
	}

}
