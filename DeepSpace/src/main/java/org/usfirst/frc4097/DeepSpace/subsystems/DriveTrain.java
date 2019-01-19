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


import org.usfirst.frc4097.DeepSpace.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.SPI;
/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private VictorSP speedController1;
    private VictorSP speedController2;
    private DifferentialDrive differentialDrive1;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    double Kp = 0.03;
    AHRS gyro = new AHRS(SPI.Port.kMXP);
    
    public DriveTrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        speedController1 = new VictorSP(3);
        addChild("Speed Controller 1",speedController1);
        speedController1.setInverted(false);
        
        speedController2 = new VictorSP(1);
        addChild("Speed Controller 2",speedController2);
        speedController2.setInverted(false);
        
        differentialDrive1 = new DifferentialDrive(speedController1, speedController2);
        addChild("Differential Drive 1",differentialDrive1);
        differentialDrive1.setSafetyEnabled(true);
        differentialDrive1.setExpiration(0.1);
        differentialDrive1.setMaxOutput(1.0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new OperatorDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }


    public void arcadeDrive(double xSpeed, double zRotation) {
        differentialDrive1.arcadeDrive(xSpeed, zRotation);
    }

    public void driveStraight(double moveSpeed){
        //double currentAngle = this.getAngle();
        //arcadeDrive(moveSpeed, -currentAngle*Kp);
        differentialDrive1.tankDrive(moveSpeed, moveSpeed, false);
    }

    public void curvatureDrive(double xSpeed, double zRotation) {
        differentialDrive1.curvatureDrive(xSpeed, zRotation, true);
    }

    public double getAngle(){
        double z = gyro.getAngle();
        while (z>=360){
            z=z-360;
            //makes sure that angle output is always between 0 and 360
        }
        return z;
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

