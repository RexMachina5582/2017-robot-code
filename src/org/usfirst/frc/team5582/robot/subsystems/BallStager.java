
package org.usfirst.frc.team5582.robot.subsystems;

import org.usfirst.frc.team5582.robot.*;
import org.usfirst.frc.team5582.robot.commands.StageBall;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;

/**
 *
 */
public class BallStager extends Subsystem {
	
	PWM ballLoadMotor;

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
        setDefaultCommand(new StageBall());
    }
    
    protected BallStager() {
    		ballLoadMotor = new PWM(RobotMap.ballLoadPWM);
    }
    
    public void turnStager() {
    	//scale the power here
    	double power = 0.5;
    	
    	ballLoadMotor.setSpeed(power);
    	
    }
    
}

