package edu.pnu.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ProductSearchDTO {

	private String productCode;
	private String name;
	private String kindId;
	private Integer price;
	
}
