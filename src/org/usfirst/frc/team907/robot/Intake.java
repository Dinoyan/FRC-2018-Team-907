package org.usfirst.frc.team907.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
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
	
	private boolean toggleOn = false;
	private boolean togglePressed = false;
	
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
		
		pivot.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
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
		
		updateToggle();
		
		if (joystickHandler.getDriveStick().getRawAxis(2) > 0.3) {
			controlIntakeSolenoids(true);
		} else  {
			controlIntakeSolenoids(false);
		}
		
		
		while (joystickHandler.getCubeStick().getRawButton(6) == true){
			controlIntakeMotors(-0.3);
		}
		
		
		//controlIntakeMotors(joystickHandler.getDriveStick().getRawAxis(3));
		
		/*if(toggleOn) {
			if(this.joystickHandler.getCubeStick().getRawButton(1)) {
				controlIntakeSolenoids(true);
			}
		} else {
			if(this.joystickHandler.getCubeStick().getRawButton(1)) {
				controlIntakeSolenoids(false);
			}
		}*/
		
		if(joystickHandler.getDriveStick().getRawAxis(3) > 0.1 ) { //&& !gotCube
			controlIntakeMotors(joystickHandler.getDriveStick().getRawAxis(3)-0.3);
			
			//Timer.delay(1);
			if (pdp2.getCurrent(3) > 14.5 || pdp2.getCurrent(13) > 14.5) {
				controlIntakeMotors(0);
				controlIntakeSolenoids(false);
				this.gotCube = true;
			}
			//SmartDashboard.putNumber("pdp current", pdp.getCurrent(3));
		} else if(joystickHandler.getCubeStick().getRawAxis(2) > 0.1) {
			controlIntakeMotors((-joystickHandler.getCubeStick().getRawAxis(2))-0.5);
			this.gotCube = false;
			//Timer.delay(1);
			//controlIntakeSolenoids(false);
			
		} else {
			controlIntakeMotors(0);
		}
		
		
		
		
		pivot.set(ControlMode.PercentOutput, joystickHandler.getCubeStick().getRawAxis(1));
		
		

	}
	
	private void updateToggle() {
		if(this.joystickHandler.getCubeStick().getRawButton(1)){
			if(!this.togglePressed) {
				toggleOn = !this.toggleOn;
				togglePressed = true;
			} else {
				togglePressed = false;
			}
		}
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
		Timer.delay(1);
		controlIntakeMotors(0);
	}
	
	public void dropIntake() {
		pivot.set(ControlMode.PercentOutput,0.5);
		Timer.delay(1);
		pivot.set(ControlMode.PercentOutput,0);
		
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
		double value = pivot.getSensorCollection().getQuadraturePosition();
		return 0;
		//return pivot.getSensorCollection().getQuadraturePosition();
	}
	
	public static void setPivot(double value) {
		//pivot.set(ControlMode.PercentOutput, value);
	}
	
	public static void resetPivot() {
	//	pivot.getSensorCollection().setQuadraturePosition(0, 0);
	}


}
