package side.project.furni.common.error.custom;

import org.springframework.http.HttpStatus;
import side.project.furni.common.error.GlobalApiException;

public class ProductNotFoundException extends GlobalApiException {

    private static final String MESSAGE = "상품을 찾을 수 없습니다";

    public ProductNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
