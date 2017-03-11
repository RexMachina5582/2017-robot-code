package org.usfirst.frc.team5582.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5582.robot.RobotMap;
import org.usfirst.frc.team5582.robot.commands.GripperDeploy;
import org.usfirst.frc.team5582.robot.commands.GripperRelease;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class GearGripper extends Subsystem {

	Servo gearClamp;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	// First, some Singleton housekeeping. Make sure there is only one.	
		public static GearGripper instance;
		
		public static GearGripper getInstance() {
			
			// Only instantiate if no prior instance exists
			if (instance == null) {
				instance = new GearGripper(); 
			}
			SmartDashboard.putData(instance);
			return instance;
		}
	protected GearGripper() {
    	gearClamp = new Servo(RobotMap.gearClampServo);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new GripperDeploy());
    }
    
    public void gripGear() {
    	gearClamp.setAngle(80);
    }
    
    public void releaseGear() {
    	gearClamp.setAngle(0);
    }
    
    
}

