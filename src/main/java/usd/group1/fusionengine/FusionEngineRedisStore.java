/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import usd.group1.fusionengine.exceptions.NoUUIDFoundException;
import usd.group1.fusionengine.exceptions.RedisProcessingException;
import usd.group1.fusionengine.redis.entities.CoordinatePair;
import usd.group1.fusionengine.responses.json.QueryResponse;

/**
 * Class that persists the coordinates in Redis
 * Coordinates can be queried by UUID
 */
public class FusionEngineRedisStore {

    /* The constant logger */
    private static final Logger logger = LogManager.getLogger(FusionEngineRedisStore.class);

    /* The Repository Interface */
    FusionEngineConfig.CoordinateRepository coordinateRepository;

    /**
     * Method that stores a coordinate pair in redis. Requires a uuid,
     * a latitude coordinate and a longitude coordinate
     * @param uuid
     * @param latitude
     * @param longitude
     * @throws RedisProcessingException
     */
    public void storeCoordinates(String uuid, String latitude, String longitude)
            throws RedisProcessingException {

        // create new coordinate pair
        CoordinatePair coordinatePair = new CoordinatePair(uuid, latitude, longitude);

        // Persist the coordinatePair Object
        try {
            logger.info("Attempting to persist coordinate data in Redis with id: {}", uuid);
            coordinateRepository.save(coordinatePair);

        } catch (Exception e) {
            throw new RedisProcessingException("Could not store data: " + e);
        }
    }

    /**
     * Method that queries redis for a coordinate pair. Requires a uuid.
     * @param uuid
     * @return QueryResponse
     * @throws RedisProcessingException
     * @throws NoUUIDFoundException
     */
    public QueryResponse retrieveCoordinates(String uuid)
            throws RedisProcessingException, NoUUIDFoundException {

        // The coordinate pair object
        CoordinatePair retrievedPair;

        // Try to retrieve the data
        try {
            logger.info("Attempting to locate coordinate data in Redis with id: {}", uuid);
            if (!coordinateRepository.findById(uuid).isPresent()) {
                throw new NoUUIDFoundException("No object was found with UUID: " + uuid);
            }
            retrievedPair = coordinateRepository.findById(uuid).get();
            return new QueryResponse(uuid, retrievedPair.getLatitude(),
                    retrievedPair.getLongitude());
        } catch (NoUUIDFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RedisProcessingException("Could not retrieve data: " + e);
        }
    }
}
