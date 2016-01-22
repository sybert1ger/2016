package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.MoveCamera;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
	//Camera cam = new Camera(RobotMap.CAM); 
	Servo pan = new Servo(RobotMap.PAN);
	Servo tilt = new Servo(RobotMap.TILT);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new MoveCamera());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void moveCamera() {
      pan.setAngle(Robot.oi.getCameraX());
      tilt.setAngle(Robot.oi.getCameraY());
    }
    
}

