package yunProject.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import yunProject.domain.entity.VisualEntity;

@NoArgsConstructor  // 생성자에 기본 생성자가 없으니까 넣어줘야 기본 생성자에도 접근 가능 (그래야 getter, setter 접근해서 매핑해주는 애들 작동 가능)
@Data
public class VisualDto {

	private long no;
	private String title;
	private String link;
	
	private String fileName;
	private String fileUrl;
	private long fileSize;
	
	public VisualEntity toEntity() {
		return VisualEntity.builder()
				.title(title)
				.link(link)
				.fileName(fileName)
				.fileSize(fileSize)
				.fileUrl(fileUrl)
				.build();
	}

	public VisualDto(VisualEntity entity) {
		this.no = entity.getNo();
		this.title = entity.getTitle();
		this.link = entity.getLink();
		this.fileName = entity.getFileName();
		this.fileUrl = entity.getFileUrl();
		this.fileSize = entity.getFileSize();
	}
	
	
}
