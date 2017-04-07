package org.usfirst.frc.team5582.robot.commands;



/**
 *
 */
public class AutoDriveUltrasonicDistance extends CommandBase {

	public boolean thing;
	public double someDistance;
    public AutoDriveUltrasonicDistance(double distance) {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
        someDistance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	thing = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double targetDistance = driveTrain.getInfraredDistance();
    	if(someDistance <  targetDistance) {
    		
    		driveTrain.goDrive(-.3);
    	} else {
    		
    		driveTrain.stopDrive();
    		thing = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return thing;
    }
    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
