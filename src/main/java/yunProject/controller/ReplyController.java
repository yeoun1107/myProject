package yunProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.ReplyInsertDto;
import yunProject.service.ReplyService;
import yunProject.service.ReplyServiceImpl;

@RestController
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@GetMapping("/cscenter/notice/detail/{bno}/reply")
	public ModelAndView reply(@PathVariable long bno) {
		return service.getReplies(bno);
	}
	
	@PostMapping("/cscenter/notice/detail/{bno}/reply")
	public void reply(@PathVariable long bno, ReplyInsertDto dto) {
		service.save(bno, dto);
	}
}
