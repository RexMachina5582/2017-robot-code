package org.usfirst.frc.team5582.robot.commands.macros;

import org.usfirst.frc.team5582.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetGear extends CommandGroup {

    public GetGear() {
        
    	//addSequential(new LowerGearElevatorFully());
    	addSequential(new WaitForGear());
    	addSequential(new GripperDeploy());
    	//addSequential(new ElevateGearFully());
    	
    }
}
