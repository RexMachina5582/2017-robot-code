package org.usfirst.frc.team5582.robot.commands;


/**
 *
 */
public class LightGearLights extends CommandBase {

    public LightGearLights() {
        // Use requires() here to declare subsystem dependencies
        // requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearLights.setLumos();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearLights.setNox();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
