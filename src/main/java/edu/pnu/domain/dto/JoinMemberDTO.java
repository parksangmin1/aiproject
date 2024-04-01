package edu.pnu.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class JoinMemberDTO {

	private String customerNum;
	private String password;
	private Boolean agree;
}
