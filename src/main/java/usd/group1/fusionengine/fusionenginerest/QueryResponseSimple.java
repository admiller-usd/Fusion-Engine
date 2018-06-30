package usd.group1.fusionengine.fusionenginerest;

import java.util.UUID;

/**
 * This class will be used to represent the json response
 * when querying for coordinates
 */
public class QueryResponseSimple {

    private final String latitude;
    private final String longitude;
    private final String uuid;

    public QueryResponseSimple(String longitude, String latitude, UUID uuid) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.uuid = uuid.toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
