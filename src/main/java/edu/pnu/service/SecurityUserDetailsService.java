package edu.pnu.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberReository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityUserDetailsService implements UserDetailsService{


	private final MemberReository memRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memRepo.findById(username)
				.orElseThrow(()-> new UsernameNotFoundException("Not Found!"));
		return new User(member.getCustomerNum(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}
	
//	@Override
//	public MemberDetail loadUserByUsername(String username) throws UsernameNotFoundException {
//		Member member = memRepo.findById(username)
//				.orElseThrow(()-> new UsernameNotFoundException("Not Found!"));
//		return new MemberDetail(member);
//	}
	
}
