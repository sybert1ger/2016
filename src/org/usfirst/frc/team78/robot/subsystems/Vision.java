package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.MoveCam;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MoveCam);
    }
    
    //MOTORS
    Servo pan = new Servo(RobotMap.PAN);
    Servo tilt = new Servo(RobotMap.TILT);
    
    public void moveCam(){
    	pan.set((Robot.oi.getCamX()/2) + 0.5);
    	tilt.set((Robot.oi.getCamY()/2) + 0.5);
    }

}

