package yunProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.ProductUpdateDto;
import yunProject.domain.dto.ProductWriteDto;
import yunProject.service.ProductService;
import yunProject.service.ProductServiceImpl;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/admin/product/write")
	public String write() {
		return "/admin/product/write";
	}
	
	@PostMapping("/admin/product/write")
	public String write(MultipartFile file, ProductWriteDto dto) {
		
		service.insert(file, dto);
		
		return "redirect:/admin/product";
	}
	
	// 관리자페이지 판매상품 목록보기 페이지 이동
	@GetMapping("/admin/product")
	public String adminProduct(ModelAndView mv) {
		service.productList(mv);
		return "/admin/product/list";
	}
	
	// 메인페이지 판매상품 목록보기 페이지 이동
	@GetMapping("/goods")
	public String goodsProduct(ModelAndView mv) {
		service.productList(mv);
		return "/goods/goods_list";
	}
	
	
	/*  관리자페이지 상품목록 뿌려주기 ajax 대신
	@ResponseBody
	@GetMapping("/admin/product/all")
	public ModelAndView getList(ModelAndView mv) {
		service.getList(mv);
		//mv.addObject("list",service.getList());
		mv.setViewName("/admin/product/listdata");
		return mv;
	}
	*/
	
	@ResponseBody // 요청을 해주면 응답을 해줘야 하는데 void면 return 하는 데이터가 없기 때문에 내부적으로만이라도 응답처리를 반영해주기 위해서
	@PostMapping("/admin/product/temp")
	public String temp(MultipartFile file) {
		//System.out.println(file.getOriginalFilename());
		return service.uploadTempFile(file);
	}
	
	
	// 메인 인덱스(추천상품목록)에 데이터를 뿌려주는 컨트롤러
	@GetMapping("/index")                               
	public String productIndex(boolean recommend, Model model) {
		service.recommendList(recommend, model); 
		return "/indexlist"; 
	}
	
	// 관리자페이지 상품목록에 데이터를 뿌려주는 컨트롤러
	@ResponseBody
	@GetMapping("/admin/product/list")          
	public ModelAndView list(ModelAndView mv) {
		service.adminProductList(mv); 
		mv.setViewName("/admin/product/listdata");
		return mv; 
	}
	
	// 카테고리(전체상품목록)에 데이터를 뿌려주는 컨트롤러
	@ResponseBody
	@GetMapping("/goods/goods_list") 					
	public ModelAndView goodsList(ModelAndView mv) {
		service.productList(mv);
		mv.setViewName("/goods/listdata");
		return mv; 
	}
	
	
	// 상품 상세정보 이동 컨트롤러
	@GetMapping("/goods/goods_list/{no}")
	public String detail(@PathVariable long no, Model model) {
		service.showDetail(no,model);
		return "/goods/goods_detail";
	}
	
	
	// 관리자페이지 상품 삭제 컨트롤러
	@ResponseBody
	@DeleteMapping("/admin/product/{no}")
	public void productDelete(@PathVariable long no) {
		service.delete(no);
	}
	
	// 관리자페이지 상품 수정(추천 여부 체크 수정) 컨트롤러
	@ResponseBody
	@PutMapping("/admin/product/{no}")
	public void productUpdate(@PathVariable long no, ProductUpdateDto dto ) {
		/* System.out.println("edit-productNo : " +no); */
		service.update(no, dto);
	}
	
	
	// 코드값을 받아 카테고리별로 상품목록을 출력해주는 컨트롤러
	@ResponseBody
	@GetMapping("/admin/product/search/{code}")
	public ModelAndView getList(@PathVariable int code, ModelAndView mv) {
		service.getlist(code, mv);
		mv.setViewName("/admin/product/listdata");
		return mv;
	}
	
	// 코드값을 받아 카테고리별로 상품목록을 출력해주는 컨트롤러
	@ResponseBody
	@GetMapping("/goods/search/{code}")
	public ModelAndView getGoodsList(@PathVariable int code, ModelAndView mv) {
		service.getlist(code, mv);
		mv.setViewName("/goods/listdata");
		return mv;
	}
	
	@GetMapping("/goods/goods_delievery")
	public String delivery() {
		return "/goods/goods_delievery";
	}
	
}
