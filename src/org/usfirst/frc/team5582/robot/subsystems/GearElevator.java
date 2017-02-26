package org.usfirst.frc.team5582.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team5582.robot.RobotMap;
import org.usfirst.frc.team5582.robot.RexRobot;
import org.usfirst.frc.team5582.robot.commands.*;

import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class GearElevator extends Subsystem {

	Spark gearElevator;
	public DigitalInput laserGearSensor;
	public AnalogInput upperLimitSensor;
	public Encoder elevatorEncoder;
	
	static final int kLowerGearEncoderLimit = 1050;
	
	// First, some Singleton housekeeping. Make sure there is only one.	
	public static GearElevator instance;
	
	public static GearElevator getInstance() {
		
		// Only instantiate if no prior instance exists
		if (instance == null) {
			instance = new GearElevator(); 
		}
		SmartDashboard.putData(instance);
		return instance;
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new StopGearElevator());
    }
    
    protected GearElevator() {
    	
    	gearElevator = new Spark(RobotMap.gearElevatorSpark);
    	upperLimitSensor = new AnalogInput(RobotMap.gearElevatorUpperLimitSensor);
    	
    	// Laser sensor and encoder will be used by clever commands
    	laserGearSensor = new DigitalInput(RobotMap.laserGearSensor);
    	elevatorEncoder = new Encoder(
    			RobotMap.gearElevatorEncoderPhaseA, 
    			RobotMap.gearElevatorEncoderPhaseB);
    	
    }
    
    public void elevateGearLift() {
    	
    	int avgRaw;
    	avgRaw = upperLimitSensor.getAverageValue();
    	double speed;
    	double distance;
    	
    	// Inverting the distance means we are spooling with strap behind spool
    	distance = elevatorEncoder.getDistance() * -1.0;
    	
    	RexRobot.messageClient.publish("sensors/limit", 
    			"limitPadAvgRaw : " + String.valueOf(avgRaw));
    	RexRobot.messageClient.publish("sensors/gearEncoder", 
    			"distance : " + String.valueOf(distance));
    	
    	// Have we triggered the limit sensor?
    	if (avgRaw > 300) {
    		speed = 0;
    		elevatorEncoder.reset();
    	} else {
    		speed = 0.9;
    	}
    	
    	// Until we have the sensor installed, override the conditional speed with constant
//    	speed = 0.9;
    	
    	// Positive when strap is behind spool, negative when in front
    	gearElevator.setSpeed(speed);
    	
    }
    
    public void dropGearLift() {
    	
    	double speed = 0.5;
    	double distance;

    	// Inverting the distance means we are spooling with strap behind spool
    	distance = elevatorEncoder.getDistance() * -1.0;
    	RexRobot.messageClient.publish("sensors/gearEncoder", 
    			"distance : " + String.valueOf(distance));
     	
    	// Lower limit enforced by encoder reading
    	if (distance > kLowerGearEncoderLimit) {
    		speed = 0;
    	}
    	
    	// Negative when strap is behind spool, positive when in front
    	gearElevator.setSpeed(-speed); 	
    }
    
    public void stopMotion() {
    	
    	gearElevator.setSpeed(0);
    	
    }

}

