package yunProject.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import yunProject.domain.entity.Reply;

@NoArgsConstructor
@Data
public class ReplyDto {

	private long no;
	private String reply;
	private String writer;
	private LocalDateTime createdDate;
	
	public ReplyDto(Reply entity) {
		this.no = entity.getNo();
		this.reply = entity.getReply();
		this.writer = entity.getWriter();
		this.createdDate = entity.getCreatedDate();
	}
	
	
}
