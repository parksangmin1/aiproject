package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Sales;
import edu.pnu.domain.dto.SalesMonthDTO;
import edu.pnu.domain.dto.SalesedDTO;

public interface OrdersRepository extends JpaRepository<Sales, Long> {

	
	@Query("SELECT new edu.pnu.domain.dto.SalesedDTO"
			+ "(s.quantity, s.saleDate, s.member.customerNum, s.product.productCode)"
			+ " FROM Sales s WHERE s.member.customerNum = :customerNum ORDER BY s.saleDate DESC " )
		
	Page<SalesedDTO> getOrdered(String customerNum,Pageable pageable);

	
	@Query("SELECT new edu.pnu.domain.dto.SalesMonthDTO(s.quantity) " +
	        "FROM Sales s LEFT JOIN  s.product p ON s.product.productCode = p.productCode "+
	        "WHERE YEAR(s.saleDate) = :year " +
	        "AND MONTH(s.saleDate) = :month")
	List<SalesMonthDTO> getSalesMonth(
			 Integer year, Integer month );

}
