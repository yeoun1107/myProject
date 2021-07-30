package yunProject.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CategoryData {
	private int categoryCode;
	private String categoryName;
	
	public CategoryData(Object[] obj) {
		this.categoryCode = (int)obj[0];
		this.categoryName = (String)obj[1];
	}

}
