package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
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
	
	//CONSTANTS
	final double SHOOTER_P = .0000034;
	final double SHOOTER_I = .0000037;

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
    
//____________________________________________________________________________________________________________________________
//SENSOR METHODS
    
    public double getRightShooterEnc(){
    	return rightEnc.getRaw();
    }
    
    public double getShooterRate(){
    	return rightEnc.getRate() * 10;//convert to wheel rpm output
    	
    }//end getShooterRate
    
}

