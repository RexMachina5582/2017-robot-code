package org.usfirst.frc.team5582.robot.commands;



/**
 *
 */
public class DropGear extends CommandBase {

    public DropGear() {
        // Use requires() here to declare subsystem dependencies
        requires(gearElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearElevator.dropGearLift();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearElevator.stopMotion();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
