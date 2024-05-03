package side.project.furni.api.controller.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import side.project.furni.api.controller.cart.request.CreateCartRequest;
import side.project.furni.api.service.cart.CartService;
import side.project.furni.api.service.cart.request.CreateCartServiceRequest;
import side.project.furni.common.dto.ApiResponse;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/create")
    public ApiResponse<?> create(@RequestBody final CreateCartRequest request) {
        return cartService.create(new CreateCartServiceRequest(request));
    }

}
