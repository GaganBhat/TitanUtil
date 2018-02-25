package org.usfirst.frc.team5431.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;

public class TitanUtilRobot extends IterativeRobot {
	WPI_TalonSRX frontLeft, frontRight, backLeft, backRight, shooterLeft, shooterRight;
	Titan.FSi6S controller;
	Titan.Toggle toggle = new Titan.Toggle();
	
	@Override
	public void robotInit() {
		frontLeft = new WPI_TalonSRX(4);
		frontRight = new WPI_TalonSRX(6);
		backLeft = new WPI_TalonSRX(3);
		backRight = new WPI_TalonSRX(7);
		
		shooterRight = new WPI_TalonSRX(8);
		shooterLeft = new WPI_TalonSRX(2);
		
		shooterLeft.set(ControlMode.Follower, shooterRight.getDeviceID());
		
		backLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
		backRight.set(ControlMode.Follower, frontRight.getDeviceID());
		
		frontLeft.setInverted(true);
		backLeft.setInverted(true);
	
		controller = new Titan.FSi6S(0);
		controller.setDeadzone(0.1);
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		if(toggle.isToggled(controller.getBackLeft())) {
			shooterRight.set(0.8);
		}else {
			shooterRight.set(0.0);
		}
		
		frontLeft.set(controller.getRawAxis(Titan.FSi6S.Axis.LEFT_Y));
		frontRight.set(controller.getRawAxis(Titan.FSi6S.Axis.RIGHT_Y));
	}

}