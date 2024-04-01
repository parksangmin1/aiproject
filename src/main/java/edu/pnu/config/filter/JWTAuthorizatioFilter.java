package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberReository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizatioFilter extends OncePerRequestFilter { //포워딩할때 여러번 필터하지말고 한번만 하게 
	
//	인가 설정을 위해 사용자의 Role 정보를 읽어 들이기 위한 객체 설정
	private final MemberReository memberRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		String srcToken = request.getHeader("Authorization");
		if(srcToken == null || !srcToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
		
			return ;
		}
		String jwtToken = srcToken.replace("Bearer ", "");

		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwt")).build().verify(jwtToken).getClaim("customerNum").asString();
	

		try {
			Optional<Member> opt = memberRepo.findById(username);
			
			if (!opt.isPresent()) {
				filterChain.doFilter(request, response);
				return;
			}
			Member findMember = opt.get();
			
			User user = new User(findMember.getCustomerNum(), findMember.getPassword(),
					AuthorityUtils.createAuthorityList(findMember.getRole().toString()));

			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(auth);

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			
		}

	}
}
// 원스필터를 상속 받게 되면 하나의 요청에 대해서 단 한번만 필터를 거치게 된다,
// 예를 들어 포워딩 되어 다른 페이지로 이동하게 되더라도 다시 이 필터를 거치지 않게 한다.