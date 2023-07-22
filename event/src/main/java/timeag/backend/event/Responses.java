package timeag.backend.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Responses {
    public static ResponseEntity<Object> NotFoundEntity(String message) {
        // BFF layer only read the status code, "not found" is just a reminder for user.
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
