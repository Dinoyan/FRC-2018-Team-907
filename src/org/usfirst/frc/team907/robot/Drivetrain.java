package org.usfirst.frc.team907.robot;

public class Drivetrain {

	double left, right, t_left, t_right;

	public void driveRobot(JoystickHandler joystickHandler, MultiSpeedController multiSpeedController) {

		double driveFront = joystickHandler.getDriveStick().getRawAxis(3);
		double driveback = -joystickHandler.getDriveStick().getRawAxis(2);
		double turn = joystickHandler.getDriveStick().getRawAxis(4);

		if (driveFront > 0.0 && driveback == 0.0) {
			t_left = driveFront + turn;
			t_right = driveFront - turn;

			left = t_left + skim(t_right);
			right = t_right + skim(t_left);

			moveRobot(multiSpeedController, left, -right);

		} else if (driveback < 0.0 && driveFront == 0.0) {

			t_left = driveback + turn;
			t_right = driveback - turn;

			left = t_left + skim(t_right);
			right = t_right + skim(t_left);

			moveRobot(multiSpeedController, left, -right);

		} else {
			moveRobot(multiSpeedController, RobotConstant.STOP_ROBOT, RobotConstant.STOP_ROBOT);
		}

		System.out.println("Hell Yeah!!!!!!");
	}

	public void moveRobot(MultiSpeedController multiSpeedController, Double left, Double right) {

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
