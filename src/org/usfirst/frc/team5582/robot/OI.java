package org.usfirst.frc.team5582.robot;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team5582.robot.subsystems.*;
import edu.wpi.first.wpilibj.buttons.Button;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {


    /* Example
	// WHEEL ARMS STATE
    public enum WheelArmsState {
    		DOWN("down"), UP("up"), STOP("stop");
    		String state;
    		
    		private WheelArmsState(String stateIn) {
    			this.state = stateIn;
    		}
    		public String toString() {
    			return "WheelArmsState" + this.state;
    		}
    }
	*/
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public static DriverStation driverStation;
	public static XboxController xboxControllerOne;
	public static XboxController xboxControllerTwo;
	public static Button skidTurn;
	public static boolean skidTurnPressed;
	public static Button tankDrive;
	public static boolean tankDrivePressed;
	
	
	public static void init()
	{
		driverStation = DriverStation.getInstance();
		
		// Tank controls
		xboxControllerOne = new XboxController(RobotMap.xboxControllerOne);
		xboxControllerTwo = new XboxController(RobotMap.xboxControllerTwo);
		xboxControllerOne.setDeadZone(0.1);
		xboxControllerTwo.setDeadZone(0.1);
		/** BUTTONS **/
		skidTurn = xboxControllerOne.x;
		skidTurnPressed = skidTurn.get();
		tankDrive = xboxControllerOne.y;
		tankDrivePressed = tankDrive.get();
		
		//EXAMPLE: topLiftUp = xboxControllerTwo.y;
				
	}	
	

	/* Example
	// WHEEL ARMS STATE
		public static WheelArmsState getWheelArmsState() {
			if (armsDownButton.get()) {
				return WheelArmsState.DOWN;
			} else if (armsUpButton.get()) {
				return WheelArmsState.UP;
			} else {
				return WheelArmsState.STOP;
			}
		}
	*/
}