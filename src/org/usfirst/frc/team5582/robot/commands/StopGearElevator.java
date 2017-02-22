package org.usfirst.frc.team5582.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team5582.robot.RexRobot;
import org.usfirst.frc.team5582.robot.commands.GripperDeploy;
import org.usfirst.frc.team5582.robot.commands.GripperRelease;
/**
 *
 */
public class StopGearElevator extends CommandBase {

    public StopGearElevator() {
        // Use requires() here to declare subsystem dependencies
        requires(gearElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	gearElevator.stopMotion();
    	
    	/*
    	 * This code block works, but doesn't make sense here.
    	 * Put something like it into a clever command.
    	 * 
    	boolean gearPresent;
	   	gearPresent = gearElevator.laserGearSensor.get();
	   	
	   	if (gearPresent) {
	   		Scheduler.getInstance().add(new GripperDeploy());
	   	} else {
	   		Scheduler.getInstance().add(new GripperRelease());
	   	}
	   	*/
//
//    	RexRobot.messageClient.publish("sensors/laserGear", 
//    			"gearPresent: " + String.valueOf(gearPresent));
//
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
