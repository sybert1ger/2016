package org.usfirst.frc.team78.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BensDemands extends CommandGroup {
    
    public  BensDemands() {
      
    	addSequential(new DriveStraightDistance(10));
    	addSequential(new SpinTurn(180));
    	addSequential(new DriveStraightDistance(10));
    }
}
