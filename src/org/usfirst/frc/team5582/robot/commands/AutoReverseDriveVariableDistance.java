package org.usfirst.frc.team5582.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class AutoReverseDriveVariableDistance extends CommandBase {
	
	private static double slowDist;
	private static double fastDist;
	private static double sSpeed;
	private static double fSpeed;
//	private static int fail

    public AutoReverseDriveVariableDistance(double slowDistance, double fastDistance, double slowSpeed, double fastSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
        slowDist = slowDistance;
        fastDist = fastDistance;
        sSpeed = slowSpeed;
        fSpeed = fastSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetDistance();
    	driveTrain.resetRamp();
    	SmartDashboard.putString("Called:", "AutoReverseDriveVariableDistance");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (driveTrain.getLeftDistance() > fastDist) {
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
}
