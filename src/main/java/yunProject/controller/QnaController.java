package yunProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import yunProject.service.QnaService;

@Slf4j
@Controller
public class QnaController {

	@Autowired
	QnaService qnaService;
	
	//@ResponseBody
	@GetMapping("/cscenter/qna/{division}")
	public String list(@PathVariable int division, int page, Model model) {
		log.debug("division : "+division);
		qnaService.list(division, page, model);
		return "/cscenter/qna/listdata";
	}
	
	@GetMapping("/cscenter/qna/list")
	public String faqList() {
		return "/cscenter/qna/list";
	}
	
	/*
	 * @GetMapping("/cscenter/notice/notice") public String notice() { return
	 * "/cscenter/notice/notice"; }
	 */
	
	@GetMapping("/cscenter/oneonone/oneonone")
	public String oneonone() {
		return "/cscenter/oneonone/oneonone";
	}
	
	@GetMapping("/cscenter/delivery/delivery")
	public String delivery() {
		return "/cscenter/delivery/delivery";
	}
	
	@GetMapping("/cscenter/as/as")
	public String as() {
		return "/cscenter/as/as";
	}
}
