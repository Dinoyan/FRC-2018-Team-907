package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Talon;

public class Elevator{
	private Talon elevCimOne;
	private Talon elevCimTwo;
	private SensorHandler sensorHandler;
	private JoystickHandler joystickHandler;

	public Elevator(SensorHandler sensorHandler, JoystickHandler joystickHandler) {
		elevCimOne = new Talon(RobotMap.ELEV_ONE);
		elevCimTwo = new Talon(RobotMap.ELEV_TWO);
		this.sensorHandler = sensorHandler;
		this.joystickHandler = joystickHandler;
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

}
