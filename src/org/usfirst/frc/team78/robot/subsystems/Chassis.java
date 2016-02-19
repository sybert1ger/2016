package org.usfirst.frc.team78.robot.subsystems;



import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

//import com.kauailabs.navx.frc.AHRS;




import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

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
	//AnalogGyro gyro = new AnalogGyro(RobotMap.GYRO);
	Encoder leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B);
	Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B);
	AnalogInput ultrasonic = new AnalogInput(1);
	AHRS ahrs = new AHRS(SPI.Port.kMXP);
		//download from here http://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/
		//install it all
		//Window->Preferences->Java->Build Path->Classpath Variables->New
		//make a new variable called navx-mxp from <HomeDirectory>\navx-mxp\java\lib\navx_frc.jar
		//right click Referenced Libraries, add the new variable

	
	//VARIABLES
	public Boolean timerStart = false;
	public boolean atTarget = false;
	public boolean noGoal;
	
	
	//CONSTANTS
	final double GYRO_P = (.017);
	final double DISTANCE_P = 0.00035;
	final double VISION_GOAL = -55;
	final double VISION_P = .0027;

	
	//TIMER
	public Timer timer = new Timer();
	
	
	//TEST SHIT
	public boolean didTurnStart = false;

	
	
	
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
    
    
    public void setTurnSpeed(double speed){
    	setSpeed(speed, -speed);
    }
    
    
 //______________________________________________________________________________ 
 //AUTO METHODS  
    
    public double driveStraightDistance(double distance){
    	double distanceError = (distance - ((getLeftEnc()))); //+ getRightEnc()) / 2));
    	double speed = distanceError*DISTANCE_P;
    	
    	/*if (distanceError > 3000){
    		speed = .8;
    	}
    	
    	else if (speed > .8){
    		speed = .8;
    	} */
    	if (speed < .25 && speed > 0){
    		speed = .25;
    	}
    	else if(speed > -.25 && speed < 0){
    		speed = -.25;
    	}
    	
    	//double driftError = getAngle();
    	//setSpeed(speed-((GYRO_P)*driftError), speed+((GYRO_P)*driftError));
    	
    	return speed;
    	
    	
    }//end driveStraightDistance
    
    
    public double headingCorrection (double heading){
    	double driftError = heading - getAngle();
    	
    	if (driftError < -180){
    		driftError = driftError + 360;
    	}
    	else if (driftError > 360){
    		driftError = driftError - 360;
    	}
    	
    	
    	return ((GYRO_P)*driftError);
    	//setSpeed(((GYRO_P)*driftError), -((GYRO_P)*driftError));
    	
    }//end headingCorrection
    
    
    public double turnAngleAdditional(double target){
    	double speed;

    	speed = headingCorrection(target);
    	
    	if (speed > .7){
    		speed = .7;
    	}
    	if(speed < -.7){ 
    		speed = -.7;
    	}
    	
    	if (speed < .25 && speed > 0){
    		speed = .25;
    	}
    	if(speed > -.25 && speed < 0){ 
    		speed = -.25;
    	}
    	
    	return speed;
    	//setTurnSpeed(speed);
    }
    
    public double visionHeadingCorrection(){
    	double error = (VISION_GOAL - Robot.vision.getVisionX());
    	
    	if (error < -180){
    		error = error + 360;
    	}
    	else if (error > 360){
    		error = error - 360;
    	}
    	
    	
    	return -((VISION_P)*error);
    }
    
    public double visionTurn(){
    	double speed;
    	didTurnStart = true;
    	speed = visionHeadingCorrection();
    	
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
    	
    	
    	return speed;
    }
    
    
//_________________________________________________________________________________________________________________________________________
//LOGIC METHODS
    
    public boolean isAtTurnTarget(double target){
    	double correctTarget = (target % 360);
    	atTarget = false;
    	
    	double error = target - getAngle();
    	
    	if (error < -180){
    		error = error + 360;
    	}
    	else if (error > 360){
    		error = error - 360;
    	}

    	if ((error < 3) && (error > -3)){
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
    	
    }// end isAtTurnTarget
    
    public boolean isAtDistanceTarget(double target){
    	boolean atTarget = false;
    	double current = (getLeftEnc()); //+ getRightEnc())/2;
    	
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
    

    public boolean isAtVisionTarget(){
    	
    	double current = Robot.vision.getVisionX();
    	
    	if (current == 0){
    		noGoal = true;
    	}
    	
    	if ((current < (VISION_GOAL + 15)) && (current > (VISION_GOAL - 15))){
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
    	
    }// end isAtTurnTarget

    
 //______________________________________________________________________________ 
 //SENSOR METHODS  
  
   // public double getGyro(){
    	//return gyro.getAngle();
    //}
    
    public void resetSensorData(){
    	//gyro.reset();
    	leftEnc.reset();
    	rightEnc.reset();
    	ahrs.reset();
    	Timer.delay(.25);
    }
    
    public void resetEncs(){
    	leftEnc.reset();
    	rightEnc.reset();
    }
    
    public double getLeftEnc(){
    	return leftEnc.getRaw();
    }
    
    public double getRightEnc(){
    	return -rightEnc.getRaw();
    }
    
    public double getUltra(){
    	return ((double)ultrasonic.getValue() - 20.0)/120.0;//converts to feet, established through test distances
    }
    
    
    public double getAngle(){
    	return ahrs.getAngle();//just look at all the different gets, figure out what is going on
    }
    
    public double getPitch(){
    	return ahrs.getPitch();//just look at all the different gets, figure out what is going on
    }
    
    public double getRoll(){
    	return ahrs.getRoll();//just look at all the different gets, figure out what is going on
    }
    
    public double getRawGyro(){
    	return ahrs.getRawGyroX();
    }
}
