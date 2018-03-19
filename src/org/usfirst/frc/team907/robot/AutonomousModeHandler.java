package org.usfirst.frc.team907.robot;

public class AutonomousModeHandler {

	public SensorHandler sensorHandler;
	public Drivetrain drivetrain;
	public Elevator elev;
	public Intake intake;
	private boolean state;

	public AutonomousModeHandler(Drivetrain drivetrain, SensorHandler sensorHandler, Elevator elevator, Intake intake) {

		this.sensorHandler = sensorHandler;
		this.drivetrain = drivetrain;
		this.elev = elevator;
		this.intake = intake;
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
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				
			} else {
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);

			}

			break;
		case RobotConstant.CENTER_POS:
			if (gameData.charAt(0) == 'L') {
				if(state){
					AutonomousActions.driveForward(drivetrain, sensorHandler, 1);
					AutonomousActions.turnLeft(drivetrain, sensorHandler, -30);
					//AutonomousActions.dropIntake(intake);
					AutonomousActions.liftCube(elev);
					sensorHandler.driveEncReset();
					AutonomousActions.driveForward(drivetrain, sensorHandler, 7);
					AutonomousActions.turnRight(drivetrain, sensorHandler, 0);
					sensorHandler.driveEncReset();
					//AutonomousActions.driveForward(drivetrain, sensorHandler, 0.4);
					AutonomousActions.JustMove(drivetrain);
					
					AutonomousActions.dropCube(intake);
					//AutonomousActions.turnRight(drivetrain, sensorHandler, 0);
					//sensorHandler.driveEncReset();
					//lift cube to position
					//AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					// drop cube
					state = false;
				}
				
			} else {
				if(state){
					
					AutonomousActions.driveForward(drivetrain, sensorHandler, 1);
					AutonomousActions.turnRight(drivetrain, sensorHandler, 30);
					AutonomousActions.dropIntake(intake);
					AutonomousActions.liftCube(elev);
					sensorHandler.driveEncReset();
					AutonomousActions.driveForward(drivetrain, sensorHandler, 7);
					AutonomousActions.turnLeft(drivetrain, sensorHandler, 0);
					sensorHandler.driveEncReset();
					//AutonomousActions.driveForward(drivetrain, sensorHandler, 0.4);
					AutonomousActions.JustMove(drivetrain);
					
					AutonomousActions.dropCube(intake);
					//AutonomousActions.turnRight(drivetrain, sensorHandler, 0);
					//sensorHandler.driveEncReset();
					//lift cube to position
					//AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
					// drop cube
					state = false;
				}
			}

			break;
		}

	}

}
