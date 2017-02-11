package org.usfirst.frc.team5582.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5582.robot.RobotMap;
import org.usfirst.frc.team5582.robot.commands.ElevateGear;

import edu.wpi.first.wpilibj.PWM;

/**
 *
 */
public class GearElevator extends Subsystem {

	PWM gearElevator;
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

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
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevateGear());
    }
    
    protected GearElevator() {
    	
    	gearElevator = new PWM(RobotMap.gearElevatorPWM);
    	
    }
    
    public void elevateGearLift() {
    	
    	gearElevator.setSpeed(.5);
    	
    }
    
    public void dropGearLift() {
    	
    	gearElevator.setSpeed(-.5);
    	
    }
    
}

