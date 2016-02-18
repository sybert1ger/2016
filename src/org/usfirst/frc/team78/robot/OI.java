package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team78.robot.commands.AntiReadyShoot;
import org.usfirst.frc.team78.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team78.robot.commands.DriveTime;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team78.robot.commands.HeadingCorrection;
import org.usfirst.frc.team78.robot.commands.Intake;
import org.usfirst.frc.team78.robot.commands.MoveIntake;
import org.usfirst.frc.team78.robot.commands.MovePancake;
import org.usfirst.frc.team78.robot.commands.MoveShooter;
import org.usfirst.frc.team78.robot.commands.ReadyShoot;
import org.usfirst.frc.team78.robot.commands.ResetSensors;
import org.usfirst.frc.team78.robot.commands.SeekGoal;
import org.usfirst.frc.team78.robot.commands.SetIntakeSpeed;
import org.usfirst.frc.team78.robot.commands.SetShooterRate;
import org.usfirst.frc.team78.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team78.robot.commands.StopShooter;
import org.usfirst.frc.team78.robot.commands.Turn;
import org.usfirst.frc.team78.robot.commands.TurnAdditional;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//JOYSTICKS
	public Joystick driverStick;
	public Joystick manipulatorStick;
	
	
	//DRIVER BUTTONS
	public Button btn1;
	public Button btn2;
	public Button btn3;
	public Button btn4;
	public Button btn5;
	public Button btn6;
	public Button btn7;
	public Button btn8;
		
	//MANIPULATOR BUTTONS
	public Button btn1M;
	public Button btn2M;
	public Button btn3M;
	public Button btn4M;
	public Button btn5M;
	public Button btn6M;
	public Button btn7M;
	public Button btn8M;
	public Button btn9M;
	
	
	
	//CONSTANTS
	final static double STICK_DEADZONE = 0.05;
	
	
	
	
	
	public OI(){
		driverStick = new Joystick(0);
		manipulatorStick = new Joystick(1);
		
		//btn1 = new JoystickButton(driverStick, 1);
		//btn1.whileHeld(new Intake());
		//btn1.whenReleased(new SetShooterSpeed(0));
		
		
		//btn2 = new JoystickButton(driverStick, 2);
		//btn2.whenPressed(new MoveIntake());
			//btn2.whenPressed(new ResetSensors());
		
		//btn3 = new JoystickButton(driverStick, RobotMap.SHOOTER_PN);
		//btn3.whenPressed(new MovePancake());
			//btn3.whileHeld(new SetIntakeSpeed(.75));
			//btn3.whileHeld(new SetShooterSpeed(-.3));
				
		//btn4 = new JoystickButton(driverStick, RobotMap.INTAKE_PN);
		//btn4.whileHeld(new ReadyShoot());
		//btn4.whenReleased(new AntiReadyShoot());
		
		
		//btn6 = new JoystickButton(driverStick, RobotMap.PANCAKE_PN);
		//btn6.whenPressed(new MoveShooter());
		btn6 = new JoystickButton(driverStick, 6);
		btn6.whileHeld(new SetIntakeSpeed(-0.75));
		
		btn5 = new JoystickButton(driverStick, 5);
		btn5.whileHeld(new SetShooterSpeed(.95));
		btn5.whenReleased(new StopShooter());
		
		btn8 = new JoystickButton(driverStick, 8);
		btn8.whileHeld(new SetShooterRate(7200));
		
		
//__________________________________________________________________________________________________________________________________________
		
		btn1M = new JoystickButton(manipulatorStick, 1);
		btn1M.whileHeld(new Intake());
		btn1M.whenReleased(new SetShooterSpeed(0));
		
		btn3M = new JoystickButton(manipulatorStick, 3);
		btn3M.whenPressed(new MovePancake());
		btn3M.whenReleased(new MovePancake());
		
		btn2M = new JoystickButton(manipulatorStick, 2); //spin up shooters 50%
		btn2M.whileHeld(new SetShooterSpeed(.50));
		btn2M.whenReleased(new StopShooter());		
			//btn3M.whenPressed(new Turn(90));
				//btn3M.whenPressed(new DriveStraightDistance(10));
		btn4M = new JoystickButton(manipulatorStick, 4); //spin up shooter 85%
		btn4M.whileHeld(new SetShooterSpeed(0.92));
		btn4M.whenReleased(new StopShooter());
		
		btn5M = new JoystickButton(manipulatorStick, 5);
		btn5M.whenPressed(new MoveIntake());
		
		btn6M = new JoystickButton(manipulatorStick, 6);
		btn6M.whenPressed(new MoveShooter());
		
		btn9M = new JoystickButton(manipulatorStick, 9);
		btn9M.whenPressed(new ResetSensors());
	}
	
	///DRIVER STICK
	public double getDriverLeftStick() {
		double stick = driverStick.getY();
		if (Math.abs(stick) < STICK_DEADZONE){
			return 0;
		}
		else
			return -stick;
	}
	
	public double getDriverRightStick() {
		double stick = driverStick.getThrottle();
		if (Math.abs(stick) < STICK_DEADZONE){
			return 0;
		}
		else
			return -stick;
	}
	
	
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

