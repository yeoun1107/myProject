package yunProject.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.CategoryData;
import yunProject.domain.dto.CategoryDto;
import yunProject.domain.entity.MyCategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	MyCategoryRepository myCategoryRepository;
	
	@Override
	public void save(CategoryDto dto) {
		//code, 각 번호가 존재하지 않습니다.
		int maxFirst=(Integer)myCategoryRepository.getMaxValueFirst();
		System.out.println("maxFirst : "+maxFirst);
		//myCategoryRepository.save(dto.toEntity());
		
	}

	// 1차 카테고리(first) 데이터 읽어오기(Model에 담는다 => 페이지에서 확인 가능)
	@Override
	public void findAll(ModelAndView mv) {
		List<CategoryData> result=myCategoryRepository.getFirstList()
				.stream()
				.map(CategoryData::new)
				.collect(Collectors.toList());
		
		mv.addObject("list", result);
	}

	@Override
	public void findAll(int first, ModelAndView mv) {

		List<CategoryData> result=myCategoryRepository.getSecondList(first)
				.stream()
				.map(CategoryData::new)
				.collect(Collectors.toList());
		
		mv.addObject("list", result);
		
	}

	@Override
	public void findAll(int first, int second, ModelAndView mv) {
		List<CategoryData> result=myCategoryRepository.getThirdList(first, second)
				.stream()
				.map(CategoryData::new)
				.collect(Collectors.toList());
		
		mv.addObject("list", result);
		
		
	}


}
