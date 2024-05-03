package side.project.furni.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.furni.domain.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
