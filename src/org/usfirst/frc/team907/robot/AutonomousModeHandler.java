package org.usfirst.frc.team907.robot;

public class AutonomousModeHandler {

	public AutonomousModeHandler() {

	}

	public void AudoMode(String position, String gameData) {

		switch (position) {
		case RobotMap.LEFT:
			if(gameData.charAt(0) == 'L') {
				
				
			} else if (gameData.charAt(1) == 'L') {
				
				
			}
			
			break;
		case RobotMap.RIGHT:
			if(gameData.charAt(0) == 'R') {
				
				
			} else if (gameData.charAt(1) == 'R') {
				
				
			}
			
			break;
		case RobotMap.CENTER:
			if(gameData.charAt(0) == 'L') {
				
				
			} else {
				
				
			}
			
			break;
		}

	}

}
