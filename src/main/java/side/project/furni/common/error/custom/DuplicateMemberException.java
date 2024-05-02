package side.project.furni.common.error.custom;

import org.springframework.http.HttpStatus;
import side.project.furni.common.error.GlobalApiException;

public class DuplicateMemberException extends GlobalApiException {

    private static final String MESSAGE = "이미 존재하는 회원입니다";

    public DuplicateMemberException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
