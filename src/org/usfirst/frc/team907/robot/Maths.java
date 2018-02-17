package org.usfirst.frc.team907.robot;

public class Maths {
	
	// calculate the speed for the elevator for switch/scale
	public static double calculateElevSpeed(SensorHandler sensorHandler, String pos) {
		
		// store the positon (scale or switch)
		String scale = RobotConstant.SCALE;
		String switchh = RobotConstant.SWITCH;	
		String origin = RobotConstant.ORIGIN;
		String climb = RobotConstant.CLIMB;
		double enc_value = sensorHandler.getElevDistance();
		double scale_value = RobotConstant.ELEVATOR_SCALE_VALUE;
		double switch_value = RobotConstant.ELEVATOR_SWITCH_VALUE;
		double origin_value = RobotConstant.ELEVATOR_ORIGIN_VALUE;
		double value = 0;
		double bottom_speed = 0;
		double top_speed = 0;
		double max_spins = 0;
		double constant = 0;
		
		if(pos == scale) {
			// storing the values
			bottom_speed = RobotConstant.ELEV_SCALE_MAX_SPEED;
			top_speed = RobotConstant.ELEV_SCALE_MIN_SPEED;
			max_spins = scale_value;
			constant = RobotConstant.ELEV_UP_CONSTANT;
			
		} else if(pos == switchh) {
			
			bottom_speed = RobotConstant.ELEV_SCALE_MAX_SPEED;
			top_speed = RobotConstant.ELEV_SCALE_MIN_SPEED;
			max_spins = switch_value;
			constant = RobotConstant.ELEV_UP_CONSTANT;
			
		} else if(pos == origin) {
			
			if(enc_value == scale_value) {
				top_speed = RobotConstant.ELEV_SCALE_MAX_SPEED;
				max_spins = scale_value;
			} else if(enc_value == switch_value) {
				top_speed = RobotConstant.ELEV_SWITCH_MAX_SPEED;
				max_spins = switch_value;
				constant = RobotConstant.ELEV_DOWN_CONSTANT;
			}
			bottom_speed = RobotConstant.ELEV_ORIGIN_MIN_SPEED; 
			
		} else if(pos == climb) {
			
			constant = RobotConstant.ELEV_UP_CONSTANT;
			
		}
		
		
		// calculate the speed difference
		double speed_diff = bottom_speed - top_speed;
		
		// calculate the slope of the equation
		double slope = speed_diff/max_spins;
		
		// calculate the final value
		value = (slope * enc_value) + constant;
		
		// return the final value
		return value;	
		
	}

}
