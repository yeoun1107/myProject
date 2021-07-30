package yunProject.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import yunProject.domain.dto.NoticeUpdateDto;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class NoticeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;//글번호
	@Column(nullable = false)
	private String subject;//제목
	@Column(nullable = false)
	private String content;//내용
	@Column
	private int readCount;//조회수
	@Column(nullable = false)
	private String writer;//작성자
	@CreatedDate
	@Column
	private LocalDateTime createdDate;//작성일
	
	@OneToMany(mappedBy = "board")
	List<Reply> replies;
	
	public NoticeEntity update(NoticeUpdateDto dto) {
		subject=dto.getSubject();
		content=dto.getContent();
		return this;
	}
	
}
