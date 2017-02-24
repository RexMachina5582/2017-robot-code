package org.usfirst.frc.team5582.robot.commands;


/**
 *
 */
public class AutoLeftGearPeg extends CommandBase {

	double rightWheelDistance = driveTrain.rightWheelCounter.getDistance();
	double leftWheelDistance = driveTrain.leftWheelCounter.getDistance();
	
	
    public AutoLeftGearPeg() {
        // Use requires() here to declare subsystem dependencies
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	rightWheelDistance = 0;
    	leftWheelDistance = 0;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (rightWheelDistance < 30 && leftWheelDistance < 30) {
    		driveTrain.goDrive(.5);
    	} else {
    		driveTrain.stopDrive();
    	}
    	if (rightWheelDistance < 45) {
            driveTrain.turn(false, .3);
        }
    	
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
