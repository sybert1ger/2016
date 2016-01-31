package org.usfirst.frc.team78.robot.subsystems;



import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
    
	//MOTORS
	Victor leftDrive1 = new Victor(RobotMap.LEFT_DRIVE_1);
	Victor rightDrive1 = new Victor(RobotMap.RIGHT_DRIVE_1);
	Victor leftDrive2 = new Victor(RobotMap.LEFT_DRIVE_2);
	Victor rightDrive2 = new Victor(RobotMap.RIGHT_DRIVE_2);
	
	//SENSORS
	AnalogGyro gyro = new AnalogGyro(RobotMap.GYRO);
	Encoder leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B);
	Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B);
	AnalogInput ultrasonic = new AnalogInput(1);
	
	//VARIABLES
	double distanceError;
	boolean timerStart = false;
	public Boolean isTurnFinished = false;
	
	//CONSTANTS
	final double GYRO_P = (.01);
	final double DISTANCEP = 0.0002;
	final double TURN_P = .0035;
	
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
    	setSpeed(left*1, right*1);
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
    	else if (speed < .15 && speed > 0){
    		speed = .15;
    	}
    	else if(speed > -.15 && speed < 0){
    		speed = -.15;
    	}
    	
    	double driftError = getGyro();
    	setSpeed(speed-((GYRO_P)*driftError), speed+((GYRO_P)*driftError));
    	
    }//end driveStraightDistance
    
    
    public boolean isAtDistanceTarget(double target){
    	boolean atTarget = false;
    	
    	double current = (getLeftEnc() + getRightEnc())/2;
    	
    	if (current < (target+75) && current > (target-75)){
    		if(timerStart == false){
    			timerStart = true;
    			timer.start();
    		}
    		
    	}
    	
    	else{
    		
    		if(timerStart == true){
    			timer.stop();
    			timer.reset();
    			timerStart = false;
    		}
    	}
    	
    	if(timer.get() >.25){
    		atTarget = true;
    
    	}
    	
    	
    	return atTarget;
    	
    }// end isAtDistanceTarget
    
    
    public void headingCorrection (double heading){
    	double driftError = heading - getGyro();
    	setSpeed(((GYRO_P)*driftError), -((GYRO_P)*driftError));
    	
    }//end headingCorrection
    
    
    public void turnAngle(double target){
    	double speed;
    	double error = target- getGyro();
    	speed = TURN_P*(error);
    	
    	if (speed > .7){
    		speed = .7;
    	}
    	if(speed < -.7){ 
    		speed = -.7;
    	}
    	
    	if (speed < .1 && speed > 0){
    		speed = .1;
    	}
    	if(speed > -.1 && speed < 0){ 
    		speed = -.1;
    	}
    	
    	setTurnSpeed(speed);
    }
    
    public boolean isAtTurnTarget(double target){
    	boolean atTarget = false;
    	
    	double current = getGyro();
    	
    	if (current < (target+5) && current > (target-5)){
    		if(timerStart == false){
    			timerStart = true;
    			timer.start();
    		}
    		
    	}
    	
    	else{
    		
    		if(timerStart == true){
    			timer.stop();
    			timer.reset();
    			timerStart = false;
    		}
    	}
    	
    	if(timer.get() >.25){
    		atTarget = true;
    		isTurnFinished = true;
    	}
    	return atTarget;
    	
    }// end isAtTurnTarget
    
    public void setTurnSpeed(double speed){
    	setSpeed(speed, -speed);
    }// end setTurnSpeed

    
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
    
    public double getUltra(){
    	return ((double)ultrasonic.getValue() - 20.0)/120.0;//converts to feet, established through test distances
    }
}











