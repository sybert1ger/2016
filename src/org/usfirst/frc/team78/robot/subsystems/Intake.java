package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
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
}

