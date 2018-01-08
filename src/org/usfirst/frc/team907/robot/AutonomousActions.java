package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Talon;

public class AutonomousActions {
	public Talon rDrive1;
	public Talon rDrive2;
	public Talon rDrive3;
	public Talon lDrive1;
	public Talon lDrive2;
	public Talon lDrive3;
	public AHRS navx;
	
	public AutonomousActions(Talon rDrive1, Talon rDrive2, Talon rDrive3, Talon lDrive1, 
			Talon lDrive2, Talon lDrive3, AHRS navx) {
		
		this.rDrive1 = rDrive1;
		this.rDrive2 = rDrive2;
		this.rDrive3 = rDrive3;
		this.lDrive1 = lDrive1;
		this.lDrive2 = lDrive2;
		this.lDrive3 = lDrive3;
		this.navx = navx;
		
	}
	
	public static void driveForward() {
		
	}
	
	public static void turnRight() {
		
	}
	
	public static void turnLeft() {
		
	}

}
