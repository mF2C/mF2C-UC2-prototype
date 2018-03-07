package si.xlab.test;

import si.xlab.smartboat.DummyHUB;
import si.xlab.smartboat.Measurement;

import dataclay.api.DataClay;
import dataclay.api.DataClayException;

public class RandomDummyCOMPSsTest {

	public static void main(String[] args) {
		try {
			// Init dataClay session
			DataClay.init();
			// create 2 boats / hubs - virtual and actual
			DummyHUB virtualHUB = new DummyHUB(true);
			virtualHUB.makePersistent("virtual");
			DummyHUB actualHUB = new DummyHUB(false);
			actualHUB.makePersistent("actual");

			System.out.println("Virtual HUB has");
			virtualHUB.printSensorList();
			System.out.println("Actual HUB has");
			
			// the part that should loop in an interval
			String fleetAvg = "AVG-alias";
			RandomDummyJobsImpl.saveFleetAVG2(fleetAvg);
			

			float localAVG = actualHUB.getAVG();

			DataClay.finish();
			
		} catch (DataClayException e1) {
			e1.printStackTrace();
		}
		
	}

}
