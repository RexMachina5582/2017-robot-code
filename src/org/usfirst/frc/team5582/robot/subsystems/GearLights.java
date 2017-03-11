package org.usfirst.frc.team5582.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Relay;
import org.usfirst.frc.team5582.robot.commands.ManageGearLights;
import org.usfirst.frc.team5582.robot.RobotMap;

/**
 *
 */
public class GearLights extends Subsystem {

    Relay gearLightsRelay;

 // First, some Singleton housekeeping. Make sure there is only one.	
 	public static GearLights instance;	
 	public static GearLights getInstance() {
 		
 		// Only instantiate if no prior instance exists
 		if (instance == null) {
 			instance = new GearLights(); 
 		}
 		SmartDashboard.putData(instance);
 		return instance;
 	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManageGearLights());
    }
    
    protected GearLights() {
    	gearLightsRelay = new Relay(RobotMap.gearLights);
    	gearLightsRelay.setDirection(Relay.Direction.kBoth);
    	
    }
    
    public void setLumos() {
    	gearLightsRelay.set(Relay.Value.kOn);
    }
    public void setNox() {
    	gearLightsRelay.set(Relay.Value.kOff);
    }
    
}

