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
	final static public int RIGHT_DRIVE_1 = 2;
	final static public int LEFT_DRIVE_2 = 1;
	final static public int RIGHT_DRIVE_2 = 3;
	
	
	//Analog Sensors
	final static public int gyro = 0;
	
	//Constants
	
	final double heading_correction_const=(.032);
	
	//Digital IO
	final static public int left_enc_a = 0;
	final static public int left_enc_b = 0;
	final static public int right_enc_a = 0;
	final static public int right_enc_b = 0;
	
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
w