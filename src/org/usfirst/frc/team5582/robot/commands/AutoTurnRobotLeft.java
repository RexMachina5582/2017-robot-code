package org.usfirst.frc.team5582.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurnRobotLeft extends CommandBase {

	private static double targetTurnDist;
	private static double targetTurnSpeed;
	
    public AutoTurnRobotLeft(double turnDistance, double turnSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
        targetTurnDist = turnDistance;
        targetTurnSpeed = turnSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetDistance();
    	driveTrain.resetRamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		driveTrain.turn(false, targetTurnSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double actualDistance = driveTrain.getLeftDistance();
    	SmartDashboard.putNumber("AutoTurn target distance", targetTurnDist);
    	SmartDashboard.putNumber("AutoTurn actual distance", actualDistance);

    	if (actualDistance < targetTurnDist) {
    		return false;
    	} else {
        	return true;
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
