package org.usfirst.frc.team5582.robot.subsystems;

import org.usfirst.frc.team5582.robot.*;
import org.usfirst.frc.team5582.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Relay;


/**
 *
 */
public class DriveTrain extends Subsystem {
	
	RobotDrive rexDrive;
	CANTalon leftTalonA, leftTalonB, rightTalonA, rightTalonB;

    public AnalogInput ultrasonicSensor;
    private double motorOutputValue = 0;
    public static DigitalInput leftEncoder;
    public static Counter leftWheelCounter;
    public static DigitalInput rightEncoder;
    public static Counter rightWheelCounter;
    
	// For turning on light
	static public Relay lightRelay;

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
        setDefaultCommand(new ArcadeDrive());
    }
    
    protected DriveTrain() {
    	// Drivetrain setup
		leftTalonA = new CANTalon(RobotMap.leftMotorCANA);
		leftTalonB = new CANTalon(RobotMap.leftMotorCANB);
		rightTalonA = new CANTalon(RobotMap.rightMotorCANA);
		rightTalonB = new CANTalon(RobotMap.rightMotorCANB);
		rexDrive = new RobotDrive(rightTalonB, rightTalonA, leftTalonB, leftTalonA);
		
		// Encoder setup
		leftEncoder = new DigitalInput(RobotMap.leftDrivetrainEncoder);
		leftWheelCounter = new Counter(leftEncoder);
		leftWheelCounter.setDistancePerPulse(3.14);
		leftWheelCounter.setMaxPeriod(1.1);
		leftWheelCounter.setSamplesToAverage(5);
		rightEncoder = new DigitalInput(RobotMap.rightDrivetrainEncoder);
		rightWheelCounter = new Counter(rightEncoder);
		rightWheelCounter.setDistancePerPulse(3.14);
		rightWheelCounter.setMaxPeriod(1.1);
		rightWheelCounter.setSamplesToAverage(5);
		
		lightRelay = new Relay(RobotMap.lightRelay);
		lightRelay.setDirection(Relay.Direction.kBoth);
		
		ultrasonicSensor = new AnalogInput(RobotMap.frontUltrasonic);
    }

    public void lightOn() {
    	lightRelay.set(Relay.Value.kOn);
    }
    
    public void lightOff() {
    	lightRelay.set(Relay.Value.kOff);
    }
    
    public void tankDrive(double leftStickY, double rightStickY) {
    		rexDrive.tankDrive(leftStickY, rightStickY);
    }
    
    public void arcadeDriveSingleStick(Joystick stick) {
    		rexDrive.arcadeDrive(stick);
    }
    
    public void arcadeDriveStickAxis(double leftY, double leftX) {
    		rexDrive.arcadeDrive(leftY, leftX);
    		// Distance avg value is around 100 at min range (13 in) and about 200 at 4 feet
//    		RexRobot.messageClient.publish("sensors/distance",
//    				"distance: " + String.valueOf(ultrasonicSensor.getAverageValue()));
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
    
    public void goDrive(double speed) {
    	rexDrive.setLeftRightMotorOutputs(-speed, speed);
    }
    
    public void turn (boolean rightTurn, double speed) {
		if (rightTurn) {
			rexDrive.setLeftRightMotorOutputs(0, speed);
		} else {
			rexDrive.setLeftRightMotorOutputs(speed, 0);
		}
	}
    
    public void resetRamp() {
    		motorOutputValue = 0;
    }
    
}

