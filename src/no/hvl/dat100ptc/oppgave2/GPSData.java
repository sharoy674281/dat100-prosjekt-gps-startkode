package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

import static no.hvl.dat100ptc.oppgave2.GPSDataConverter.toSeconds;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		gpspoints = new GPSPoint[n];
		antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		boolean inserted = false;

		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
		}
		return inserted;
	
	}

	public boolean insert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		GPSPoint gpspoint = new GPSPoint(time,latitude,longitude,elevation);
		return insertGPS(gpspoint);
	}

	public void print() {
		for (int i = 0; i < gpspoints.length; i++) {
			System.out.println(gpspoints[i].toString());

		}
	}
}
