//package edu.pnu;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import edu.pnu.domain.Member;
//import edu.pnu.domain.Role;
//import edu.pnu.persistence.MemberReository;
//
//@SpringBootTest
//public class MemberInitialize {
//	@Autowired
//	MemberReository memRepo;
//	
//	PasswordEncoder encoder = new BCryptPasswordEncoder();
//	
//	@Test
//	public void dowork() {
//		memRepo.save(Member.builder()
//				.username("member")
//				.password(encoder.encode("abcd"))
//				.agree(true)
//				.role(Role.ROLE_MEMBER)
//				.build());
//		
//		memRepo.save(Member.builder()
//				.username("manager")
//				.password(encoder.encode("abcd"))
//				.agree(true)
//				.role(Role.ROLE_MEMBER)
//				.build());
//		
//		memRepo.save(Member.builder()
//				.username("admin")
//				.password(encoder.encode("abcd"))
//				.agree(true)
//				.role(Role.ROLE_MEMBER)
//				.build());
//	}
//}
