package edu.pnu.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ProductDetatilInputDTO {

	private String print;
	private String detail;
	private String texture;
	private String season;
	private String kindId;
	
}
