package edu.pnu.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.dto.RequestedDTO;
import edu.pnu.domain.dto.SalesedDTO;
import edu.pnu.service.RecommendService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RecommendController {

	private final RecommendService recommendService;
	
	@GetMapping("/request/{pageNum}")
	public Page<RequestedDTO> getRequested(@AuthenticationPrincipal User user,@PathVariable Integer pageNum ) {
		String customerNum = user.getUsername();	
		System.out.println(customerNum);
		return recommendService.getRequested(customerNum, pageNum);
	}
}
