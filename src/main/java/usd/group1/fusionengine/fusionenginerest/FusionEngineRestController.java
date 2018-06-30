package usd.group1.fusionengine.fusionenginerest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * The Restful Controller. Handles external http responses.
 */
@RestController
public class FusionEngineRestController {

    /* The constant logger */
    private static final Logger logger = LogManager.getLogger(FusionEngineRestController.class);

    /* REST Endpoint for submitting coordinates */
    private static final String submitEndpoint = "/submit";

    @RequestMapping(method=RequestMethod.POST, path=submitEndpoint)
    SubmitResponse submitCoordinates (
            @RequestParam(value="latitude") String latitude,
            @RequestParam(value="longitude") String longitude) {
        logger.info("Received request to submit coordinates: {}, {}", latitude, longitude);

        // Generate a unique UUID at time of evocation
        String uuid = UUID.randomUUID().toString();

        // Convert the inputs and get results
        FusionEngineLogic.convert(latitude, longitude);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        // Persist the results
        // TODO: Store the results in storage class

        // The response body
        return new SubmitResponse(uuid, "Stored object with coordinates: " + latResult + ", " + lonResult);
    }

    // TODO: Create a method to query for results

}
