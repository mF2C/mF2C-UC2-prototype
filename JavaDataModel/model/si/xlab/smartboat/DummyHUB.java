package si.xlab.smartboat;

import java.util.ArrayList;
import java.util.List;

public class DummyHUB extends SensorHUB {

	private final boolean simulateVirtual;
    private List<RandomNumberSensor> randomSensors;
	
	// the dummy hub will use random number generator "sensors"
	// and differently process the results
	// to simulate an actual HUB with multiple sensors
	// and a virtual one, that is basically the wrapper around
	// the direct sensor code communication
	public DummyHUB(boolean virtual) {
		simulateVirtual = virtual;
		if(virtual) {
			// we are simulating just a wrapped sensor communication
			randomSensors = new ArrayList<RandomNumberSensor>(1);
			randomSensors.add(new RandomNumberSensor());
			
		}else {
			// we are simulating HUB communication with multiple sensors
			randomSensors = new ArrayList<RandomNumberSensor>(4);
			randomSensors.add(new RandomNumberSensor(0, "Was Negative Number", new RandomNumberSensor.ResultProcesser() {
				@Override
				public float processInt(int i) {
					if(i < 0)
						return 1;
					return 0;
				}
			}));
			randomSensors.add(new RandomNumberSensor(1, "3 Decimals", new RandomNumberSensor.ResultProcesser() {
				@Override
				public float processInt(int i) {
					return i * 0.001f;
				}
			}));
			randomSensors.add(new RandomNumberSensor(2, "Procents", new RandomNumberSensor.ResultProcesser() {
				@Override
				public float processInt(int i) {
					return i % 100;
				}
			}));
			randomSensors.add(new RandomNumberSensor(3, "Absolute Values", new RandomNumberSensor.ResultProcesser() {
				@Override
				public float processInt(int i) {
					if(i < 0)
						return -i;
					return i;
				}
			}));
		}
	}
	
	@Override
	public List<? extends Sensor> getSensorList() {
		return randomSensors;
	}

	@Override
	public void printFreshData() {
		for(RandomNumberSensor sensor : randomSensors)
			System.out.println(sensor.getFreshMeasurement());
	}

	@Override
	public void shutdownSensors() {
		// NOTHING TO DO
	}
	
	@Override
	public boolean isVirtualHUB() {
		return simulateVirtual;
	}

	@Override
	public void printSensorList() {
		for(RandomNumberSensor sensor : randomSensors)
			System.out.println(sensor);
	}

	public float getAVG() {
		float sum =0;
		for(RandomNumberSensor sensor : randomSensors)
			sum += sensor.getAVG();
		return sum / randomSensors.size();
	}
	
}
