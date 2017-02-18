package org.usfirst.frc.team5582.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5582.robot.OI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class GripperDeploy extends CommandBase {

    public GripperDeploy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(gearGripper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearGripper.gripGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
