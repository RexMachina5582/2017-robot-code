package org.usfirst.frc.team5582.robot.commands;



/**
 *
 */
public class DriveDistance extends CommandBase {

	private static double dist;
	private static double classSpeed;
	
    public DriveDistance(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
        dist = distance;
        classSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (driveTrain.leftWheelCounter.getDistance() < dist) {
    		driveTrain.goDrive(classSpeed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
