package si.xlab.smartboat;

import java.util.List;

abstract class SensorHUB {

	// we want:
	
	/*
	 * 	some hub metadata management
	 * */
	
	//	for database export simplification we
	//	hardcode an indicator for direct sensors
	//	we call such HUBs virtual, since it is
	//  a direct sensor communication
	public abstract boolean isVirtualHUB();

	/*
	 * attached sensor list with details
	 * 		get all sensors
	 * 		add new sensor at end or at index
	 * 		remove sensors
	 * */
	
	public abstract List<? extends Sensor> getSensorList();
	
	public abstract void printSensorList();
	
	/*
	void addSensor();
	void addSensor(int i);
	Sensor popSensor();
	Sensor removeSensor(int i);
	*/
	
	/*
	 * fresh data / latest measurement
	 */
	
	public abstract void printFreshData();
	
	/*
	 * data history from - till
	 * if virtual(direct sensor) / real
	 * producer / name / id
	 * present sensor types
	 */

	/*
	 *  Load measurements from JSON dump
	 * */
	/*
	public void loadMeasurementsFromJSON(JSONArray dump) throws JSONException;
	*/
	/*
	 * manage sensors
	*/ 
	
	public abstract void shutdownSensors();
}
