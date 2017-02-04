package org.usfirst.frc.team5582.robot.commands;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class FloatInput implements PIDSource {

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		//throw new NotImplementedException();
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 0;
	}

}
