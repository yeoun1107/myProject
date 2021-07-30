package yunProject.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yunProject.domain.dto.QnaUpdateDto;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QnaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String division;
	
	@Column(nullable = false)
	private String question;
	
	@Column(nullable = false)
	private String answer;

	public QnaEntity update(QnaUpdateDto dto) {
		question=dto.getQuestion();
		answer=dto.getAnswer();
		return this;
	}
}
