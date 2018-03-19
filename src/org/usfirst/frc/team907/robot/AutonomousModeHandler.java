package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutonomousModeHandler {

	public SensorHandler sensorHandler;
	public Drivetrain drivetrain;
	public Elevator elev;
	public Intake intake;
	private boolean state;
	private Timer timer;

	public AutonomousModeHandler(Drivetrain drivetrain, SensorHandler sensorHandler, Elevator elevator, Intake intake) {

		this.sensorHandler = sensorHandler;
		this.drivetrain = drivetrain;
		this.elev = elevator;
		this.intake = intake;
		this.state = true;
		
		timer = new Timer();

	}

	public void AudoModeSelect(String position, String priority, String gameData) {

		switch (position) {
		case RobotConstant.LEFT_POS:
			if (gameData.charAt(1) == 'L') {
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				AutonomousActions.liftCubeScale(elev);
				AutonomousActions.dropCube(intake);
				
			} else {
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				AutonomousActions.turnRight(drivetrain, sensorHandler, -90);
				sensorHandler.driveEncReset();
				AutonomousActions.driveForward(drivetrain, sensorHandler, 1);
				AutonomousActions.turnLeft(drivetrain, sensorHandler, 0);
				AutonomousActions.liftCubeScale(elev);
				AutonomousActions.dropCube(intake);
				
			}
			break;
		case RobotConstant.RIGHT_POS:
			if (gameData.charAt(1) == 'R') {
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				AutonomousActions.liftCubeScale(elev);
				AutonomousActions.dropCube(intake);
				
			} else {
				AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
				AutonomousActions.turnLeft(drivetrain, sensorHandler, -90);
				sensorHandler.driveEncReset();
				AutonomousActions.driveForward(drivetrain, sensorHandler, 1);
				AutonomousActions.turnRight(drivetrain, sensorHandler, 0);
				AutonomousActions.liftCubeScale(elev);
				AutonomousActions.dropCube(intake);
			}

			break;
			
		case RobotConstant.CENTER_POS:
			if (gameData.charAt(0) == 'L') {
				if(state){
					AutonomousActions.driveForward(drivetrain, sensorHandler, 1);
					AutonomousActions.turnLeft(drivetrain, sensorHandler, -30);
					AutonomousActions.liftCubeSwitch(elev);
					sensorHandler.driveEncReset();
					AutonomousActions.driveForward(drivetrain, sensorHandler, 7);
					AutonomousActions.turnRight(drivetrain, sensorHandler, 0);
					sensorHandler.driveEncReset();
					AutonomousActions.JustMove(drivetrain, timer);
					AutonomousActions.dropCube(intake);
					state = false;
				}
				
			} else {
				if(state){
					AutonomousActions.driveForward(drivetrain, sensorHandler, 1);
					AutonomousActions.turnRight(drivetrain, sensorHandler, 30);
					AutonomousActions.dropIntake(intake);
					AutonomousActions.liftCubeSwitch(elev);
					sensorHandler.driveEncReset();
					AutonomousActions.driveForward(drivetrain, sensorHandler, 7);
					AutonomousActions.turnLeft(drivetrain, sensorHandler, 0);
					sensorHandler.driveEncReset();
					AutonomousActions.JustMove(drivetrain, timer);
					AutonomousActions.dropCube(intake);
					state = false;
				}
			}

			break;
			
		case RobotConstant.AUTO_RUN:
			AutonomousActions.driveForward(drivetrain, sensorHandler, 10);
			break;
		}

	}

}
