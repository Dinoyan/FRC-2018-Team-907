package org.usfirst.frc.team907.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class CustomTalon {
	
	private TalonSRX talonSRX;
	private JoystickHandler joystickHandler;
	
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;

	public CustomTalon(JoystickHandler joystickHandler) {
		/* first choose the sensor */
		talonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,  RobotConstant.kTimeoutMs);
		

		/* set the peak, nominal outputs */
		talonSRX.configNominalOutputForward(0, RobotConstant.kTimeoutMs);
		talonSRX.configNominalOutputReverse(0, RobotConstant.kTimeoutMs);
		talonSRX.configPeakOutputForward(1, RobotConstant.kTimeoutMs);
		talonSRX.configPeakOutputReverse(-1, RobotConstant.kTimeoutMs);

		/* set closed loop gains in slot0 */
		talonSRX.config_kF(RobotConstant.kPIDLoopIdx, 0.1097, RobotConstant.kTimeoutMs);
		talonSRX.config_kP(RobotConstant.kPIDLoopIdx, 0.113333, RobotConstant.kTimeoutMs);
		talonSRX.config_kI(RobotConstant.kPIDLoopIdx, 0, RobotConstant.kTimeoutMs);
		talonSRX.config_kD(RobotConstant.kPIDLoopIdx, 0, RobotConstant.kTimeoutMs);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		/* get gamepad axis */
		double motorOutput = talonSRX.getMotorOutputPercent();
		/* prepare line to print */
		_sb.append("\tout:");
		_sb.append(motorOutput);
		_sb.append("\tspd:");
		_sb.append(talonSRX.getSelectedSensorVelocity(RobotConstant.kPIDLoopIdx));

		if (joystickHandler.getCubeStick().getRawButton(5)) {
			/* Speed mode */
			/* Convert 500 RPM to units / 100ms.
			 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
			double targetVelocity_UnitsPer100ms = RobotConstant.RPM * 4096 / 600;
			/* 500 RPM in either direction */
			talonSRX.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

			/* append more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(talonSRX.getClosedLoopError(RobotConstant.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetVelocity_UnitsPer100ms);
		} else {
			/* Percent voltage mode */
			talonSRX.set(ControlMode.PercentOutput, joystickHandler.getCubeStick().getRawAxis(4));
		}

		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}
		_sb.setLength(0);
	}

}
