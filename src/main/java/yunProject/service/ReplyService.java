package yunProject.service;

import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.ReplyInsertDto;

public interface ReplyService {

	ModelAndView getReplies(long bno);

	void save(long bno, ReplyInsertDto dto);

}
