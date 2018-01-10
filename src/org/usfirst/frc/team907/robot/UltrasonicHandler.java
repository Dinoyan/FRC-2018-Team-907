package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicHandler {
	
	public Ultrasonic leftUltra;
	public Ultrasonic rightUltra;
	
	public UltrasonicHandler(Ultrasonic leftUltra, Ultrasonic rightUltra) {
		this.leftUltra = leftUltra;
		this.rightUltra = rightUltra;
	}
	
	public void init() {
		leftUltra = new Ultrasonic(RobotMap.LEFT_ULTRASONIC, RobotMap.LEFT_ULTRASONIC);
		rightUltra = new Ultrasonic(RobotMap.RIGHT_ULTRASONIC, RobotMap.RIGHT_ULTRASONIC);
		
		leftUltra.setAutomaticMode(true);
		rightUltra.setAutomaticMode(true);
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
