package yunProject.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import yunProject.domain.entity.NoticeEntity;

@RequiredArgsConstructor
@Data
public class NoticeListDto {

	private long no;//글번호
	private String subject;//제목
	private String writer;//작성자
	private int readCount;//조회수
	private LocalDateTime createdDate;//작성일
	
	public NoticeListDto(NoticeEntity entity) {
		this.no = entity.getNo();
		this.subject = entity.getSubject();
		this.writer = entity.getWriter();
		this.readCount = entity.getReadCount();
		this.createdDate = entity.getCreatedDate();
	}
}
