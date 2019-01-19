// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4097.DeepSpace.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4097.DeepSpace.Robot;
import org.usfirst.frc4097.DeepSpace.subsystems.DriveTrain;

/**
 *
 */
public class OperatorDrive extends Command {

        double moveSpeed;
        double rotateSpeed;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public OperatorDrive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double moveSpeed = Robot.oi.joystick1.getRawAxis(1);
        double rotateSpeed = Robot.oi.joystick1.getRawAxis(0);
        double rotateDime = Robot.oi.joystick1.getRawAxis(2);

        moveSpeed = moveSpeed*.5;
        rotateSpeed = rotateSpeed * -.5;
        rotateDime = rotateDime * -0.5;
        if (Robot.oi.joystick1.getRawAxis(2)<1 && Robot.oi.joystick1.getRawAxis(2)>-1){
            Robot.driveTrain.curvatureDrive(moveSpeed, rotateSpeed);
        }
        else{
        Robot.driveTrain.arcadeDrive(moveSpeed, rotateDime);
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
