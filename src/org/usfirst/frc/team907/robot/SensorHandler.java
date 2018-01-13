package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class SensorHandler {
	
	private Encoder leftEnc;
	private Encoder rightEnc;
	private AHRS ahrs;
	private AnalogInput leftUltra;
	private AnalogInput rightUltra;
	
	
	public SensorHandler() {
		leftEnc = new Encoder(RobotMap.LEFT_ENC_ONE, RobotMap.LEFT_ENC_TWO, false, Encoder.EncodingType.k4X);
		rightEnc = new Encoder(RobotMap.RIGHT_ENC_ONE, RobotMap.RIGHT_ENC_TWO, false, Encoder.EncodingType.k4X);
		ahrs = new AHRS(SerialPort.Port.kMXP);
		leftUltra = new AnalogInput(RobotMap.LEFT_ULTRASONIC);
		rightUltra = new AnalogInput(RobotMap.RIGHT_ULTRASONIC);
	}

	public Encoder getLeftEnc() {
		return leftEnc;
	}

	public Encoder getRightEnc() {
		return rightEnc;
	}

	public void encReset() {
		leftEnc.reset();
		rightEnc.reset();
	}
	
	public void navxReset() {
		ahrs.reset();
	}

	public AHRS getAhrs() {
		return ahrs;
	}
	
	public AnalogInput getLeftUltra() {
		return leftUltra;
	}

	public AnalogInput getRightUltra() {
		return rightUltra;
	}

	public double getRightRange() {
		double rightRange = rightUltra.getVoltage() * 106.23 + 0.3973;
		return rightRange;
	}
	
	public double getLeftRange() {
		double leftRange = leftUltra.getVoltage() * 106.23 + 0.3973;
		return leftRange;
	}

}
