package yunProject.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, String> {

	Optional<Member> findByEmail(String email);

	
	/* Optional<Member> findByEmailAndIsSocial(String username, boolean b); */
	/* Optional<Member> findByUserId(String userId); */
	// 사용자 정의 메서드 구현(jpa메서드 쿼리 문법에 맞게 메서드를 작성해서 사용가능)
}
