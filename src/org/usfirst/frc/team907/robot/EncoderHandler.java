package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderHandler {

	private Encoder leftEnc;
	private Encoder rightEnc;

	public void init() {
		leftEnc = new Encoder(RobotMap.LEFT_ENC_ONE, RobotMap.LEFT_ENC_TWO, false, Encoder.EncodingType.k4X);
		rightEnc = new Encoder(RobotMap.RIGHT_ENC_ONE, RobotMap.RIGHT_ENC_TWO, false, Encoder.EncodingType.k4X);
	}

	public Encoder getLeftEnc() {
		return leftEnc;
	}

	public Encoder getRightEnc() {
		return rightEnc;
	}

	public void reset() {
		leftEnc.reset();
		rightEnc.reset();
	}

}
