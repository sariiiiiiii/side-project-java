package side.project.furni.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.furni.domain.cart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
