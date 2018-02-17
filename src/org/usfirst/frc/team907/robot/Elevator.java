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
		while (sensorHandler.getElevDistance() <= RobotConstant.ELEVATOR_SWITCH_VALUE) {
			
			// calculate the speed based on the encoder values
			double speed = Maths.calculateElevSpeed(sensorHandler, RobotConstant.SWITCH);
				
			this.elevCimOne.set(speed);
			this.elevCimTwo.set(speed);
		}
				
		this.elevCimOne.set(RobotConstant.ELEVATOR_ZERO_SPEED);
		this.elevCimTwo.set(RobotConstant.ELEVATOR_ZERO_SPEED);
			
	}

	public void scalePosition() {
		while (sensorHandler.getElevDistance() <= RobotConstant.ELEVATOR_SCALE_VALUE) {
			double speed = Maths.calculateElevSpeed(sensorHandler, RobotConstant.SCALE);
			
			this.elevCimOne.set(speed);
			this.elevCimTwo.set(speed);
		}
		this.elevCimOne.set(RobotConstant.ELEVATOR_ZERO_SPEED);
		this.elevCimTwo.set(RobotConstant.ELEVATOR_ZERO_SPEED);
			
	}

	public void climbPosition() {
		while (sensorHandler.getElevDistance() <= RobotConstant.ELEVATOR_CLIMB_VALUE) {
			double speed = Maths.calculateElevSpeed(sensorHandler, RobotConstant.SCALE);
			
			this.elevCimOne.set(speed);
			this.elevCimTwo.set(speed);
			
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
		/*if (Math.abs(sensorHandler.getLeftRange() - sensorHandler.getRightRange()) <= 4.0) {
			this.ready = true;
		}*/
		return this.ready;
	}

}
