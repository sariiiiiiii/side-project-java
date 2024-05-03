package side.project.furni.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.common.error.custom.DuplicateMemberException;
import side.project.furni.common.error.custom.LoginFailedApiException;
import side.project.furni.common.error.custom.MemberNotFoundException;
import side.project.furni.common.error.custom.ProductNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            LoginFailedApiException.class, DuplicateMemberException.class,
            MemberNotFoundException.class, ProductNotFoundException.class
    })
    protected ApiResponse<?> handleGlobalApiException(GlobalApiException e) {
        log.error("global api exception = {}", e.toString());
        return ApiResponse.ERROR(e.getStatus(), e.getMessage());
    }

}
