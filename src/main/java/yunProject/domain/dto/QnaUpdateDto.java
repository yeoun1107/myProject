package yunProject.domain.dto;

import lombok.Data;
import yunProject.domain.entity.QnaEntity;

@Data
public class QnaUpdateDto {
	
	private String question;
	private String answer;
	
	}
