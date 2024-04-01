package edu.pnu.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.domain.dto.JoinMemberDTO;
import edu.pnu.domain.dto.UpdateMemberDTO;
import edu.pnu.persistence.MemberReository;
import edu.pnu.web.response.ResponseData;
import edu.pnu.web.response.ResponseMessage;
import edu.pnu.web.response.StatusCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final MemberReository memRepo;
	
	public Optional<Member> getMember(String id) {
		return memRepo.findById(id);
	}

	public ResponseEntity<?> joinMember(JoinMemberDTO dto) {
		ResponseEntity<?> response = null;
		try {
	            if (!memRepo.existsById(dto.getCustomerNum())) {
	                Member member = Member.builder()
	                        .customerNum(dto.getCustomerNum())
	                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
	                        .agree(dto.getAgree())
	                        .role(Role.ROLE_MEMBER)
	                        .build();
	                memRepo.save(member);
	                response = ResponseEntity.ok(ResponseMessage.SIGN_UP_SUCCESS);
	            } else {
	                response = ResponseEntity.badRequest().body(ResponseMessage.ALREADY_USER);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response = ResponseEntity.internalServerError().body(ResponseMessage.SIGN_UP_FAIL);
	        }
		 return response;
	}

	public ResponseEntity<?> updateMember(UpdateMemberDTO dto) {
		
		if(memRepo.existsById(dto.getCustomerNum())) {
			try {
				Member member = Member.builder()
						.customerNum(dto.getCustomerNum())
						.password(dto.getPassword())
						.agree(true)
						.role(Role.ROLE_MEMBER)
						.build();
				System.out.println(member);
				memRepo.save(member);
			} catch (Exception e) {
				e.printStackTrace();
			return new ResponseEntity<Object>(ResponseData.res(StatusCode.OK, ResponseMessage.UPDATE_INFO_FAIL), HttpStatus.OK);
			}
		}
		return new ResponseEntity<Object>(ResponseData.res(StatusCode.OK, ResponseMessage.UPDATE_INFO_FAIL), HttpStatus.OK);
		
		
	}
	
	
	
}
