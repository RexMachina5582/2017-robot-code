package org.usfirst.frc.team5582.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5582.robot.RobotMap;
import org.usfirst.frc.team5582.robot.commands.GripperDeploy;
import org.usfirst.frc.team5582.robot.commands.GripperRelease;
import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class RopePuller extends Subsystem {
	
	Spark winchMotor;

	public static RopePuller instance;
	
	public static RopePuller getInstance() {
		
		// Only instantiate if no prior instance exists
		if (instance == null) {
			instance = new RopePuller(); 
		}
		SmartDashboard.putData(instance);
		return instance;
	}
	
	protected RopePuller() {
		winchMotor = new Spark(RobotMap.winchSpark);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void pull() {
    	winchMotor.set(-1.0);
    }
    
    public void release() {
    	winchMotor.set(0.4);
    }
    
    public void stop() {
    	winchMotor.set(0);
    }
}

