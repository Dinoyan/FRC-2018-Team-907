package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicHandler {
	
	public void init(Ultrasonic leftUltra, Ultrasonic rightUltra) {
		leftUltra = new Ultrasonic(RobotMap.LEFT_ULTRASONIC, RobotMap.LEFT_ULTRASONIC);
		rightUltra = new Ultrasonic(RobotMap.RIGHT_ULTRASONIC, RobotMap.RIGHT_ULTRASONIC);
		
		leftUltra.setAutomaticMode(true);
		rightUltra.setAutomaticMode(true);
	}
	
	public double getDistance(Ultrasonic ultrasonic) {
		double value = ultrasonic.getRangeInches();
		return value;
	}
	
}
