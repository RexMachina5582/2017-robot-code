package org.usfirst.frc.team5582.robot.commands;



/**
 *
 */
public class DriveBackVariablyDistance extends CommandBase {
	
	private static double slowDist;
	private static double fastDist;
	private static double sSpeed;
	private static double fSpeed;

    public DriveBackVariablyDistance(double slowDistance, double fastDistance, double slowSpeed, double fastSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
        slowDist = slowDistance;
        fastDist = fastDistance;
        sSpeed = slowSpeed;
        fSpeed = fastSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (driveTrain.leftWheelCounter.getDistance() < slowDist && 
    			driveTrain.rightWheelCounter.getDistance() < slowDist) {
    		driveTrain.goDrive(sSpeed);
    	}
    	if (driveTrain.leftWheelCounter.getDistance() > slowDist && 
    			driveTrain.rightWheelCounter.getDistance() > slowDist) {
    		driveTrain.goDrive(fSpeed);
    	}
    	if (driveTrain.leftWheelCounter.getDistance() > fastDist && 
    			driveTrain.rightWheelCounter.getDistance() > fastDist) {
    		driveTrain.stopDrive();
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
