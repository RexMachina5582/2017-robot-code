package org.usfirst.frc.team5582.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PWM;

/**
 *
 */
public class GearGripper extends Subsystem {

	PWM gripper;
	
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void clampGear() {
    }
    
    public void releaseGear() {
    }
    
}

