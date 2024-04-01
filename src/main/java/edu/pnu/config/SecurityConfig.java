package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizatioFilter;
import edu.pnu.persistence.MemberReository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
//	private final OAuth2SuccessHandler customHandler;	
	
	private final MemberReository memRepo;
	
	private final AuthenticationConfiguration authenticationConfiguration;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable());	
		http.authorizeHttpRequests(auth->auth
				.anyRequest().permitAll());
		http.formLogin(frmLogin->frmLogin.disable());  // form을 이용한 로그인을 사용하지 않겟다
		http.httpBasic(basic->basic.disable());  // 	Http Basic인증 방식을 사용하지 않곘다
		
		
//		세션을 유지하지 않고 삭제 -> url 호출 뒤 응답할 때 까지는 유지되지만 응답 후 삭제된다는 의미.
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
//		인증필터 등록
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
//		인가필터 등록		
		http.addFilterBefore(new JWTAuthorizatioFilter(memRepo), AuthorizationFilter.class);
		
		// oauth2 세션을 사용하지 않을떄 
		http.oauth2Login(oauth2->oauth2
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true));
//		http.oauth2Login(oauth2 -> oauth2.successHandler(customHandler));
		return http.build();
	}


	
}
