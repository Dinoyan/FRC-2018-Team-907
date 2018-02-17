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
		
	// priority position
	public static final String SCALE = "Scale";
	public static final String SWITCH = "Switch";
	
	// DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN DRIVETRAIN 
	// Teleop Driving Constants
	public static double DRIVE_MAX_ANGLE = 4.0;
	public static double DRIVE_ZERO_SPEED = 0.0;
	public static double DRIVE_TURNING_SPEED = 0.25;
	public static double DRIVE_DRIVING_SPEED = 0.50;
	//public static double TURNING_GAIN = 0.5;


	// INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKE INTAKEINTAKE INTAKE INTAKE INTAKE
	// Motor Speeds
	public static double INTAKE_ZERO_SPEED = 0.0;
	public static double INTAKE_VOMIT_SPEED = -0.5;
	public static double INTAKE_PICKUP_SPEED = 0.5;

	// Boolean States
	public static boolean OPEN_INTAKE = true;
	public static boolean CLOSE_INTAKE = false;
	
	
	// ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR ELEVATOR
	// Elevator Constants
	public static double ELEV_TRANSFORM_CONSTANT = 1.0; 
	
	// Elevator values
	public static double ELEVATOR_START_VALUE = 0.0;
	public static double ELEVATOR_SCALE_VALUE = 0.0;
	public static double ELEVATOR_CLIMB_VALUE = 0.0;
	public static double ELEVATOR_SWITCH_VALUE = 0.0;

	
	// Motor Speeds
	public static double ELEVATOR_ZERO_SPEED = 0.0;
	public static double ELEV_CLIMB_MAX_SPEED = 1.0;
	public static double ELEV_CLIMB_MIN_SPEED = 0.1;
	public static double ELEV_SCALE_MAX_SPEED = 1.0;
	public static double ELEV_SCALE_MIN_SPEED = 0.1;
	public static double ELEV_SWITCH_MAX_SPEED = 1.0;
	public static double ELEV_SWITCH_MIN_SPEED = 0.1;

	// SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS SENSORS
	// Encoder units
	public static double DRIVE_ENC_UNITS = 256.0;
	public static double ELEVATOR_ENC_UNITS = 256.0;

	// gyro
	public static double LEFT_TURN_ANGLE = 0.0;
	public static double RIGHT_TURN_ANGLE = 0.0;

		
}
