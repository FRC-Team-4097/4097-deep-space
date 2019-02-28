// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4097.DeepSpace.subsystems;


import java.util.Dictionary;

import org.usfirst.frc4097.DeepSpace.Robot;
import org.usfirst.frc4097.DeepSpace.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class GrabberArm extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private VictorSP pivotMotorUpper;
    private VictorSP pivotMotorLower;
    private AnalogPotentiometer upperPot;
    private AnalogPotentiometer lowerPot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // works when g=0.2
    double g = 0.1;
    public double lowerTarget = 0;
    public double upperTarget = 0;
    //Based on what these targets are used for, they should probably be changed to parameters, rather than variables
    public boolean autoControl = false;
    public String presetString = "None";
    public GrabberArm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        pivotMotorUpper = new VictorSP(0);
        addChild("pivotMotorUpper",pivotMotorUpper);
        pivotMotorUpper.setInverted(false);
        
        pivotMotorLower = new VictorSP(9);
        addChild("pivotMotorLower",pivotMotorLower);
        pivotMotorLower.setInverted(false);
        
        upperPot = new AnalogPotentiometer(0, 270.0, 0.0);
        addChild("UpperPot",upperPot);
        
        
        lowerPot = new AnalogPotentiometer(1, 270.0, 0.0);
        addChild("LowerPot",lowerPot);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        //PIDController is no longer in the robot map
        //PIDController ElbowController = new PIDController(0.1, 0.1, 0.1, lowerPot, pivotMotorLower);
        //fix them numbers
        //pot midpoint is 115
    }
    public void elbowTurn(double ry){
        //turns the motor closest to the bot frame dependent on ry
        //CURRENTLY WORKS, BUT STALLS MOTOR
        ry=0.75*ry;
        if (ry >= -g && ry <= g){
            if (lowerPot.get() >= 90){
                if (lowerPot.get() >= 115){
                    ry = 0;
                }
                else{
                    ry = -g/2;
                }
            }
            else{
                ry = g/2;
            }
        }
        /*if (lowerPot.get()<=115){
            ry = ry-0.15;
        }*/
        //else if (ry < -g){
            //ry=ry+g;
        //}
        //constant meant to fight gravity
        /*
        if (ry==0){
            potAdjust(getLowerPot());
        }*/
        pivotMotorLower.setSpeed(-ry);
        SmartDashboard.putNumber("ry", ry);
    }
    /*public void potAdjust(double t){
        if (getLowerPot()>t){
            pivotMotorLower.setSpeed(g);
        }
        else{
            pivotMotorLower.setSpeed(-g);
        }*/
        //https://wpilib.screenstepslive.com/s/3120/m/7912/l/79828-operating-the-robot-with-feedback-from-sensors-pid-control
        //Use above link to help set up potAdjust for continouous pid
        //https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599736-pidsubsystems-for-built-in-pid-control
        //Note: the pot was set up with analog potentiometer rather than as pid controller
    //}
    public void wristTurn(double ly){
        //does the same thing as elbowturn, but does it for the motor closest to the pivot arm
        ly=0.2*ly;
        //previously 0.15
        pivotMotorUpper.setSpeed(ly);
    }
    public void stopArms(){
        pivotMotorLower.setSpeed(0.5*g);
        pivotMotorUpper.setSpeed(0);
    }

    public void goToTargetAngle(){
        //Straight: -110
        //Gay: -200
        double lowerAngle = lowerPot.get();
        double upperAngle = upperPot.get();
        //remove UpperAngle, it's not used for anything
        if (autoControl){
            double speed = (lowerTarget-lowerAngle)/180;
            if (speed < 0.25){
                speed = 0.25;
            }
            if ((lowerTarget-lowerAngle) >=5 || (lowerTarget-lowerAngle) <= -5){
                elbowTurn(speed);
            }
            else {
                autoControl = false;
            }
        }
    }
    
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new GrabbyGrab());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("UpperPot", upperPot.get());
        SmartDashboard.putNumber("LowerPot", lowerPot.get());
        SmartDashboard.putBoolean("autoControl", autoControl);
        SmartDashboard.putString("preset", presetString);
        SmartDashboard.putNumber("right stick", Robot.oi.xbox.getRawAxis(5));
        SmartDashboard.putNumber("left stick", Robot.oi.xbox.getRawAxis(1));
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

