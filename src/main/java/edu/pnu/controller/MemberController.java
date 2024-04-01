package edu.pnu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.domain.dto.JoinMemberDTO;
import edu.pnu.domain.dto.UpdateMemberDTO;
import edu.pnu.service.MemberService;
import edu.pnu.web.response.ResponseData;
import edu.pnu.web.response.ResponseMessage;
import edu.pnu.web.response.StatusCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;
	
	  @PostMapping("/join")
	    public ResponseEntity<?> joinMember(@RequestBody JoinMemberDTO dto) {
	        ResponseEntity<?> response = null;
	        
	        return memberService.joinMember(dto);
	    }
	
	@PutMapping("/user/update")
	public ResponseEntity<?> updateMember(@RequestBody UpdateMemberDTO dto) {
		memberService.updateMember(dto);
		return new ResponseEntity<Object>(ResponseData.res(StatusCode.OK, ResponseMessage.UPDATE_INFO_SUCCESS), HttpStatus.OK);
	}
}
