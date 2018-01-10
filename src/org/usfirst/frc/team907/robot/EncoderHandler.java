package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderHandler {
	
	public void init(Encoder leftEnc, Encoder rightEnc) {
		leftEnc = new Encoder(RobotMap.LEFT_ENC_ONE, RobotMap.LEFT_ENC_TWO, false, Encoder.EncodingType.k4X);
		rightEnc = new Encoder(RobotMap.RIGHT_ENC_ONE, RobotMap.RIGHT_ENC_TWO, false, Encoder.EncodingType.k4X);
	}
	
	public void reset(Encoder leftEnc, Encoder rightEnc) {
		leftEnc.reset();
		rightEnc.reset();
	}

}
