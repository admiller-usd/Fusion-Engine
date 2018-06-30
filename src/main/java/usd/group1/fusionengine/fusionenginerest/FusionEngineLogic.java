
package usd.group1.fusionengine.fusionenginerest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class that handles the logic for storing coordinates and returning
 * different formats
 */
public class FusionEngineLogic {

	/* The constant logger */
	private static final Logger logger = LogManager.getLogger(FusionEngineLogic.class);

	/*
	 * Needs to take in two coordinates and should generate a "unique" ID so
	 * someone can query for the results later on
	 */
	import java.text.DecimalFormat;
	import java.text.NumberFormat;

	import org.omg.Messaging.SyncScopeHelper;

	public static double lat=0;
	public static double lng = 0;
	public static int UUID = 1; //is a UUID increment sufficient? 
	public static DecimalFormat nf =  (DecimalFormat) DecimalFormat.getInstance();

	public static void convert(String Latitude, String Longitude) { // good enough with two parameters?

    		String[] fields = Latitude.trim().split(" ");
    		double degree = Double.parseDouble(fields[0].split("[^0-9]")[0]);
    		double minute = Double.parseDouble(fields[1].split("[^0-9]")[0]) / 60.0;
    		double second = Double.parseDouble(fields[2].split("[^0-9.]")[0]) / 3600.0;
    		char dir = fields[3].charAt(0);
    		
    		if (dir == 'N') {
    			lat = degree + minute + second;
    		} else if (dir == 'S') {
    			lat = -degree - minute - second;
    		} 
    		fields = Longitude.trim().split(" ");
    		degree = Double.parseDouble(fields[0].split("[^0-9]")[0]);
    		minute = Double.parseDouble(fields[1].split("[^0-9]")[0]) / 60.0;
    		second = Double.parseDouble(fields[2].split("[^0-9.]")[0]) / 3600.0;
    		dir = fields[3].charAt(0);
    		if (dir == 'E') {
    			lng = degree + minute + second;
    		} else if (dir == 'W' ) {
    			lng = -degree - minute - second;
    		}
    		nf.setMaximumFractionDigits(5);
    		nf.setMinimumFractionDigits(5);
    		++UUID;
    		// is this where I should log the UUID & how should I do this?
    	System.out.println("Lat ="+nf.format(lat)+" Long ="+nf.format(lng));
    	}

	public static String getLatitude(){ // can we just be returning the static data or should somebody be passing us the UUID and we go get that from the logger?
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

