package yunProject.service;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import yunProject.domain.dto.PageInfo;
import yunProject.domain.dto.QnaDto;
import yunProject.domain.dto.QnaResultDto;
import yunProject.domain.dto.QnaUpdateDto;
import yunProject.domain.entity.Division;
import yunProject.domain.entity.QnaEntity;
import yunProject.domain.entity.QnaEntityRepository;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaEntityRepository qnaEntityRepository;
	
	@Override
	public void write(QnaDto dto) {
		qnaEntityRepository.save(dto.toEntity());
		
	}

	@Override
	public void list(int division, int page, Model model) {
		Division div=Division.values()[division];
		//Division[] arr=Division.values();
		//Division div=arr[0];   을 한 줄로 합 친게 41라인.
		
		//쿼리메서드
		//키워드 OrderBy
		//Desc
		Sort sort=Sort.by(Direction.DESC, "no");
		int size=6;
		Pageable pageable=PageRequest.of(page-1, size, sort);
		//qnaEntityRepository.findAll(pageable);
		Page<QnaEntity> result=qnaEntityRepository.findAllByDivision(div.name(),pageable);
		int pageTot=result.getTotalPages();
		//System.out.println(pageTot);
		if(!result.isEmpty()) {
			PageInfo pageInfo=PageInfo.builder()
					.from(1).to(pageTot).size(size).page(page)
					.build();
			model.addAttribute("pi", pageInfo);
			
			List<QnaResultDto> list=result.getContent()
					.stream()
					.map(QnaResultDto::new)   // entity가 new QnaResultDto(QnaEntity entity)되면서 안에 있는 정보들이 다시 세팅 후 리턴
					.collect(Collectors.toList());   //By 뒤에가 WHERE 절.
			
			model.addAttribute("list", list);
		}
	}

	
	@Override
	public void getQnaList(int division, int pageNo, ModelAndView mv) {
		//jpa 쿼리메서드 호출
		//where절이 division=shop인 데이터만 가지고 오세요 (select * from qna_entity where division="shop") -> 근데 데이터가 여러개니까 return값은 List로 받자.
		//Division div=Division.values()[division];  //enum 정보를 넣을 수 있는 변수를 생성   (values : enum의 목록을 배열로 모아놓음)
		/*
		List<QnaEntity> result=qnaEntityRepository.findAllByDivision(div.name());    // div.name()은 enum파일 안에 있는 영어 이름. ordinal로 숫자값, 한글 이름도 설정되어있기 때문에 쓸 수 있다.
		
		List<QnaResultDto> list=new Vector<>();
		
		for(QnaEntity entity:result) {
			QnaResultDto dto=new QnaResultDto(entity);
			list.add(dto);
		}
		*/
		
		// 바로 위의 68~78 주석 블록과 동일한 실행 내용.(+paging처리)
		Division div=Division.values()[division];  //enum 정보를 넣을 수 있는 변수를 생성   (values : enum의 목록을 배열로 모아놓음)
		
		//int page=0; //페이지번호
		int size=8; //개수
		Pageable pageable=PageRequest.of(pageNo-1, size, Direction.ASC, "question");
		Page<QnaEntity> result=qnaEntityRepository.findAllByDivision(div.name(), pageable);
		if(!result.isEmpty()) {
			int pageTotal=result.getTotalPages();
			mv.addObject("to",pageTotal);   //page갯수를 html페이지에서 확인하도록 저장
			
			List<QnaResultDto> list=         //qnaEntityRepository.findAllByDivision(div.name());은 List<QnaEntity> 타입이니까 데이터 타입 불일치(바꿔줘야 된다)
					result.getContent()
					//qnaEntityRepository.findAllByDivision(div.name())
					//.stream().map(entity->new QnaResultDto(entity) )
					.stream().map(QnaResultDto::new)
					.collect(Collectors.toList());
			
			//데이터 타입을 바꾸고 묶어서 model에다가 넣어주었다.
			mv.addObject("list", list);
		}
	}

	@Override
	public void delete(long no) {
		qnaEntityRepository.deleteById(no);
	}

	@Transactional
	@Override
	public void update(long no, QnaUpdateDto dto) {
		
		qnaEntityRepository.findById(no)
							.map(entity->entity.update(dto))
							.get();
		
		//qnaEntityRepository.findById(no).get().update(dto);
		
		/*
		Optional<QnaEntity> result=qnaEntityRepository.findById(no);
		
		if(result.isPresent()) {
			QnaEntity entity=result.get(); // 현재 DB에 저장되어있는 update 전 원본
			entity.update(dto);
			
			qnaEntityRepository.save(entity);
		}
		*/
		
	}

}
