package two.db.connection.postsql.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import two.db.connection.postsql.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
