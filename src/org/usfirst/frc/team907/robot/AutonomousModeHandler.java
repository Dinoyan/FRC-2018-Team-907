package org.usfirst.frc.team907.robot;

public class AutonomousModeHandler {

	public SensorHandler sensorHandler;
	public Drivetrain drivetrain;
	public Intake intake;
	public Elevator elevator;
	private boolean state;

	public AutonomousModeHandler(Drivetrain drivetrain, SensorHandler sensorHandler, Intake intake, Elevator elevator) {

		this.sensorHandler = sensorHandler;
		this.drivetrain = drivetrain;
		this.intake = intake;
		this.elevator = elevator;
		this.state = true;

	}

	public void AudoModeSelect(String position, String priority, String gameData) {

		switch (position) {
		case RobotConstant.LEFT_POS:
			if (gameData.charAt(0) == 'L') {
				AutonomousActions.driveForward(drivetrain, sensorHandler, 5);
				AutonomousActions.turnRight(drivetrain, sensorHandler, 90);
				sensorHandler.driveEncReset();
				AutonomousActions.driveForward(drivetrain, sensorHandler, 2);
				
			} else if (gameData.charAt(1) == 'L') {
				AutonomousActions.driveForward(drivetrain, sensorHandler, 5);
				AutonomousActions.turnLeft(drivetrain, sensorHandler, -90);
				sensorHandler.driveEncReset();
				AutonomousActions.driveForward(drivetrain, sensorHandler, 2);
			}
			break;
		case RobotConstant.RIGHT_POS:
			if (gameData.charAt(0) == 'R') {
				
			} else if (gameData.charAt(1) == 'R') {

			}

			break;
		case RobotConstant.CENTER_POS:
			if (gameData.charAt(0) == 'L') {
				if(state){
					AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					AutonomousActions.turnLeft(drivetrain, sensorHandler, -8);
					sensorHandler.driveEncReset();
					AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					AutonomousActions.turnRight(drivetrain, sensorHandler, 0);
					sensorHandler.driveEncReset();
					//lift cube to position
					AutonomousActions.liftCubeSwitch(elevator);
					AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					// drop cube
					AutonomousActions.dropCube(intake);
					state = false;
				}
				

			} else {
				if(state){
					
					AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					AutonomousActions.turnRight(drivetrain, sensorHandler, 8);
					sensorHandler.driveEncReset();
					AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					AutonomousActions.turnLeft(drivetrain, sensorHandler, 0);
					sensorHandler.driveEncReset();
					//lift cube to position
					AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					// drop cube
					state = false;
					
				}
			}

			break;
		}

	}

}
