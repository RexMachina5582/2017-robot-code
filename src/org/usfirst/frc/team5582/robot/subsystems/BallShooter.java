package org.usfirst.frc.team5582.robot.subsystems;

import org.usfirst.frc.team5582.robot.*;
import org.usfirst.frc.team5582.robot.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class BallShooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Spark ballShooterMotor;

	// First, some Singleton housekeeping. Make sure there is only one.	
	public static BallShooter instance;
	
public static BallShooter getInstance() {
	
		// Only instantiate if no prior instance exists
		if (instance == null) {
			instance = new BallShooter(); 
		}
		SmartDashboard.putData(instance);
		return instance;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new StopShooter());
    }
    
    protected BallShooter() {
    	
    	ballShooterMotor = new Spark(RobotMap.ballShootSpark);
    	
    }
    
    public void spinMotor(){
    	double power = 0.75;
    	
    	ballShooterMotor.setSpeed(power);
    }

    public void stopMotor() {
    	
    	ballShooterMotor.setSpeed(0);
    	
    }
    
}
