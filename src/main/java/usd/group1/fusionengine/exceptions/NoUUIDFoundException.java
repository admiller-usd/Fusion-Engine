/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class throws a 404 Not Found error if no matching
 * UUID is found when querying the DataStore
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No UUID found")
public class NoUUIDFoundException extends Exception {

    public NoUUIDFoundException(String message) {
        super(message);
    }
}
