package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// 속성들중에 final이 붙어있는 속성 들을 파라미터로 하는 생성자를 만들어달라
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//	시큐리티에서 제공해주는 객체
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
//		제이슨 데이터를 읽어서 자바 클래스로 바꿔줌 
		ObjectMapper mapper = new ObjectMapper();
		Member member = null;
		try {
//			readvalue가 인풋으로 받은 리퀘스트 데이터를 제이슨으로 바꿔서 멤버 생성자에 넣음
			member = mapper.readValue(request.getInputStream(), Member.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		security에게 로그인 요청에 필요한 객체 생성
		Authentication authRoken = new UsernamePasswordAuthenticationToken(member.getCustomerNum(),member.getPassword());
		
//		 인증 진행 -> UserDetailsService를 상속받은 클래스의 loadUserByUsername 호출 
		Authentication auth = authenticationManager.authenticate(authRoken);
		System.out.println("auth : " + auth );
		return auth;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//		 인증 결과 생성된 authentication 객체에서 필요한 정보를 읽어서 토큰을 만들어서 헤더에 추가한다.
		User user = (User)authResult.getPrincipal();
		String token = JWT.create()
//				do filter 어쩌구 저쩌꾸를 안쓰고 .chaining으로만 
				.withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*10*30))
				.withClaim("customerNum", user.getUsername())
//				키는 내마음대로
				.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		response.addHeader("Authorization", "Bearer " + token);
		

//		return new ResponseEntity<Object>(ResponseData.res(StatusCode.OK, ResponseMessage.SIGN_UP_SUCCESS), HttpStatus.OK);
	}
	
	
	
}
