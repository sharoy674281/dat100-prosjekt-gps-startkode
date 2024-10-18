package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		int tid = 0;
		int time = Integer.parseInt(timestr.substring(5, 7));
		int minutt = Integer.parseInt(timestr.substring(14, 16));
		int sekund = Integer.parseInt(timestr.substring(17, 19));
		tid += sekund + minutt * 60 + time * 60 * 60;
		return tid;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		GPSPoint gpsPoint = new GPSPoint(time,latitude, longitude, elevation);
		return gpsPoint;
	}
	
}
