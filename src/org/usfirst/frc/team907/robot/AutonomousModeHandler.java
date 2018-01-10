package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class AutonomousModeHandler {
	 
	public AHRS navx;
	public EncoderHandler encoderHandler;
	public Drivetrain drivetrain;

	public AutonomousModeHandler(Drivetrain drivetrain, AHRS navx, EncoderHandler encoderHandler) {

		this.drivetrain = drivetrain;
		this.navx = navx;
		this.encoderHandler = encoderHandler;

	}

	public void AudoModeSelect(String position, String gameData) {

		switch (position) {
		case RobotMap.LEFT_POS:
			if (gameData.charAt(0) == 'L') {
				System.out.println("Test: Game Data Working");
			} else if (gameData.charAt(1) == 'L') {

			}

			break;
		case RobotMap.RIGHT_POS:
			if (gameData.charAt(0) == 'R') {

			} else if (gameData.charAt(1) == 'R') {

			}

			break;
		case RobotMap.CENTER_POS:
			if (gameData.charAt(0) == 'L') {

			} else {

			}

			break;
		}

	}

}
