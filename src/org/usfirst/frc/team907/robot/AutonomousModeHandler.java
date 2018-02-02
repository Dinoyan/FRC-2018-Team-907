package org.usfirst.frc.team907.robot;

public class AutonomousModeHandler {

	public SensorHandler sensorHandler;
	public Drivetrain drivetrain;

	public AutonomousModeHandler(Drivetrain drivetrain, SensorHandler sensorHandler) {

		this.sensorHandler = sensorHandler;
		this.drivetrain = drivetrain;

	}

	public void AudoModeSelect(String position, String priority, String gameData) {

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
