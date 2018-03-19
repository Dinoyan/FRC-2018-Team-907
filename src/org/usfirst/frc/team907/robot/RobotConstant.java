package org.usfirst.frc.team907.robot;

public class RobotConstant {
	
	// ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT ROBOT
	public static double DRIVE_WHEEL_SIZE = 4.0;

	// AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO AUTO
	// Auto Distances
	public static double DEFAULT_AUTO_DISTANCE = 0.0;
	
	
	// Auto position.
	public static final String DEFAULT = "Default";
	public static final String LEFT_POS = "Left";
	public static final String RIGHT_POS = "Right";
	public static final String CENTER_POS = "Center";
	public static final String AUTO_RUN = "Auto Run";
		
	// priority position
	public static final String SCALE = "Scale"; // #bigone
	public static final String SWITCH = "Switch";
	public static final String CLIMB = "Climb";
	public static final String ORIGIN = "Origin";
	
	// DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN 
	// Teleop Driving Constants
	public static double DRIVE_MAX_ANGLE = 4.0;
	public static double DRIVE_ZERO_SPEED = 0.0;
	public static double DRIVE_TURNING_SPEED = 0.80;
	public static double DRIVE_TURNING_SPEED2 = 0.5;
	public static double DRIVE_DRIVING_SPEED = 0.60;
	//public static double TURNING_GAIN = 0.5;


	// INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKEINTAKE INTAKE INTAKE INTAKE
	// Motor Speeds
	public static double INTAKE_ZERO_SPEED = 0.0;
	public static double INTAKE_VOMIT_SPEED = -0.4;
	public static double INTAKE_PICKUP_SPEED = 0.5;

	// Boolean States
	public static boolean OPEN_INTAKE = true;
	public static boolean CLOSE_INTAKE = false;
	
	
	// ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR
	
	// Elevator values
	public static double ELEVATOR_ORIGIN_VALUE = 0.0;
	public static double ELEVATOR_SCALE_VALUE = 200;
	public static double ELEVATOR_CLIMB_VALUE = 0.0;
	public static double ELEVATOR_SWITCH_VALUE = 550;

	
	// Motor Speeds
	public static double ELEV_SWITCH_MAX_SPEED = 0.40;
	public static double ELEV_SCALE_MAX_SPEED = 0.60;
	public static double ELEV_CLIMB_MAX_SPEED = 0.80;
	public static double ELEV_ORIGIN_MAX_SPEED = 0.20;
	public static double ELEV_MIN_SPEED = 0.25;
	public static double ELEV_ZERO_SPEED = 0.0;


	// SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS
	// Encoder units
	public static double DRIVE_ENC_UNITS = 256.0 * 42.0 / 36.0;
	public static double ELEVATOR_ENC_UNITS = 256.0;

	// gyro
	public static double LEFT_TURN_ANGLE = 0.0;
	public static double RIGHT_TURN_ANGLE = 0.0;
	
	
	
	
	//TALONSRX STUFF
	
	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/*
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;
	
	/* choose so that Talon does not report sensor out of phase */
	public static boolean kSensorPhase = true;

	/* choose based on what direction you want to be positive,
		this does not affect motor invert. */
	public static boolean kMotorInvert = false;

		
}
