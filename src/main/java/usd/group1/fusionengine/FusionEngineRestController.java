/*                                                *
* ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
*                  Programmers:                   *
*                  Austin Miller                  *
*                 Kathrine Lavieri                *
*                                                 */
package usd.group1.fusionengine;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usd.group1.fusionengine.exceptions.BadFormattedRequestException;
import usd.group1.fusionengine.exceptions.NoUUIDFoundException;
import usd.group1.fusionengine.exceptions.RedisProcessingException;
import usd.group1.fusionengine.responses.json.QueryResponse;
import usd.group1.fusionengine.responses.json.SubmitResponse;

import java.util.UUID;

/**
 * The Restful Controller. Handles external http responses.
 */
@RestController
public class FusionEngineRestController {

    /* The constant logger */
    private static final Logger logger = LogManager.getLogger(
            FusionEngineRestController.class);

    /* REST Endpoint for submitting coordinates */
    private static final String submitEndpoint = "/submit";

    /* REST Endpoint for querying for coordinates */
    private static final String queryEndpoint = "/query";

    /* REST Endpoint for basic homepage */
    private static final String homePageEndpoint = "/";

    /* Brought in from the config class */
    @Autowired
    private FusionEngineRedisStore redisStore;

    public FusionEngineRestController(FusionEngineRedisStore redisStore) {

        // Validate that the bean was auto-wired in properly
        Validate.notNull(redisStore);

        this.redisStore = redisStore;
    }

    /**
     * A REST Controller which handles requests to convert and store coordinate data
     * @param latitude
     * @param longitude
     * @return
     * @throws BadFormattedRequestException
     */
    @RequestMapping(method=RequestMethod.POST, path=submitEndpoint)
    public ResponseEntity<SubmitResponse> submitCoordinates (
            @RequestParam(value="latitude") String latitude,
            @RequestParam(value="longitude") String longitude)
            throws BadFormattedRequestException, RedisProcessingException {
        logger.info("Received request to submit coordinates: {}, {}",
                latitude, longitude);

        // Generate a unique UUID at time of evocation
        String uuid = UUID.randomUUID().toString();

        // Convert the inputs and get results
        FusionEngineLogic.convert(latitude, longitude);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        // Persist the results in redis
        redisStore.storeCoordinates(uuid, latResult, lonResult);

        // The response body
        SubmitResponse result = new SubmitResponse(uuid, "Stored object with coordinates: " +
                latResult + ", " + lonResult);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * A REST Controller which handles requests to query for coordinate data
     * @param uuid
     * @return
     * @throws NoUUIDFoundException
     */
    @RequestMapping(method=RequestMethod.GET, path=queryEndpoint)
    public ResponseEntity<QueryResponse> QueryResult(
            @RequestParam(value="uuid") String uuid) throws NoUUIDFoundException,
            RedisProcessingException {

        // query for coordinates
        QueryResponse result = redisStore.retrieveCoordinates(uuid);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
