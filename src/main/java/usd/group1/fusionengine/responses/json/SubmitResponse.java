/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine.responses.json;

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
