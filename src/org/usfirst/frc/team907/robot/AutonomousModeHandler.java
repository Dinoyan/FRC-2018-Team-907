package org.usfirst.frc.team907.robot;

public class AutonomousModeHandler {

	public AHRSHandler ahrsHandler;
	public EncoderHandler encoderHandler;
	public Drivetrain drivetrain;

	public AutonomousModeHandler(Drivetrain drivetrain, AHRSHandler ahrsHandler,
			EncoderHandler encoderHandler) {

		this.drivetrain = drivetrain;
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
		case RobotMap.DEFAULT:
			AutonomousActions.defaultAuto(drivetrain, ahrsHandler, encoderHandler);
			break;
		}

	}

}
