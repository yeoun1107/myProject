package yunProject.controller;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import yunProject.domain.dto.MemberLoginDto; 
import yunProject.domain.dto.MemberRequestDto; 
import yunProject.service.MemberService; 
import yunProject.service.MemberServiceImpl;
  
  @Slf4j
  @Controller 
  public class MemberController { // service는 멤버로 만드세요.
  
	  @Autowired //bean에 new serviceImpl()이 등록되어있으니까 치지 말고 @autowired로 가져오자.
	  private MemberService service;
  
	  @ResponseBody         // 리턴된 데이터를 boolean 타입으로 페이지에 전달되게끔 하는 어노테이션.
	  @PostMapping("/member/check")
	  public boolean check(String email) {
		  log.debug(email);
		  return service.emailCheck(email);   //24라인의 String 타입의 email을 파라미터로 받아서 service에서 check하는 로직을 처리하게끔 하자는 것.
	  }
	  
	  @PostMapping("/member/join") 
	  public String join(MemberRequestDto dto, Model model) { 
		  log.debug("회원가입 처리*****");
		  service.join(dto, model); 
		  return "/log/login"; 
	  }
	  
	  /*	
	  @PostMapping("/member/login") 
	  public String login(MemberLoginDto dto, Model model) {
	  
		  return service.login(dto, model); 
	  }
		  // security가 로그인처리를 해주고 있기 때문에 없앴다 
	 */
  }
 