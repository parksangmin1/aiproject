package edu.pnu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.pnu.domain.dto.RequestedDTO;
import edu.pnu.persistence.RecommendRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendService {

	private final RecommendRepository recommendRepository;

	public Page<RequestedDTO> getRequested(String customerNum,Integer pageNum) {
		Pageable pageable = PageRequest.of((pageNum-1), 10);
		Page<RequestedDTO> requestedPage = recommendRepository.getRequested(customerNum,pageable);
		
		return requestedPage;
	}

	
	
	
}
