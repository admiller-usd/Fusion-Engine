package usd.group1.fusionengine.fusionenginerest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static void storeCoordinates (String uuid, String latitude, String longitude) {
        logger.info("storeCoordinates: received uuid {}, " +
                "Latitude {}, Longitude {}", uuid, latitude, longitude);

        List<String> coordinates = new ArrayList<>();
        coordinates.add(latitude);
        coordinates.add(longitude);

        storageMap.put(uuid, coordinates);
        logger.info("Stored object with uuid: {}", uuid);
    }

}
