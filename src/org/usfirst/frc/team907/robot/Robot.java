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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private String m_autoSelected;
	private String m_priority;
	private SendableChooser<String> auto_chooser = new SendableChooser<>();
	private String gameData;
	private String testSub;
	private Drivetrain drivetrain;
	private JoystickHandler joystickHandler;
	private AutonomousModeHandler AutonomousModeHandler;
	private static SensorHandler sensorHandler;
	private Elevator elevator;
	private Intake intake;
	private LEDHandler led;
	private IntakePID intakePID;
	private PowerDistributionPanel pdp;
	private Timer time;
	private int _loops = 0;
	
	
	@Override
	public void robotInit() {
		// Dashboard auto chooser
		auto_chooser.addDefault("Center Auto", RobotConstant.CENTER_POS);
		auto_chooser.addObject("Right Auto", RobotConstant.RIGHT_POS);
		auto_chooser.addObject("Left Auto", RobotConstant.LEFT_POS);
		auto_chooser.addObject("Auto Run", RobotConstant.AUTO_RUN);	
		SmartDashboard.putData("Auto choices", auto_chooser);
		
	
		sensorHandler = new SensorHandler();
		joystickHandler = new JoystickHandler();
		led = new LEDHandler();
		drivetrain = new Drivetrain(joystickHandler);
		intake = new Intake(sensorHandler, joystickHandler);
		elevator = new Elevator(sensorHandler, joystickHandler);
		
		AutonomousModeHandler = new AutonomousModeHandler(drivetrain, sensorHandler, elevator, intake);
		CameraServer.getInstance().startAutomaticCapture();
		
		time = new Timer();
	}

	@Override
	public void autonomousInit() {
		
		time.start();
		
		sensorHandler.getAhrs().reset();
		sensorHandler.driveEncReset();
		sensorHandler.elevEncReset();

		m_autoSelected = auto_chooser.getSelected();
		System.out.println("Auto selected: " + m_autoSelected);
		DataLogger.logData(m_autoSelected);
		
		intakePID = new IntakePID(SmartDashboard.getNumber("intake P", 0.0),SmartDashboard.getNumber("intake I", 0.0),
				SmartDashboard.getNumber("intake D", 0.0),SmartDashboard.getNumber("Setpoint", 0.0));
		
		intakePID.enable();

		// Game Data from the field.
		this.gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		led.offGreen();
		led.offRed();
		led.offYellow();
	}

	@Override
	public void autonomousPeriodic() {
		
		
		AutonomousModeHandler.AudoModeSelect(m_autoSelected, m_priority, gameData);
	
		updateDashboard();
		if(SmartDashboard.getNumber("intake P", 0.0) != intakePID.getPIDController().getP() ||
				SmartDashboard.getNumber("intake I", 0.0) != intakePID.getPIDController().getI() ||
				SmartDashboard.getNumber("intake D", 0.0) != intakePID.getPIDController().getD() ||
				SmartDashboard.getNumber("Setpoint", 0.0) != intakePID.getPIDController().getSetpoint()) {
			intakePID.disable();
			intakePID = new IntakePID(SmartDashboard.getNumber("intake P", 0.0),SmartDashboard.getNumber("intake I", 0.0),
					SmartDashboard.getNumber("intake D", 0.0),SmartDashboard.getNumber("Setpoint", 0.0));
			intakePID.enable();			
		}
	}
	

	@Override
	public void teleopInit() {
		//sensorHandler.driveEncReset();
		sensorHandler.getAhrs().reset();
		
		led.onRed();
		led.offRed();
		led.offYellow();
		
		sensorHandler.elevEncReset();
	}

	@Override
	public void teleopPeriodic() {
		updateDashboard();
		drivetrain.driveRobot();
		elevator.operateElevator();
		intake.operateIntake();
			
		if (!sensorHandler.getElevSwitchOneStatus()) {
			this.sensorHandler.elevEncReset();
			
		}
		
		if(!sensorHandler.getElevSwitchTwoStatus()) {
			elevator.emergencyStop();
			
		}
	}
	
	@Override
	public void testInit() {
	}
	@Override
	public void testPeriodic() {
		updateDashboard();
	}

	public static void updateDashboard() {
		//SmartDashboard.putNumber("PDP Current", pdp.getCurrent(0));
		//SmartDashboard.putNumber("Left Ultrasonic", sensorHandler.getLeftRange());
		//SmartDashboard.putNumber("Right Ultrasonic", sensorHandler.getRightRange());
		SmartDashboard.putNumber("ELEVATOR ENC", sensorHandler.getElevDistance());
		SmartDashboard.putNumber("Left Encoder", sensorHandler.getLeftDistance());
		SmartDashboard.putNumber("Right Encoder", sensorHandler.getRightDistance());
		SmartDashboard.putNumber("Angle", sensorHandler.getAhrs().getAngle());
		SmartDashboard.putNumber("Elevator_Encoder", sensorHandler.getElevDistance());
		SmartDashboard.putBoolean("Max Height" , sensorHandler.getElevSwitchTwoStatus());
		SmartDashboard.putBoolean("Starting Pos", sensorHandler.getElevSwitchOneStatus());
	}
	
	public void disabledPeriodic() {
		
	}
}
