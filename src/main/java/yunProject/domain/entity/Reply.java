package yunProject.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String reply;
	
	@Column(nullable = false)
	private String writer;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@ManyToOne
	@JoinColumn(name="boardNo")
	NoticeEntity board;
}
