package yunProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// BCryptPasswordEncoder : 암호화 이후 복호화가 불가능한 암호화 방법을 제공하는 인코더
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests() // url별 권한 관리를 설정하는 옵션
				.antMatchers("/","/member/**","/log/**","/cscenter/**","/index/**","/goods/**").permitAll()    // 모든권한 부여
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")				// Role이 ADMIN인 관리자만 접근 가능
				.anyRequest().authenticated();                          // 나머지 URL은 권한이 부여되어야만 접근 가능
				
		http.csrf().disable();

		http.formLogin().loginPage("/log/login")
						.loginProcessingUrl("/member/login")
						.usernameParameter("email")				// default값 : ("username")
						.passwordParameter("password");			// default값 : ("password")
		
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/resources/**")
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/images/**");
	}
	
	
}
