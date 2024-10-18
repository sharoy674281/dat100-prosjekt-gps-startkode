package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;



public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double minVerdi = da[0];
		for (int i = 0; i < da.length; i++) {
			if (da[i] < minVerdi) {
				minVerdi = da[i];
			}
		}
		return minVerdi;
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] breddeGrad = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			breddeGrad[i] = gpspoints[i].getLatitude();
		}
		return breddeGrad;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] lengdeGrader = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			lengdeGrader[i] = gpspoints[i].getLongitude();
		}
		return lengdeGrader;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double latitude1 = gpspoint1.getLatitude();
		double longitude1 = gpspoint1.getLongitude();
		double latitude2 = gpspoint2.getLatitude();
		double longitude2 = gpspoint2.getLongitude();

		double phi1 = Math.toRadians(latitude1);
		double phi2 = Math.toRadians(latitude2);
		double deltaPhi = Math.toRadians(latitude2 - latitude1);
		double deltaLambda = Math.toRadians(longitude2 - longitude1);

		double a = compute_a(phi1, phi2, deltaPhi, deltaLambda);

		double c = compute_c(a);

		double avstand = R * c;

		return avstand;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
		double sum = (Math.pow(sin(deltaphi / 2), 2)) + cos(phi1) * cos(phi2) * Math.pow(sin(deltadelta/2),2);
		return sum;
	}

	private static double compute_c(double a) {
		double sum = 2 * atan2(sqrt(a), sqrt((1-a)));
		return sum;
	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double meter = distance(gpspoint1, gpspoint2);
		double tid1 = gpspoint1.getTime();
		double tid2 = gpspoint2.getTime();
		double tid = tid2 - tid1;
		double speed = meter / tid;
		System.out.println(speed);
		return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		throw new UnsupportedOperationException(TODO.method());
		
		// TODO 
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		
	}
}
