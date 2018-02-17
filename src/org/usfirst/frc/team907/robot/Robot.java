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
import edu.wpi.first.wpilibj.Joystick;
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
	
	// TalonSRX test code.
	TalonSRX _talon = new TalonSRX(2);
	Joystick _joy = new Joystick(3);
	boolean _lastButton1 = false;
	/** save the target position to servo to */
	double targetPositionRotations;
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
		
		//TALONSRX test code:
		/* choose the sensor and sensor direction */
		_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
				Constants.kTimeoutMs);

		/* choose to ensure sensor is positive when output is positive */
		_talon.setSensorPhase(Constants.kSensorPhase);

		/* choose based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. */ 
		_talon.setInverted(Constants.kMotorInvert);

		/* set the peak and nominal outputs, 12V means full */
		_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		_talon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		/* set closed loop gains in slot0, typically kF stays zero. */
		_talon.config_kF(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kPIDLoopIdx, 0.1, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);

		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = _talon.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (Constants.kSensorPhase)
			absolutePosition *= -1;
		if (Constants.kMotorInvert)
			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		_talon.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

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
		
		if (sensorHandler.getElevSwitchOneStatus()) {
			this.sensorHandler.elevEncReset();
			elevator.emergencyStop();
		}
		
		if(sensorHandler.getElevSwitchTwoStatus()) {
			elevator.emergencyStop();
		}
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

			//DataLogger.logData("Left Ultrasonic : " + Double.toString(sensorHandler.getLeftRange()));
			//DataLogger.logData("Right Ultrasonic : " + Double.toString(sensorHandler.getRightRange()));
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
		
		
		commonLoop();
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
		//SmartDashboard.putNumber("Left Ultrasonic", sensorHandler.getLeftRange());
		//SmartDashboard.putNumber("Right Ultrasonic", sensorHandler.getRightRange());
		SmartDashboard.putNumber("Left Encoder", sensorHandler.getLeftDistance());
		SmartDashboard.putNumber("Right Encoder", sensorHandler.getRightDistance());
		SmartDashboard.putNumber("Angle", sensorHandler.getAhrs().getAngle());
		SmartDashboard.putBoolean("Max Height" , sensorHandler.getElevSwitchTwoStatus());
		SmartDashboard.putBoolean("Starting Pos", sensorHandler.getElevSwitchOneStatus());
	}
	
	public void disabledPeriodic() {
		commonLoop();
	}
	
	
	void commonLoop() {
		/* get gamepad axis */
		double leftYstick = _joy.getY();
		double motorOutput = _talon.getMotorOutputPercent();
		boolean button1 = _joy.getRawButton(1);
		boolean button2 = _joy.getRawButton(2);
		/* deadband gamepad */
		if (Math.abs(leftYstick) < 0.10) {
			/* within 10% of zero */
			leftYstick = 0;

		}
		/* on button1 press enter closed-loop mode on target position */
		if (!_lastButton1 && button1) {
			/* Position mode - button just pressed */

			/* 10 Rotations * 4096 u/rev in either direction */
			targetPositionRotations = leftYstick * 10.0 * 4096;
			_talon.set(ControlMode.Position, targetPositionRotations);

		}
		/* on button2 just straight drive */
		if (button2) {
			/* Percent voltage mode */
			_talon.set(ControlMode.PercentOutput, leftYstick);
		}
		_lastButton1 = button1;
	}
	
	
	
}
