package yunProject.service;

import org.springframework.ui.Model;

import yunProject.domain.dto.NoticeUpdateDto;
import yunProject.domain.dto.NoticeWriteDto;

public interface NoticeService {

	void getNoticeList(Model model);

	void showDetail(long no, Model model);

	void save(NoticeWriteDto dto);

	void delete(long no);

	void update(long no, NoticeUpdateDto dto);

}
