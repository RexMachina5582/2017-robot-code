package org.usfirst.frc.team5582.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveDistance extends CommandBase {

	private static int failSafeCycleCount;
	private static double calcTime;
	private static double targetDistance;
	private static double targetSpeed;
	
    public AutoDriveDistance(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
        targetDistance = distance;
        targetSpeed = (-1)*speed;
        calcTime = ((distance / 20) * 50) + 50;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetDistance();
    	driveTrain.resetRamp();
    	SmartDashboard.putString("Called:", "AutoDriveDistance");
    	failSafeCycleCount = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	failSafeCycleCount++;
    	double traveledDistance = driveTrain.getLeftDistance();
    	double adjustedSpeed = targetSpeed;
    	if (traveledDistance < targetDistance) {   
    		double distanceDiff = targetDistance - traveledDistance;
    		if (distanceDiff < 10) {
    			// Start slowing down
    			adjustedSpeed = targetSpeed * (1.0 - 1.0/distanceDiff);
    		}
    		driveTrain.goDrive(adjustedSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double actualDistance = driveTrain.getLeftDistance();
    	SmartDashboard.putNumber("AutoDrive target distance", targetDistance);
    	SmartDashboard.putNumber("AutoDrive actual distance", actualDistance);

    	if (actualDistance >= targetDistance) {
    		return true;
    	} else if (failSafeCycleCount > calcTime) {
    		return true;
    	}
    	else if(failSafeCycleCount > 320)
    	{
    		return true;
    	}
    	else {
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
