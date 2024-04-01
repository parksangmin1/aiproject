package edu.pnu.domain.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ProductHomeDTO {

	private String productCode;
	private String name;
	private Integer price;
	private String season;
	
//	private Boolean image;
//	private String category_id;
	
	
	
}
