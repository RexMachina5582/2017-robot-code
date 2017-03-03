package org.usfirst.frc.team5582.robot.commands;



/**
 *
 */
public class TurnRobotLeft extends CommandBase {

	private static double turnDist;
	private static double classTurnSpeed;
	
    public TurnRobotLeft(double turnDistance, double turnSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
        turnDist = turnDistance;
        classTurnSpeed = turnSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.rightWheelCounter.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (driveTrain.rightWheelCounter.getDistance() < turnDist) {
    		driveTrain.turn(false, classTurnSpeed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (driveTrain.rightWheelCounter.getDistance() < turnDist) {
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
