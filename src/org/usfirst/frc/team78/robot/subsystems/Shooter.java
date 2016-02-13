package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    

	//MOTORS
	Talon rightShooter = new Talon(RobotMap.RIGHT_SHOOTER);
	
	//SENSORS
	Encoder rightEnc = new Encoder(RobotMap.RIGHT_SHOOTER_ENC_A, RobotMap.RIGHT_SHOOTER_ENC_B);
	
	//VARIABLES
	public double shooterSpeed = 0;
	public double pComponent;
	public double iComponent = 0;
	public double rateError;
	boolean timerStart = false;
	
	//CONSTANTS
	final double SHOOTER_P = .0000034;
	final double SHOOTER_I = .0000037;
	
	//TIMER
	Timer timer = new Timer();

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
//____________________________________________________________________________________________________________________________
//MOTOR METHODS
    
    public void setShooterSpeed(double speed){
    	rightShooter.set(speed);
    }
    
    public void setShooterRate(double rate){
    	rateError = rate - getShooterRate();
    	pComponent = (SHOOTER_P)*rateError;
    	iComponent = (SHOOTER_I)*(rateError + (2/3)*iComponent);
    	//iComponent = rateError + iComponent;
    	
    	
    	shooterSpeed = shooterSpeed + pComponent + iComponent;
    	setShooterSpeed(shooterSpeed);

    }//end setShooterRate
    
    public void stopShooter(){
    	setShooterSpeed(0);
    }
    
    public boolean isAtShooterRate (double targetRate){
    	boolean atRate = false;
    	
    	double current = getShooterRate();
    	
    	if (current < (targetRate+250) && current > (targetRate-250)){
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
    		atRate = true;
    	}
    	return atRate;
    	
    }// end isAtTurnTarget
    
//____________________________________________________________________________________________________________________________
//SENSOR METHODS
    
    public double getRightShooterEnc(){
    	return rightEnc.getRaw();
    }
    
    public double getShooterRate(){
    	return rightEnc.getRate() * 10;//convert to wheel rpm output
    }
    
    public void resetSensorData(){
    	rightEnc.reset();
    }
    
}

