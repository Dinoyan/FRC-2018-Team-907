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
		while (sensorHandler.getElevSwitchOneStatus() != true) {
			this.elevCimOne.set(-0.2);
			this.elevCimTwo.set(-0.2);
		}
		this.elevCimOne.set(0);
		this.elevCimTwo.set(0);
	}

	public void switchPosition() {
		
	}

	public void scalePosition() {
		
	}

	public void climbPosition() {
		while (sensorHandler.getElevEnc().getDistance() <= RobotConstant.ELEVATOR_CLIMB_VALUE) {
			if (sensorHandler.getElevEnc().getDistance() <= RobotConstant.ELEVATOR_POS_SPEED_ONE) {
				this.elevCimOne.set(0.4);
				this.elevCimTwo.set(0.4);
			} else if (sensorHandler.getElevEnc().getDistance() <= RobotConstant.ELEVATOR_POS_SPEED_TWO) {
				this.elevCimOne.set(0.3);
				this.elevCimTwo.set(0.3);
			} else if (sensorHandler.getElevEnc().getDistance() <= RobotConstant.ELEVATOR_POS_SPEED_THREE) {
				this.elevCimOne.set(0.2);
				this.elevCimTwo.set(0.2);
			} else {
				this.elevCimOne.set(0.1);
				this.elevCimTwo.set(0.1);
			}
		}
		this.elevCimOne.set(0);
		this.elevCimTwo.set(0);
	}

	public void operateElevator() {
		
	}
	
	public void emergencyStop() {
		this.elevCimOne.set(0);
		this.elevCimTwo.set(0);
	}

	public boolean readyToClimb() {
		if (Math.abs(sensorHandler.getLeftRange() - sensorHandler.getRightRange()) <= 4.0) {
			this.ready = true;
		}
		return this.ready;
	}

}
