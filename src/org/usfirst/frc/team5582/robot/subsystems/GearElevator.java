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
    	
    	// Have we triggered the limit sensor?
    	if (avgRaw > 400) {
    		speed = 0;
    	} else {
    		speed = 0.8;
    	}
    	
    	// Until we have the sensor installed, override the conditional speed with constant
    	speed = 0.8;
    	
    	gearElevator.setSpeed(-speed);

    	double distance;
    	distance = elevatorEncoder.getDistance();
    	RexRobot.messageClient.publish("sensors/gearEncoder", 
    			"distance : " + String.valueOf(distance));
    	
    }
    
    public void dropGearLift() {
    	
    	double speed = 0.5;
    	
    	gearElevator.setSpeed(speed);
    	
    	double distance;
    	distance = elevatorEncoder.getDistance();
    	RexRobot.messageClient.publish("sensors/gearEncoder", 
    			"distance : " + String.valueOf(distance));
    	
    }
    
    public void stopMotion() {
    	
    	gearElevator.setSpeed(0);
    	
    }

}

