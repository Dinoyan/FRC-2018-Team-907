/*----------------------------------------------------------------------------*/
/* @author - Dinoyan Ganeshalingam, Manish Suresh
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private String m_autoSelected;
	private String m_priority;
	private SendableChooser<String> auto_chooser = new SendableChooser<>();
	private SendableChooser<String> priority_chooser = new SendableChooser<>();
	
	private SendableChooser<String> testSubsystems = new SendableChooser<>();
	
	private String gameData;
	private String testSub;

	private Drivetrain drivetrain;
	private JoystickHandler joystickHandler;
	private AutonomousModeHandler AutonomousModeHandler;
	private SensorHandler sensorHandler;
	private Elevator elevator;
	private Intake intake;
	private LEDHandler led;
	
	//private PowerDistributionPanel pdp;

	private int _loops = 0;

	@Override
	public void robotInit() {
		// Dashboard auto chooser
		auto_chooser.addDefault("Center Auto", RobotConstant.CENTER_POS);
		auto_chooser.addObject("Right Auto", RobotConstant.RIGHT_POS);
		auto_chooser.addObject("Left Auto", RobotConstant.LEFT_POS);
		
		// Dashboard priority chooser
		priority_chooser.addDefault("Switch", RobotConstant.SWITCH);
		priority_chooser.addObject("Scale", RobotConstant.SCALE);
		priority_chooser.addObject("Default Auto", RobotConstant.DEFAULT);
			
		SmartDashboard.putData("Auto choices", auto_chooser);
		
		
		SmartDashboard.putData("Subsystem Tester", testSubsystems);
		testSubsystems.addDefault("Drivetrain" , "drivetrain");
		testSubsystems.addDefault("Elevator" , "elevator");
		testSubsystems.addDefault("Intake" , "intake");

		
		sensorHandler = new SensorHandler();
		joystickHandler = new JoystickHandler();
		led = new LEDHandler();
		
		drivetrain = new Drivetrain(joystickHandler);
		intake = new Intake(sensorHandler, joystickHandler);
		elevator = new Elevator(sensorHandler, joystickHandler);
		
		
		//pdp = new PowerDistributionPanel();

		AutonomousModeHandler = new AutonomousModeHandler(drivetrain, sensorHandler);
		
		CameraServer.getInstance().startAutomaticCapture();

	}

	@Override
	public void autonomousInit() {
		sensorHandler.getAhrs().reset();
		sensorHandler.driveEncReset();
		sensorHandler.elevEncReset();

		m_autoSelected = auto_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		DataLogger.logData(m_autoSelected);

		// Game Data from the field.
		this.gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		led.offGreen();
		led.offRed();
		led.offYellow();
	}

	@Override
	public void autonomousPeriodic() {
		// Run the auto handler.
		AutonomousModeHandler.AudoModeSelect(m_autoSelected, m_priority, gameData);
		updateDashboard();
	}

	@Override
	public void teleopInit() {
		sensorHandler.driveEncReset();
		sensorHandler.getAhrs().reset();
		
		led.onRed();
		led.offRed();
		led.offYellow();
	}

	@Override
	public void teleopPeriodic() {
		if (++_loops >= 10) {
			_loops = 0;
			updateDashboard();

			DataLogger.logData("Left Ultrasonic : " + Double.toString(sensorHandler.getLeftRange()));
			DataLogger.logData("Right Ultrasonic : " + Double.toString(sensorHandler.getRightRange()));
		}

		drivetrain.driveRobot();
		elevator.operateElevator();
		intake.operateIntake();
		
		if (elevator.readyToClimb()) {
			led.onGreen();
			led.offRed();
			joystickHandler.vibrateDriveStick();
			joystickHandler.vibrateDriveStick();
		} else {
			led.onRed();
			led.offRed();
		}
		
		
		if (sensorHandler.getElevSwitchOneStatus()) {
			this.sensorHandler.elevEncReset();
			elevator.emergencyStop();
		}
		
		if(sensorHandler.getElevSwitchTwoStatus()) {
			elevator.emergencyStop();
		}
	}
	
	@Override
	public void testInit() {
		testSub = testSubsystems.getSelected();
		
	}
	@Override
	public void testPeriodic() {
		updateDashboard();
		
		if(testSub.equals("intake")) {
			
		}
		
		
	}

	private void updateDashboard() {
		//SmartDashboard.putNumber("PDP Current", pdp.getCurrent(0));
		SmartDashboard.putNumber("Left Ultrasonic", sensorHandler.getLeftRange());
		SmartDashboard.putNumber("Right Ultrasonic", sensorHandler.getRightRange());
		SmartDashboard.putNumber("Left Encoder", sensorHandler.getLeftDistance());
		SmartDashboard.putNumber("Right Encoder", sensorHandler.getRightDistance());
		SmartDashboard.putNumber("Angle", sensorHandler.getAhrs().getAngle());
		SmartDashboard.putBoolean("Max Height" , sensorHandler.getElevSwitchTwoStatus());
		SmartDashboard.putBoolean("Starting Pos", sensorHandler.getElevSwitchOneStatus());
	}
}
