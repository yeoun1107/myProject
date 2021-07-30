
package yunProject.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import yunProject.domain.dto.MemberLoginDto;
import yunProject.domain.dto.MemberRequestDto;
import yunProject.domain.entity.Member;
import yunProject.domain.entity.MemberRepository;
import yunProject.domain.entity.MemberRole;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public void join(MemberRequestDto dto, Model model) {
		// ip 정보를 dto에 추가로 저장
		String ip=request.getRemoteAddr();
		dto.setIp(ip);
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		//Role 적용
		Member entity=dto.toEntity();
		entity.addRole(MemberRole.USER);
		
		memberRepository.save(entity);
		//repository 객체는 save 할 때 entity객체만 허용
		// toEntity()메서드를 통해서 dto 데이터를 -> Member로 세팅
		// save 하면 Member가 리턴.
		//memberRepository.save(dto.toEntity());
		model.addAttribute("welcome", dto.getName()+"님! 회원가입을 축하합니다.");

	}



	@Override
	public String login(MemberLoginDto dto, Model model) {
		// 로그인 체크 방법 (db에는 이미 암호화로 인해 비밀번호가 1234가 아니다)
		
			// 1. userId 존재유무 확인
			// repository.findById(null)  : 여기서 뜻하는 Id는 pk칼럼(no)
			 Optional<Member> opt= memberRepository.findByEmail(dto.getEmail());
			 
			 if(opt.isPresent()) {
			// 2. userId가 존재하면 비밀번호 확인
				 Member entity=opt.get();
				 System.out.println(entity);
			// matches() 메서드를 통해서 비밀번호 체크
				 boolean result=passwordEncoder.matches(dto.getPassword(), entity.getPassword());
				 if(result) {
					 //System.out.println("비밀번호 일치");
					 //로그인처리하세요
					 return "redirect:/";
				 }
			 }
			 model.addAttribute("loginErr","아이디가 존재하지 않거나 비밀번호가 불일치!");
			 
			 return "/log/login";
		
	}



	@Override
	public boolean emailCheck(String email) {
		Optional<Member> result= memberRepository.findByEmail(email);
		return result.isEmpty();
		
	}

}
