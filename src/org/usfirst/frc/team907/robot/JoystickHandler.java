package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickHandler {
	
	public void init(Joystick driveStick, Joystick cubeStick) {
		driveStick = new Joystick(RobotMap.DRIVE_STICK);
		cubeStick = new Joystick(RobotMap.CUBE_STICK);
	}

}
