package yunProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user/communitylist")
	public String communityList() {
		return "/user/communitylist";
	}
	
}
