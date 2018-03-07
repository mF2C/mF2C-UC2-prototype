package si.xlab.test;

import dataclay.api.DataClay;
import dataclay.api.DataClayException;
import si.xlab.smartboat.DummyHUB;

public class RandomDummyTest {

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
			actualHUB.printSensorList();
			
			while(true) {
				System.out.println("Virtual");
				virtualHUB.printFreshData();
				System.out.println("Actual");
				actualHUB.printFreshData();
	
				// sleep 1s
				try{
					Thread.sleep(1000);
				}catch(Exception e) {
					// on interruption
					DataClay.finish();
				}
			}
		} catch (DataClayException e1) {
			e1.printStackTrace();
		}
		
	}

}
