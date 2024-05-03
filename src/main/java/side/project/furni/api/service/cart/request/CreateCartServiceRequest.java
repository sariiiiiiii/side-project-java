package side.project.furni.api.service.cart.request;

import side.project.furni.api.controller.cart.request.CreateCartRequest;

public record CreateCartServiceRequest(
        Long memberId,
        Long productId,
        int quantity
) {

    public CreateCartServiceRequest(CreateCartRequest request) {
        this(
                request.memberId(),
                request.productId(),
                request.quantity()
        );
    }
}
