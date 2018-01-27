package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.Relay;

public class LEDHandler {
	private Relay green;
	private Relay yellow;
	private Relay red;

	public LEDHandler() {
		green = new Relay(RobotMap.GREEN_LED);
		yellow = new Relay(RobotMap.YELLOW_LED);
		red = new Relay(RobotMap.RED_LED);
	}

	public void onGreen() {
		green.set(Relay.Value.kOn);
	}

	public void offGreen() {
		green.set(Relay.Value.kOff);
	}

	public void onYellow() {
		yellow.set(Relay.Value.kOn);
	}

	public void offYellow() {
		yellow.set(Relay.Value.kOff);
	}

	public void onRed() {
		red.set(Relay.Value.kOn);
	}

	public void offRed() {
		red.set(Relay.Value.kOff);
	}

}
