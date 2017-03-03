package org.usfirst.frc.team5582.robot.commands.macros;

import org.usfirst.frc.team5582.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftGearPeg extends CommandGroup {

    public AutoLeftGearPeg() {
    	addSequential(new AutoDriveDistance(20, .5)); 
    	addSequential(new AutoTurnRobotLeft(15, .5));
    	
    	// Leave these next 3 off for testing on Frank
    	addSequential(new AutoTargetRotate());
    	addSequential(new AutoDriveToRange(100, 0.8));
    	addSequential(new AutoHangGearOnPeg());
    	
    	addSequential(new AutoReverseDriveVariableDistance(6, 30, .2, .7)); 
    }
}