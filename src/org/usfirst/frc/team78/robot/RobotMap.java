package org.usfirst.frc.team78.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	//MOTORS(PWN)
	final static public int LEFT_DRIVE_1 = 0;
	final static public int LEFT_DRIVE_2 = 1;
	final static public int RIGHT_DRIVE_1 = 2;
	final static public int RIGHT_DRIVE_2 = 3;
	
	//MOTORS(CAN)
	final static public int LEFT_SHOOTER = 0;
	final static public int RIGHT_SHOOTER = 1;
	final static public int LEFT_INTAKE = 3;
	final static public int RIGHT_INTAKE = 2;
	/*final static public int PAN = 4;
	final static public int TILT = 5;
	final static public int RIGHT_SHOOTER = 6;
	final static public int LEFT_SHOOTER = 7;*/
	
	
	//ANALOG SENSORS
	final static public int GYRO = 0;
	
	
	//DIGITAL IO
	final static public int LEFT_ENC_A = 0;
	final static public int LEFT_ENC_B = 1;
	final static public int RIGHT_ENC_A = 2;
	final static public int RIGHT_ENC_B = 3;
	final static public int RIGHT_SHOOTER_ENC_A = 4;
	final static public int RIGHT_SHOOTER_ENC_B = 5;
	final static public int LEFT_SHOOTER_ENC_A = 6;
	final static public int LEFT_SHOOTER_ENC_B = 7;
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
