
package org.usfirst.frc.team5582.robot;

import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5582.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 

 */

//TODO Get camera feed for smartdashboard
public class RexRobot extends IterativeRobot {

    Command firstCommand;
    Command autonomousCommand;
    Command cameraCommand;
    Command autonomousWinch;
    Command autonomousBallArms;
    CameraServer cameraServer;
    CameraServer camera;
    //USBCamera cameraFront;
    //USBCamera cameraRear;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		CommandBase.init();
		/*
	 	  camera = CameraServer.getInstance();
	   	  camera.setQuality(12);
	  	  camera.startAutomaticCapture("cam1");
	  	  */
	  	  
		//cameraFront = new USBCamera("cam0");
		//cameraRear = new USBCamera("cam1");
		//cameraRear.setFPS(12);
		//cameraRear.updateSettings();
		//cameraRear.openCamera();
		//cameraFront.setFPS(12);
		//cameraFront.updateSettings();
		//cameraFront.openCamera();
		//cameraServer = CameraServer.getInstance();
		//cameraServer.startAutomaticCapture(cameraFront);
		//cameraServer.startAutomaticCapture(cameraRear);
		
		
   }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    		if (autonomousCommand != null) autonomousCommand.start();
    		if (autonomousBallArms != null) autonomousBallArms.start();
    		// autonomousWinch.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
       Scheduler.getInstance().run();
    }

    public void teleopInit() {
        Scheduler.getInstance().add(firstCommand);
        Scheduler.getInstance().add(cameraCommand);
        SmartDashboard.putData(Scheduler.getInstance());
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
