package yunProject.domain.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yunProject.domain.entity.Member;

@Getter
public class MemberLoginDto extends User{


	private String name;      // form 태그에서 가져온다.
	private String email;
	private boolean isSocial;
	
	public MemberLoginDto(Member entity) {
		super(
				entity.getEmail(),       	// Id (Pk) == email
				entity.getPassword(), 		// 비밀번호
				entity.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet())        //"ROLE_USER"
				// Roles는 Enum 타입인데 여기서 요구하는건 SimpleGrantedAuthority타입이니까 타입을 바꾸어줘야 한다.
		);
		
		name=entity.getName();
		email=entity.getEmail();
		isSocial=entity.isSocial();
	}
}
