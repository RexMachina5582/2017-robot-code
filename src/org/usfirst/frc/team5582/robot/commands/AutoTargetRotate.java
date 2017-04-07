package org.usfirst.frc.team5582.robot.commands;

import org.json.simple.JSONObject;
import org.usfirst.frc.team5582.robot.RexRobot;
import org.usfirst.frc.team5582.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTargetRotate extends CommandBase {
	private double offset;
	private static int searchCycles = 0;
	private static int maxSearchCycles;

	//Major is the large rotation phase, and minor is the small
	private double MAJOR_SPEED = .40;
	private double MINOR_SPEED = .20;
	private double TRANSITION_ERROR = 50;
	private double TOLERANCE = .5;
	private boolean hasTarget;
	
	PIDController rotationPID;
	FloatInput errorInput;
	PIDOutput rotateOutput;
	
	public AutoTargetRotate(int maxCycles) {
    	requires(driveTrain);
    	maxSearchCycles = maxCycles;
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.lightOn();
    	SmartDashboard.putString("Called:", "AutoTargetRotate");
    	searchCycles = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	searchCycles++;
    		JSONObject obj = null;
	    	try {
	    		obj = RexRobot.messageClient.getJsonObject("rex/vision/telemetry");
	    		hasTarget = (Boolean)obj.getOrDefault("hasTarget",false);
	    	} catch (Exception e) {
	    		hasTarget = false;
	    		System.out.println("hasTarget is equal to false");
	    	}
	    	
	    	
	    	if (!hasTarget){
	    		driveTrain.stopDrive();
	    		System.out.println("No Target, stopping");
	    		return;
	    	}
	    
	    	double distance = (Double) obj.get("targetDistance");
	    	
	    	if (distance > 9999){
	    		driveTrain.stopDrive();
	    		System.out.println("Distance is " + distance + "!");
	    		return;
	    	}
	    	
	    	/*
	    	 * horizontalOffset
	    	 * 
	    	 */
	    	
	    	//Values sampled physically
	    	double horizError = (distance * .001406397) - .3897812794; 
	//    	double offsetCorrection = .045;
	    	double offsetCorrection = 0;
	    	offset = (Double) obj.get("horizontalOffset") - horizError + offsetCorrection;
	
	    	System.out.println("Offset is set to" + offset);
	    	double speed = 0;
	    	if (Math.abs(offset) > TRANSITION_ERROR){
	    		speed = MAJOR_SPEED;
	    	} else {
	    		speed = MINOR_SPEED;
	    	}
	    	
	    	if (offset < 0) {
	    		speed = -speed;
	    	}
	    	
	    	if (Math.abs(offset) < TOLERANCE) {
	    		speed = 0;
	    	}
    	
    		System.out.println(speed);
    	
    		driveTrain.turn(false, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (searchCycles > maxSearchCycles) {
    		return true;
    	}
    		System.out.println("TargetRotate offset:" + offset);
    	if (!hasTarget) {
    		return false;
    	}
        return Math.abs(offset) < TOLERANCE;
    }

    // Called once after isFinished returns true
    protected void end() {
		driveTrain.stopDrive();
		driveTrain.lightOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    }
}
