package yunProject.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@EntityListeners(AuditingEntityListener.class)          //listener 적용이 되어야 자동으로 저장될 수 있다 -> application.java에 @EnableJpaAuditing 추가
@MappedSuperclass										//이거는 entity가 아니고 entity에 매핑시켜 들어가야하니까  
public class BaseDate{
	@Column
	@CreatedDate
	LocalDateTime createdDate;
	
	@Column
	@LastModifiedDate
	LocalDateTime updatedDate;
	
}

