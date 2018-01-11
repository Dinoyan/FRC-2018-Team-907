package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

public class AutonomousModeHandler {
	 
	public AHRSHandler ahrsHandler;
	public EncoderHandler encoderHandler;
	public MultiSpeedController multiSpeedController;

	public AutonomousModeHandler(MultiSpeedController multiSpeedController, AHRSHandler ahrsHandler, EncoderHandler encoderHandler) {

		this.multiSpeedController = multiSpeedController;
		this.ahrsHandler = ahrsHandler;
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
