package org.usfirst.frc.team5582.robot.commands;

import org.usfirst.frc.team5582.robot.commands.CommandBase;

/**
 *
 */
public class AutoDriveToRange extends CommandBase {
	
	private double targetDistance;
	private double targetSpeed;

    public AutoDriveToRange(double distance, double speed) {
    	targetDistance = distance;
    	targetSpeed = speed;
    	
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		driveTrain.goStraightRamp(targetSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (sensedDistance() <= targetDistance) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    protected double sensedDistance() {
    	return driveTrain.ultrasonicSensor.getAverageValue();
    }
}
