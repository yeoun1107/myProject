
package yunProject.domain.dto;

import javax.persistence.Column;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import yunProject.domain.entity.Member;

@Data
public class MemberRequestDto {

	private String email;          // form 태그에서 가져온다.    
	private String password;       // form 태그에서 가져온다.
	private String name;           // form 태그에서 가져온다.
	private String birth;           // form 태그에서 가져온다.
	private String phone;           // form 태그에서 가져온다.
	private String ip;             // ip : request 객체에서 가져온다.
	
	
	// data를 entity 객체로 세팅하기 위한 메서드
	public Member toEntity() {
		return Member.builder()
				.email(email)
				.password(password)
				.name(name)
				.birth(birth)
				.phone(phone)
				.ip(ip)
				.build();
	}
}

