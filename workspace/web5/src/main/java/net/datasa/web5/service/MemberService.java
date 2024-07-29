package net.datasa.web5.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	public void join(MemberDTO dto) {
		MemberEntity entity = MemberEntity.builder()
				.memberId(dto.getMemberId())
				.memberPassword(passwordEncoder.encode(dto.getMemberPassword()))
				.memberName(dto.getMemberName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address(dto.getAddress())
				.enabled(true)
				.rolename("ROLE_USER")
				.build();
		
		repo.save(entity);
	}
	
	/**
	 * 전달받은 아이디를 DB에서 조회하여 사용중인지 여부 리턴
	 * @param searchId조회할 아이디
	 * @return 있으면 사용불가 false, 없으면 사용가능 true
	 */
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
	
	public MemberDTO getMember(String username) {
		
		MemberEntity entity = repo.findById(username)
				.orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
		
		MemberDTO dto = MemberDTO.builder()
				.memberId(entity.getMemberId())
				.memberName(entity.getMemberName())
				.email(entity.getEmail())
				.phone(entity.getPhone())
				.address(entity.getAddress())
				.build();
		
		return dto;
	}
	
	public void update(MemberDTO dto) {
		MemberEntity entity = repo.findById(dto.getMemberId())
				.orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
		
		entity.setMemberName(dto.getMemberName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		
		//비밀번호는 전달된 값이 있을때에만 암호화해서 수정
		if(dto.getMemberPassword() != null && !dto.getMemberPassword().isEmpty()) {
			entity.setMemberPassword(passwordEncoder.encode(dto.getMemberPassword()));
		}
		//리턴될때 DB에 저장됨.
	}
}