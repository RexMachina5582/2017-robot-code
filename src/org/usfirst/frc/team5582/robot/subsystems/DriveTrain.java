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
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Relay;

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
    
    // Testing the I-CubeX Touch Sensor (flat pad)
//    AnalogInput touchPad;
    
    // Testing the Mach Engineering touchless encoder, http://mach.engineering/
    DigitalInput touchlessEncoder;
    Counter driveTrainCounter;
    
    // Testing a relay for the target light
    Relay targetLightRelay;

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
    		
//    		touchPad = new AnalogInput(3);
    		
    		touchlessEncoder = new DigitalInput(9);
    		driveTrainCounter = new Counter(touchlessEncoder);
    		// Initialize some assumptions about the touchless encoder as a counted object.
    		// Distance per pulse is wheel diameter (6 inches) * pi = 18.84 divided by the
    		// number of pulses (one per hub screw): 18.84 / 6 = 3.14
    		driveTrainCounter.setDistancePerPulse(3.14);
    		driveTrainCounter.setMaxPeriod(1.1);
    		driveTrainCounter.setSamplesToAverage(5);
    		
    		targetLightRelay = new Relay(0);
    		targetLightRelay.setDirection(Relay.Direction.kReverse);
    }
    
    public void tankDrive(double leftStickY, double rightStickY) {
    		rexDrive.tankDrive(leftStickY, rightStickY);
    }
    
    public void arcadeDriveSingleStick(Joystick stick) {
    		rexDrive.arcadeDrive(stick);
    }
    public void arcadeDriveStickAxis(double leftY, double leftX) {
    	rexDrive.arcadeDrive(leftY, leftX);
    	
		targetLightRelay.set(Relay.Value.kOn);
    	
    	// Mach Engineering touchless encoder test
    	double distance = driveTrainCounter.getDistance();
    	boolean pulse = touchlessEncoder.get();
    	RexRobot.messageClient.publish("sensors/encoder", 
    			"pulse : " + String.valueOf(pulse) + ", distance: " + String.valueOf(distance));

    	// I-CubeX touch pad test
//    	int raw, avgRaw;
//       	raw = touchPad.getValue();
//    	avgRaw = touchPad.getAverageValue();
//    	RexRobot.messageClient.publish("sensors/touch", 
//    			"touch pad: raw " + String.valueOf(raw) + ", avgRaw " + String.valueOf(avgRaw));
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

