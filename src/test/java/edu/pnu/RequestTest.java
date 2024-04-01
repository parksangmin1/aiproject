package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.dto.RequestedDTO;
import edu.pnu.persistence.RecommendRepository;

@SpringBootTest
public class RequestTest {

//	@Autowired
//	private RecommendRepository recommendRepository;
	
	
//	@Test
//	public void getRequested() {
//		String customerNum = "1";
//		Integer pageNum = 1;
//		Integer year = 2024;
//		Integer month = 3;
//		Pageable pageable = PageRequest.of((pageNum-1), 10);
//		Page<RequestedDTO> requestPage = recommendRepository.getRequested(customerNum,pageable, year, month);
//		System.out.println(requestPage);
//		
//	}
	
//	@Test
//	public void Test(String "") {
//		
//	}
}
