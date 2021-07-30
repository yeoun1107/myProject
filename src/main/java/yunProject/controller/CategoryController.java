package yunProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.CategoryDto;
import yunProject.service.CategoryService;
import yunProject.service.CategoryServiceImpl;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/admin/category")             // 페이지 이동
	public String category() {
		return "/admin/category/write";
	}
	
	@PostMapping("/admin/category")           // 값 전달
	public String category(CategoryDto dto) {
		categoryService.save(dto);
		return "/admin/category/write";
	}
	
	// 1차 카테고리(first) 정보를 읽어와서 페이지로 데이터 전송
	@ResponseBody
	@GetMapping("/admin/category/first")
	public ModelAndView first(ModelAndView mv) {
		categoryService.findAll(mv);
		mv.setViewName("/admin/category/data");
		return mv;
	}
	
	// 2차 카테고리(second) 정보를 읽어와서 페이지로 데이터 전송
	@ResponseBody
	@GetMapping("/admin/category/{first}/second")
	public ModelAndView second(@PathVariable int first, ModelAndView mv) {
		categoryService.findAll(first, mv);
		mv.setViewName("/admin/category/data");
		return mv;
	}
		
	// 3차 카테고리(second) 정보를 읽어와서 페이지로 데이터 전송
	@ResponseBody
	@GetMapping("/admin/category/{first}/{second}/third")
	public ModelAndView second(@PathVariable int first, @PathVariable int second, ModelAndView mv) {
		categoryService.findAll(first,second, mv);
		mv.setViewName("/admin/category/data");
		return mv;
	}	
		
	@ResponseBody
	@GetMapping("admin/product/menu")
	public ModelAndView menu(ModelAndView mv) {
		categoryService.findAll(mv);
		mv.setViewName("/admin/product/menu");
		return mv;
	}
	
	@ResponseBody
	@GetMapping("goods/menu")
	public ModelAndView goodsmenu(ModelAndView mv) {
		categoryService.findAll(mv);
		mv.setViewName("/goods/menu");
		return mv;
	}
	
	@ResponseBody
	@GetMapping("admin/product/menu/{first}")
	public ModelAndView menu(@PathVariable int first, ModelAndView mv) {
		categoryService.findAll(first,mv);
		mv.setViewName("/admin/product/second");
		return mv;
	}
	
	@ResponseBody
	@GetMapping("goods/menu/{first}")
	public ModelAndView goodsmenu(@PathVariable int first, ModelAndView mv) {
		categoryService.findAll(first,mv);
		mv.setViewName("/goods/second");
		return mv;
	}
	
	@ResponseBody
	@GetMapping("admin/product/menu/{first}/{second}")
	public ModelAndView menu(@PathVariable int first, @PathVariable int second, ModelAndView mv) {
		
		categoryService.findAll(first,second,mv);
		mv.setViewName("/admin/product/third");
		return mv;
	}
	
	@ResponseBody
	@GetMapping("goods/menu/{first}/{second}")
	public ModelAndView goodsmenu(@PathVariable int first, @PathVariable int second, ModelAndView mv) {
		
		categoryService.findAll(first,second,mv);
		mv.setViewName("/goods/third");
		return mv;
	}
		
}
