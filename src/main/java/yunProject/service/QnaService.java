package yunProject.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.QnaDto;
import yunProject.domain.dto.QnaUpdateDto;

public interface QnaService {

	void write(QnaDto dto);

	//void list(int division, Model model);

	void list(int division, int page, Model model);

	//void getQnaList(int division, Model model);

	void getQnaList(int division, int pageNo, ModelAndView mv);

	void delete(long no);

	void update(long no, QnaUpdateDto dto);


}
