package side.project.furni.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import side.project.furni.common.error.ErrorResponse;

import java.io.Serializable;

@Getter
public class ApiResponse<T> implements Serializable {

    private final boolean result;
    private T Contents;
    private ErrorResponse Error;

    private ApiResponse(T contents) {
        this.result = true;
        this.Contents = contents;
    }

    public ApiResponse() {
        this.result = true;
    }

    public ApiResponse(ErrorResponse error) {
        this.result = false;
        Contents = null;
        Error = error;
    }

    public static <T> ApiResponse<T> from(T contents) {
        return new ApiResponse<>(contents);
    }

    public static ApiResponse<?> OK() {
        return new ApiResponse<>();
    }

    public static ApiResponse<?> ERROR(HttpStatus status, String message) {
        return new ApiResponse<>(new ErrorResponse(status, message));
    }

}
