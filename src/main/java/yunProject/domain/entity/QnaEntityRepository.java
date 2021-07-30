package yunProject.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface QnaEntityRepository extends JpaRepository<QnaEntity, Long> {
	//select * from qna_entity where division="shop" order by no desc
	List<QnaEntity> findAllByDivisionOrderByNoDesc(String division);

	Page<QnaEntity> findAllByDivision(String name, Pageable pageable);

	//select * from qna_entity where division="shop"
	List<QnaEntity> findAllByDivision(String name);

}
