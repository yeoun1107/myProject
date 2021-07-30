package yunProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import yunProject.domain.entity.NoticeEntity;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class NoticeWriteDto {

	private String subject;//제목
	private String content;//내용
	private String writer;//작성자
	
	public NoticeEntity toEntity() {
		return NoticeEntity.builder()
				.subject(subject)
				.content(content)
				.writer(writer)
				.build();
	}
}
