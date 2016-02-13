package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.MoveCam;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    //MOTORS
    Servo pan = new Servo(RobotMap.PAN);
    Servo tilt = new Servo(RobotMap.TILT);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MoveCam());
    }
    
    
    public void moveCam(){
    	pan.set((Robot.oi.getCamX()/2) + 0.5);
    	tilt.set((Robot.oi.getCamY()/2) + 0.5);
    }
    
    public double getVisionX(){
    	return Robot.table.getDouble("X");
    }
    
    public double getVisionY(){
    	return Robot.table.getDouble("Y");
    }

}

