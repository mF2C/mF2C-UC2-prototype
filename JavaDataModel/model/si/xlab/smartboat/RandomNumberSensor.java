package si.xlab.smartboat;

import java.util.Random;

public class RandomNumberSensor extends Sensor {

	// define for instant lambda functions
	public interface ResultProcesser{ public float processInt(int i); }

	private final static String generalName = "Random number generator";
	private final static Random numberGenerator = new Random();
	// defined in constructor
	private final ResultProcesser processor;
	
	public RandomNumberSensor() {
		this(numberGenerator.nextInt(), "Default", null);	
	}

	public RandomNumberSensor(int id, String nameSuffix, ResultProcesser function) {
		super(id, SensorGroup.HUB, String.format("%s [%s]", generalName, nameSuffix), null, 1);
		// use default
		if(function == null) {
			processor = new ResultProcesser() {
				@Override
				public float processInt(int i) {
					return i;
				}
			};
		}else {
			processor = function;
		}
	}
	
	@Override
	public Measurement getFreshMeasurement() {
		history.add(new Measurement(processor.processInt(numberGenerator.nextInt())));
		return super.getFreshMeasurement();
	}
	
	public float getAVG() {
		float sum = 0;
		for(Measurement m : history)
			sum += m.value;
		return sum / history.size();
	}

}
