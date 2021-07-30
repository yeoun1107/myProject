package yunProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.QnaDto;
import yunProject.domain.dto.QnaUpdateDto;
import yunProject.service.QnaService;

@Controller
public class AdminController {
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("/admin/list")
	public String adminList() {
		return "/admin/list";
	}
	
	@GetMapping("/admin/qna/write")
	public String qnaWrite() {
		return "/admin/qna/write";
	}
	
	@PostMapping("/admin/qna/write")
	public String qnaWrite(QnaDto dto) {
		qnaService.write(dto);
		return "redirect:/admin/qna/list";
	}
	
	@GetMapping("/admin/qna/list")
	public String qnaList() {
		return "/admin/qna/list";
	}

	@ResponseBody
	@GetMapping("/admin/qna/{division}/{pageNo}")
	public ModelAndView qnaList(@PathVariable int division, @PathVariable int pageNo, ModelAndView mv) {   // Model 객체 : html페이지에서 데이터를 읽어들일 수 있도록 해주는 역할
		System.out.println("division :"+division);
		System.out.println("pageNo :"+pageNo);
		//qnaService.getQnaList(division, pageNo, model);
		qnaService.getQnaList(division, pageNo, mv);
		mv.setViewName("/admin/qna/listdata");
		return mv;
	}
	
	@ResponseBody
	@DeleteMapping("/admin/qna/{no}")
	public void qnaDelete(@PathVariable long no) {
		//System.out.println("delete-no : "+no);
		qnaService.delete(no);
	}
	
	@ResponseBody
	@PutMapping("/admin/qna/{no}")
	public void qnaUpdate(@PathVariable long no, QnaUpdateDto dto) {
		System.out.println("edit-no : "+no);
		//System.out.println("edit-question : "+dto.getQuestion());
		//System.out.println("edit-answer : "+dto.getAnswer());
		qnaService.update(no,dto);
	}

	
}
