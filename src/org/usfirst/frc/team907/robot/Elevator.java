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
		
		elevCimOne.setSafetyEnabled(true);
		elevCimTwo.setSafetyEnabled(true);
		
		this.sensorHandler = sensorHandler;
		this.joystickHandler = joystickHandler;
		
		this.ready = false;
	}

	public void startPositon() {
		
	}

	public void switchPosition() {
		
	}

	public void scalePosition() {
		
	}

	public void climbPosition() {
		
	}

	public void operateElevator() {
		
	}
	
	public boolean readyToClimb() {
		if (Math.abs(sensorHandler.getLeftRange() - sensorHandler.getRightRange()) <= 4.0) {
			this.ready = true;
		}
		return this.ready;
		
	}

}
