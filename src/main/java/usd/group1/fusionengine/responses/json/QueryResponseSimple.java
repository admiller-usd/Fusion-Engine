/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine.responses.json;

import java.util.UUID;

/**
 * This class will be used to represent the json response
 * when querying for coordinates
 */
public class QueryResponseSimple {

    private final String uuid;
    private final String latitude;
    private final String longitude;

    public QueryResponseSimple(String uuid, String latitude, String longitude) {
        this.uuid = uuid;
        this.latitude = latitude;
        this.longitude = longitude;
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
