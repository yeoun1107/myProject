package yunProject.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import yunProject.domain.entity.NoticeEntity;

@RequiredArgsConstructor
@Data
public class NoticeDetailDto {

	private long no;
	private String subject;
	private int readCount;
	private String writer;
	private LocalDateTime createdDate;
	private String content;
	
	public NoticeDetailDto(NoticeEntity entity) {
		this.no=entity.getNo();
		this.subject=entity.getSubject();
		this.readCount=entity.getReadCount();
		this.writer=entity.getWriter();
		this.createdDate=entity.getCreatedDate();
		this.content=entity.getContent();
	}
}
