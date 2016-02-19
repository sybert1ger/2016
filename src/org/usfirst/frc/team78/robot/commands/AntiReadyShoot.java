package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AntiReadyShoot extends CommandGroup {
    
    public  AntiReadyShoot() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	if(Robot.shooter.pancakePnState == true){//if up, bring it down
    		addSequential(new AlternatePancake());
    		addSequential(new DoNothing(1));
    	}
    	
    	if(Robot.shooter.shooterPnState == true){//if down, bring it up
    		addSequential(new AlternateShooter());
    		addSequential(new DoNothing(2));
    	}
    	
    	addSequential(new StopShooter());
    	
    	
    	
    }
}
