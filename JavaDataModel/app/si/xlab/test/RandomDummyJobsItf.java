package si.xlab.test;

import integratedtoolkit.types.annotations.Constraints;
import integratedtoolkit.types.annotations.Parameter;
import integratedtoolkit.types.annotations.Parameter.Direction;
import integratedtoolkit.types.annotations.Parameter.Type;
import integratedtoolkit.types.annotations.task.Method;

public interface RandomDummyJobsItf {
	
	@Constraints(computingUnits = "1", memorySize = "0.3")
	@Method(declaringClass = "si.xlab.test.RandomDummyJobsImpl")
	void saveFleetAVG2(
		@Parameter(type = Type.STRING, direction = Direction.INOUT)
		String avarageAlias
	);

}
