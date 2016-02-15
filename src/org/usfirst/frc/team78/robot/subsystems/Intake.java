package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	//MOTORS
	CANTalon leftIntake = new CANTalon(RobotMap.LEFT_INTAKE);
	CANTalon rightIntake = new CANTalon(RobotMap.RIGHT_INTAKE);
	
	//Solenoids
	DoubleSolenoid lift = new DoubleSolenoid(RobotMap.INTAKE_FOREWARD, RobotMap.INTAKE_REVERSE);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntakeSpeed(double speed){
    	leftIntake.set(speed);
    	rightIntake.set(-speed);
    }
    
    public void stopIntake(){
    	setIntakeSpeed(0);
    }
    
    public void liftIntake(){
    	lift.set(DoubleSolenoid.Value.kForward);
    }
    public void lowerIntake(){
    	lift.set(DoubleSolenoid.Value.kReverse);
    }
}

