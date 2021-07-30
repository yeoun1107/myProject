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

import yunProject.domain.dto.NoticeUpdateDto;
import yunProject.domain.dto.NoticeWriteDto;
import yunProject.service.NoticeService;
import yunProject.service.NoticeServiceImpl;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService service;
	
	@GetMapping("/admin/notice")
	public String adminNotice(Model model) {
		service.getNoticeList(model);
		return "/admin/notice/list";
	}
	
	@GetMapping("/cscenter/notice/notice")    //공지사항 목록 보기 컨트롤러
	public String notice(Model model) {
		service.getNoticeList(model);
		return "/cscenter/notice/notice";
	}
	
	@GetMapping("/admin/notice/write")    // 공지사항 등록 페이지 이동 컨트롤러
	public String write() {
		return "/admin/notice/write";
	}
	
	@PostMapping("/admin/notice/write")       //글쓰기 처리 컨트롤러
	public String write(NoticeWriteDto dto) {
		service.save(dto);
		return "redirect:/admin/notice";
	}
	
	@GetMapping("/cscenter/notice/detail/{no}")   //공지사항 상세보기 컨트롤러
	public String detail(@PathVariable long no, Model model) {
		service.showDetail(no, model);
		return "/cscenter/notice/detail";
	}
	
	@GetMapping("/admin/notice/detail/{no}")   //공지사항 상세보기 컨트롤러
	public String detail2(@PathVariable long no, Model model) {
		service.showDetail(no, model);
		return "/admin/notice/detail";
	}
	
	@GetMapping("/admin/notice/list")
	public String list(Model model) {
		service.getNoticeList(model);
		return "/admin/notice/list";
	}
	
	@ResponseBody 
	@DeleteMapping("/admin/notice/{no}")
	public void noticeDelete(@PathVariable long no) {
		service.delete(no);
	}
	
	@ResponseBody
	@PutMapping("/admin/notice/{no}")
	public void noticeUpdate(@PathVariable long no, NoticeUpdateDto dto) {
		service.update(no,dto);
	}
	
}
