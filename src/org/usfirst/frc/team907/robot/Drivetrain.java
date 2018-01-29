package org.usfirst.frc.team907.robot;

public class Drivetrain {

	double left, right;
	double zero = RobotConstant.ZERO_SPEED;

	public void driveRobot(JoystickHandler joystickHandler, MultiSpeedController multiSpeedController) {

		double driveFront = joystickHandler.getDriveStick().getRawAxis(3);
		double driveBack = -joystickHandler.getDriveStick().getRawAxis(2);
		double turn = joystickHandler.getDriveStick().getRawAxis(4);

		if (driveFront > zero && driveBack == zero) {

			// calculations to output the required speed for the drive motors
			left = (driveFront + turn) + skim(driveFront - turn);
			right = (driveFront - turn) + skim(driveFront + turn);

			// moves the robot
			moveRobot(multiSpeedController, left, -right);

		} else if (driveBack < zero && driveFront == zero) {
			
			// calculations to output the required speed for the drive motors
			left = driveBack + turn + skim(driveBack - turn);
			right = driveBack - turn + skim(driveBack + turn);

			// move the robot
			moveRobot(multiSpeedController, left, -right);

		} else if(turn < zero && turn > zero && driveBack == zero && driveFront == zero){
			
			// turns the robot while standing still
			moveRobot(multiSpeedController, turn, turn);
			
		} else {
			moveRobot(multiSpeedController, RobotConstant.ZERO_SPEED, RobotConstant.ZERO_SPEED);
		}

		System.out.println("Hell Yeah!!!!!!");
	}

	public void moveRobot(MultiSpeedController multiSpeedController, Double left, Double right) {

		// sets the speed to power the drive motors
		multiSpeedController.getlDrive1().set(left);
		multiSpeedController.getlDrive2().set(left);

		multiSpeedController.getrDrive1().set(right);
		multiSpeedController.getrDrive2().set(right);

	}

	public double skim(double v) {
		if (v > 1.0) {
			return -((v - 1.0) * RobotConstant.TURNING_GAIN);
		} else if (v < -1.0) {
			return -((v + 1.0) * RobotConstant.TURNING_GAIN);
		}
		return 0;
	}

}
