/*----------------------------------------------------------------------------*/
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
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private String gameData;
	

	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", RobotMap.DEFAULT_AUTO);
		m_chooser.addObject("My Auto", RobotMap.CUSTOM_AUTO);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		// Game Data from the field.
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}

	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}


	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case RobotMap.CUSTOM_AUTO:
				// Put custom auto code here
				break;
			case RobotMap.DEFAULT_AUTO:
			default:
				// Put default auto code here
				break;
		}
	}

	@Override
	public void teleopPeriodic() {
	}

	
	@Override
	public void testPeriodic() {
	}
}
