package org.usfirst.frc.team5582.robot.commands.macros;

import org.usfirst.frc.team5582.robot.commands.AutoDriveDistance;
import org.usfirst.frc.team5582.robot.commands.AutoDriveUltrasonicDistance;
import org.usfirst.frc.team5582.robot.commands.AutoHangGearOnPeg;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddleGear extends CommandGroup {

    public AutoMiddleGear() {
        addSequential(new AutoDriveUltrasonicDistance(30));
        addSequential(new AutoHangGearOnPeg(30,40));
        addSequential(new AutoDriveDistance(30, 0.7));
    }
}
