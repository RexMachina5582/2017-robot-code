package org.usfirst.frc.team5582.robot;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team5582.robot.subsystems.*;
import org.usfirst.frc.team5582.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;


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
	// Button Type
	public static Button seekTarget;
	public static Button intakeBall;
	public static Button elevateLift;
	public static Button dropLift;
	public static Button shootBall;
	public static Button stageBall;
	public static Button climbRope;
	public static Button releaseRope;
	
	public static Trigger deployGripper;
	public static Trigger releaseGripper;
	
	
	public static void init()
	{
		driverStation = DriverStation.getInstance();
		
		// Tank controls
		xboxControllerOne = new XboxController(RobotMap.xboxControllerOne);
		xboxControllerTwo = new XboxController(RobotMap.xboxControllerTwo);
		xboxControllerOne.setDeadZone(0.1);
		//xboxControllerTwo.setDeadZone(0.1);
		/** BUTTONS **/
		seekTarget = xboxControllerOne.a;
		seekTarget.whileHeld(new TargetRotate());
		intakeBall = xboxControllerOne.b;
		intakeBall.whileHeld(new IntakeBall());
		elevateLift = xboxControllerOne.y;
		elevateLift.whileHeld(new ElevateGear());
		dropLift = xboxControllerOne.x;
		dropLift.whileHeld(new DropGear());
		shootBall = xboxControllerOne.lb;
		shootBall.whileHeld(new ShootBall());
		stageBall = xboxControllerOne.rb;
		stageBall.whileHeld(new StageBall());
		climbRope = xboxControllerOne.start;
		climbRope.whileHeld(new PullRope());
		releaseRope = xboxControllerOne.back;
		releaseRope.whileHeld(new ReleaseRope());
		
		deployGripper = xboxControllerOne.lt;
		deployGripper.whenActive(new GripperDeploy());
		releaseGripper = xboxControllerOne.rt;
		releaseGripper.whenActive(new GripperRelease());
		
			
		
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