package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.dto.SalesMonthDTO;
import edu.pnu.persistence.OrdersRepository;

@SpringBootTest
public class OrderTest {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Test
	public void Test() {
//		Integer pageNum = 1;
		Integer year = 2019;
		Integer month = 2;
//		Pageable pageable = PageRequest.of((pageNum- 1), 40);
		List<SalesMonthDTO> salePage = ordersRepository.getSalesMonth(year, month);
		System.out.println(salePage);
	}
}
