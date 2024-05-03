package side.project.furni.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.furni.domain.product.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

}
