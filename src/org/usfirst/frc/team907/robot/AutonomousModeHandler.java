package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class AutonomousModeHandler {
	public Talon rDrive1;
	public Talon rDrive2;
	public Talon rDrive3;
	public Talon lDrive1;
	public Talon lDrive2;
	public Talon lDrive3;
	public AHRS navx;
	public Encoder rightEnc;
	public Encoder leftEnc;

	public AutonomousModeHandler(Talon rDrive1, Talon rDrive2, Talon rDrive3, Talon lDrive1, Talon lDrive2,
			Talon lDrive3, AHRS navx, Encoder rightEnc, Encoder leftEnc) {

		this.rDrive1 = rDrive1;
		this.rDrive2 = rDrive2;
		this.rDrive3 = rDrive3;
		this.lDrive1 = lDrive1;
		this.lDrive2 = lDrive2;
		this.lDrive3 = lDrive3;
		this.navx = navx;
		this.rightEnc = rightEnc;
		this.leftEnc = leftEnc;

	}

	public void AudoModeSelect(String position, String gameData) {

		switch (position) {
		case RobotMap.LEFT:
			if (gameData.charAt(0) == 'L') {
				System.out.println("Test: Game Data Working");
			} else if (gameData.charAt(1) == 'L') {

			}

			break;
		case RobotMap.RIGHT:
			if (gameData.charAt(0) == 'R') {

			} else if (gameData.charAt(1) == 'R') {

			}

			break;
		case RobotMap.CENTER:
			if (gameData.charAt(0) == 'L') {

			} else {

			}

			break;
		}

	}

}
