package edu.pnu.domain.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SalesedDTO {

	private Integer quantity;
	private Date saleDate;
	private String customerNum;
	private String productCode;
//	private String name;
}
