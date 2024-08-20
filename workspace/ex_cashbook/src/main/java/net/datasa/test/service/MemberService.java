package net.datasa.test.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.test.domain.dto.MemberDTO;
import net.datasa.test.domain.entity.MemberEntity;
import net.datasa.test.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원정보 서비스
 */
@RequiredArgsConstructor
@Service
@Transactional      //Mysql에서 자동으로 커밋 해주는데 오류가 뜨면 rollback도 해줌
                    //수정(Update)할때 수정값에 문제가 없으면 수정후 자동
                    //Commit을 하므로 memberRepository.save() 생략 가능.

public class MemberService {

    //WebSecurityConfig에서 생성한 암호화 인코더
    private final BCryptPasswordEncoder passwordEncoder;

    //회원 정보 관련 리포지토리
    private final MemberRepository memberRepository;

    /**
     * 회원 가입 처리
     * @param dto 회원 정보
     */
    public void join(MemberDTO dto) {
        //전달된 회원정보를 테이블에 저장한다.
        MemberEntity entity = MemberEntity.builder()
                .memberId(dto.getMemberId())
                .memberPw(passwordEncoder.encode(dto.getMemberPw()))        //비밀번호는 암호화
                .build();

        memberRepository.save(entity);
    }
}