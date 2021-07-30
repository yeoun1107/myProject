package yunProject.domain.entity;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

	List<Reply> findAllByBoardNoOrderByNoDesc(long bno);
	
	List<Reply> findAllByBoardNo(long bno, Sort sort);

}
