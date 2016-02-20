package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team78.robot.commands.AlternateIntake;
import org.usfirst.frc.team78.robot.commands.AntiIntake;
import org.usfirst.frc.team78.robot.commands.AntiReadyShoot;
import org.usfirst.frc.team78.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team78.robot.commands.DriveTime;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team78.robot.commands.HeadingCorrection;
import org.usfirst.frc.team78.robot.commands.Intake;
import org.usfirst.frc.team78.robot.commands.MoveIntake;
import org.usfirst.frc.team78.robot.commands.PortCoooolis;
import org.usfirst.frc.team78.robot.commands.PunchPancake;
import org.usfirst.frc.team78.robot.commands.AlternateShooter;
import org.usfirst.frc.team78.robot.commands.MoveShooter;
import org.usfirst.frc.team78.robot.commands.LowGoal;
import org.usfirst.frc.team78.robot.commands.ReadyShoot;
import org.usfirst.frc.team78.robot.commands.ReadyShoot90;
import org.usfirst.frc.team78.robot.commands.ResetSensors;
import org.usfirst.frc.team78.robot.commands.SeekGoal;
import org.usfirst.frc.team78.robot.commands.SetIntakeSpeed;
import org.usfirst.frc.team78.robot.commands.SetShooterRate;
import org.usfirst.frc.team78.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team78.robot.commands.StUCK;
import org.usfirst.frc.team78.robot.commands.StopShooter;
import org.usfirst.frc.team78.robot.commands.TestCommand;
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
	public Joystick tStick;
	public Joystick weekZeroMStick;
	
	
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
	
	//TEST STICK
	public Button btn1T;
	public Button btn2T;
	public Button btn3T;
	public Button btn4T;
	public Button btn5T;
	public Button btn6T;
	public Button btn7T;
	public Button btn8T;
	public Button btn9T;
	
	//WEEK ZERO MISTAKES STICK
	public Button btn1W;
	public Button btn2W;
	public Button btn3W;
	public Button btn4W;
	public Button btn5W;
	public Button btn6W;
	public Button btn7W;
	public Button btn8W;
	public Button btn9W;
	
	
	
	//CONSTANTS
	final static double STICK_DEADZONE = 0.05;
	
	
	
	
	
	public OI(){
		driverStick = new Joystick(0);
		manipulatorStick = new Joystick(3);
		tStick = new Joystick(2);
		weekZeroMStick = new Joystick(1);
		
		btn6 = new JoystickButton(driverStick, 6);
		btn6.whileHeld(new PortCoooolis());
		
//__________________________________________________________________________________________________________________________________
//WEEK ZERO WEIRD TEMPORARY BUTTONS
		
		btn1W = new JoystickButton(weekZeroMStick, 1);
		btn1W.whenPressed(new Intake());
		btn1W.whenReleased(new SetShooterSpeed(0));
		
		btn6W = new JoystickButton(weekZeroMStick, 6);
		btn6W.whenPressed(new MoveShooter("up"));
		btn6W.whenReleased(new MoveShooter("down"));
		
		btn5W = new JoystickButton(weekZeroMStick, 5);
		btn5W.whenPressed(new AlternateIntake());
		
		btn4W = new JoystickButton(weekZeroMStick, 4);
		btn4W.whenPressed(new ReadyShoot());
		btn4W.whenReleased(new AntiReadyShoot());
					
		btn3W = new JoystickButton(weekZeroMStick, 3);
		btn3W.whenPressed(new PunchPancake());
		
		btn2W = new JoystickButton(weekZeroMStick, 2);
		btn2W.whenPressed(new ReadyShoot90());
		btn2W.whenReleased(new AntiReadyShoot());
		
		btn7W = new JoystickButton(weekZeroMStick, 7);
		btn7W.whileHeld(new StUCK());
		
		btn8W = new JoystickButton(weekZeroMStick, 8);
		btn8W.whenPressed(new LowGoal());
		btn8W.whenReleased(new SetShooterSpeed(0));
		
		
		//btn6W = new JoystickButton(weekZeroMStick, 6);
		//btn6W.whenPressed(new SetIntakeSpeed(.5));
		
		
		//toggle intake
		//toggle shooter
		//spin up 
		//fire
		
		
		
//__________________________________________________________________________________________________________________________________________
		
		btn1M = new JoystickButton(manipulatorStick, 1);
		//btn1M.whileHeld(command);
		btn1M.whenPressed(new Intake());
		btn1M.whenReleased(new AntiIntake());
		
		btn3M = new JoystickButton(manipulatorStick, 3);
		btn3M.whenPressed(new PunchPancake());
		//btn3M.whenReleased(new AlternatePancake());
		
		btn4M = new JoystickButton(manipulatorStick, 4); 
		btn4M.whenPressed(new ReadyShoot());
		btn4M.whenReleased(new AntiReadyShoot());
		
		btn2M = new JoystickButton(manipulatorStick, 2); //spin up shooters 50%
		btn2M.whenPressed(new ReadyShoot90());
		btn2M.whenReleased(new AntiReadyShoot());
		
				
		btn7M = new JoystickButton(manipulatorStick, 7);
		btn7M.whileHeld(new StUCK());
		//btn2M.whenReleased(new SetIntakeSpeed(0));
		
		
		
			//btn3M.whenPressed(new Turn(90));
				//btn3M.whenPressed(new DriveStraightDistance(10));
		/*btn4M = new JoystickButton(manipulatorStick, 4); //spin up shooter 85%
		btn4M.whileHeld(new SetShooterSpeed(1));
		btn4M.whenReleased(new StopShooter());*/
		
		btn5M = new JoystickButton(manipulatorStick, 5);
		btn5M.whenPressed(new AlternateIntake());
		
		btn6M = new JoystickButton(manipulatorStick, 6);
		btn6M.whenPressed(new AlternateShooter());
		
		btn8M = new JoystickButton(manipulatorStick, 8);
		btn8M.whileHeld(new LowGoal());
		btn8M.whenReleased(new SetShooterSpeed(0));
		//btn8M.whenReleased(new SetShooterSpeed(0));
		
		//btn9M = new JoystickButton(manipulatorStick, 9);
		//btn9M.whenPressed(new ResetSensors());
		
		
//__________________________________________________________________________________________________________________________________________
	
		btn1T = new JoystickButton(tStick, 1);
		btn1T.whenPressed(new PunchPancake());
		//btn1T.whileHeld(new Intake());
		//btn1T.whenReleased(new AntiIntake());
		//btn1T.whenPressed(new Turn(90));
		
		btn2T = new JoystickButton(tStick, 2);
		btn2T.whenPressed(new AlternateIntake());
		//btn2T.whenPressed(new MoveShooter("up"));
		
		btn3T = new JoystickButton(tStick, 3);
		btn3T.whenPressed(new AlternateShooter());
		
		btn4T = new JoystickButton(tStick, 4);
		btn4T.whenPressed(new MoveShooter("down"));
		
		/*//btn1T.whenPressed(new TestCommand());
		
		/*btn1T = new JoystickButton(tStick, 1);
		btn1T.whenPressed(new MoveIntake("down"));
		
		btn2T = new JoystickButton(tStick, 1);
		btn2T.whenPressed(new MoveIntake("up"));
		
		btn3T = new JoystickButton(tStick, 2);
		btn2T.whenPressed(new AlternateIntake());*/
		
		
		
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

