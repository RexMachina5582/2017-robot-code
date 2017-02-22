
package org.usfirst.frc.team5582.robot;

import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    Command cameraCommand;
    Command autonomousWinch;
    Command autonomousBallArms;
    public static MessageClient messageClient;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	messageClient = new MessageClient("tcp://localhost:5888", "rex/vision/telemetry");
		messageClient.connect();
		CommandBase.init();
		CameraServer.getInstance().startAutomaticCapture();
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
