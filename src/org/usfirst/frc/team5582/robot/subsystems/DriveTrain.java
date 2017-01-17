//This branch has been commandeered by drew
package org.usfirst.frc.team5582.robot.subsystems;

import org.usfirst.frc.team5582.robot.*;
import org.usfirst.frc.team5582.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	RobotDrive rexDrive;
	RobotDrive rexDriveRear;
	CANTalon leftTalonA, leftTalonB, rightTalonA, rightTalonB;
    //public AnalogInput ultrasonicSensor;
    public Encoder encoder;
    private double motorOutputValue = 0;

	// First, some Singleton housekeeping. Make sure there is only one.	
	public static DriveTrain instance;
	
	public static DriveTrain getInstance() {
		
		// Only instantiate if no prior instance exists
		if (instance == null) {
			instance = new DriveTrain(); 
		}
		SmartDashboard.putData(instance);
		return instance;
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDrive());
    }
    
    protected DriveTrain() {
    		leftTalonA = new CANTalon(RobotMap.leftMotorCANA);
    		leftTalonB = new CANTalon(RobotMap.leftMotorCANB);
    		rightTalonA = new CANTalon(RobotMap.rightMotorCANA);
    		rightTalonB = new CANTalon(RobotMap.rightMotorCANB);
    		rexDrive = new RobotDrive(rightTalonB, rightTalonA, leftTalonB, leftTalonA);
    		rexDriveRear = new RobotDrive(rightTalonA, leftTalonA);
    }
    
    public void tankDrive(double leftStickY, double rightStickY) {
    		rexDrive.tankDrive(leftStickY, rightStickY);
    }
    
    public void arcadeDriveSingleStick(Joystick stick) {
    		rexDrive.arcadeDrive(stick);
    }
    public void arcadeDriveStickAxis(double leftY, double leftX) {
    		rexDrive.arcadeDrive(leftY, leftX);
    }
    public void arcadeDriveSkidTurn(double leftMotor, double rightMotor) {
    		rexDriveRear.setLeftRightMotorOutputs(leftMotor, rightMotor);
    }

    public void arcadeDriveAutonomous(double finalPower, double limit) {
    		double change = finalPower - motorOutputValue;
    		if (change > limit) change = limit;
    		else if (change < -limit) change = -limit;
    		motorOutputValue += change;
    		rexDrive.setLeftRightMotorOutputs(-motorOutputValue, -motorOutputValue);
    }
    public void arcadeDriveAutonomous(double finalPower) {
    		double limit = 0.08;
		double change = finalPower - motorOutputValue;
		if (change > limit) change = limit;
		else if (change < -limit) change = -limit;
		motorOutputValue += change;
		rexDrive.setLeftRightMotorOutputs(-motorOutputValue, -motorOutputValue);
}
    public void stopDrive() {
    		rexDrive.setLeftRightMotorOutputs(0, 0);
    }
    public void resetRamp() {
    		motorOutputValue = 0;
    }
    
}

