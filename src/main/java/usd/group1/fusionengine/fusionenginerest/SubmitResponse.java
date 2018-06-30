package usd.group1.fusionengine.fusionenginerest;

import java.util.UUID;

/**
 * This class will be used to represent the json response
 * when submitting coordinates to be persisted
 */
public class SubmitResponse {

    private final String uuid;
    private final String message;

    public SubmitResponse(String uuid, String message) {
        this.uuid = uuid;
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public String getMessage() {
        return message;
    }
}
