package si.xlab.smartboat;

import java.util.ArrayList;
import java.util.List;

abstract class Sensor {
	
	// each sensor type has to have following final values
	// this should be set in the constructor of each subclass
	protected final String SENSOR_TYPE;	// not set == 'NO TYPE'
	protected final String NAME;		// not set == 'NO NAME'
	// null when no unit
	protected final String BASIC_UNIT;	// not set == 'NO UNIT'
	// should be 1 when no unit
	protected final float UNIT_FACTOR;	// not set == 0
	
	// each actual sensor should also hold an id
	protected final int id;
	
	// data structures across subclasses
	protected List<Measurement> history;

	protected Sensor() {
		this(-1);
	}
	
	protected Sensor(int id) {
		this(id, "NO TYPE", "NO NAME", "NO UNIT", 0);
	}
	
	public Sensor(int id, SensorGroup groupType, String name, String unit, float factor) {
		this(id, groupType.toString(), name, unit, factor);
	}
	
	protected Sensor(int id, String type, String name,
						String unit, float factor) {
		this.id = id;
		SENSOR_TYPE = type;
		NAME = name;
		BASIC_UNIT = unit;
		UNIT_FACTOR = factor;
		history = new ArrayList<Measurement>();
	}

	public String toString() {
		return String.format("Sensor %s [ID = %d] is %s type with %f * %s ",
								NAME,
								id,
								SENSOR_TYPE,
								UNIT_FACTOR,
								BASIC_UNIT
		);
	}

	// getters for outside subclasses
	public int getId() {
		return id;
	}
	
	public String getType() {
		return SENSOR_TYPE;
	}
	
	public String getName() {
		return NAME;
	}
	
	public String getBasicUnit() {
		return BASIC_UNIT;
	}
	
	public float getUnitFactor() {
		return UNIT_FACTOR;
	}

	/*
	 *  the (abstract) methods to be overwritten
	 */
	
	public Measurement getFreshMeasurement() {
		if(history.size() < 1)
			return null;
		return history.get(history.size()-1);
	}
	
	public List<Measurement> getMeasurementHistory(){
		return history;
	}

	/*
	 * FOR NOW NOT YET NEEDED
	 * 
	public abstract List<Measurement> getMeasurementHistory(long from);
	public abstract List<Measurement> getMeasurementHistory(long from, long till);
	*/
}

// To simplify type grouping
enum SensorGroup
{
	POWER("Power Supply"),
	HUB("Sensor HUB"),
	MOBILE("Mobile Service"),
	BLUETOOTH("Bluetooth"),
	GPS("GPS"),
	MOVEMENT("Movement"),
	WEATHER("Weather"),
	SHIP("Ship Condition"),
	CHIPOLO("Chipolo");
 
    private String type;
 
    SensorGroup(String type) {
        this.type = type;
    }
 
    public String toString() {
        return type;
    }
}
