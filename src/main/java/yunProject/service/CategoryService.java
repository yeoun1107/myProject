package yunProject.service;

import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.CategoryDto;

public interface CategoryService {

	void save(CategoryDto dto);

	void findAll(ModelAndView mv);

	void findAll(int first, ModelAndView mv);

	void findAll(int first, int second, ModelAndView mv);


}
