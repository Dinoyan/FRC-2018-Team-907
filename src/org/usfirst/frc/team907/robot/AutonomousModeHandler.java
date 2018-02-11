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
		case RobotConstant.LEFT_POS:
			if (gameData.charAt(0) == 'L') {
				
			} else if (gameData.charAt(1) == 'L') {

			}

			break;
		case RobotConstant.RIGHT_POS:
			if (gameData.charAt(0) == 'R') {
				
			} else if (gameData.charAt(1) == 'R') {

			}

			break;
		case RobotConstant.CENTER_POS:
			if (gameData.charAt(0) == 'L') {
				
				AutonomousActions.driveForward(drivetrain, sensorHandler, 30);
				AutonomousActions.turnLeft(drivetrain, sensorHandler, -8);
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				AutonomousActions.turnRight(drivetrain, sensorHandler, 8);
				//lift cube to position
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				// drop cube

			} else {
				
				AutonomousActions.driveForward(drivetrain, sensorHandler, 30);
				AutonomousActions.turnRight(drivetrain, sensorHandler, 8);
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				AutonomousActions.turnLeft(drivetrain, sensorHandler, -8);
				//lift cube to position
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				// drop cube
				
			

			}

			break;
		}

	}

}
