package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnHeading extends Command {

	double target_deg;
	
    public TurnHeading() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	target_deg = deg;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetSensorData();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.headingCorrection(target_deg);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.chassis.getGyro() < target_deg + 3 && Robot.chassis.getGyro() > target_deg - 3){
    		return true;
    	} else {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
