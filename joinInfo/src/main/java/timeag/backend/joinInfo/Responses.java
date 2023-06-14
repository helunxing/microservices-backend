package timeag.backend.joinInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Responses {
    public static ResponseEntity<Object> NotFoundEntity() {
        // BFF layer only read the status code, "not found" is just a reminder for user.
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
