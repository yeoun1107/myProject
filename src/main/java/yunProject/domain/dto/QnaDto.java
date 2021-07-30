package yunProject.domain.dto;

import lombok.Data;
import yunProject.domain.entity.QnaEntity;

@Data
public class QnaDto {
	private String division;
	private String question;
	private String answer;
	
	public QnaEntity toEntity() {
		return QnaEntity.builder()
				.division(division)
				.question(question)
				.answer(answer)
				.build();
	}
}
