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
    private static final String submitEndpoint = "/submit";

    @RequestMapping(method=RequestMethod.POST, path=submitEndpoint)
    SubmitResponse submitCoordinates (
            @RequestParam(value="latitude") Double latitude,
            @RequestParam(value="longitude") Double longitude) {
        logger.info("Received request to submit coordinates: {}, {}",
                String.valueOf(latitude), String.valueOf(longitude));

        // The Logic class will store the coordinates and return a UUID
        // TODO: Implement Logic Class here

        // Temporary UUID generated here for testing
        UUID uuid = UUID.randomUUID();
        return new SubmitResponse(uuid, "Received request to submit coordinates: " +
                String.valueOf(latitude) + ", " + String.valueOf(longitude));
    }
}
