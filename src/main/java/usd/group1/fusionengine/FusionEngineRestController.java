/*                                                *
* ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
*                  Programmers:                   *
*                  Austin Miller                  *
*                 Kathrine Lavieri                *
*                                                 */
package usd.group1.fusionengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usd.group1.fusionengine.exceptions.BadFormattedRequestException;
import usd.group1.fusionengine.exceptions.NoUUIDFoundException;
import usd.group1.fusionengine.responses.json.QueryResponseSimple;
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

    /**
     * A REST Controller which handles requests to convert and store coordinate data
     * @param latitude
     * @param longitude
     * @return
     * @throws BadFormattedRequestException
     */
    @RequestMapping(method=RequestMethod.POST, path=submitEndpoint)
    SubmitResponse submitCoordinates (
            @RequestParam(value="latitude") String latitude,
            @RequestParam(value="longitude") String longitude) throws BadFormattedRequestException {
        logger.info("Received request to submit coordinates: {}, {}",
                latitude, longitude);

        // Generate a unique UUID at time of evocation
        String uuid = UUID.randomUUID().toString();

        // Convert the inputs and get results
        FusionEngineLogic.convert(latitude, longitude);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        // Persist the results
        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);

        // The response body
        return new SubmitResponse(uuid, "Stored object with coordinates: " +
                latResult + ", " + lonResult);
    }

    /**
     * A REST Controller which handles requests to query for coordinate data
     * @param uuid
     * @return
     * @throws NoUUIDFoundException
     */
    @RequestMapping(method=RequestMethod.GET, path=queryEndpoint)
    public QueryResponseSimple QueryResult(
            @RequestParam(value="uuid") String uuid) throws NoUUIDFoundException {

        // query for coordinates
        return FusionEngineDataStore.retrieveCoordinates(uuid);
    }
}
