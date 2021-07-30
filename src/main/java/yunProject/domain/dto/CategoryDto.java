package yunProject.domain.dto;

import lombok.Data;
import yunProject.domain.entity.MyCategory;

@Data
public class CategoryDto {

	private String fname;
	private String sname;
	private String tname;
	
	public MyCategory toEntity() {
		return MyCategory.builder()
				.fname(fname)
				.sname(sname)
				.tname(tname)
				.build();
	}
}
