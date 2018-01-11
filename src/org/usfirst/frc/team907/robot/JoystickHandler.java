package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickHandler {
	
	private Joystick driveStick;
	private Joystick cubeStick;
	
	public void init() {
		driveStick = new Joystick(RobotMap.DRIVE_STICK);
		cubeStick = new Joystick(RobotMap.CUBE_STICK);
	}

	public Joystick getDriveStick() {
		return driveStick;
	}

	public Joystick getCubeStick() {
		return cubeStick;
	}

}
