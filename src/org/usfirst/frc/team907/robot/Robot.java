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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private String gameData;
	
	public PowerDistributionPanel pdp;

	public Joystick driveStick;
	public Joystick cubeStick;
	public Talon rDrive1;
	public Talon rDrive2;
	public Talon rDrive3;
	public Talon lDrive1;
	public Talon lDrive2;
	public Talon lDrive3;

	public AutonomousModeHandler AutoHandler;
	public DrivetrainHandler driveHandler;

	@Override
	public void robotInit() {
		// Dashboard auto chooser
		m_chooser.addDefault("Right Auto", RobotMap.RIGHT);
		m_chooser.addObject("Left Auto", RobotMap.LEFT);
		m_chooser.addObject("Center Auto", RobotMap.CENTER);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		this.pdp = new PowerDistributionPanel();

		this.driveStick = new Joystick(RobotMap.DRIVE_STICK);
		this.cubeStick = new Joystick(RobotMap.CUBE_STICK);

		this.rDrive1 = new Talon(RobotMap.RIGHT_DRIVE1);
		this.rDrive2 = new Talon(RobotMap.RIGHT_DRIVE2);
		this.rDrive3 = new Talon(RobotMap.RIGHT_DRIVE3);
		this.lDrive1 = new Talon(RobotMap.LEFT_DRIVE1);
		this.lDrive2 = new Talon(RobotMap.LEFT_DRIVE2);
		this.lDrive3 = new Talon(RobotMap.LEFT_DRIVE3);

		this.AutoHandler = new AutonomousModeHandler();
		
		this.driveHandler = new DrivetrainHandler(rDrive1, rDrive2, rDrive3, 
				lDrive1, lDrive2, lDrive3, driveStick);

	}

	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);

		// Game Data from the field.
		this.gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	@Override
	public void autonomousPeriodic() {
		this.AutoHandler.AudoMode(m_autoSelected, gameData);

	}

	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Current", pdp.getCurrent(0));
		
		this.driveHandler.driveRobot();
		
	}

	@Override
	public void testPeriodic() {
	}
}
