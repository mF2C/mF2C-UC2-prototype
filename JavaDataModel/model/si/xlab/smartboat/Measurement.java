package si.xlab.smartboat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Measurement {
	
	// for now the id is simply the timestamp
	// latter either hash or other arrangement
	public final String id;
	public final float value;
	public final String hex;
	private final Date timestamp;
	// TODO define timestamp format - currently default
	private final SimpleDateFormat timestampFormat = new SimpleDateFormat();

	
	public Measurement(float measurement) {
		timestamp = new Date();
		value = measurement;
		hex = getHEX(value);
		id = generateId();
	}

	public Measurement(float measurement, String timestamp) {
		Date tmpDate = null;
		try {
			tmpDate = timestampFormat.parse(timestamp);
		} catch (ParseException e) {
			System.out.println("Failed parsing timestamp, setting it to null");
		}
		this.timestamp = tmpDate;
		value = measurement;
		hex = getHEX(value);
		id = generateId();
	}
	
	private String generateId() {
		if (timestamp == null)
			return null;
		return timestamp.toString();
	}
	
	public String getHEX(float value) {
		int floatAsInt = Float.floatToRawIntBits(value);
		return String.format("0x%8s", Integer.toHexString(floatAsInt)).replace(' ', '0');
	}

	public String getTimestamp() {
		return timestamp.toString();
	}
	
    public String toString() {
        return String.format("%.3f\t[%s]\t%s", value, hex, timestamp);
    }
	
}
