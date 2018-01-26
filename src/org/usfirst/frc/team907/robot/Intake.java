package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Intake {
	
	private Solenoid openIntake;
	private Solenoid closeIntake;
	
	private Talon rightIntake;
	private Talon leftIntake;
	
	private SensorHandler sensorHandler;
	
	public Intake (SensorHandler sensorHandler) {
		openIntake = new Solenoid(RobotMap.OPEN_INTAKE);
		closeIntake = new Solenoid(RobotMap.CLOSE_INTAKE);
		
		rightIntake = new Talon(RobotMap.RIGHT_INTAKE);
		leftIntake = new Talon(RobotMap.LEFT_INTAKE);
		
		this.sensorHandler = sensorHandler;
	}
	
	public void pickUpCube() {
		
		// open the intake
		controlIntakeSolenoids(RobotConstant.OPEN_INTAKE);
		
		// Take in the power cube
		controlIntakeMotors(RobotConstant.PICKUP_SPEED);
		
		// get the status of photosensor and close the intake based on its reading
		if(sensorHandler.getPhotoSensorStatus()) {
			// stop the motors
			controlIntakeMotors(RobotConstant.STOP_INTAKE_MOTORS);
			
			// close the solenoids
			controlIntakeSolenoids(RobotConstant.CLOSE_INTAKE);
		}		
	}
	
	public void vomitCube() {
			
			// open the intake
			//controlIntakeSolenoids(RobotConstant.OPEN_INTAKE);
			
			// Take in the power cube
			controlIntakeMotors(RobotConstant.VOMIT_SPEED);			
		}
	
	private void controlIntakeSolenoids(boolean state) {
		openIntake.set(state);
		closeIntake.set(!state);
	}
	
	private void controlIntakeMotors(double speed) {
		rightIntake.set(speed);
		leftIntake.set(-speed);
	}

}
