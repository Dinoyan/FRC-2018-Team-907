/*----------------------------------------------------------------------------*/
/* @author - Dinoyan Ganeshalingam, Manish Suresh
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private String m_autoSelected;
	private SendableChooser<String> auto_chooser = new SendableChooser<>();
	private String gameData;

	private PDPHandler pdpHandler;
	private Drivetrain drivetrain;
	private JoystickHandler joystickHandler;
	private MultiSpeedController multiSpeedController;
	private AutonomousModeHandler AutonomousModeHandler;
	private SensorHandler sensorHandler;
	
	private int _loops = 0;

	@Override
	public void robotInit() {
		// Dashboard auto chooser
		auto_chooser.addDefault("Default Auto", RobotMap.DEFAULT);
		auto_chooser.addObject("Right Auto", RobotMap.RIGHT_POS);
		auto_chooser.addObject("Left Auto", RobotMap.LEFT_POS);
		auto_chooser.addObject("Center Auto", RobotMap.CENTER_POS);
		SmartDashboard.putData("Auto choices", auto_chooser);

		pdpHandler = new PDPHandler();
		drivetrain = new Drivetrain();
		sensorHandler = new SensorHandler();
		joystickHandler = new JoystickHandler();
		multiSpeedController = new MultiSpeedController();

		AutonomousModeHandler = new AutonomousModeHandler(multiSpeedController, drivetrain, sensorHandler);

	}

	@Override
	public void autonomousInit() {
		m_autoSelected = auto_chooser.getSelected();
		//autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		DataLogger.logData(m_autoSelected);

		// Game Data from the field.
		this.gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	@Override
	public void autonomousPeriodic() {
		// Run the auto handler.
		AutonomousModeHandler.AudoModeSelect(m_autoSelected, gameData);
	}

	@Override
	public void teleopPeriodic() {
		if(++_loops >= 10) {
        		_loops = 0;
        		SmartDashboard.putNumber("Current", pdpHandler.getCurrent());
        		SmartDashboard.putNumber("Left Ultrasonic", sensorHandler.getLeftRange());
        		SmartDashboard.putNumber("Right Ultrasonic",sensorHandler.getRightRange());
        		DataLogger.logData("Current : " + Double.toString(pdpHandler.getCurrent()));
        		DataLogger.logData("Left Ultrasonic : " + Double.toString(sensorHandler.getLeftRange()));
        		DataLogger.logData("Right Ultrasonic : " + Double.toString(sensorHandler.getRightRange()));
        }
		
		drivetrain.driveRobot(joystickHandler, multiSpeedController);

	}

	@Override
	public void testPeriodic() {
	}
}
