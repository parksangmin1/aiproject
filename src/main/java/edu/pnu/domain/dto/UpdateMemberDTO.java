package edu.pnu.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UpdateMemberDTO {
	
	private String customerNum;
	private String password;
}
