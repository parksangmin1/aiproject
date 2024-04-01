package edu.pnu.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProductFilterDTO {

	 private String productCode;
	 private Long totalQuantity;
	 private String name;
	 private String kindId;
	 private Integer price;
	 private String season;
	 
//	 public ProductFilterDTO(String productCode, Long totalQuantity) {
//	        this.productCode = productCode;
//	        this.totalQuantity = totalQuantity;
//	    }
	 
	 
}
