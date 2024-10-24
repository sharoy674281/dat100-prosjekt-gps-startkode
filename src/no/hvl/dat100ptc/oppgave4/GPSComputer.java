package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

import java.security.spec.RSAOtherPrimeInfo;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {
		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {
		double distansen = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distansen += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		System.out.println(distansen);
		return distansen;
	}

	public double totalElevation() {
		double elevation = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double elevation1 = gpspoints[i].getElevation();
			double elevation2 = gpspoints[i + 1].getElevation();

			if (elevation2 > elevation1) {
				elevation += elevation2 - elevation1;
			}
		}
		return elevation;
		
	}

	public int totalTime() {
		int tiden = 0;
		for (int i = 0; i < gpspoints.length -1; i++) {
			tiden += gpspoints[i +1].getTime() - gpspoints[i].getTime();
		}
		return tiden;
	}
		

	public double[] speeds() {
		double[] speeds = new double[gpspoints.length-1];
		for (int i = 0; i < gpspoints.length -1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i +1]);
		}
		return speeds;
	}
	
	public double maxSpeed() {
		double[] speedArray = speeds();
		double firstSpeed = speedArray[0];
		for (int i = 1; i < speedArray.length; i++) {
			if (speedArray[i] > firstSpeed) {
				firstSpeed = speedArray[i];
			}
		}
		System.out.println(firstSpeed);
		return firstSpeed;
	}

	public double averageSpeed() {
		return totalDistance() / totalTime();
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		double speedMph = speed * MS;
		double timer = secs / 3600.0;
		double met = 0.0;
		if (speedMph < 10) {
			met = 6.0;
		} else if (speedMph <= 14) {
			met = 8.0;
		} else if (speedMph <= 16) {
			met = 10.0;
		} else if (speedMph <= 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}

		double kcal = met * weight * timer;
		System.out.println(kcal);
		return kcal;
	}

	public double totalKcal(double weight) {
		double[] speeds = speeds();
		double totalKalorier = 0.0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			int tidenMellom= gpspoints[i+1].getTime() - gpspoints[i].getTime();
			double kalorier = kcal(weight, tidenMellom, speeds[i]);
			totalKalorier += kalorier;
		}
		return totalKalorier;
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {
		double weight = 80.0;

		int totalTime = totalTime();
		int hours = totalTime / 3600;
		int minutes = (totalTime % 3600) / 60;
		int seconds = totalTime % 60;

		double totalDistance = totalDistance() / 1000.0;

		double totalElevation = totalElevation();

		double maxSpeed = maxSpeed() * 3.6;

		double averageSpeed = averageSpeed() * 3.6;

		double totalKcal = totalKcal(weight);

		System.out.println("==============================================");
		System.out.printf("Total Time     :   %02d:%02d:%02d\n", hours, minutes, seconds);
		System.out.printf("Total distance :   %10.2f km\n", totalDistance);
		System.out.printf("Total elevation:   %10.2f m\n", totalElevation);
		System.out.printf("Max speed      :   %10.2f km/t\n", maxSpeed);
		System.out.printf("Average speed  :   %10.2f km/t\n", averageSpeed);
		System.out.printf("Energy         :   %10.2f kcal\n", totalKcal);
		System.out.println("==============================================");
	}

}
