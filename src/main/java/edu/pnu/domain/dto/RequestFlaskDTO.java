package edu.pnu.domain.dto;

import java.io.File;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class RequestFlaskDTO {
	private String path;
	private byte[] image;
}
