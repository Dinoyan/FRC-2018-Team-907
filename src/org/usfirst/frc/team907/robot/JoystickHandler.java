package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickHandler {

	public Joystick driveStick;
	public Joystick cubeStick;
	
	public JoystickHandler (Joystick driveStick, Joystick cubeStick) {
		this.driveStick = driveStick;
		this.cubeStick = cubeStick;
	}
	
	public void init() {
		driveStick = new Joystick(RobotMap.DRIVE_STICK);
		cubeStick = new Joystick(RobotMap.CUBE_STICK);
	}
	
}
