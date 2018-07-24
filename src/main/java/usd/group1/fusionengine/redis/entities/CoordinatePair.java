/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine.redis.entities;

import java.io.Serializable;

public class CoordinatePair implements Serializable {

    private String uuid;
    private String latitude;
    private String longitude;

    public CoordinatePair(String uuid, String latitude, String longitude) {
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
