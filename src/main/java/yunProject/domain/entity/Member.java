package yunProject.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "yun_member")
@Entity
public class Member extends BaseDate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(unique = true, nullable = false)
	private String email;   //join.html의 name속성과 일치
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String birth;
	
	@Column
	private String phone;
	
	@Column
	private String ip;
	
	@Column
	private boolean isSocial;
	
	// Entity가 아닌 단순한 형태의 객체 집합을 정의하고 관리하는 방법입니다.
	// 부모 클래스와 별도로 저장하거나 테이블에서 가져올 수 없습니다.
	// 관계 테이블의 데이터는 항상 부모와 함께 저장되고 삭제됩니다.
	// 관계하는 테이블의 Entity 클래스를 생성하지 않고 사용할 때 씁니다.
	
	@Enumerated(EnumType.STRING)						// 	DB에 적용시 문자열로 저장 (default : 숫자)
	// JPA로 DB에 저장할 때 Enum값을 어떤 형태로 저장할지를 결정하고 기본적으로 int 타입의 숫자가 저장된다
	// 숫자로 저장되면 DB로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수 없기 때문에 문자열(EnumType.STRING)로 저장될 수 있도록 선언한 것이다
	
	@ElementCollection(fetch = FetchType.EAGER)			//  LAZY : 지연로딩(get메서드가 실행될 때 조인쿼리가 날라간다)         FetchType.EAGER : 즉시로딩
	// 관계형DB에는 컬렉션과 같은 형태의 데이터를 컬럼에 저장할 수 없기 때문에 별도의 테이블을 생성하여 컬렉션을 관리해야 한다
	// 이때 컬렉션 객체임을 JPA에게 알려주는 어노테이션이 @ElementCollection 이다
	
	@Builder.Default
	// @Builder를 사용하면 기본값이 null로 들어가는데 이를 방지하기 위해 @Builder.Default 어노테이션을 이용하여 기본값을 설정해준 것이다
	private Set<MemberRole> roles= new HashSet<MemberRole>();
	
	public void addRole(MemberRole role) {
		roles.add(role);
	}
}
