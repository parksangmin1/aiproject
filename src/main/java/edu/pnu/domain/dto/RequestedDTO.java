package edu.pnu.domain.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class RequestedDTO {

	private Long requestId;
	private String customerNum;
	private String originalFilename;
	private Date requestDate;
//	private String path;
}
