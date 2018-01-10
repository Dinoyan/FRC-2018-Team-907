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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private String gameData;

	public PowerDistributionPanel pdp;
	private Joystick driveStick;
	private Joystick cubeStick;
	private Talon rDrive1;
	private Talon rDrive2;
	private Talon rDrive3;
	private Talon lDrive1;
	private Talon lDrive2;
	private Talon lDrive3;
	private AHRS ahrs;
	private Encoder leftEnc;
	private Encoder rightEnc;
	private Ultrasonic leftUltra;
	private Ultrasonic rightUltra;

	private Drivetrain drivetrain;
	private MultiSpeedController multiSpeedController;
	private EncoderHandler encoderHandler;
	private JoystickHandler joystickHandler;
	private UltrasonicHandler ultrasonicHandler;
	private AutonomousModeHandler AutonomousModeHandler;

	@Override
	public void robotInit() {
		// Dashboard auto chooser
		m_chooser.addDefault("Right Auto", RobotMap.RIGHT_POS);
		m_chooser.addObject("Left Auto", RobotMap.LEFT_POS);
		m_chooser.addObject("Center Auto", RobotMap.CENTER_POS);
		SmartDashboard.putData("Auto choices", m_chooser);

		this.pdp = new PowerDistributionPanel();
		this.ahrs = new AHRS(SerialPort.Port.kMXP);

		encoderHandler = new EncoderHandler(leftEnc, rightEnc);
		joystickHandler = new JoystickHandler(driveStick, cubeStick);
		ultrasonicHandler = new UltrasonicHandler(leftUltra, rightUltra);
		multiSpeedController = new MultiSpeedController(rDrive1, rDrive2, rDrive3, lDrive1, lDrive2, lDrive3);
		
		drivetrain = new Drivetrain(multiSpeedController, driveStick, ultrasonicHandler);
		AutonomousModeHandler = new AutonomousModeHandler(drivetrain, ahrs, encoderHandler);
		
		encoderHandler.init();
		joystickHandler.init();
		ultrasonicHandler.init();
		multiSpeedController.init();
		
	}

	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
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
		SmartDashboard.putNumber("Current", pdp.getCurrent(0));
		SmartDashboard.putNumber("Left Ultrasonic", ultrasonicHandler.getLeftDistance());
		SmartDashboard.putNumber("Right Ultrasonic", ultrasonicHandler.getRightDistance());

		LoggerData.logData("Current : " + Double.toString(pdp.getCurrent(0)));
		LoggerData.logData("Left Encoder : " + Double.toString(ultrasonicHandler.getLeftDistance()));
		LoggerData.logData("Right Encoder : " + Double.toString(ultrasonicHandler.getRightDistance()));

		this.drivetrain.driveRobot();

	}

	@Override
	public void testPeriodic() {
	}
}
