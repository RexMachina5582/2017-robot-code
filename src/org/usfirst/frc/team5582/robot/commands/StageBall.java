package org.usfirst.frc.team5582.robot.commands;

import org.usfirst.frc.team5582.robot.OI;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StageBall extends CommandBase {
	
    public StageBall() {
    		requires(ballStager);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //SmartDashboard.putData(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		ballStager.turnStager();
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
