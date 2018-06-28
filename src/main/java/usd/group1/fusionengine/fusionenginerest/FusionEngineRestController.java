package usd.group1.fusionengine.fusionenginerest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Restful Controller. Handles external http responses.
 */
@RestController
public class FusionEngineRestController {

    /* The constant logger */
    private static final Logger logger = LogManager.getLogger(FusionEngineRestController.class);
    private static final String submitEndpoint = "/submit";

    @RequestMapping(submitEndpoint)
    String submitCoordinates (
            @RequestParam(value="latitude", defaultValue="0") String latitude,
            @RequestParam(value="longitude", defaultValue = "0") String longitude) {
        logger.info("Received request to submit coordinates: {}, {}", latitude, longitude);
        // logic class stores coordinates
        return "Received request to submit coordinates: " + latitude + ", " + longitude;
    }
}
