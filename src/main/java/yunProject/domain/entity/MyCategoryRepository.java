package yunProject.domain.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import yunProject.domain.dto.CategoryData;


public interface MyCategoryRepository extends JpaRepository<MyCategory, Integer> {

	@Query("select max(third) as maxfirst FROM MyCategory mc")
	Object getMaxValueFirst();

	
	
	// distinct : 중복 제거
	// MyCategory라고 쓰는 이유는 DB를 건드리는게 아니라 entity를 건드리기 때문
	@Query("select distinct first,fname from MyCategory mc") 
	List<Object[]> getFirstList();
	
	@Query("select distinct second,sname from MyCategory mc where first=:firstvalue") 
	List<Object[]> getSecondList(@Param("firstvalue") int first);


	@Query("select distinct third,tname from MyCategory mc where first=:firstvalue and second=:secondvalue") 
	List<Object[]> getThirdList(@Param("firstvalue") int first, @Param("secondvalue") int second);


	
}
