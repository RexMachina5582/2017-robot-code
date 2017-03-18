
package org.usfirst.frc.team5582.robot;

import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import java.awt.image.DataBufferShort;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.usfirst.frc.team5582.robot.MessageClient;

import org.usfirst.frc.team5582.robot.commands.*;
import org.usfirst.frc.team5582.robot.commands.macros.*;
import org.usfirst.frc.team5582.robot.OI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 

 */

public class RexRobot extends IterativeRobot {

    Command firstCommand;
    Command autonomousCommand;
    SendableChooser autoChooser;
    String[] autoChoices;
    Command cameraCommand;
    Command autonomousWinch;
    Command autonomousBallArms;
    Command autoLeftGearPeg;
    Command autoRightGearPeg;
    public static MessageClient messageClient;
    SmartDashboard dash;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	dash = new SmartDashboard();
    	messageClient = new MessageClient("tcp://localhost:5888", "rex/vision/telemetry");
		messageClient.connect();
		CommandBase.init();
		CameraServer.getInstance().startAutomaticCapture();
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Simple drive forward", new AutoDriveDistance(20, 0.6));
		autoChooser.addObject("Station 3: peg to left", new AutoLeftGearPeg());
		autoChoices = new String[]{
				"simple",
				"station 1",
				"station 3"
		};
		dash.putStringArray("Auto List", autoChoices);
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
    	
    	String autoSelection = new String(dash.getString("Auto Selector", "simple"));
    	SmartDashboard.putString("Autonomous mode", autoSelection);    	
    	
    	if (autoSelection.equals("station 1")) {
    		autonomousCommand = new AutoDriveDistance(200, -0.6);
    	} else if (autoSelection.equals("station 3")) {
    		autonomousCommand = new AutoLeftGearPeg();
    	} else {
    		autonomousCommand = new AutoDriveDistance(0, 0.6);
    	}
    	
    	
//    	if (autoSelection.equals("station 3")) {
//    		autonomousCommand = new AutoLeftGearPeg();
//    	} else {
//    		autonomousCommand = new AutoDriveDistance(20, 0.6);
//    	}
//    	autonomousCommand = (Command) autoChooser.getSelected();
    	autonomousCommand.start();

//    	if (OI.autoPegSwitch) {
//            if (autoLeftGearPeg != null) autoLeftGearPeg.start();
//        } else {
//            if (autoRightGearPeg != null) autoLeftGearPeg.start();
//        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
       Scheduler.getInstance().run();
    }

    public void teleopInit() {
        Scheduler.getInstance().add(firstCommand);
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
