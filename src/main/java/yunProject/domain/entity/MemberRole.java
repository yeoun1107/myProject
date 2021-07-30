package yunProject.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
  // name    ("role"   , "title")          ordinal
	GUSET("ROLE_GUEST", "비회원인증"),     //   0
	USER("ROLE_USER", "회원"),           //   1
	ADMIN("ROLE_ADMIN", "관리자");       //    2
	
	private final String role;
	private final String title;
}
