package yunProject.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import yunProject.domain.entity.Division;
import yunProject.domain.entity.QnaEntity;

@NoArgsConstructor
@Data
public class QnaResultDto {
	private long no;
	private String division;
	private String question;
	private String answer;
	
	public QnaResultDto(QnaEntity entity) { // DB의 결과를 여기에 세팅해주는 놈
		no=entity.getNo();
		String _division=entity.getDivision();
		division=Division.valueOf(_division).getDtitle();   //18 19라인은 enum의 한글표현식을 가져가기 위해서
		question=entity.getQuestion();
		answer=entity.getAnswer();
	}
	
}
