package edu.pnu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.pnu.domain.dto.SalesMonthDTO;
import edu.pnu.domain.dto.SalesedDTO;
import edu.pnu.persistence.OrdersRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SalesService {

	private final OrdersRepository ordersRepository;

	public Page<SalesedDTO> getOrderd(String customerNum,Integer pageNum) {
		
		Pageable pageable = PageRequest.of((pageNum-1), 10);
		Page<SalesedDTO> ordersPage = ordersRepository.getOrdered(customerNum,pageable);
		return ordersPage;
		
	}

	public List<SalesMonthDTO> getSalesMonth(Integer year, Integer month) {
	
		List<SalesMonthDTO> salesPage = ordersRepository.getSalesMonth(year,month);
		return salesPage;
	}
	
	
}
