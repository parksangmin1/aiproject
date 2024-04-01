package edu.pnu.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ProductDTO {
	
	private String productCode;
	private Integer style;
	private String mainCategory;
	private String name;
	private String season;
	private String subCategory;
}
