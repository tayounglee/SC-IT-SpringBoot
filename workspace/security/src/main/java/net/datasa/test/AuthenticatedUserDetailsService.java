package net.datasa.test;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticatedUserDetailsService implements UserDetailsService {

	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 전달받은 아이디로 회원정보 DB에서 조회
		// 없으면 예외
		// 있으면 그 정보로 UserDetails 객체 생성하여 리턴
		AuthenticatedUser user = AuthenticatedUser.builder()
				.id("abc")
				.password(passwordEncoder.encode("123"))
				.name("홍길동")
				.roleName("ROLE_USER")
				.enabled(true)
				.build();
		log.debug("인증정보: {}", user);
		return user;
	}
}