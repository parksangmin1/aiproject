package edu.pnu.controller;

import java.util.List;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.dto.SalesMonthDTO;
import edu.pnu.domain.dto.SalesedDTO;
import edu.pnu.service.SalesService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SalesController {

	private final SalesService salesService;
	
	@GetMapping("sales/{pageNum}")
	public Page<SalesedDTO> getOrderd(@AuthenticationPrincipal User user
			,@PathVariable Integer pageNum) {
		String customerNum = user.getUsername();
		return salesService.getOrderd(customerNum, pageNum);
	}
	
	@GetMapping("sales/{year}/{month}")
	public List<SalesMonthDTO> getSalesMonth(@AuthenticationPrincipal User user
			,@PathVariable Integer year, @PathVariable Integer month){
		String customerNum = user.getUsername();
		
		return salesService.getSalesMonth(year,month);
		
	}
}
