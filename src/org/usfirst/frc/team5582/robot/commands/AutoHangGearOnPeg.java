package org.usfirst.frc.team5582.robot.commands;

import org.usfirst.frc.team5582.robot.commands.CommandBase;

/**
 *
 */
public class AutoHangGearOnPeg extends CommandBase {
	
	private int elevatorDropPoint = 250;

    public AutoHangGearOnPeg() {
    	requires(gearElevator);
    	requires(gearGripper);
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.stopDrive();
    	gearGripper.releaseGear();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearElevator.dropGearLift();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (gearElevator.elevatorEncoder.getDistance() >= elevatorDropPoint) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
