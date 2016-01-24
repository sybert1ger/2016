package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinTurn extends Command {

	double speed;
	double angle;
	
    public SpinTurn(double i) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        angle = i;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetSensorData();
    	Robot.chassis.setSpeed(angle / 410, -(angle / 410));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	speed = Robot.chassis.scaleSpeed(Robot.chassis.getGyro(), angle);
    	Robot.chassis.setSpeed(speed, -speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.chassis.getGyro() >= angle){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopAllDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
