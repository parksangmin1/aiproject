package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberReository;
import edu.pnu.persistence.ProductRepository;
import edu.pnu.service.SecurityUserDetailsService;


@SpringBootTest
public class ProductRepoTest {

	@Autowired
	private  ProductRepository productRepository;
	
	@Autowired
	private MemberReository memberReository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;
	
//	@Test
//	public void Test() {
////		40개로 나눌때 9번째 페이지를 요청하겠다. 
//		System.out.println("1");
//		Pageable pageable = PageRequest.of(0, 40);
//		System.out.println("11");
//		Page<ProductHomeDTO> page = productRepository.getProductHome(pageable);
//		System.out.println(page.getContent());
//		System.out.println(page.getSize());
//	}
	
//	@Test
//	public void Test1() {
//		String kindId = "TS";
//		Integer pageNum = 1;
//		Pageable pageable = PageRequest.of((pageNum- 1), 40);
//		Page<ProductFilterDTO> page = productRepository.getProductFiltered(pageable, kindId);
//		System.out.println(page.getContent());
//		System.out.println(page.getSize());
//	}
//	@Test
//	public void Test2() {
//		Member member = memberReository.findById("11").orElse(null);
//		String pass = bCryptPasswordEncoder.encode("abcd");
//		member.setPassword(pass);
//		memberReository.save(member);
//		System.out.println(member);
//	}
	@Test
	public void Test3() {
		Integer pageNum = 1;
		Pageable pageable = PageRequest.of((pageNum-1), 15);
		String print = "스트라이프";
		String detail = "단추";
		String texture = "우븐";
		String season = "봄";
		String kindId = "WS";
		 productRepository.getProductDetailList(print,detail,texture,season,kindId,pageable);
 		
		
		
	}
}
