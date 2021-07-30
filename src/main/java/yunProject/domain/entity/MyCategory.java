package yunProject.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class MyCategory {

	@Id
	private int code;
	
	private int first;
	private String fname;
	private int second;
	private String sname;
	private int third;
	private String tname;
}
