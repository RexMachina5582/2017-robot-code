//This branch has been commandeered by drew
package org.usfirst.frc.team5582.robot.subsystems;

import org.usfirst.frc.team5582.robot.*;
import org.usfirst.frc.team5582.robot.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class BallIntake extends Subsystem {
	
	Spark ballIntake;
    

	// First, some Singleton housekeeping. Make sure there is only one.	
	public static BallIntake instance;
	
	public static BallIntake getInstance() {
		
		// Only instantiate if no prior instance exists
		if (instance == null) {
			instance = new BallIntake(); 
		}
		SmartDashboard.putData(instance);
		return instance;
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new StopIntake());
    }
    
    protected BallIntake() {
    	
    	ballIntake = new Spark(RobotMap.ballIntakeSpark);
    	
    }
    
    public void intakeBall() {
    	
    	ballIntake.setSpeed(1);
    	
    }
    
    public void expelBall() {
    	
    	ballIntake.setSpeed(-1);
    	
    }
    
    public void opertateIntake(double speed) {
    	
    	ballIntake.setSpeed(speed);
    	
    }
    
    public void stopIntake() {
    	
    	ballIntake.setSpeed(0);
    	
    }
    
}
    

