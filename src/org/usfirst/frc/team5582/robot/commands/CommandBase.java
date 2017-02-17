package org.usfirst.frc.team5582.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5582.robot.OI;
import org.usfirst.frc.team5582.robot.subsystems.*;

/**
 *
 */
public abstract class CommandBase extends Command {
	
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Camera camera;
	public static BallStager ballStager;
	public static BallShooter ballShooter;
	public static BallIntake ballIntake;
	public static GearElevator gearElevator;
	public static GearGripper gearGripper;
	
	public static void init() {
		driveTrain = DriveTrain.getInstance();
		ballIntake = BallIntake.getInstance();
		gearElevator = GearElevator.getInstance();
		gearGripper = GearGripper.getInstance();
		ballStager = BallStager.getInstance();
		ballShooter = BallShooter.getInstance();
		oi = new OI();
		oi.init();
		//camera = Camera.getInstance();
	}

    public CommandBase() {
    		super();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
