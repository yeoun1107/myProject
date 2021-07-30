package yunProject.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.ProductUpdateDto;
import yunProject.domain.dto.ProductWriteDto;

public interface ProductService {

	void insert(MultipartFile file, ProductWriteDto dto);

	String uploadTempFile(MultipartFile file);

	void recommendList(boolean recommend, Model model);
	
	void productList(ModelAndView mv);
	
	void adminProductList(ModelAndView mv);
	
	void delete(long no);

	void update(long no, ProductUpdateDto dto);

	void showDetail(long no, Model model);

	void getlist(int code, ModelAndView mv);


	 //void getList(ModelAndView mv);  관리자페이지 상품목록 뿌려주기 ajax 대신


}
