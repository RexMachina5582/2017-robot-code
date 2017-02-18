package org.usfirst.frc.team5582.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static int xboxControllerOne = 0;
	public static int xboxControllerTwo = 1;
	public static int leftMotorCANA = 1;
    public static int leftMotorCANB = 2;
    public static int rightMotorCANA = 3;
    public static int rightMotorCANB = 4;
    public static int ballLoadSpark = 0;
    public static int ballShootSpark = 1;
    public static int ballIntakeSpark = 4;
    public static int gearElevatorSpark = 5;
    public static int gearClampServo = 3;
    public static int winchSpark = 2;
	
}
