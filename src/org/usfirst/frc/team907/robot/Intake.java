package org.usfirst.frc.team907.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {

	private PowerDistributionPanel pdp2;
	
	private Solenoid openIntake;
	private Solenoid closeIntake;

	private Talon rightIntake;
	private Talon leftIntake;
	private static TalonSRX pivot;

	private SensorHandler sensorHandler;
	private JoystickHandler joystickHandler;
	
	private boolean gotCube;

	public Intake(SensorHandler sensorHandler, JoystickHandler joystickHandler) {
		openIntake = new Solenoid(RobotMap.OPEN_INTAKE);
		closeIntake = new Solenoid(RobotMap.CLOSE_INTAKE);

		rightIntake = new Talon(RobotMap.RIGHT_INTAKE);
		leftIntake = new Talon(RobotMap.LEFT_INTAKE);
		pivot = new TalonSRX(RobotMap.INTAKE_PIVOT);
		
		//pivot.setSafetyEnabled(true);
		pdp2 = new PowerDistributionPanel();
		this.sensorHandler = sensorHandler;
		this.joystickHandler = joystickHandler;
		
		this.gotCube = false;
	}

	public void operateIntake() {

		// operates the different actions of the intake based on the buttons pressed
		if (joystickHandler.getCubeStick().getRawButton(1)) {
			// operate the normal intake
			//pickUpCube();
			controlIntakeSolenoids(true);
			
		} else if (joystickHandler.getCubeStick().getRawButton(2)) {
			//vomitCube();
			controlIntakeSolenoids(false);
		} 
		
		if(joystickHandler.getCubeStick().getRawAxis(3) > 0.1 && !gotCube) {
			controlIntakeMotors(joystickHandler.getCubeStick().getRawAxis(3));
			if (pdp2.getCurrent(3) > 14 || pdp2.getCurrent(13) > 14) {
				controlIntakeMotors(0);
				controlIntakeSolenoids(true);
				this.gotCube = true;
			}
			//SmartDashboard.putNumber("pdp current", pdp.getCurrent(3));
		} else if(joystickHandler.getCubeStick().getRawAxis(2) > 0.1) {
			controlIntakeMotors(-joystickHandler.getCubeStick().getRawAxis(2));
			this.gotCube = false;
		} else {
			controlIntakeMotors(0);
		}
		
		
		pivot.set(ControlMode.PercentOutput, joystickHandler.getCubeStick().getRawAxis(1));

	}

	public void pickUpCube() {

		// open the intake
		//controlIntakeSolenoids(RobotConstant.OPEN_INTAKE);

		// Take in the power cube
		//controlIntakeMotors(RobotConstant.INTAKE_PICKUP_SPEED);

		// get the status of photosensor and close the intake based on its reading
		/*if (sensorHandler.getPhotoSensorStatus()) {
			// stop the motors
			controlIntakeMotors(RobotConstant.INTAKE_ZERO_SPEED);

			// close the solenoids
			controlIntakeSolenoids(RobotConstant.CLOSE_INTAKE);
		}*/
	}

	public void vomitCube() {

		// open the intake
		// controlIntakeSolenoids(RobotConstant.OPEN_INTAKE);

		// Take in the power cube
		controlIntakeMotors(RobotConstant.INTAKE_VOMIT_SPEED);
	}

	private void controlIntakeSolenoids(boolean state) {
		openIntake.set(state);
		closeIntake.set(!state);
	}

	private void controlIntakeMotors(double speed) {
		rightIntake.set(speed);
		leftIntake.set(-speed);
	}
	
	
	public static double getPivotEncoderValues() {
		return pivot.getSensorCollection().getQuadraturePosition();
	}
	
	public static void setPivot(double value) {
		pivot.set(ControlMode.PercentOutput, value);
	}
	
	public static void resetPivot() {
		pivot.getSensorCollection().setQuadraturePosition(0, 0);
	}


}
