package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AlternateIntake extends Command {
   
	
	public AlternateIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    if (Robot.intake.intakePnState == false){
		Robot.intake.alternateIntake(true);
		//Robot.intake.intakePnState = true;
	}
	else{
		Robot.intake.alternateIntake(false);
		//Robot.intake.intakePnState = false;
	}
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
