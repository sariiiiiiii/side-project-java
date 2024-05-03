package side.project.furni.common.error.custom;

import org.springframework.http.HttpStatus;
import side.project.furni.common.error.GlobalApiException;

public class MemberNotFoundException extends GlobalApiException {

    private static final String MESSAGE = "회원을 찾을 수 없습니다";

    public MemberNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
