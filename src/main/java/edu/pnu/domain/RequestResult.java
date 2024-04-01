package edu.pnu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RequestResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requestResultId;
	@Column(length = 45)
	private String segment;
	private Integer like;
	@Column(length = 45)
	private String print;
	@Column(length = 45)
	private String detail;
	private Integer style;
	@Column(length = 45)
	private String texture;
	@Column(length = 45)
	private String kindId;
	
	@ManyToOne
	@JoinColumn(name="request_id")
	private Request request;
}



