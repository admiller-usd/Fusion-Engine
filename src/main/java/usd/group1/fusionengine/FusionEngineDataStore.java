package usd.group1.fusionengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import usd.group1.fusionengine.exceptions.NoUUIDFoundException;
import usd.group1.fusionengine.responses.json.QueryResponseSimple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that persists the coordinates in memory
 * Can be queried by other services by UUID String
 */
public class FusionEngineDataStore {

    /* The constant logger */
    private static final Logger logger = LogManager.getLogger(FusionEngineDataStore.class);

    /* The shared storage map */
    private static final Map<String, List<String>> storageMap = new HashMap<>();

    /**
     * Method that stores Coordinates and maps them to a UUID
     * @param uuid
     * @param latitude
     * @param longitude
     */
    public static void storeCoordinates (String uuid, String latitude, String longitude) {
        logger.info("request to store object with uuid {}, Latitude {}, " +
                "Longitude {}", uuid, latitude, longitude);

        List<String> coordinates = new ArrayList<>();
        coordinates.add(latitude);
        coordinates.add(longitude);

        // Store coordinates in HashMap with UUID as key
        storageMap.put(uuid, coordinates);
        logger.info("Stored object with uuid: {}, coordinates {}, {}",
                uuid, coordinates.get(0), coordinates.get(1));
    }

    /**
     * Method retrieves a set of coordinates by a given UUID
     * @param uuid
     * @return
     * @throws NoUUIDFoundException
     */
    public static QueryResponseSimple retrieveCoordinates (String uuid) throws NoUUIDFoundException {
        logger.info("request to retrieve object with uuid: {}", uuid);

        // Iterate through HashMap and find the object
        for (Map.Entry<String, List<String>> entry : storageMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(uuid)) {
                logger.info("matched object found with uuid {},", uuid);
                return new QueryResponseSimple(uuid, entry.getValue().get(0), entry.getValue().get(1));
            }
        }
        // If there was no match, throw a "No UUID" exception
        throw new NoUUIDFoundException("No object was found with UUID: " + uuid);
    }
}
