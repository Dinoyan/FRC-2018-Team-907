package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickHandler {

	private Joystick driveStick;
	private Joystick cubeStick;
	
	boolean toggleOn = false;
    boolean togglePressed = false;

	public JoystickHandler() {
		driveStick = new Joystick(RobotMap.DRIVE_STICK);
		cubeStick = new Joystick(RobotMap.CUBE_STICK);
	}

	public Joystick getDriveStick() {
		return driveStick;
	}

	public Joystick getCubeStick() {
		return cubeStick;
	}

	public void vibrateDriveStick() {
		driveStick.setRumble(RumbleType.kRightRumble, 1);
	}

	public void vibrateCubeStick() {
		cubeStick.setRumble(RumbleType.kRightRumble, 1);
	}
	
	public void updateToggle(int button) {
		if(cubeStick.getRawButton(1)) {
            if(!togglePressed){
                toggleOn = !toggleOn;
                togglePressed = true;
            }
        } else {
            togglePressed = false;
        }
}

}
