package yunProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.ReplyDto;
import yunProject.domain.dto.ReplyInsertDto;
import yunProject.domain.entity.NoticeEntity;
import yunProject.domain.entity.NoticeEntityRepository;
import yunProject.domain.entity.Reply;
import yunProject.domain.entity.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	NoticeEntityRepository noticeEntityRepository;
	
	@Autowired
	ReplyRepository repository;
	
	@Override
	public ModelAndView getReplies(long bno) {
		ModelAndView mv=new ModelAndView("/cscenter/notice/reply");
		
		Sort sort=Sort.by("no").descending();
		List<ReplyDto> result=repository.findAllByBoardNo(bno,sort)
				.stream()
				.map(ReplyDto::new)
				.collect(Collectors.toList());
		
		mv.addObject("replies", result);
		
		return mv;
	}

	@Transactional
	@Override
	public void save(long bno, ReplyInsertDto dto) {
		Reply rEntity=Reply.builder()
				.reply(dto.getReply())
				.writer(dto.getWriter())
				.board(NoticeEntity.builder().no(bno).build())
				.build();
		
		repository.save(rEntity);
	}

}
