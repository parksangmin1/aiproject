package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.pnu.domain.Request;
import edu.pnu.domain.dto.RequestedDTO;

public interface RecommendRepository extends JpaRepository<Request, Long> {

	String save(String fullPath);

	@Query("SELECT new edu.pnu.domain.dto.RequestedDTO(r.requestId,r.member.customerNum, r.originalFilename, r.requestDate) FROM Request r "
			+ "WHERE r.member.customerNum = :customerNum ORDER BY r.requestDate DESC")
	Page<RequestedDTO> getRequested(String customerNum, Pageable pageable);
	
	@Query("SELECT r from Request r WHERE r.requestId = :requestId AND"
			+ " r.member.customerNum = :username ")
	List<Request> rerenderImage(String username, Long requestId);

	
//	@Query("SELECT new edu.pnu.domain.dto.RequestedDTO(r.customerNum) "
//			+ "FROM Request r WHERE r.customerNum = :customerNum AND year(r.request_date) = :year AND month(r.request_date) = :month")
//	List<RequestedDTO> getRecommeded(String customerNum, Long year, Long month);

//	@Query("SELECT new edu.pnu.domain.dto.RequestedDTO(r.customerNum) "
//			+ "FROM Request r ")
//	List<RequestedDTO> getRecommeded();
}
