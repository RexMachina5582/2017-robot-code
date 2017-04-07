package org.usfirst.frc.team5582.robot.commands.macros;

import org.usfirst.frc.team5582.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoLeftGearPeg extends CommandGroup {

    public AutoLeftGearPeg() {
    	addSequential(new AutoDriveDistance(70, -0.5));
    	addSequential(new AutoTurnRobotLeft(30, -.5, 90));
    	
    	// Leave these next 3 off for testing on Frank
    	addSequential(new AutoTargetRotate(60));
    	addSequential(new AutoDriveDistance(15, -0.5));
    	addSequential(new AutoTargetRotate(60));
    	//addSequential(new AutoDriveDistance(15, -0.5));
    	addSequential(new AutoDriveUltrasonicDistance(30));
    	//use ultrasonic distance
    	//addSequential(new AutoDriveDistance(50, -0.5));
    	//addSequential(new AutoDriveToRange(100, 0.8));
    	addSequential(new AutoHangGearOnPeg(30,40));
    	
    	addSequential(new AutoDriveDistance(30, 0.5)); 
    }
}