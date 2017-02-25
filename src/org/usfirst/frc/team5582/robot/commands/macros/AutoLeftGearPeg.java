package org.usfirst.frc.team5582.robot.commands.macros;

import org.usfirst.frc.team5582.robot.commands.CommandBase;
import org.usfirst.frc.team5582.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;



/**
 *
 */
public class AutoLeftGearPeg extends CommandGroup {

    public AutoLeftGearPeg() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	addSequential(new DriveDistance(30, .5));
    	addSequential(new TurnRobotLeft(10, .5));
    	addSequential(new TargetRotate());
    	// new SenseDistanceLeft();
    	addSequential(new DriveBackVariablyDistance(6, 30, .2, .7));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
