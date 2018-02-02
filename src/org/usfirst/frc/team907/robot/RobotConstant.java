package org.usfirst.frc.team907.robot;

public class RobotConstant {

	public static double DEFAULT_AUTO_DISTANCE = 0.0;
	
	
	// Auto position.
	public static final String RIGHT_POS = "Right";
	public static final String CENTER_POS = "Center";
	public static final String LEFT_POS = "Left";
	public static final String DEFAULT = "Default";
		
	// priority position
	public static final String SCALE = "Scale";
	public static final String SWITCH = "Switch";

		
	// gyro
	public static double RIGHT_TURN_ANGLE = 0.0;
	public static double LEFT_TURN_ANGLE = 0.0;

	// Turning sensitivity for driving
	public static double TURNING_GAIN = 0.5;
	
	// Robot Drive Speeds
	public static double ZERO_SPEED = 0.0;

	// Intake Speeds
	public static double PICKUP_SPEED = 0.5;
	public static double STOP_INTAKE_MOTORS = 0.0;
	public static double VOMIT_SPEED = -0.5;

	// Intake Boolean States
	public static boolean OPEN_INTAKE = true;
	public static boolean CLOSE_INTAKE = false;

	// Elevator intake values
	public static double ELEVATOR_START_VALUE = 0.0;
	public static double ELEVATOR_SWITCH_VALUE = 0.0;
	public static double ELEVATOR_SCALE_VALUE = 0.0;
	public static double ELEVATOR_CLIMB_VALUE = 0.0;

	// Drive encoder units
	public static double ENC_UNITS = 256.0;

	// Ulra vals
	public static double ULTRA_CALC = 106.23 + 0.3973;
}
