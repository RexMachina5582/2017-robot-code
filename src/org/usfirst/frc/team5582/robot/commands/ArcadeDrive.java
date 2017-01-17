package org.usfirst.frc.team5582.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5582.robot.OI;

/**
 *
 */
public class ArcadeDrive extends CommandBase {

	private int tankControl = 0;
	
    public ArcadeDrive() {
    		requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putData(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		driveTrain.arcadeDriveStickAxis(OI.xboxControllerOne.leftStick.getY(), 
    		OI.xboxControllerOne.leftStick.getX());
    		//---
    		boolean skidTurnPressed = OI.xboxControllerOne.x.get();
    		if (skidTurnPressed) {
    			driveTrain.arcadeDriveSkidTurn(OI.xboxControllerOne.leftStick.getX(), OI.xboxControllerOne.leftStick.getX());
    		}
    		boolean tankDrivePressed = OI.xboxControllerOne.y.get();
    		if (tankDrivePressed) {
    			tankControl++;
    		}
    		if (tankControl % 2 != 0) {
			driveTrain.tankDrive(OI.xboxControllerOne.leftStick.getY(), OI.xboxControllerOne.rightStick.getY());
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
