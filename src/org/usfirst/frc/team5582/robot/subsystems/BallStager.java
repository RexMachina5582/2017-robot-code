
package org.usfirst.frc.team5582.robot.subsystems;

import org.usfirst.frc.team5582.robot.*;
import org.usfirst.frc.team5582.robot.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class BallStager extends Subsystem {
	
	Spark ballLoadMotor;

	// First, some Singleton housekeeping. Make sure there is only one.	
	public static BallStager instance;
	
	public static BallStager getInstance() {
		
		// Only instantiate if no prior instance exists
		if (instance == null) {
			instance = new BallStager(); 
		}
		SmartDashboard.putData(instance);
		return instance;
	}
    
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        setDefaultCommand(new StopStager());
    }
    
    protected BallStager() {
    		ballLoadMotor = new Spark(RobotMap.ballLoadSpark);
    }
    
    public void turnStager() {
    	//scale the power here
    	double power = -1;
    	
    	ballLoadMotor.setSpeed(power);
    	
    }
    
    public void stopStager() {
    	
    	ballLoadMotor.setSpeed(0);
    	
    }
    
}

