//This branch has been commandeered by drew
package org.usfirst.frc.team5582.robot.subsystems;

import org.usfirst.frc.team5582.robot.*;
import org.usfirst.frc.team5582.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Counter;

import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	RobotDrive rexDrive;
	CANTalon leftTalonA, leftTalonB, rightTalonA, rightTalonB;
	public static AHRS ahrs;
	PIDController turnController;
	double rotateToAngleRate;
	
    static final double kP = 0.03;
    static final double kI = 0.00;
    static final double kD = 0.00;
    static final double kF = 0.00;

    static final double kToleranceDegrees = 2.0f;    
    
    static final double kTargetAngleDegrees = 90.0f;

    //public AnalogInput ultrasonicSensor;
    private double motorOutputValue = 0;
    public static DigitalInput leftEncoder;
    public static Counter leftWheelCounter;

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
    		leftEncoder = new DigitalInput(RobotMap.leftDrivetrainEncoder);
    		leftWheelCounter = new Counter(leftEncoder);
    		leftWheelCounter.setDistancePerPulse(3.14);
    		leftWheelCounter.setMaxPeriod(1.1);
    		leftWheelCounter.setSamplesToAverage(5);
    		try {
    			/***********************************************************************
    			 * navX-MXP:
    			 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            
    			 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
    			 ************************************************************************/
                ahrs = new AHRS(SPI.Port.kMXP); 
            } catch (RuntimeException ex ) {
                DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
            }

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
    		rexDrive.setLeftRightMotorOutputs(leftMotor, rightMotor);
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

