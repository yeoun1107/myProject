package yunProject.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import yunProject.domain.entity.ProductEntity;

@RequiredArgsConstructor
@Data
public class ProductDto {

	private long no;
	private String title;
	private long price;
	private long saleprice;
	private double salerate;
	private long quantity;
	private boolean recommend;
	
	private String fileName;
	private String fileUrl;
	private long fileSize;
	
	
	// 이 생성자의 목적 : entity 컬럼의 데이터를 DTO 칼럼으로 저장하기 위한 생성자
	public ProductDto(ProductEntity entity) {
		this.no = entity.getNo();
		this.title = entity.getTitle();
		this.price = entity.getPrice();
		this.saleprice = entity.getSaleprice();
		this.salerate = entity.getSalerate();
		this.quantity = entity.getQuantity();
		this.recommend = entity.isRecommend();
		this.fileName = entity.getFileName();
		this.fileUrl = entity.getFileUrl();
		this.fileSize = entity.getFileSize();
	}
	
	
}
