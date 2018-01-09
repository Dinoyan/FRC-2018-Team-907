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

	private AutonomousModeHandler AutonomousModeHandler;
	private DrivetrainHandler driveHandler;

	@Override
	public void robotInit() {
		// Dashboard auto chooser
		m_chooser.addDefault("Right Auto", RobotMap.RIGHT);
		m_chooser.addObject("Left Auto", RobotMap.LEFT);
		m_chooser.addObject("Center Auto", RobotMap.CENTER);
		SmartDashboard.putData("Auto choices", m_chooser);

		this.pdp = new PowerDistributionPanel();

		// Navx board (gyro purposes)
		this.ahrs = new AHRS(SerialPort.Port.kMXP);
		this.leftEnc = new Encoder(RobotMap.LEFT_ENC_ONE, RobotMap.LEFT_ENC_TWO, false, Encoder.EncodingType.k4X);
		this.rightEnc = new Encoder(RobotMap.RIGHT_ENC_ONE, RobotMap.RIGHT_ENC_TWO, false, Encoder.EncodingType.k4X);
		this.leftUltra = new Ultrasonic(RobotMap.LEFT_ULTRASONIC, RobotMap.LEFT_ULTRASONIC);
		this.rightUltra = new Ultrasonic(RobotMap.RIGHT_ULTRASONIC, RobotMap.RIGHT_ULTRASONIC);

		this.cubeStick = new Joystick(RobotMap.CUBE_STICK);
		this.driveStick = new Joystick(RobotMap.DRIVE_STICK);

		this.rDrive1 = new Talon(RobotMap.RIGHT_DRIVE1);
		this.rDrive2 = new Talon(RobotMap.RIGHT_DRIVE2);
		this.rDrive3 = new Talon(RobotMap.RIGHT_DRIVE3);
		this.lDrive1 = new Talon(RobotMap.LEFT_DRIVE1);
		this.lDrive2 = new Talon(RobotMap.LEFT_DRIVE2);
		this.lDrive3 = new Talon(RobotMap.LEFT_DRIVE3);

		this.AutonomousModeHandler = new AutonomousModeHandler(rDrive1, rDrive2, rDrive3, lDrive1, lDrive2, lDrive3,
				ahrs, leftEnc, rightEnc);

		this.driveHandler = new DrivetrainHandler(rDrive1, rDrive2, rDrive3, lDrive1, lDrive2, lDrive3, driveStick, leftUltra, rightUltra);
		
		
		driveHandler.init();

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
		SmartDashboard.putNumber("Left Ultrasonic", driveHandler.leftDistance());
		SmartDashboard.putNumber("Right Ultrasonic", driveHandler.rightDistance());

		LoggerData.logData("Current : " + Double.toString(pdp.getCurrent(0)));

		this.driveHandler.driveRobot();

	}

	@Override
	public void testPeriodic() {
	}
}
