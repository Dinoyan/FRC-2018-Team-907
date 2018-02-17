package org.usfirst.frc.team907.robot;

public class Maths {
	
	// calculate the speed for the elevator for switch/scale
	public double calculateElevSpeed(SensorHandler sensorHandler, String pos) {
		
		// store the positon (scale or switch)
		String scale = RobotConstant.SCALE;
		String switchh = RobotConstant.SWITCH;	
		double enc_value = sensorHandler.getElevDistance();
		double value = 0;
		
		if(pos == scale) {
			// storing the values
			double max_speed = RobotConstant.ELEV_SCALE_MAX_SPEED;
			double min_speed = RobotConstant.ELEV_SCALE_MIN_SPEED;
			double max_spins = RobotConstant.ELEVATOR_SCALE_VALUE;
			double constant = RobotConstant.ELEV_TRANSFORM_CONSTANT;
			
			// calculate the speed difference
			double speed_diff = max_speed - min_speed;
			
			// calculate the slope of the equation
			double slope = -(speed_diff/max_spins);
			
			// calculate the final value
			value = (slope * enc_value) + constant;
			
		}
		
		// return the final value
		return value;	
		
	}

}
