package org.usfirst.frc.team78.robot.subsystems;



import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
    
	Talon leftDrive1 = new Talon(RobotMap.LEFT_DRIVE_1);
	Talon rightDrive1 = new Talon(RobotMap.RIGHT_DRIVE_1);
	Talon leftDrive2 = new Talon(RobotMap.LEFT_DRIVE_2);
	Talon rightDrive2 = new Talon(RobotMap.RIGHT_DRIVE_2);
	
	AnalogGyro gyro = new AnalogGyro(RobotMap.gyro);
	Encoder leftEnc = new Encoder(RobotMap.left_enc_a, RobotMap.left_enc_b); 
	Encoder rightEnc = new Encoder(RobotMap.right_enc_a, RobotMap.right_enc_b); 
	
	final double heading_correction_const=(.01);
	final double straight_error_const = (.006);
	final double distancep = (0.0003);
	
	double distanceerror;
	
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveStraightDistance(int Distance){
    	distanceerror = Distance - ((getleftenc() + getrightenc()));
    	double speed = distanceerror * distancep;
    	if (speed > .45){
    	else speed = .45
    			
    		
    
    }
    //_________________________________________________________________________________________
    //Drive Methods
    
    public void driveWithJoysticks(){
    	double left = Robot.oi.getDriverLeftStick();
    	double right = Robot.oi.getDriverRightStick();
    	setSpeed(left*.3, right*.3);
    }
    
    public void setSpeed(double left, double right){
    	leftDrive1.set(-left);
    	rightDrive1.set(right);
    	leftDrive2.set(-left);
    	rightDrive2.set(right);
    }
    
    public void stopAllDrive(){
    	setSpeed(0,0);
    }
    public void heading_correction_const(double heading){
    	double driftError = heading_correction_const - getGyro();
    	setSpeed(-((heading_correction_const)*driftError), ((heading_correction_const)*driftError));
    }
    
  //___________________________________________________________________________________________________________________
  //Sensor Methods
  public double getGyro(){
  	return gyro.getAngle();
  }

  public void resetSensorData(){
  	gyro.reset();
  	
  
  	
  }
    
}


