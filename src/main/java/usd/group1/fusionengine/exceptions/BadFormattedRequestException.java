package usd.group1.fusionengine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throws a 400 Bad Request for wrongly formatted requests
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Request not formatted properly")
public class BadFormattedRequestException extends Exception {

    public BadFormattedRequestException(String message) {
        super(message);
    }
}
