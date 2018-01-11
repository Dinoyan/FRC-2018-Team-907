/*----------------------------------------------------------------------------*/
/* @author - Dinoyan Ganeshalingam, Manish Suresh
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private String m_autoSelected;
	private SendableChooser<String> auto_chooser = new SendableChooser<>();
	private String gameData;

	private PDPHandler pdpHandler;
	private Drivetrain drivetrain;
	private AHRSHandler ahrsHandler;
	private EncoderHandler encoderHandler;
	private JoystickHandler joystickHandler;
	private UltrasonicHandler ultrasonicHandler;
	private MultiSpeedController multiSpeedController;
	private AutonomousModeHandler AutonomousModeHandler;

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
		encoderHandler = new EncoderHandler();
		joystickHandler = new JoystickHandler();
		ultrasonicHandler = new UltrasonicHandler();
		multiSpeedController = new MultiSpeedController();
		
		pdpHandler.init();
		ahrsHandler.init();
		encoderHandler.init();
		joystickHandler.init();
		ultrasonicHandler.init();
		multiSpeedController.init();

		AutonomousModeHandler = new AutonomousModeHandler(multiSpeedController, ahrsHandler, encoderHandler);
				
	}

	@Override
	public void autonomousInit() {
		m_autoSelected = auto_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		LoggerData.logData(m_autoSelected);

		// Game Data from the field.
		this.gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	@Override
	public void autonomousPeriodic() {
		AutonomousModeHandler.AudoModeSelect(m_autoSelected, gameData);
	}

	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Current", pdpHandler.getCurrent());
		//SmartDashboard.putNumber("Left Ultrasonic", ultrasonicHandler.getLeftDistance());
		//SmartDashboard.putNumber("Right Ultrasonic", ultrasonicHandler.getRightDistance());

		LoggerData.logData("Current : " + Double.toString(pdpHandler.getCurrent()));
		LoggerData.logData("Left Ultrasonic : " + Double.toString(ultrasonicHandler.getLeftDistance()));
		LoggerData.logData("Right Ultrasonic : " + Double.toString(ultrasonicHandler.getRightDistance()));

		drivetrain.driveRobot(joystickHandler, multiSpeedController);

	}

	@Override
	public void testPeriodic() {
	}
}
