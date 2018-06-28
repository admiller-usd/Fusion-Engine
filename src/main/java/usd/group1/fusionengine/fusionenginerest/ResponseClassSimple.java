package usd.group1.fusionengine.fusionenginerest;

/**
 * This class will be used to represent the json response
 */
public class ResponseClassSimple {

    private final String latitude;
    private final String longitude;

    public ResponseClassSimple(String longitude, String latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
