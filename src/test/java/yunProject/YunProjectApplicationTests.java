package yunProject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import yunProject.domain.dto.NoticeWriteDto;
import yunProject.domain.entity.Division;
import yunProject.domain.entity.Member;
import yunProject.domain.entity.MemberRepository;
import yunProject.domain.entity.MemberRole;
import yunProject.domain.entity.MyCategory;
import yunProject.domain.entity.MyCategoryRepository;
import yunProject.domain.entity.QnaEntity;
import yunProject.domain.entity.QnaEntityRepository;
import yunProject.service.NoticeService;

@SpringBootTest
class YunProjectApplicationTests {

	@Autowired
	MemberRepository memberRepository;         // 왜 필요해?? => DB에 저장해야되니까
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	QnaEntityRepository qnaEntityRepository;
	
	@Autowired
	MyCategoryRepository myCategoryRepository;
	
	@Autowired
	NoticeService service;
	
	//@Test
	void contextLoads() {
		IntStream.rangeClosed(1, 3).forEach(i->{
			Member entity=Member.builder()
					.email("test"+i+"@test.com")
					.name("회원"+i)
					.password(passwordEncoder.encode("1234"))
					.build();
			
			entity.addRole(MemberRole.USER);
			
			if(i==3) {
				entity.addRole(MemberRole.ADMIN);
			}
			
			memberRepository.save(entity);         // 마지막에 저장 필수! 파라미터는 entity로.
			
		});
	}
	
	//@Test
	void 관리자계정추가() {
		
		Member entity=Member.builder()
		.email("admin@test.com")
		.password(passwordEncoder.encode("1234"))
		.name("관리자")
		.ip("127.0.0.1")
		.build();
		
		entity.addRole(MemberRole.GUSET);
		entity.addRole(MemberRole.USER);
		entity.addRole(MemberRole.ADMIN);
		
		memberRepository.save(entity); 
	}
	
	//@Test
	void faq데이터삽입() {
		//Division.values(); enum요소들을 순서대로 배열
		for(Division div:Division.values()) {          // enum요소들을 순차적으로 돌며 1부터 20까지 넣어준다.
			IntStream.rangeClosed(1, 20).forEach(i->{
				QnaEntity entity=QnaEntity.builder()
						.division(div.name())
						.question(div.getDtitle()+" 질문"+i)
						.answer(div.getDtitle()+" 답변"+i)
						.build();
				qnaEntityRepository.save(entity);
			});
			
		}
		
	}
	
	//@Test
	void 공지사항데이터삽입() {
		IntStream.rangeClosed(1, 10).forEach(i->{
			service.save(NoticeWriteDto.builder()
					.subject("공지사항 제목 "+i)
					.content("공지사항 내용 "+i)
					.writer("작성자"+(i%3+1))
					.build());
		});
	}
	
	//@Test
	void 카테고리예제입력() {
		MyCategory entity=MyCategory.builder()
				.code(101211)
				.first(100000)
				.fname("BASEBALL")
				.second(1200)
				.sname("보호장비")
				.third(11)
				.tname("포수용")
				.build();
		myCategoryRepository.save(entity);
	}
	


}
