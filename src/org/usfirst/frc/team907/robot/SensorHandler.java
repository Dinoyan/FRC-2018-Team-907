package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SerialPort;

public class SensorHandler {

	private Encoder leftEnc;
	private Encoder rightEnc;
	private Encoder elevatorEnc;
	private AHRS ahrs;
	private DigitalInput photoSensor;
	private DigitalInput elevSwitchOne;
	private DigitalInput elevSwitchTwo;

	public SensorHandler() {
		leftEnc = new Encoder(RobotMap.LEFT_ENC_ONE, RobotMap.LEFT_ENC_TWO, true, Encoder.EncodingType.k4X);
		rightEnc = new Encoder(RobotMap.RIGHT_ENC_ONE, RobotMap.RIGHT_ENC_TWO, true, Encoder.EncodingType.k4X);
		elevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_ONE, RobotMap.ELEVATOR_ENC_TWO, false, Encoder.EncodingType.k4X);
		ahrs = new AHRS(SerialPort.Port.kMXP);
		photoSensor = new DigitalInput(RobotMap.PHOTOSENSOR);
		elevSwitchOne = new DigitalInput(RobotMap.ELEV_LIMIT_ONE);
		elevSwitchTwo = new DigitalInput(RobotMap.ELEV_LIMIT_TWO);
	}

	public void driveEncReset() {
		leftEnc.reset();
		rightEnc.reset();
	}
	
	public void elevEncReset() {
		elevatorEnc.reset();
	}

	public void navxReset() {
		ahrs.reset();
	}

	public AHRS getAhrs() {
		return ahrs;
	}
	
	public double getLeftDistance() {
		double leftDistance = (leftEnc.getDistance() / RobotConstant.DRIVE_ENC_UNITS) * Math.PI * RobotConstant.DRIVE_WHEEL_SIZE;
		
		return -leftDistance;
	}

	public double getRightDistance() {
		double rightDistance = (rightEnc.getDistance() / RobotConstant.DRIVE_ENC_UNITS) * Math.PI * RobotConstant.DRIVE_WHEEL_SIZE;
		
		return rightDistance;
	}
	
	public double getElevDistance() {
		double distance = elevatorEnc.getDistance();
		
		return distance;
	}
	
	public double getCurrentAngle() {
		double angle = ahrs.getAngle();
		
		return angle;
	}

	public boolean getPhotoSensorStatus() {
		return photoSensor.get();
	}
	
	public boolean getElevSwitchOneStatus() {
		return elevSwitchOne.get();
	}
	
	public boolean getElevSwitchTwoStatus() {
		return elevSwitchTwo.get();
	}

}
