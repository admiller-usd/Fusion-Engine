
package usd.group1.fusionengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;


/**
 * The class that handles the formatting of coordinates
 */
public class FusionEngineLogic {

	/* The constant logger */
	private static final Logger logger = LogManager.getLogger(FusionEngineLogic.class);

	/*
	 * Needs to take in two coordinates and should generate a "unique" ID so
	 * someone can query for the results later on
	 */

	public static double lat = 0;
	public static double lng = 0;
	public static DecimalFormat nf =  (DecimalFormat) DecimalFormat.getInstance();

	public static void convert(String Latitude, String Longitude) {

	        logger.info("Convert: Received latitude {} and longitude {}", Latitude, Longitude);

	        // Convert the Latitude
    		String[] fields = Latitude.trim().split(" ");
    		double degree = Double.parseDouble(fields[0].split("[^0-9]")[0]);
    		double minute = Double.parseDouble(fields[1].split("[^0-9]")[0]) / 60.0;
    		double second = Double.parseDouble(fields[2].split("[^0-9.]")[0]) / 3600.0;
    		char direction = fields[3].charAt(0);
    		if (direction == 'N') {
    			lat = degree + minute + second;
    		} else if (direction == 'S') {
    			lat = -degree - minute - second;
    		}

    		// Convert the Longitude
    		fields = Longitude.trim().split(" ");
    		degree = Double.parseDouble(fields[0].split("[^0-9]")[0]);
    		minute = Double.parseDouble(fields[1].split("[^0-9]")[0]) / 60.0;
    		second = Double.parseDouble(fields[2].split("[^0-9.]")[0]) / 3600.0;
    		direction = fields[3].charAt(0);
    		if (direction == 'E') {
    			lng = degree + minute + second;
    		} else if (direction == 'W' ) {
    			lng = -degree - minute - second;
    		}

    		nf.setMaximumFractionDigits(5);
    		nf.setMinimumFractionDigits(5);
    	    System.out.println("Lat ="+nf.format(lat)+" Long ="+nf.format(lng));
    	}

	public static String getLatitude(){
    		nf.setMaximumFractionDigits(5);
    		nf.setMinimumFractionDigits(5);
    		return nf.format(lat);
    	}

	public static String getLongitude(){
    		nf.setMaximumFractionDigits(5);
    		nf.setMinimumFractionDigits(5);
    		return nf.format(lng);
    	}

}

