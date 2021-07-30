package yunProject.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yunProject.domain.dto.ProductUpdateDto;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private long price;
	
	@Column(nullable = false)
	private long saleprice;
	
	@Column(nullable = false)
	private long salerate;
	
	@Column(nullable = false)
	private long quantity;
	
	@Column(nullable = false)
	private boolean recommend;
	
	@Column(nullable = false)
	private String fileName;
	
	@Column(nullable = false)
	private String fileUrl;
	
	@Column(nullable = false)
	private long fileSize;
	
	@ManyToOne
	MyCategory category;
	
	public ProductEntity update(ProductUpdateDto dto) {
		recommend=dto.isRecommend();
		return this;
	}
	
}
