package org.usfirst.frc.team78.robot.subsystems;



import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

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
	
	
	
	
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
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
    
}

