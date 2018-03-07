package si.xlab.test;

import dataclay.api.DataClay;
import si.xlab.smartboat.DummyHUB;
import si.xlab.smartboat.Measurement;

public class RandomDummyJobsImpl implements RandomDummyJobsItf {

	@Override
	public void saveFleetAVG2(String avarageAlias) {
		try {
			// Init dataClay session
			DataClay.init();
			// simulate search if still in network
			DummyHUB virtualHUB = DummyHUB.getByAlias("virtual");
			virtualHUB.printFreshData();
			Measurement avg = new Measurement(virtualHUB.getAVG());
			avg.makePersistent(avarageAlias);
			DataClay.finish();
		} catch (DataClayException e1) {
				e1.printStackTrace();
		}

	}

}
