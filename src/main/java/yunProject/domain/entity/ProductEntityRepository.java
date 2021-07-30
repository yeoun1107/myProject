package yunProject.domain.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import yunProject.domain.dto.ProductDto;


public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findAllByRecommend(boolean recommend);

	List<ProductEntity> findByCategoryCodeGreaterThanAndCategoryCodeLessThan(int code, int i);

	List<ProductEntity> findByCategoryCode(int code);

}
