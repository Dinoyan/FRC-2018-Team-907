package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Talon;

public class Elevator{
	private Talon elevCimOne;
	private Talon elevCimTwo;
	private SensorHandler sensorHandler;
	private JoystickHandler joystickHandler;
	private boolean ready;

	public Elevator(SensorHandler sensorHandler, JoystickHandler joystickHandler) {
		elevCimOne = new Talon(RobotMap.ELEV_ONE);
		elevCimTwo = new Talon(RobotMap.ELEV_TWO);
		
		//elevCimOne.setSafetyEnabled(true);
		//elevCimTwo.setSafetyEnabled(true);
		
		this.sensorHandler = sensorHandler;
		this.joystickHandler = joystickHandler;
		
		this.ready = false;
	}

	/*public void startPositon() {
		while (sensorHandler.getElevSwitchOneStatus() != true) {
			this.elevCimOne.set(-0.2);
			this.elevCimTwo.set(-0.2);
		}
		this.elevCimOne.set(0);
		this.elevCimTwo.set(0);
	}*/

	public void switchPosition() {
		while (sensorHandler.getElevDistance() <= RobotConstant.ELEVATOR_SWITCH_VALUE) {
			
			double speed = Maths.calculateElevSpeed(sensorHandler, RobotConstant.SWITCH);
				
			this.elevCimOne.set(speed);
			this.elevCimTwo.set(speed);
		}
				
		this.elevCimOne.set(RobotConstant.ELEV_MIN_SPEED);
		this.elevCimTwo.set(RobotConstant.ELEV_MIN_SPEED);
			
	}

	public void scalePosition() {
		while (sensorHandler.getElevDistance() <= RobotConstant.ELEVATOR_SCALE_VALUE) {
			
			double speed = Maths.calculateElevSpeed(sensorHandler, RobotConstant.SCALE);
			
			this.elevCimOne.set(speed);
			this.elevCimTwo.set(speed);
		}
				
		this.elevCimOne.set(RobotConstant.ELEV_MIN_SPEED);
		this.elevCimTwo.set(RobotConstant.ELEV_MIN_SPEED);
			
	}

	public void climbPosition() {
		while (sensorHandler.getElevDistance() <= RobotConstant.ELEVATOR_CLIMB_VALUE) {
			
			this.elevCimOne.set(RobotConstant.ELEV_CLIMB_MAX_SPEED);
			this.elevCimTwo.set(RobotConstant.ELEV_CLIMB_MAX_SPEED);
		}
				
		this.elevCimOne.set(RobotConstant.ELEV_MIN_SPEED);
		this.elevCimTwo.set(RobotConstant.ELEV_MIN_SPEED);
			 
	}
	
	public void originPosition() {
		//while(sensorHandler.getElevDistance() > RobotConstant.ELEVATOR_ORIGIN_VALUE)
	}

	public void operateElevator() {
		
		if (this.sensorHandler.getElevSwitchOneStatus()) {
			elevCimOne.set(joystickHandler.getCubeStick().getRawAxis(5));
			elevCimTwo.set(joystickHandler.getCubeStick().getRawAxis(5));
		} else {
			this.elevCimOne.set(0);
			this.elevCimTwo.set(0);
		}
		
		if (this.sensorHandler.getElevSwitchTwoStatus()) {
			elevCimOne.set(joystickHandler.getCubeStick().getRawAxis(5));
			elevCimTwo.set(joystickHandler.getCubeStick().getRawAxis(5));
		} else {
			this.elevCimOne.set(-RobotConstant.ELEV_MIN_SPEED);
			this.elevCimTwo.set(-RobotConstant.ELEV_MIN_SPEED);
		}
		
	
		/*
		if(joystickHandler.getCubeStick().getRawButton(1)) {
			
		}
		else if(joystickHandler.getCubeStick().getRawButton(2)) {
			switchPosition();
		}
		else if(joystickHandler.getCubeStick().getRawButton(3)) {
			scalePosition();
		}
		else if(joystickHandler.getCubeStick().getRawButton(4)) {
			climbPosition();
		}
		*/
		
	}
	
	public void emergencyStop() {
		this.elevCimOne.set(0);
		this.elevCimTwo.set(0);
	}

	public boolean readyToClimb() {
		/*if (Math.abs(sensorHandler.getLeftRange() - sensorHandler.getRightRange()) <= 4.0) {
			this.ready = true;
		}*/
		return this.ready;
	}

}
