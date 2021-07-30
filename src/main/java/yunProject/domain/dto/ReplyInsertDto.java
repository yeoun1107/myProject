package yunProject.domain.dto;

import lombok.Data;
import yunProject.domain.entity.Reply;

@Data
public class ReplyInsertDto {

	private String reply;
	private String writer;
	
	public Reply toEntity() {
		return Reply.builder()
				.reply(reply)
				.writer(writer)
				.build();
	}
}
