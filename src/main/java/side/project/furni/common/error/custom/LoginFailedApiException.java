package side.project.furni.common.error.custom;

import org.springframework.http.HttpStatus;
import side.project.furni.common.error.GlobalApiException;

public class LoginFailedApiException extends GlobalApiException {

    private static final String MESSAGE = "계정을 찾을 수 없습니다";

    public LoginFailedApiException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}
