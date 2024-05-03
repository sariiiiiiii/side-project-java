package side.project.furni.api.controller.cart.request;

public record CreateCartRequest(
        Long memberId,
        Long productId,
        int quantity
) {
}
