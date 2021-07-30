package yunProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import yunProject.domain.dto.MemberLoginDto;
import yunProject.domain.entity.MemberRepository;

@Service
public class MemberDetailService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;

	/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> result=memberRepository.findByEmailAndIsSocial(username, false);            //DB에서 데이터를 가져오는데 데이터타입이 UserDetails여야 하는데 아니다. 그래서 UserDetails 타입으로 바꾸어줘야 한다.
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않는 이메일 또는 아이디 입니다.");
		}
		Member entity=result.get();
		MemberLoginDto memberLoginDto=new MemberLoginDto(entity);
		return memberLoginDto;
	}
	*/


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		
		
		  return memberRepository.findByEmail(email).map(MemberLoginDto::new).orElseThrow();
		 
	
	}

}

