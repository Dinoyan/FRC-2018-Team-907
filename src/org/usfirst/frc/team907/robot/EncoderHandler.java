package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderHandler {
	
	public Encoder leftEnc;
	public Encoder rightEnc;
	
	public EncoderHandler(Encoder leftEnc, Encoder rightEnc) {
		this.leftEnc = leftEnc;
		this.rightEnc = rightEnc;
	}
	
	public void init() {
		leftEnc = new Encoder(RobotMap.LEFT_ENC_ONE, RobotMap.LEFT_ENC_TWO, false, Encoder.EncodingType.k4X);
		rightEnc = new Encoder(RobotMap.RIGHT_ENC_ONE, RobotMap.RIGHT_ENC_TWO, false, Encoder.EncodingType.k4X);
	}
	
	public void reset() {
		leftEnc.reset();
		rightEnc.reset();
	}

}
