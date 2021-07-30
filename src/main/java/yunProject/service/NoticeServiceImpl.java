package yunProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import yunProject.domain.dto.NoticeDetailDto;
import yunProject.domain.dto.NoticeListDto;
import yunProject.domain.dto.NoticeUpdateDto;
import yunProject.domain.dto.NoticeWriteDto;
import yunProject.domain.entity.NoticeEntityRepository;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeEntityRepository repository;
	
	@Transactional
	@Override
	public void getNoticeList(Model model) {
		List<NoticeListDto> result=repository.findAll().stream()
			.map(NoticeListDto::new)
			.collect(Collectors.toList());
		model.addAttribute("list",result);
		
	}

	@Override
	public void showDetail(long no, Model model) {

		NoticeDetailDto result=repository.findById(no)
				.map(NoticeDetailDto::new)
				.orElseThrow();
		
		model.addAttribute("detail",result);
	}

	@Override
	public void save(NoticeWriteDto dto) {
		repository.save(dto.toEntity());
		
	}

	@Override
	public void delete(long no) {
		repository.deleteById(no);
	}

	@Transactional
	@Override
	public void update(long no, NoticeUpdateDto dto) {
		repository.findById(no)
				.map(entity->entity.update(dto))
				.get();
	}

}
