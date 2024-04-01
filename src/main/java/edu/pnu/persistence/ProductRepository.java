package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Product;
import edu.pnu.domain.dto.ProductDetailListDTO;
import edu.pnu.domain.dto.ProductFilterDTO;
import edu.pnu.domain.dto.ProductHomeDTO;

public interface ProductRepository extends JpaRepository<Product, String> {

	@Query("SELECT new edu.pnu.domain.dto.ProductHomeDTO(p.productCode, p.name, p.price, p.season) " +
		       "FROM Product p WHERE p.category.id = 'A'")
	Page<ProductHomeDTO> getProductHome(Pageable pageable);

	@Query("SELECT new edu.pnu.domain.dto.ProductHomeDTO(p.productCode, p.name, p.price, p.season) " +
		       "FROM Product p WHERE p.category.id = 'A' ")
	Page<ProductHomeDTO> getProductHome1(Pageable pageable);
	
	
	@Query("SELECT new edu.pnu.domain.dto.ProductFilterDTO(s.product.productCode, SUM(s.quantity) as totalQuantity"
			+ ", s.product.name, s.product.kind.kindId, s.product.price, s.product.season) " +
		       "FROM Sales s " +
		       "LEFT JOIN s.product p " +
		       "WHERE s.product.kind.kindId  = :kindId AND p.category.categoryId = 'A' " + 
		       "GROUP BY s.product.productCode " +
		       "HAVING SUM(s.quantity) > 0 " +
		       "ORDER BY SUM(s.quantity) DESC")
	Page<ProductFilterDTO> getProductFiltered(Pageable pageable, String kindId);

//	@Query("SELECT p FROM Product p WHERE p.productCode "
//			+ "in(:upperSimi) AND p.texture = :texture AND p.print = :print AND p.detail = :detail")
//	List<Product> getRecommendInn(@Param("innSimi") List<String> innSimi, @Param("texture") String texture, @Param("print") String print, @Param("detail") String detail);
//	
	@Query("SELECT p FROM Product p WHERE p.productCode "
			+ "in(:innSimi) AND p.season= :season AND p.detail = :detail")
	List<Product> getRecommendInn(@Param("innSimi") List<String> innSimi, 
			@Param("season") String season,@Param("detail") String detail);

	@Query("SELECT p FROM Product p WHERE p.productCode  "
			+ "IN(:botSimi) AND p.kind.gkindId != 'BOT' AND p.texture = :texture AND p.print = :print AND p.detail = :detail")
	List<Product> getRecommendBot(@Param("botSimi") List<String> botSimi, @Param("texture") String texture, @Param("print") String print, @Param("detail") String detail);

	
	
	@Query("SELECT p FROM Product p LEFT JOIN Sales s ON p.productCode = s.product.productCode " +
		       "WHERE p.print = :print AND p.detail = :detail AND p.texture = :texture " +
		       "AND p.season = :season AND p.kind.kindId = :kindId")
		Page<Product> getProductDetailList(String print, String detail, String texture,
		                                   String season, String kindId, Pageable pageable);

	
	
//	@Query("SELECT p FROM Product p RIGHT JOIN  "
//			+  " (SELECT s.product.productCode as code,sum(quantity) as q FROM Product p "
//			+  " LEFT JOIN Sales s ON s.product.productCode= p.productCode" 
//		    +  " WHERE p.texture= :texture AND p.print = :print AND p.detail = :detail"
//		    +  " GROUP BY s.product.productCode " +
//		       " ORDER BY q DESC) t"
//		    +  " on t.code=p.productCode")
//	List<Product> getRecommendInnDB(@Param("innSimi") List<String> innSimi, 
//			@Param("texture") String texture, @Param("print") String print, @Param("detail") String detail);
	


	

}
