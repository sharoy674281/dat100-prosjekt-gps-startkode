package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint gpspoint1 = new GPSPoint(1, 2.0, 3.0, 5.0);
		
		GPSPoint gpspoint2 = new GPSPoint(2, 3.0, 4.0, 7.0);

		GPSData gpsdata = new GPSData(2);
		
		gpsdata.insertGPS(gpspoint1);
		
		gpsdata.insertGPS(gpspoint2);
		
		gpsdata.print();

	}
}
