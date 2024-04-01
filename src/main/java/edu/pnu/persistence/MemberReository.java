package edu.pnu.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Member;

public interface MemberReository extends JpaRepository<Member, String> {

	void save(Optional<Member> member);
	
}
