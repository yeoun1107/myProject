
package yunProject.service;

import org.springframework.ui.Model;

import yunProject.domain.dto.MemberLoginDto;
import yunProject.domain.dto.MemberRequestDto;

public interface MemberService {

	void join(MemberRequestDto dto, Model model);

	String login(MemberLoginDto dto, Model model);

	boolean emailCheck(String email);

}

