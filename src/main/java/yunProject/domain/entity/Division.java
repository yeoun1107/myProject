package yunProject.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Division {
	shop("매장 이용"),         //0
	ypoint("Y.POINT"),		 //1
	member("회원"),			 //2
	membership("멤버십"),		 //3
	online("온라인"),			 //4
	discount("할인혜택"),		 //5
	store("스토어"),			 //6
	custom("커스텀제작");		 //7
	
	final String dtitle;
}
