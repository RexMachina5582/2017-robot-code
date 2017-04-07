package org.usfirst.frc.team5582.robot.commands;

import org.usfirst.frc.team5582.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoHangGearOnPeg extends CommandBase {
	
	
	private double maxCycles;
	private double currentCycles;
	private int elevatorDropPoint;

    public AutoHangGearOnPeg(int time, int distance) {
    	maxCycles = time;
    	elevatorDropPoint = distance;
    	requires(gearElevator);
    	requires(gearGripper);
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//maxCycles = 40;
    	currentCycles = 0;
    	driveTrain.stopDrive();
    	gearGripper.releaseGear();
    	SmartDashboard.putString("Called:", "AutoHangGearOnPeg");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//currentCycles++;
    	gearElevator.dropGearLift();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if(currentCycles >= maxCycles) {
    		//return true;
    	//}
    	if (gearElevator.elevatorEncoder.getDistance() >= elevatorDropPoint) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearElevator.stopMotion();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
