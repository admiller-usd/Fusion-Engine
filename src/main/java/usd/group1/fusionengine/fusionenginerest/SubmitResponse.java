package usd.group1.fusionengine.fusionenginerest;

import java.util.UUID;

/**
 * This class will be used to represent the json response
 * when submitting coordinates to be persisted
 */
public class SubmitResponse {

    private final UUID uuid;
    private final String message;

    public SubmitResponse(UUID uuid, String message) {
        this.uuid = uuid;
        this.message = message;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMessage() {
        return message;
    }
}
