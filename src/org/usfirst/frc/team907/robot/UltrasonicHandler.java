package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicHandler {
	
	private Ultrasonic leftUltra;
	private Ultrasonic rightUltra;

	public void init() {
		leftUltra = new Ultrasonic(RobotMap.LEFT_ULTRASONIC, RobotMap.LEFT_ULTRASONIC);
		rightUltra = new Ultrasonic(RobotMap.RIGHT_ULTRASONIC, RobotMap.RIGHT_ULTRASONIC);
		
		leftUltra.setAutomaticMode(true);
		rightUltra.setAutomaticMode(true);
	}
	
	public Ultrasonic getLeftUltra() {
		return leftUltra;
	}

	public Ultrasonic getRightUltra() {
		return rightUltra;
	}

	public double getLeftDistance() {
		double value = leftUltra.getRangeInches();
		return value;
	}
	
	public double getRightDistance() {
		double value = rightUltra.getRangeInches();
		return value;
	}
	
}
