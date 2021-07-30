package yunProject.domain.dto;

import lombok.Data;
import yunProject.domain.entity.MyCategory;
import yunProject.domain.entity.ProductEntity;

@Data
public class ProductWriteDto {

	private int first;
	private int second;
	private int third;
	
	private String title;
	private long price;
	private String saleoption;
	private long saledata;
	private long quantity;
	private boolean recommend;
	
	private String fileName;
	private String fileUrl;
	private long fileSize;
	
	public ProductEntity toEntity() {
		
		// 할인가 : 할인여부에 따라 다르게 부여
		long saleprice=0; // 할인되는 가격
		long salerate=0;  // 할인율
		if(saleoption.equals("rate")) {
			//10원이상만 표현 : 1원자리에서 무조건 올림
			saleprice=(int)(price*((double)saledata/100));
			salerate=saledata;
		}else if(saleoption.equals("saleprice")) {
			saleprice=saledata;
			salerate=(int)((double)saledata/price*100);
		}
		
		int code=first+second+third;
		
		return ProductEntity.builder()
				.title(title)
				.price(price)
				.saleprice(saleprice)
				.salerate(salerate)
				.quantity(quantity)
				.recommend(recommend)
				.fileName(fileName)
				.fileUrl(fileUrl)
				.fileSize(fileSize)
				.category(MyCategory.builder().code(code).build())
				.build();
	}
}
