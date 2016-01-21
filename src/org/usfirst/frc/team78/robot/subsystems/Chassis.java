package org.usfirst.frc.team78.robot.subsystems;



import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
    
	//MOTORS
	Talon leftDrive1 = new Talon(RobotMap.LEFT_DRIVE_1);
	Talon rightDrive1 = new Talon(RobotMap.RIGHT_DRIVE_1);
	Talon leftDrive2 = new Talon(RobotMap.LEFT_DRIVE_2);
	Talon rightDrive2 = new Talon(RobotMap.RIGHT_DRIVE_2);
	
	//SENSORS
	AnalogGyro gyro = new AnalogGyro(RobotMap.GYRO);
	Encoder leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B);
	Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B);
	
	//VARIABLES
	double distanceError;
	
	//CONSTANTS
	final double GYRO_P = (.01);
	final double DISTANCEP = 0.00015;
	
	//TIMER
	Timer timer = new Timer();
	
	
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
 //______________________________________________________________________________   
 //DRIVE METHODS
    
    public void driveWithJoysticks(){
    	double left = Robot.oi.getDriverLeftStick();
    	double right = Robot.oi.getDriverRightStick();
    	setSpeed(left*.3, right*.3);
    }
    
    public void setSpeed(double left, double right){
    	leftDrive1.set(left);
    	rightDrive1.set(-right);
    	leftDrive2.set(left);
    	rightDrive2.set(-right);
    }
    
    public void stopAllDrive(){
    	setSpeed(0,0);
    }
    
    
 //______________________________________________________________________________ 
 //AUTO METHODS  
    
    public void driveStraightDistance(double distance){
    	distanceError = (distance - ((getLeftEnc() + getRightEnc()) / 2));
    	double speed = distanceError*DISTANCEP;
    	
    	if (distanceError > 3000){
    		speed = .45;
    	}
    	
    	else if (speed > .45){
    		speed = .45;
    	}
    	else if (speed < .1 && speed > 0){
    		speed = .1;
    	}
    	else if(speed > -.1 && speed < 0){
    		speed = -.1;
    	}
    	
    	double driftError = getGyro();
    	setSpeed(speed-((GYRO_P)*driftError), speed+((GYRO_P)*driftError));
    	
    }//end driveStraightDistance
    
    public boolean isAtTarget(double target){
    	boolean atTarget = false;
    	double current = (getLeftEnc() + getRightEnc())/2;
    	
    	if (current < (target+75) && current > (target-75)){
    		timer.start();
    	}
    	else{
    		timer.stop();
    		timer.reset();
    	}
    	
    	if(timer.get() >.25){
    		atTarget = true;
    	}
    	return atTarget;
    	
    }// end isAtTarget
    
    public void headingCorrection (double heading){
    	double driftError = heading - getGyro();
    	setSpeed(((GYRO_P)*driftError), -((GYRO_P)*driftError));
    	
    }//end headingCorrection
    
    public void turnAngle(double angle){
    	
    }
    
    public void setTurnSpeed(double speed){
    	setSpeed(speed, -speed);
    }
    
    
 //______________________________________________________________________________ 
 //SENSOR METHODS  
  
    public double getGyro(){
    	return gyro.getAngle();
    }
    
    public void resetSensorData(){
    	gyro.reset();
    	leftEnc.reset();
    	rightEnc.reset();
    }
    
    public double getLeftEnc(){
    	return -leftEnc.getRaw();
    }
    
    public double getRightEnc(){
    	return rightEnc.getRaw();
    }
}











