package net.datasa.web5.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.web5.domain.dto.MemberDTO;
import net.datasa.web5.domain.entity.MemberEntity;
import net.datasa.web5.repository.MemberRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
	
	private final MemberRepository repo;
	
	public void join(MemberDTO dto) {
		MemberEntity entity = MemberEntity.builder()
				.memberId(dto.getMemberId())
				.memberPassword(dto.getMemberPassword())
				.memberName(dto.getMemberName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address(dto.getAddress())
				.enabled(true)
				.rolename("ROLE_USER")
				.build();
		
		repo.save(entity);
	}
	
	public boolean findId(String searchId) {
		/*
		if(repo.findById(searchId).isPresent()) {
			return false;
		}
		else {
			return true;
		}
		*/
		
		return !repo.existsById(searchId);
	}
}