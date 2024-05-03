package side.project.furni.api.service.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.project.furni.api.service.cart.request.CreateCartServiceRequest;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.domain.cart.repository.CartItemRepository;
import side.project.furni.domain.cart.repository.CartRepository;
import side.project.furni.domain.member.repository.MemberRepository;
import side.project.furni.domain.product.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ApiResponse<?> create(final CreateCartServiceRequest request) {


        return ApiResponse.OK();
    }

}
