package side.project.furni.common.error;

import org.springframework.http.HttpStatus;

public abstract class GlobalApiException extends RuntimeException {

    public abstract HttpStatus getStatus();

    public GlobalApiException(String message) {
        super(message);
    }

}
