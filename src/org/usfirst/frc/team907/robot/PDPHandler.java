package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class PDPHandler {

	public PowerDistributionPanel pdp;

	public  PDPHandler() {
		pdp = new PowerDistributionPanel();
	}

	public PowerDistributionPanel getPdp() {
		return pdp;
	}

	public double getCurrent() {
		return pdp.getCurrent(0);
	}
}
