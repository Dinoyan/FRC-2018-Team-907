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
	//private SendableChooser<String> priority_chooser = new SendableChooser<>();
	
	//private SendableChooser<String> testSubsystems = new SendableChooser<>();
	
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

	
	//private PowerDistributionPanel pdp;
	
	// TalonSRX test code
	/*/.
	TalonSRX _talon = new TalonSRX(2);
	Joystick _joy = new Joystick(3);
	boolean _lastButton1 = false;
	/** save the target position to servo to */
	//double targetPositionRotations;
	private int _loops = 0;
	

	@Override
	public void robotInit() {
		// Dashboard auto chooser
		auto_chooser.addDefault("Center Auto", RobotConstant.CENTER_POS);
		auto_chooser.addObject("Right Auto", RobotConstant.RIGHT_POS);
		auto_chooser.addObject("Left Auto", RobotConstant.LEFT_POS);
		
		// Dashboard priority chooser
		//priority_chooser.addDefault("Switch", RobotConstant.SWITCH);
		//priority_chooser.addObject("Scale", RobotConstant.SCALE);
		//priority_chooser.addObject("Default Auto", RobotConstant.DEFAULT);
			
		SmartDashboard.putData("Auto choices", auto_chooser);
		
		
		
		//SmartDashboard.putData("Subsystem Tester", testSubsystems);
		//testSubsystems.addDefault("Drivetrain" , "drivetrain");
		//testSubsystems.addDefault("Elevator" , "elevator");
		//testSubsystems.addDefault("Intake" , "intake");

		
		sensorHandler = new SensorHandler();
		joystickHandler = new JoystickHandler();
		led = new LEDHandler();
		
		drivetrain = new Drivetrain(joystickHandler);
		intake = new Intake(sensorHandler, joystickHandler);
		elevator = new Elevator(sensorHandler, joystickHandler);
		
		time = new Timer();
		
		//pdp = new PowerDistributionPanel();

		AutonomousModeHandler = new AutonomousModeHandler(drivetrain, sensorHandler, elevator, intake);
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void autonomousInit() {
		
		time.start();
		
		sensorHandler.getAhrs().reset();
		sensorHandler.driveEncReset();
		sensorHandler.elevEncReset();

		m_autoSelected = auto_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
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
		
		// Run the auto handler.
		
		/*while(sensorHandler.getElevDistance() < 2000) {
			elevator.operateElevator(-1.0);
			updateDashboard();
		}
		elevator.operateElevator(-0.15);*/
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
		
		//TuneIntakePID();
		
		/*
		if (sensorHandler.getElevSwitchOneStatus()) {
			this.sensorHandler.elevEncReset();
			elevator.emergencyStop();
		}
		
		if(sensorHandler.getElevSwitchTwoStatus()) {
			elevator.emergencyStop();
		}*/
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
		/*
		if (++_loops >= 10) {
			_loops = 0;
			

			//DataLogger.logData("Left Ultrasonic : " + Double.toString(sensorHandler.getLeftRange()));
			//DataLogger.logData("Right Ultrasonic : " + Double.toString(sensorHandler.getRightRange()));
		}*/
		updateDashboard();
		//SmartDashboard.putNumber("TalonSRX", intake.getPivotEncoderValues());
		drivetrain.driveRobot();
		elevator.operateElevator();
		intake.operateIntake();
		
		//elevator.ElevatorSetPoints();
		//pdp.clearStickyFaults();
		
	
		
		
		/*
	
		if (elevator.readyToClimb()) {
			led.onGreen();
			led.offRed();
			joystickHandler.vibrateDriveStick();
			joystickHandler.vibrateDriveStick();
		} else {
			led.onRed();
			led.offRed();
		}*/
		
		
		
		if (!sensorHandler.getElevSwitchOneStatus()) {
			this.sensorHandler.elevEncReset();
			
		}
		
		if(!sensorHandler.getElevSwitchTwoStatus()) {
			elevator.emergencyStop();
			
		}
		
		
		
		//commonLoop();
	}
	
	@Override
	public void testInit() {
		//testSub = testSubsystems.getSelected();
		
	}
	@Override
	public void testPeriodic() {
		updateDashboard();
		
		//if(testSub.equals("intake")) {
			
	//	}
		
		
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
		//commonLoop();
		/*
		intakePID.disable();
		if(joystickHandler.getDriveStick().getRawButton(1)) {
			sensorHandler.driveEncReset();
			sensorHandler.elevEncReset();
			intake.resetPivot();
		}*/
	//}
	
	/*
	void commonLoop() {
		/* get gamepad axis */
	/*
		double leftYstick = _joy.getY();
		double motorOutput = _talon.getMotorOutputPercent();
		boolean button1 = _joy.getRawButton(1);
		boolean button2 = _joy.getRawButton(2);
		/* deadband gamepad */
	/*
		if (Math.abs(leftYstick) < 0.10) {
			/* within 10% of zero */
			//leftYstick = 0;

		//}
		/* on button1 press enter closed-loop mode on target position */
/*
		if (!_lastButton1 && button1) {
			/* Position mode - button just pressed */

			/* 10 Rotations * 4096 u/rev in either direction */
			//targetPositionRotations = leftYstick * 10.0 * 4096;
			//_talon.set(ControlMode.Position, targetPositionRotations);

		//}
		/* on button2 just straight drive */
		//if (button2) {
			/* Percent voltage mode */
			//_talon.set(ControlMode.PercentOutput, leftYstick);
		}
		//_lastButton1 = button1;
	//}
	
	
	
}
