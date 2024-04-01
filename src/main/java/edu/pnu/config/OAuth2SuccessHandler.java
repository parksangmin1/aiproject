package edu.pnu.config;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberReository;
import edu.pnu.util.CustomMyUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final MemberReository memRepo;
	private final PasswordEncoder encoder;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		log.info("OAuth2SuccessHandler:onAuthenticationSuccess");
		OAuth2User uesr  = (OAuth2User)authentication.getPrincipal();
		System.out.println(uesr.toString());
		String username = CustomMyUtil.getUsernameFromOAuth2User(uesr);
		if (username ==null) {
			log.error("onAuthenticationSuccess:Cannot generate username from oauth2user!");
			throw new ServletException("Cannot generate username from oauth2user!");
		}
		log.info("onAuthenticationSuccess:" + username);
		memRepo.save(Member.builder().
				customerNum(username)
				.password(encoder.encode("1a2s3d4f"))
				.role(Role.ROLE_MEMBER)
				.build());
	}
}