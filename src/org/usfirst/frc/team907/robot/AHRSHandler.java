package org.usfirst.frc.team907.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;

public class AHRSHandler {
	
	private AHRS ahrs;
	
	public void init() {
		ahrs = new AHRS(SerialPort.Port.kMXP);
	}
	
	public void reset() {
		ahrs.reset();
	}

	public AHRS getAhrs() {
		return ahrs;
	}

}
