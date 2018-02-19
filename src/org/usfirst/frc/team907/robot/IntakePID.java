package org.usfirst.frc.team907.robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakePID extends PIDSubsystem {

    // Initialize your subsystem here
    public IntakePID(double p, double i, double d, double setpoint) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("IntakePID",p,i,d);
    	setSetpoint(setpoint);
    	//setAbsoluteTolerance(600);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Intake.getPivotEncoderValues();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Intake.setPivot(output);
    	Robot.updateDashboard();
    	SmartDashboard.putNumber("pid_output", output);
    }
}
