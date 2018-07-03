/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import usd.group1.fusionengine.exceptions.BadFormattedRequestException;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The class that handles the formatting of coordinates
 */
public class FusionEngineLogic {

	/* The constant logger */
	private static final Logger logger = LogManager.getLogger(FusionEngineLogic.class);

	public static double lat = 0;
	public static double lng = 0;
	public static DecimalFormat nf =  (DecimalFormat) DecimalFormat.getInstance();

    /**
     * Coordinates can be submitted in three formats: decimal, minutes, or minutes-seconds
     * @param Latitude
     * @param Longitude
     */
	public static void convert(String Latitude, String Longitude) throws BadFormattedRequestException {

	    Pattern pattern = Pattern.compile("[A-z]");
        Matcher checkLat = pattern.matcher(Latitude);
        Matcher checkLon = pattern.matcher(Longitude);

	    // Check if Decimal Format. If so, we can just store them and be done
        // Decimal format should not contain letters, apostrophes, or quotes
        if ((Latitude.indexOf('\'') < 0)&&(Longitude.indexOf('\'') < 0)&&
                (!checkLat.find())&&(!checkLon.find())) {
            logger.info("Coordinates {} and {} are in decimal format", Latitude, Longitude);
            lat = Double.valueOf(Latitude.trim());
            lng = Double.valueOf(Longitude.trim());
        }
        // Check if Minutes-Seconds Format
        // Minutes-Seconds should contain both apostrophes and quotes
        else if (((Latitude.indexOf('\'') >= 0)&&(Longitude.indexOf('\'') >= 0))&&
                ((Latitude.indexOf('\"') >= 0)&&(Longitude.indexOf('\"') >= 0))) {
            logger.info("Coordinates {} and {} are in minutes-seconds format", Latitude, Longitude);
            convertMinutesSeconds(Latitude, Longitude);
        }
        // Check if Minutes format (no quotes)
        // Minutes format should only contain apostrophes
        else if ((Latitude.indexOf('\'') >= 0)&&(Longitude.indexOf('\'') >= 0)) {
            logger.info("Coordinates {} and {} are in minutes format", Latitude, Longitude);
            convertMinutes(Latitude, Longitude);
        }
        else {
            logger.info("Bad request to format {} and {}", Latitude, Longitude);
            throw new BadFormattedRequestException("The request to store {} and {} did not match a " +
                    "standard coordinate format.");
        }
    }

    /**
     * Private method for converting to Minutes Seconds format
     * @param Latitude
     * @param Longitude
     */
	private static void convertMinutesSeconds(String Latitude, String Longitude) {

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

    /**
     * Private method for converting minutes format
     * @param Latitude
     * @param Longitude
     */
    	private static void convertMinutes(String Latitude, String Longitude) {
    	    //TODO: Implement logic here
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

