package yunProject.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import yunProject.domain.dto.ProductDto;
import yunProject.domain.dto.ProductUpdateDto;
import yunProject.domain.dto.ProductWriteDto;
import yunProject.domain.entity.ProductEntityRepository;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductEntityRepository productEntityRepository;
	
	@Override
	public void insert(MultipartFile file, ProductWriteDto dto) {
		// 1. file정보 확보하기
		long fileSize=file.getSize();
		if(fileSize>2*1024*1024) {
			log.error("파일 용량 초과");
			return;
		}
		String fileName=file.getOriginalFilename();
		String fileUrl="/images/product/"; // 가져오는게 아니라 우리가 정하는 것
		//static/images/product/
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl);
		ClassPathResource tempCpr=new ClassPathResource("static"+fileUrl+"temp/");
		try {
			File location=cpr.getFile();
			//해당폴더에 파일이 존재..(잘못 선택한 파일)
			//file.transferTo(new File(location, fileName));  //파일 업로드하는 문장
			File tempFile=new File(tempCpr.getFile(), fileName);
			if(tempFile.exists()) {
				tempFile.renameTo(new File(location, fileName));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 2. DB에 저장
		
		// 		1) Dto에 파일 정보 셋팅
		dto.setFileName(fileName);
		dto.setFileUrl(fileUrl);
		dto.setFileSize(fileSize);
		
		// 		2) entity로 변환 후 저장
		productEntityRepository.save(dto.toEntity());
	}

	@Override
	public String uploadTempFile(MultipartFile file) {
		// 1. file정보 확보하기
		long fileSize=file.getSize();
		if(fileSize>2*1024*1024) {
			log.error("파일 용량 초과");
			return null;
		}
		String fileName=file.getOriginalFilename();
		//String hardFileUrl= "E:/spring/workspace/yunProject/src/main/resources/static/images/product/temp";
		String fileUrl="/images/product/temp/"; // 가져오는게 아니라 우리가 정하는 것
		//static/images/product/
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl);
		try {
			//File location=new File(hardFileUrl);
			File location=cpr.getFile();
			for(File element:location.listFiles()) {
				element.delete();
			};
			
			file.transferTo(new File(location, fileName));  //파일 업로드하는 문장
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileUrl+fileName;  //    "/images/product/temp/"+"파일이름"
		
	}

	@Transactional
	@Override
	public void productList(ModelAndView mv) {
		mv.addObject("list", productEntityRepository.findAll().stream()
				.map(ProductDto::new)
				.collect(Collectors.toList())); 
		
	}
	
	@Transactional
	@Override
	public void adminProductList(ModelAndView mv) {
		mv.addObject("list",productEntityRepository.findAll().stream()
				.map(ProductDto::new)
				.collect(Collectors.toList()));
		
	}

	@Override
	public void recommendList(boolean recommend, Model model) {
		List<ProductDto> result=productEntityRepository.findAllByRecommend(recommend=true).stream()
				.map(ProductDto::new)
				.collect(Collectors.toList());
		model.addAttribute("list", result);
		
	}

	@Override
	public void delete(long no) {
		productEntityRepository.deleteById(no);
	}

	@Transactional
	@Override
	public void update(long no, ProductUpdateDto dto) {
		productEntityRepository.findById(no)
								.map(entity->entity.update(dto))
								.get();
		
	}

	@Override
	public void showDetail(long no, Model model) {
		
		ProductDto result=productEntityRepository.findById(no)
				.map(ProductDto::new)
				.orElseThrow();
		
		model.addAttribute("detail",result);
		
	}

	@Transactional
	@Override
	public void getlist(int code, ModelAndView mv) {
		// >(크다) : 메서드쿼리 키워드 GreaterThan
		// select * from my_category where code>100000 and code<110000;
		// select * from my_category where code=100000
		int step=0;
		if(code%10000==0) { 			//첫번째 카테고리 값
			step=10000;
		}else if(code%100==0) { 		//두번째 카테고리 값
			step=100;
		}
		else{ 							//(세번째)마지막 카테고리 값
			mv.addObject("list", productEntityRepository.findByCategoryCode(code).stream()
					.map(ProductDto::new)
					.collect(Collectors.toList()));
			return;
		}
		mv.addObject("list", productEntityRepository.findByCategoryCodeGreaterThanAndCategoryCodeLessThan(code,code+step).stream()
				.map(ProductDto::new)
				.collect(Collectors.toList()));
		
	}

	
	
	/*   관리자페이지 상품목록 뿌려주기 ajax 대신
	@Override
	public void getList(ModelAndView mv) {
		mv.addObject("list", ProductEntityRepository.findAll().stream()
					.map(ProductDto::new)
					.collect(Collectors.toList()));
	}
	*/

	
}
