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
 * An exception thrown if there is an error processing
 * some redis process
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR,
        reason="Could not process the data in Redis")
public class RedisProcessingException extends Exception {

    public RedisProcessingException(String message) {
        super(message);
    }
}
